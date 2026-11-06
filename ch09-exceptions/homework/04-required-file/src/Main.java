import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    static void printFile(String name) {
        try (BufferedReader in = new BufferedReader(new FileReader(name))) {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException error) {
            throw new RuntimeException("could not open " + name, error);
        }
    }

    public static void main(String[] args) throws IOException {
        Files.createDirectories(Path.of("data"));
        Files.writeString(Path.of("data/settings.txt"), "theme=dark\nlanguage=en\n");

        try {
            printFile("data/settings.txt");
        } catch (RuntimeException error) {
            System.out.println(error.getMessage());
        }
    }
}
