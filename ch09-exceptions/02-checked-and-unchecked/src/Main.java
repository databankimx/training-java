import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    static void readConfig() throws IOException {
        Files.readString(Path.of("missing-config.txt"));
    }

    public static void main(String[] args) {
        try {
            readConfig();
        } catch (IOException error) {
            System.out.println("Checked exception caught: " + error.getClass().getSimpleName());
        }

        try {
            int value = Integer.parseInt("forty-two");
            System.out.println(value);
        } catch (NumberFormatException error) {
            System.out.println("Unchecked exception caught: " + error.getClass().getSimpleName());
        }
    }
}
