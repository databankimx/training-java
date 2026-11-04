import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        Files.createDirectories(Path.of("data"));
        try (PrintWriter out = new PrintWriter("data/test.txt")) {
            out.print("This first version of the text is quite long.");
        }

        try (RandomAccessFile file = new RandomAccessFile("data/test.txt", "rw")) {
            file.writeBytes("Short text.");
        }

        System.out.println(Files.readString(Path.of("data/test.txt")));
    }
}
