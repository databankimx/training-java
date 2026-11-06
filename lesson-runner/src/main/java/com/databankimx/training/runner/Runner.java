package com.databankimx.training.runner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.swing.SwingUtilities;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

/**
 * Compiles and runs a step's program. The step's source is copied into its build folder, so the repository's files
 * are never changed, and an edited Main.java replaces only the copy. Output is sent to the page as events.
 */
final class Runner {

    private final Consumer<JsonObject> events;
    private volatile Process process;
    private volatile OutputStream input;

    Runner(Consumer<JsonObject> events) {
        this.events = events;
    }

    /** Starts a compile and run in the background. The page receives the output as events. */
    JsonElement run(Path step, JsonObject args) throws IOException {
        if (process != null && process.isAlive()) {
            throw new IllegalStateException("A program is already running.");
        }
        boolean test = args.has("test") && args.get("test").getAsBoolean();
        String source = args.has("source") && !args.get("source").isJsonNull() ? args.get("source").getAsString() : null;
        List<String> programArgs = new ArrayList<>();
        if (args.has("args") && args.get("args").isJsonArray()) {
            args.getAsJsonArray("args").forEach(a -> programArgs.add(a.getAsString()));
        }

        Thread worker = new Thread(() -> {
            try {
                compileAndRun(step, source, programArgs, test);
            } catch (Exception error) {
                output(error.getMessage() == null ? error.toString() : error.getMessage() + "\n", "stderr");
                finished(1);
            }
        }, "run-step");
        worker.setDaemon(true);
        worker.start();
        return JsonNull.INSTANCE;
    }

    private void compileAndRun(Path step, String source, List<String> programArgs, boolean test) throws Exception {
        Path build = step.resolve("build");
        deleteTree(build);
        Path sources = Files.createDirectories(build.resolve("src"));
        Path classes = Files.createDirectories(build.resolve("classes"));
        copyTree(step.resolve("src"), sources);
        // A test is a class named Tests in the step's tests folder. It is compiled with the step's source.
        copyTree(step.resolve("tests"), build.resolve("tests"));
        // Chapter assets, such as images and sounds, are shared by the chapter's steps, so they are copied beside the program.
        if (step.getParent() != null) {
            copyTree(step.getParent().resolve("assets"), build.resolve("assets"));
        }
        // The visualizer page is copied beside the program, so the trace the program writes is found next to it.
        Path page = step.resolve("visualize.html");
        if (Files.isRegularFile(page)) {
            Files.copy(page, build.resolve("visualize.html"), StandardCopyOption.REPLACE_EXISTING);
        }
        if (source != null) {
            Files.writeString(sources.resolve("Main.java"), source, StandardCharsets.UTF_8);
        }

        status("Compiling " + step.getFileName() + "...");
        List<String> files = new ArrayList<>(javaFiles(sources));
        if (test) {
            files.addAll(javaFiles(build.resolve("tests")));
        }
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new IllegalStateException("No Java compiler is available. The runner needs a full JDK, not a JRE.");
        }
        Path junitJar = test && usesJUnit(build.resolve("tests")) ? junitJar(step) : null;
        List<String> options = new ArrayList<>(List.of("-encoding", "UTF-8", "-d", classes.toString()));
        if (junitJar != null) {
            options.addAll(List.of("-cp", junitJar.toString()));
        }
        options.addAll(files);
        ByteArrayOutputStream messages = new ByteArrayOutputStream();
        int compiled = compiler.run(null, null, messages, options.toArray(new String[0]));
        String text = messages.toString(StandardCharsets.UTF_8);
        if (!text.isEmpty()) {
            output(text, "stderr");
        }
        if (compiled != 0) {
            finished(compiled);
            return;
        }

        status(test ? "Running tests" : "Running Main");
        String java = Paths.get(System.getProperty("java.home"), "bin", "java.exe").toString();
        String mainClass = test ? "Tests" : "Main";
        List<String> command;
        if (junitJar != null) {
            command = new ArrayList<>(List.of(java, "-jar", junitJar.toString(), "execute", "--disable-ansi-colors", "--class-path", classes.toString(),
                    "--scan-class-path", classes.toString(), "--details", "tree"));
            command.addAll(programArgs);
        } else {
            // The program's arguments go through an argument file, which the launcher reads itself, so Windows never
            // expands wildcards such as * in them.
            command = new ArrayList<>(List.of(java, "@" + argumentFile(build, classes, mainClass, programArgs)));
        }
        process = new ProcessBuilder(command).directory(build.toFile()).start();
        input = process.getOutputStream();
        Thread out = pipe(process.getInputStream(), "stdout");
        Thread err = pipe(process.getErrorStream(), "stderr");
        int code = process.waitFor();
        out.join();
        err.join();
        input = null;
        finished(code);
    }

    /**
     * Writes the class path, the main class, and the program's arguments to an argument file, and returns its path. Each
     * item is quoted, so spaces and symbols reach the program exactly as they were typed.
     */
    private static String argumentFile(Path build, Path classes, String mainClass, List<String> programArgs) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add(quoted("-cp"));
        lines.add(quoted(classes.toString()));
        lines.add(quoted(mainClass));
        for (String arg : programArgs) {
            lines.add(quoted(arg));
        }
        Path file = build.resolve("program-args.txt");
        Files.write(file, lines, StandardCharsets.UTF_8);
        return file.toString();
    }

    /** An argument wrapped in double quotes, with backslashes and double quotes escaped, as an argument file expects. */
    private static String quoted(String text) {
        return "\"" + text.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
    }

    /** Sends a line the user typed to the running program. */
    void sendInput(String text) throws IOException {
        OutputStream stream = input;
        if (stream == null) {
            throw new IllegalStateException("No program is waiting for input.");
        }
        stream.write(text.getBytes(StandardCharsets.UTF_8));
        stream.flush();
    }

    void stop() {
        Process running = process;
        if (running != null) {
            running.destroyForcibly();
        }
    }

    private Thread pipe(java.io.InputStream stream, String name) {
        Thread thread = new Thread(() -> {
            // Output is sent as soon as any text arrives, not only at the end of a line. A prompt such as "Enter a number: "
            // has no newline, and it must show before the program waits for input.
            try (InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
                char[] buffer = new char[256];
                int count;
                while ((count = reader.read(buffer)) != -1) {
                    output(new String(buffer, 0, count), name);
                }
            } catch (IOException ignored) {
                // The program has ended, so there is nothing more to read.
            }
        }, "pipe-" + name);
        thread.setDaemon(true);
        thread.start();
        return thread;
    }

    private void status(String text) {
        output(text + "\n", "status");
    }

    private void output(String text, String stream) {
        JsonObject payload = new JsonObject();
        payload.addProperty("text", text);
        payload.addProperty("stream", stream);
        event("run-output", payload);
    }

    private void finished(Integer code) {
        JsonObject payload = new JsonObject();
        payload.addProperty("code", code);
        event("run-finished", payload);
    }

    private void event(String name, JsonObject payload) {
        JsonObject message = new JsonObject();
        message.addProperty("kind", "event");
        message.addProperty("event", name);
        message.add("payload", payload);
        events.accept(message);
    }

    /** True when a test in the step's tests folder uses JUnit, so the JUnit launcher must run it. */
    private static boolean usesJUnit(Path tests) throws IOException {
        for (String file : javaFiles(tests)) {
            if (Files.readString(Path.of(file)).contains("org.junit")) {
                return true;
            }
        }
        return false;
    }

    /** The JUnit console launcher, kept with the course's other vendored files. Throws when it is missing. */
    private static Path junitJar(Path step) {
        Path root = step;
        while (root != null && !Files.exists(root.resolve("parts.json"))) {
            root = root.getParent();
        }
        if (root == null) {
            throw new IllegalStateException("Could not find the course folder above " + step);
        }
        Path jar = root.resolve("resources").resolve("vendor").resolve("junit")
                .resolve("junit-platform-console-standalone.jar");
        if (!Files.isRegularFile(jar)) {
            throw new IllegalStateException("JUnit is not installed. Place junit-platform-console-standalone.jar in resources\\vendor\\junit, then press Test again.");
        }
        return jar;
    }

    private static List<String> javaFiles(Path dir) throws IOException {
        if (!Files.isDirectory(dir)) {
            return new ArrayList<>();
        }
        try (Stream<Path> files = Files.walk(dir)) {
            return files.filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().endsWith(".java"))
                    .map(Path::toString)
                    .sorted()
                    .toList();
        }
    }

    private static void copyTree(Path from, Path to) throws IOException {
        if (!Files.isDirectory(from)) {
            return;
        }
        try (Stream<Path> files = Files.walk(from)) {
            for (Path file : files.filter(Files::isRegularFile).toList()) {
                Path target = to.resolve(from.relativize(file).toString());
                Files.createDirectories(target.getParent());
                Files.copy(file, target);
            }
        }
    }

    /** Removes a build folder and everything in it. It only ever removes a step's own build folder. */
    private static void deleteTree(Path build) throws IOException {
        if (!Files.exists(build)) {
            return;
        }
        if (!build.getFileName().toString().equals("build")) {
            throw new IllegalStateException("Refusing to delete " + build);
        }
        try (Stream<Path> files = Files.walk(build)) {
            for (Path p : files.sorted(Comparator.reverseOrder()).toList()) {
                Files.delete(p);
            }
        }
    }

    /** Runs an event on the window's thread, since the page can only be changed from there. */
    static Consumer<JsonObject> onWindowThread(Consumer<JsonObject> send) {
        return message -> SwingUtilities.invokeLater(() -> send.accept(message));
    }

    JsonArray noFiles() {
        return new JsonArray();
    }
}
