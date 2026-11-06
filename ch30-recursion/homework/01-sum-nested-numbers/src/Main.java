import java.util.List;

public class Main {
    // Adds every number in a list that may contain other lists. A number is a base case, and a list is handled by
    // calling the same method on it.
    static int sumNested(List<?> items) {
        int total = 0;
        for (Object item : items) {
            if (item instanceof List<?> sublist) {
                total += sumNested(sublist);
            } else {
                total += (Integer) item;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        List<Object> numbers = List.of(1, List.of(2, List.of(3, 4)), 5, List.of(6));
        System.out.println("Sum: " + sumNested(numbers));
    }
}
