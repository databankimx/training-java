import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Function<Integer, Integer> square = x -> x * x;
        System.out.println("Square of 4: " + square.apply(4));

        List<Integer> values = new ArrayList<>(List.of(5, 2, 9, 1, 6, 3, 1, 4));
        values.sort((a, b) -> Integer.compare(b, a));

        StringBuilder descending = new StringBuilder("Descending:");
        for (int value : values) {
            descending.append(" ").append(value);
        }
        System.out.println(descending);
    }
}
