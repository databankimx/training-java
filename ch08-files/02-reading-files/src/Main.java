import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Path file = Path.of("notes.txt");
        Files.writeString(file, "apples\nbananas\ncherries\n");

        String text = Files.readString(file);
        System.out.print("Whole file:\n" + text);

        List<String> lines = Files.readAllLines(file);
        System.out.println("Line count: " + lines.size());
        System.out.println("Second line: " + lines.get(1));
    }
}
