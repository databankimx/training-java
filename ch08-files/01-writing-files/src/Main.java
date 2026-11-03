import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        Path file = Path.of("greeting.txt");
        Files.writeString(file, "Hello, file.\nThis line was written by Java.\n");
        System.out.println("Wrote " + file.toAbsolutePath().getFileName());
        System.out.println("Exists: " + Files.exists(file));
    }
}
