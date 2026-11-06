import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        CompletableFuture<Integer> six = CompletableFuture.supplyAsync(() -> 6);
        CompletableFuture<Integer> seven = CompletableFuture.supplyAsync(() -> 7);

        // thenCombine waits for both futures, then calls the function with their two values.
        CompletableFuture<Integer> product = six.thenCombine(seven, (x, y) -> x * y);
        System.out.println("Product: " + product.join());

        // thenApply starts from the value of one future, once it is ready.
        CompletableFuture<Integer> doubled = product.thenApply(value -> value * 2);
        System.out.println("Doubled: " + doubled.join());

        // exceptionally supplies a fallback value when the work failed.
        CompletableFuture<Integer> failing = CompletableFuture.supplyAsync(() -> {
            throw new IllegalStateException("sensor offline");
        });
        int reading = failing.exceptionally(error -> -1).join();
        System.out.println("Reading after failure: " + reading);

        // allOf waits for every future in the list.
        List<CompletableFuture<Integer>> parts = List.of(
                CompletableFuture.supplyAsync(() -> 10),
                CompletableFuture.supplyAsync(() -> 20),
                CompletableFuture.supplyAsync(() -> 30));
        CompletableFuture.allOf(parts.toArray(new CompletableFuture[0])).join();
        int total = 0;
        for (CompletableFuture<Integer> part : parts) {
            total += part.join();
        }
        System.out.println("Sum of three: " + total);
    }
}
