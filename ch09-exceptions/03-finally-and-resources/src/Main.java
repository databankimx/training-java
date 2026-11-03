import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    static void work(boolean fail) {
        System.out.println("start work");
        try {
            if (fail) {
                throw new IllegalStateException("something went wrong");
            }
            System.out.println("work finished");
        } finally {
            System.out.println("cleanup always runs");
        }
    }

    public static void main(String[] args) throws IOException {
        work(false);
        try {
            work(true);
        } catch (IllegalStateException error) {
            System.out.println("Caught: " + error.getMessage());
        }

        Path file = Path.of("resource-demo.txt");
        Files.writeString(file, "data\n");
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            System.out.println("read: " + reader.readLine());
        }
        System.out.println("reader closed automatically");
    }
}
