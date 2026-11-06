import java.util.function.Function;
import java.util.function.IntConsumer;

public class Main {
    public static void main(String[] args) {
        // A lambda captures the value of a local variable. The variable must not change after the capture.
        int factor = 3;
        Function<Integer, Integer> scaleByValue = x -> x * factor;
        System.out.println("Scaled by value: " + scaleByValue.apply(5));

        // A lambda cannot change a local variable, so a changing total is kept in an array of one element.
        int[] total = {0};
        IntConsumer addTo = x -> total[0] += x;
        addTo.accept(4);
        addTo.accept(6);
        System.out.println("Total through an array: " + total[0]);
    }
}
