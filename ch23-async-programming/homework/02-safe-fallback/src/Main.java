import java.util.concurrent.CompletableFuture;

public class Main {
    // Reads a value that might fail. Here the failure is simulated for negative inputs.
    static int readSensor(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("no sensor " + id);
        }
        return id * 10;
    }

    public static void main(String[] args) {
        CompletableFuture<Integer> good = CompletableFuture.supplyAsync(() -> readSensor(3))
                .exceptionally(error -> 0);
        CompletableFuture<Integer> bad = CompletableFuture.supplyAsync(() -> readSensor(-1))
                .exceptionally(error -> 0);

        System.out.println("Good reading: " + good.join());
        System.out.println("Bad reading: " + bad.join());
        System.out.println("Total: " + (good.join() + bad.join()));
    }
}
