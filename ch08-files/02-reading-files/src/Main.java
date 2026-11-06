import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        Files.createDirectories(Path.of("data"));
        Files.writeString(Path.of("data/notes.txt"),
                "First note: buy bread.\nSecond note: call the dentist.\nThird note: finish the report.\n");

        try {
            BufferedReader in = new BufferedReader(new FileReader("data/notes.txt"));
            String line;
            int count = 0;
            while ((line = in.readLine()) != null) {
                count++;
                System.out.println(count + ": " + line);
            }
            in.close();
            System.out.println("Lines read: " + count);
        } catch (IOException error) {
            System.out.println("Could not open data/notes.txt");
        }
    }
}
