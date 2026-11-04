import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Main {
    static void makeSample() throws IOException {
        Path root = Path.of("sample");
        Files.createDirectories(root.resolve("music"));
        Files.createDirectories(root.resolve("photos/beach"));
        Files.writeString(root.resolve("readme.txt"), "readme\n");
        Files.writeString(root.resolve("notes.txt"), "notes\n");
        Files.writeString(root.resolve("music/song.txt"), "song\n");
        Files.writeString(root.resolve("photos/holiday.txt"), "holiday\n");
        Files.writeString(root.resolve("photos/beach/wave.txt"), "wave\n");
    }

    static void listFolder(File folder, int depth) {
        File[] entries = folder.listFiles();
        Arrays.sort(entries);
        for (File entry : entries) {
            System.out.println(" ".repeat(depth * 2) + entry.getName());
            if (entry.isDirectory()) {
                listFolder(entry, depth + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        makeSample();
        listFolder(new File("sample"), 0);
    }
}
