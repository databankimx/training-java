import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

public class Main {
    // Formats a non-negative number with a comma between each group of three digits, such as 1,000,000.
    static String withCommas(long value) {
        return String.format(Locale.US, "%,d", value);
    }

    // Describes a search result for people: the index when the value was found, or NOT FOUND for -1.
    static String describe(int index) {
        return index == -1 ? "NOT FOUND" : "index " + index;
    }

    // Looks for target from the start, one value at a time. Used here for comparison with binary search.
    static int linearSearch(int[] values, int target, long[] comparisons) {
        for (int i = 0; i < values.length; i++) {
            comparisons[0]++;
            if (values[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // Looks for target in a sorted list. It checks the middle, then throws away the half that can't hold the target.
    // The low and high indexes mark the part of the list still being searched.
    static int binarySearch(int[] values, int target, long[] comparisons) {
        int low = 0;
        int high = values.length - 1;
        while (low <= high) {
            int middle = (low + high) / 2;
            comparisons[0]++;
            if (values[middle] == target) {
                return middle;
            }
            if (values[middle] < target) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return -1;
    }

    // Writes trace.js, which the visualizer page loads. Each frame shows the range still being searched.
    static void writeTrace(int[] values, int target, Path path) throws IOException {
        StringBuilder out = new StringBuilder();
        out.append("window.trace = {\n");
        out.append("  \"title\": \"Binary search\",\n");
        out.append("  \"target\": ").append(target).append(",\n");
        out.append("  \"frames\": [\n");

        StringBuilder array = new StringBuilder("[");
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                array.append(", ");
            }
            array.append(values[i]);
        }
        array.append("]");

        int low = 0;
        int high = values.length - 1;
        int count = 0;
        boolean first = true;
        boolean found = false;
        while (low <= high && !found) {
            int middle = (low + high) / 2;
            count++;
            if (!first) {
                out.append(",\n");
            }
            first = false;
            out.append("    {\"array\": ").append(array).append(", \"range\": [").append(low).append(", ")
                    .append(high).append("], \"compare\": [").append(middle).append("], ");
            if (values[middle] == target) {
                found = true;
                out.append("\"found\": ").append(middle).append(", \"message\": \"Found ").append(target)
                        .append(" at index ").append(middle).append(" after ").append(count)
                        .append(" comparisons.\"}");
            } else if (values[middle] < target) {
                out.append("\"message\": \"Index ").append(middle).append(" holds ").append(values[middle])
                        .append(", which is less than ").append(target)
                        .append(", so the lower half is thrown away.\"}");
                low = middle + 1;
            } else {
                out.append("\"message\": \"Index ").append(middle).append(" holds ").append(values[middle])
                        .append(", which is more than ").append(target)
                        .append(", so the upper half is thrown away.\"}");
                high = middle - 1;
            }
        }
        if (!found) {
            if (!first) {
                out.append(",\n");
            }
            out.append("    {\"array\": ").append(array).append(", \"compare\": [], \"message\": \"The range is empty. ")
                    .append(target).append(" is not here.\"}");
        }
        out.append("\n  ]\n};\n");
        Files.writeString(path, out.toString());
    }

    public static void main(String[] args) throws IOException {
        // A short sorted list for the visualizer. Each value's position is easy to read off.
        int[] small = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        long[] comparisons = {0};
        int index = binarySearch(small, 7, comparisons);
        System.out.println("Sorted list: " + small.length + " values");
        System.out.println("Searching for 7: " + describe(index) + " after " + comparisons[0] + " comparisons");
        writeTrace(small, 7, Path.of("trace.js"));

        // A long sorted list, 0 to 1,000, with each value sitting at the index that matches it.
        int[] large = new int[1001];
        for (int v = 0; v <= 1000; v++) {
            large[v] = v;
        }
        System.out.println("Sorted list: " + withCommas(large.length) + " values");

        comparisons[0] = 0;
        index = binarySearch(large, 737, comparisons);
        long[] linearComparisons = {0};
        linearSearch(large, 737, linearComparisons);
        System.out.println("  for 737: " + describe(index) + " after " + comparisons[0] + " comparisons (linear search: "
                + withCommas(linearComparisons[0]) + ")");

        comparisons[0] = 0;
        index = binarySearch(large, 5000, comparisons);
        System.out.println("  for 5,000: " + describe(index) + " after " + comparisons[0] + " comparisons");

        // Try: search the sorted list for 12. Predict the index, and the comparison count, before you press Run.
    }
}
