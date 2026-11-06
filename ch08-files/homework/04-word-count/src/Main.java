import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Files.createDirectories(Path.of("data"));
        Files.writeString(Path.of("data/essay.txt"),
                "The quick brown fox\njumps over the lazy dog.\nThe end.\n");

        int lines = 0;
        try (BufferedReader in = new BufferedReader(new FileReader("data/essay.txt"))) {
            while (in.readLine() != null) {
                lines++;
            }
        }

        int words = 0;
        try (Scanner in = new Scanner(new FileReader("data/essay.txt"))) {
            while (in.hasNext()) {
                in.next();
                words++;
            }
        }

        System.out.println("Lines: " + lines);
        System.out.println("Words: " + words);
    }
}
