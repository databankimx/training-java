import java.util.List;
import java.util.Optional;

public class Main {
    // Returns the first even value, or an empty Optional when the list has none.
    static Optional<Integer> firstEven(List<Integer> values) {
        for (int value : values) {
            if (value % 2 == 0) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        Optional<Integer> found = firstEven(List.of(3, 5, 8, 9));
        System.out.println("First even: " + found.orElse(-1));

        Optional<Integer> missing = firstEven(List.of(1, 3, 7));
        System.out.println("First even: " + missing.orElse(-1));

        missing.ifPresentOrElse(
                value -> System.out.println("Found " + value),
                () -> System.out.println("No even value"));
    }
}
