import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        Path file = Path.of("readings.txt");
        Files.writeString(file, "21.5\n22.0\n");

        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String first = reader.readLine();
            String second = reader.readLine();
            String third = reader.readLine();
            System.out.println("Readings: " + first + ", " + second + ", " + third);
        } finally {
            System.out.println("Reader closed.");
        }
    }
}
