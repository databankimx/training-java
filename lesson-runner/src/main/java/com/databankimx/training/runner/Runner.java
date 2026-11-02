package com.databankimx.training.runner;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
        if (args.has("test") && args.get("test").getAsBoolean()) {
            throw new IllegalArgumentException("Tests need JUnit, which the runner does not have yet.");
        }
        String source = args.has("source") && !args.get("source").isJsonNull() ? args.get("source").getAsString() : null;
        List<String> programArgs = new ArrayList<>();
        if (args.has("args") && args.get("args").isJsonArray()) {
            args.getAsJsonArray("args").forEach(a -> programArgs.add(a.getAsString()));
        }

        Thread worker = new Thread(() -> {
            try {
                compileAndRun(step, source, programArgs);
            } catch (Exception error) {
                output(error.getMessage() == null ? error.toString() : error.getMessage() + "\n", "stderr");
                finished(null);
            }
        }, "run-step");
        worker.setDaemon(true);
        worker.start();
        return JsonNull.INSTANCE;
    }

    private void compileAndRun(Path step, String source, List<String> programArgs) throws Exception {
        Path build = step.resolve("build");
        deleteTree(build);
        Path sources = Files.createDirectories(build.resolve("src"));
        Path classes = Files.createDirectories(build.resolve("classes"));
        copyTree(step.resolve("src"), sources);
        if (source != null) {
            Files.writeString(sources.resolve("Main.java"), source, StandardCharsets.UTF_8);
        }

        status("Compiling " + step.getFileName() + "...");
        List<String> files = javaFiles(sources);
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new IllegalStateException("No Java compiler is available. The runner needs a full JDK, not a JRE.");
        }
        List<String> options = new ArrayList<>(List.of("-encoding", "UTF-8", "-d", classes.toString()));
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

        status("Running Main");
        String java = Paths.get(System.getProperty("java.home"), "bin", "java.exe").toString();
        List<String> command = new ArrayList<>(List.of(java, "-cp", classes.toString(), "Main"));
        command.addAll(programArgs);
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
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output(line + "\n", name);
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

    private static List<String> javaFiles(Path dir) throws IOException {
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
