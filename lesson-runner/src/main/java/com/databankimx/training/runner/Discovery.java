package com.databankimx.training.runner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Reads the course catalogue: the parts in parts.json, the chapters in each part, and the steps, sections, and
 * homework inside each chapter. The shape matches what the C runner sends, so the page works unchanged.
 *
 * Folder conventions: a step is a folder named NN-name, a section is a folder named sNN-name holding steps, and a
 * chapter's homework is a folder named homework holding steps, with an optional title.txt.
 */
final class Discovery {

    /** The repository root, where parts.json lives. The runner starts in lesson-runner, so the root is one level up. */
    static final Path ROOT = Paths.get("..").toAbsolutePath().normalize();

    private Discovery() {
    }

    static JsonArray listParts() throws IOException {
        JsonArray parts = new JsonArray();
        Path file = ROOT.resolve("parts.json");
        if (!Files.exists(file)) {
            return parts;
        }
        JsonObject catalogue = JsonParser.parseString(Files.readString(file, StandardCharsets.UTF_8)).getAsJsonObject();
        for (JsonElement element : catalogue.getAsJsonArray("parts")) {
            JsonObject part = element.getAsJsonObject();
            JsonObject out = new JsonObject();
            out.addProperty("title", part.get("title").getAsString());
            JsonArray chapters = new JsonArray();
            for (JsonElement name : part.getAsJsonArray("chapters")) {
                Path dir = ROOT.resolve(name.getAsString());
                // A chapter listed in parts.json but not yet written is left out, so the menu shows only real chapters.
                if (Files.isDirectory(dir)) {
                    chapters.add(chapter(dir));
                }
            }
            out.add("chapters", chapters);
            parts.add(out);
        }
        return parts;
    }

    private static JsonObject chapter(Path dir) throws IOException {
        JsonObject out = new JsonObject();
        out.addProperty("name", dir.getFileName().toString());
        out.addProperty("path", dir.toString());
        out.addProperty("title", headingOf(dir.resolve("lesson.md"), dir.getFileName().toString()));

        out.add("steps", steps(dir, "^\\d\\d-.*"));

        JsonArray sections = new JsonArray();
        for (Path section : childDirectories(dir, "^s\\d\\d-.*")) {
            JsonObject item = new JsonObject();
            item.addProperty("name", section.getFileName().toString());
            item.addProperty("path", section.toString());
            item.addProperty("title", headingOf(section.resolve("lesson.md"), section.getFileName().toString()));
            item.add("steps", steps(section, "^\\d\\d-.*"));
            sections.add(item);
        }
        out.add("sections", sections);

        Path homework = dir.resolve("homework");
        if (Files.isDirectory(homework)) {
            out.addProperty("homework_path", homework.toString());
            out.addProperty("homework_title", homeworkTitle(homework));
            out.add("homework", steps(homework, "^\\d\\d-.*"));
        } else {
            out.addProperty("homework_path", "");
            out.addProperty("homework_title", "Homework");
            out.add("homework", new JsonArray());
        }
        return out;
    }

    /** Each step folder under a directory, in name order. */
    private static JsonArray steps(Path dir, String pattern) throws IOException {
        JsonArray steps = new JsonArray();
        for (Path step : childDirectories(dir, pattern)) {
            steps.add(step(step));
        }
        return steps;
    }

    private static JsonObject step(Path dir) throws IOException {
        JsonObject out = new JsonObject();
        out.addProperty("name", dir.getFileName().toString());
        out.addProperty("path", dir.toString());
        out.addProperty("title", headingOf(dir.resolve("lesson.md"), humanize(dir.getFileName().toString())));
        Path src = dir.resolve("src");
        out.addProperty("has_code", Files.isDirectory(src) && hasJavaFile(src));
        out.addProperty("has_main", Files.isRegularFile(src.resolve("Main.java")));
        Path tests = dir.resolve("tests");
        out.addProperty("has_tests", Files.isDirectory(tests) && hasJavaFile(tests));
        // The visualizer and the step options are added with the runner's run commands.
        out.addProperty("has_visualizer", false);
        out.add("options", new JsonArray());
        return out;
    }

    private static List<Path> childDirectories(Path dir, String pattern) throws IOException {
        List<Path> found = new ArrayList<>();
        if (!Files.isDirectory(dir)) {
            return found;
        }
        try (Stream<Path> children = Files.list(dir)) {
            children.filter(Files::isDirectory)
                    .filter(p -> p.getFileName().toString().matches(pattern))
                    .sorted(Comparator.comparing(p -> p.getFileName().toString()))
                    .forEach(found::add);
        }
        return found;
    }

    private static boolean hasJavaFile(Path dir) throws IOException {
        try (Stream<Path> files = Files.walk(dir)) {
            return files.anyMatch(p -> p.getFileName().toString().endsWith(".java"));
        }
    }

    /** The first "## " heading in a Markdown file, without the marker. Falls back when the file has none. */
    private static String headingOf(Path markdown, String fallback) throws IOException {
        if (!Files.isRegularFile(markdown)) {
            return fallback;
        }
        for (String line : Files.readAllLines(markdown, StandardCharsets.UTF_8)) {
            if (line.startsWith("## ")) {
                return line.substring(3).trim();
            }
        }
        return fallback;
    }

    private static String homeworkTitle(Path homework) throws IOException {
        Path title = homework.resolve("title.txt");
        if (Files.isRegularFile(title)) {
            return Files.readString(title, StandardCharsets.UTF_8).trim();
        }
        return "Homework";
    }

    /** "01-hello-world" becomes "Hello world", for steps that have no heading of their own. */
    private static String humanize(String folder) {
        String text = folder.replaceFirst("^\\d+-", "").replace('-', ' ');
        return text.isEmpty() ? folder : Character.toUpperCase(text.charAt(0)) + text.substring(1);
    }
}
