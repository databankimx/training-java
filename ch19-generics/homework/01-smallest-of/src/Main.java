import java.util.List;

public class Main {
    // The smallest value in a list. The list must have at least one value.
    static <T extends Comparable<T>> T smallestOf(List<T> values) {
        T smallest = values.get(0);
        for (int i = 1; i < values.size(); i++) {
            if (values.get(i).compareTo(smallest) < 0) {
                smallest = values.get(i);
            }
        }
        return smallest;
    }

    public static void main(String[] args) {
        System.out.println("Smallest number: " + smallestOf(List.of(5, 2, 9)));
        System.out.println("Smallest word: " + smallestOf(List.of("pear", "apple", "fig")));
    }
}
