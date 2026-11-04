import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        Files.createDirectories(Path.of("data"));
        Files.writeString(Path.of("data/input.txt"), "apples\nbananas\ncherries\n");

        try (BufferedReader in = new BufferedReader(new FileReader("data/input.txt"));
             PrintWriter out = new PrintWriter("data/numbered.txt")) {
            String line;
            int count = 0;
            while ((line = in.readLine()) != null) {
                count++;
                out.print(count + ". " + line + "\n");
            }
            System.out.println("Wrote " + count + " lines to data/numbered.txt");
        } catch (IOException error) {
            System.out.println("Could not open the files.");
        }
    }
}
