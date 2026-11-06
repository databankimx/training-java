import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        // One future for each number from 1 to 5, each computing its square in the background.
        List<CompletableFuture<Integer>> squares = new ArrayList<>();
        for (int n = 1; n <= 5; n++) {
            int number = n;
            squares.add(CompletableFuture.supplyAsync(() -> number * number));
        }

        CompletableFuture.allOf(squares.toArray(new CompletableFuture[0])).join();

        int total = 0;
        for (CompletableFuture<Integer> square : squares) {
            total += square.join();
        }
        System.out.println("Sum of squares: " + total);
    }
}
