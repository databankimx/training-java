import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Main {
    public static void main(String[] args) throws IOException {
        Path log = Path.of("visits.log");
        Files.writeString(log, "visit: first\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        Files.writeString(log, "visit: second\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        System.out.print(Files.readString(log));
    }
}
