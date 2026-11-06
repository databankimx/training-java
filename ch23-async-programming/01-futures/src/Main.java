import java.util.concurrent.CompletableFuture;

public class Main {
    // A slow piece of work. Sleeping stands in for a call to a service or a disk.
    static int slowSquare(int x) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException error) {
            Thread.currentThread().interrupt();
        }
        return x * x;
    }

    public static void main(String[] args) {
        System.out.println("Start");

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> slowSquare(7));
        System.out.println("Started the work");

        int result = future.join();
        System.out.println("Result: " + result);
        System.out.println("End");
    }
}
