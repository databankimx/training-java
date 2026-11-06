import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Path file = Path.of("scores.txt");
        Files.writeString(file, "72\n95\n88\n61\n");

        List<String> lines = Files.readAllLines(file);
        int total = 0;
        for (String line : lines) {
            total += Integer.parseInt(line);
        }
        System.out.println("Scores read: " + lines.size());
        System.out.println("Total: " + total);
    }
}
