package com.databankimx.training.runner;

import java.awt.Desktop;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.swing.SwingUtilities;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * The commands the page can call. Each returns a JSON value, or throws, and the error text is sent to the page.
 * Paths from the page must stay inside the repository, so a request can't read files outside it.
 *
 * The main window and the pop-out lesson window both call these commands. Events go to the main window, and lesson
 * and theme updates also go to the pop-out when it is open.
 */
final class Commands {

    private volatile Consumer<JsonObject> mainSink = message -> { };
    private volatile Consumer<JsonObject> popoutSink = null;
    private volatile LessonWindow lessonWindow;

    private final Runner runner = new Runner(message -> mainSink.accept(message));

    private volatile String lessonTitle = "";
    private volatile String lessonText = "";
    private volatile boolean light = false;

    void setMainSink(Consumer<JsonObject> sink) {
        this.mainSink = sink;
    }

    void setPopoutSink(Consumer<JsonObject> sink) {
        this.popoutSink = sink;
    }

    void setLessonWindow(LessonWindow window) {
        this.lessonWindow = window;
    }

    /** Sends an event to the main window. Used by the pop-out lesson window, which opens and closes the lesson. */
    void notifyMain(String event) {
        mainSink.accept(event(event, new JsonObject()));
    }

    JsonElement invoke(String command, JsonObject args) throws IOException {
        return switch (command) {
            case "list_parts" -> Discovery.listParts();
            case "read_lesson" -> new JsonPrimitive(readOptional(inside(str(args, "path")).resolve("lesson.md")));
            case "list_source_files" -> sourceFiles(inside(str(args, "path")));
            case "read_source" -> new JsonPrimitive(read(inside(str(args, "path")).resolve("src"), str(args, "file")));
            case "read_step_file" -> new JsonPrimitive(read(inside(str(args, "path")), str(args, "file")));
            case "read_commands" -> commands(inside(str(args, "path")));
            case "run_step" -> runner.run(inside(str(args, "path")), args);
            case "stop_run" -> {
                runner.stop();
                yield new JsonObject();
            }
            case "send_input" -> {
                runner.sendInput(str(args, "text"));
                yield new JsonObject();
            }
            case "open_file" -> {
                Desktop.getDesktop().open(inside(str(args, "path")).toFile());
                yield new JsonObject();
            }
            case "set_lesson" -> {
                lessonTitle = str(args, "title");
                lessonText = str(args, "text");
                Consumer<JsonObject> popout = popoutSink;
                if (popout != null) {
                    JsonObject payload = new JsonObject();
                    payload.addProperty("title", lessonTitle);
                    payload.addProperty("text", lessonText);
                    popout.accept(event("lesson-updated", payload));
                }
                yield new JsonObject();
            }
            case "get_lesson" -> {
                JsonObject lesson = new JsonObject();
                lesson.addProperty("title", lessonTitle);
                lesson.addProperty("text", lessonText);
                yield lesson;
            }
            case "set_theme" -> {
                light = args.get("light").getAsBoolean();
                Consumer<JsonObject> popout = popoutSink;
                if (popout != null) {
                    popout.accept(event("theme-changed", new JsonPrimitive(light)));
                }
                yield new JsonObject();
            }
            case "get_theme" -> new JsonPrimitive(light);
            case "open_lesson_window" -> {
                SwingUtilities.invokeLater(() -> requireLessonWindow().open());
                yield new JsonObject();
            }
            case "dock_lesson" -> {
                SwingUtilities.invokeLater(() -> requireLessonWindow().dock());
                yield new JsonObject();
            }
            case "open_visualizer" -> {
                // The page sits beside the trace in the step's build folder, which the last run wrote.
                Path page = inside(str(args, "path")).resolve("build").resolve("visualize.html");
                if (!Files.isRegularFile(page)) {
                    throw new IllegalStateException("Run this step once, so its trace is written, then press Visualize.");
                }
                Desktop.getDesktop().open(page.toFile());
                yield new JsonObject();
            }
            default -> throw new IllegalArgumentException("Not built yet: " + command);
        };
    }

    private LessonWindow requireLessonWindow() {
        LessonWindow window = lessonWindow;
        if (window == null) {
            throw new IllegalStateException("The lesson window is not ready yet.");
        }
        return window;
    }

    private static JsonObject event(String name, JsonElement payload) {
        JsonObject message = new JsonObject();
        message.addProperty("kind", "event");
        message.addProperty("event", name);
        message.add("payload", payload);
        return message;
    }

    private static String str(JsonObject args, String name) {
        if (!args.has(name) || args.get(name).isJsonNull()) {
            throw new IllegalArgumentException("Missing argument: " + name);
        }
        return args.get(name).getAsString();
    }

    /** The given path as an absolute path, if it is inside the repository. Otherwise the request is refused. */
    static Path inside(String path) {
        Path target = Paths.get(path).toAbsolutePath().normalize();
        if (!target.startsWith(Discovery.ROOT)) {
            throw new IllegalArgumentException("Outside the course folder: " + path);
        }
        return target;
    }

    /** Reads a file under base. The file name must stay inside base. */
    private static String read(Path base, String file) throws IOException {
        Path target = base.resolve(file).normalize();
        if (!target.startsWith(base.normalize())) {
            throw new IllegalArgumentException("Outside the step folder: " + file);
        }
        return Files.readString(target, StandardCharsets.UTF_8);
    }

    private static String readOptional(Path file) throws IOException {
        return Files.isRegularFile(file) ? Files.readString(file, StandardCharsets.UTF_8) : "";
    }

    /** The Java and XML files in a step's src folder. Main.java comes first, since it is the file the editor opens. */
    private static JsonArray sourceFiles(Path step) throws IOException {
        Path src = step.resolve("src");
        List<String> names = new ArrayList<>();
        if (Files.isDirectory(src)) {
            try (Stream<Path> files = Files.walk(src)) {
                files.filter(Files::isRegularFile)
                        .filter(p -> p.getFileName().toString().endsWith(".java")
                                || p.getFileName().toString().endsWith(".xml"))
                        .map(p -> src.relativize(p).toString())
                        .sorted(Comparator.comparing((String n) -> !n.equals("Main.java")).thenComparing(n -> n))
                        .forEach(names::add);
            }
        }
        JsonArray out = new JsonArray();
        names.forEach(out::add);
        return out;
    }

    /** The quick picks in commands.txt, one per line, without blank lines. */
    private static JsonArray commands(Path step) throws IOException {
        JsonArray out = new JsonArray();
        Path file = step.resolve("commands.txt");
        if (!Files.isRegularFile(file)) {
            return out;
        }
        for (String line : Files.readAllLines(file, StandardCharsets.UTF_8)) {
            if (!line.isBlank()) {
                out.add(line.trim());
            }
        }
        return out;
    }
}
