import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Main {
    public static void main(String[] args) throws IOException {
        Path log = Path.of("log.txt");
        Files.writeString(log, "first entry\n");
        Files.writeString(log, "second entry\n");
        System.out.print("After overwrite:\n" + Files.readString(log));

        Files.writeString(log, "third entry\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        System.out.print("After append:\n" + Files.readString(log));
    }
}
