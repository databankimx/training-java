import java.util.Arrays;
import java.util.Locale;

public class Main {
    // Formats a number with a comma between each group of three digits, such as 1,000,000. Negative numbers keep their sign.
    static String withCommas(long value) {
        return String.format(Locale.US, "%,d", value);
    }

    // Builds every whole number from -n to n exactly once, in an order that looks random. The seed is fixed, so the
    // order is the same on every run.
    static int[] makeSortData(int n) {
        int[] values = new int[2 * n + 1];
        for (int v = -n; v <= n; v++) {
            values[v + n] = v;
        }
        long state = 12345;
        for (int i = values.length - 1; i > 0; i--) {
            state = (state * 1103515245L + 12345L) % 2147483648L;
            int j = (int) (state % (i + 1));
            int temp = values[i];
            values[i] = values[j];
            values[j] = temp;
        }
        return values;
    }

    // True when every value is no smaller than the one before it.
    static boolean isSorted(int[] values) {
        for (int i = 1; i < values.length; i++) {
            if (values[i - 1] > values[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // The short list from the sorting steps. Arrays.sort sorts it in place, in one call.
        int[] small = {14, 3, 19, 7, 1, 12, 20, 5, 16, 9, 2, 17, 6, 11, 18, 4, 13, 8, 15, 10};
        Arrays.sort(small);
        System.out.println("Short list: " + small.length + " values");
        StringBuilder sorted = new StringBuilder("Sorted:");
        for (int value : small) {
            sorted.append(" ").append(value);
        }
        System.out.println(sorted);

        // A search for 12 in the sorted list. Arrays.binarySearch returns the index when the value is present.
        int index = Arrays.binarySearch(small, 12);
        boolean present = index >= 0;
        System.out.println("Search for 12: " + (present ? "present" : "absent") + " at index " + index);

        // The timing list: every whole number from -1,000 to 1,000, in a shuffled order, sorted in one call.
        int[] timing = makeSortData(1000);
        Arrays.sort(timing);
        System.out.println();
        System.out.println("Timing list: " + withCommas(timing.length) + " values, from -1,000 to 1,000");
        System.out.println("Sorted correctly: " + (isSorted(timing) ? "yes" : "no"));

        // Two searches on the long sorted list: one value that is there, and one that is not.
        boolean found = Arrays.binarySearch(timing, 737) >= 0;
        System.out.println("Search for 737: " + (found ? "present" : "absent"));
        boolean missing = Arrays.binarySearch(timing, 5000) >= 0;
        System.out.println("Search for " + withCommas(5000) + ": " + (missing ? "present" : "absent"));
    }
}
