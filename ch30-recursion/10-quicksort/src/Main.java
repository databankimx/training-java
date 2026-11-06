import java.util.ArrayList;
import java.util.List;

public class Main {
    // The middle value of three numbers: the largest of the two smaller ones, and the smallest of the rest.
    static int medianOfThree(int a, int b, int c) {
        return Math.max(Math.min(a, b), Math.min(Math.max(a, b), c));
    }

    // Quicksort with a new list at each level. A list of zero or one items is already sorted, which is the base case.
    static List<Integer> quicksort(List<Integer> numbers) {
        if (numbers.size() <= 1) {
            return numbers;
        }

        // The pivot is the median of the first, middle, and last items.
        int pivot = medianOfThree(
                numbers.get(0),
                numbers.get(numbers.size() / 2),
                numbers.get(numbers.size() - 1));

        List<Integer> less = new ArrayList<>();
        List<Integer> equal = new ArrayList<>();
        List<Integer> greater = new ArrayList<>();
        for (int n : numbers) {
            if (n < pivot) {
                less.add(n);
            } else if (n == pivot) {
                equal.add(n);
            } else {
                greater.add(n);
            }
        }

        List<Integer> result = new ArrayList<>(quicksort(less));
        result.addAll(equal);
        result.addAll(quicksort(greater));
        return result;
    }

    public static void main(String[] args) {
        List<Integer> numbers = List.of(20, 77, 45, -26, -87, 63, -94, -2, -66, 45);
        System.out.println("Unsorted: " + numbers);
        System.out.println("Sorted:   " + quicksort(numbers));

        System.out.println("Empty:    " + quicksort(List.of()));
        System.out.println("One item: " + quicksort(List.of(5)));
    }
}
