import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        Files.createDirectories(Path.of("data"));
        try {
            PrintWriter out = new PrintWriter("data/greetings.txt");
            out.print("Hello, file!\n");
            out.print("Writing to a file works like System.out.\n");
            out.close();
            System.out.println("Wrote data/greetings.txt");
        } catch (IOException error) {
            System.out.println("Could not create the file.");
        }
    }
}
