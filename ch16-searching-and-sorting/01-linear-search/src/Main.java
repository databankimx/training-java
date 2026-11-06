import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

public class Main {
    // Formats a non-negative number with a comma between each group of three digits, such as 1,000,000.
    static String withCommas(long value) {
        return String.format(Locale.US, "%,d", value);
    }

    // Builds every whole number from 0 to max exactly once, in an order that looks random. The seed is fixed, so the
    // order is the same on every run.
    static int[] makeData(int max) {
        int[] values = new int[max + 1];
        for (int v = 0; v <= max; v++) {
            values[v] = v;
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

    static long comparisons = 0;

    // Looks for target from the start, one value at a time. Returns its index, or -1 if it isn't there.
    // The comparisons field counts every value that was checked.
    static int linearSearch(int[] values, int target) {
        for (int i = 0; i < values.length; i++) {
            comparisons++;
            if (values[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // Describes a search result for people: the index when the value was found, or NOT FOUND for -1.
    static String describe(int index) {
        return index == -1 ? "NOT FOUND" : "index " + index;
    }

    // Writes trace.js, which the visualizer page loads. It holds one frame for each comparison.
    static void writeTrace(int[] values, int target, Path path) throws IOException {
        StringBuilder out = new StringBuilder();
        out.append("window.trace = {\n");
        out.append("  \"title\": \"Linear search\",\n");
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

        boolean first = true;
        boolean found = false;
        for (int i = 0; i < values.length && !found; i++) {
            if (!first) {
                out.append(",\n");
            }
            first = false;
            out.append("    {\"array\": ").append(array).append(", \"compare\": [").append(i).append("], ");
            if (values[i] == target) {
                found = true;
                out.append("\"found\": ").append(i).append(", \"message\": \"Found ").append(target)
                        .append(" at index ").append(i).append(" after ").append(i + 1).append(" comparisons.\"}");
            } else {
                out.append("\"message\": \"Index ").append(i).append(" holds ").append(values[i])
                        .append(", which is not ").append(target).append(".\"}");
            }
        }
        if (!found) {
            if (!first) {
                out.append(",\n");
            }
            out.append("    {\"array\": ").append(array).append(", \"compare\": [], \"message\": \"Checked all ")
                    .append(values.length).append(" values. ").append(target).append(" is not here.\"}");
        }
        out.append("\n  ]\n};\n");
        Files.writeString(path, out.toString());
    }

    public static void main(String[] args) throws IOException {
        // A short list for the visualizer. The values are in no particular order, and the position of each is known.
        int[] small = {4, 7, 10, 0, 2, 8, 9, 3, 5, 6, 1, 11};
        comparisons = 0;
        int index = linearSearch(small, 3);
        System.out.println("Data: " + small.length + " values");
        System.out.println("Searching for 3: " + describe(index) + " after " + comparisons + " comparisons");
        writeTrace(small, 3, Path.of("trace.js"));

        // A long list, shuffled, with every whole number from 0 to 1,000. It is counted, not drawn.
        int[] large = makeData(1000);
        System.out.println("Searching " + withCommas(large.length) + " values");

        // First a valid value, one that is in the list: 737. Where it sits depends on the shuffle, so nothing about the
        // list's order is assumed.
        comparisons = 0;
        index = linearSearch(large, 737);
        System.out.println("  for 737: " + describe(index) + " after " + withCommas(comparisons) + " comparisons");

        // Then an invalid value: 5,000 is outside the range the list covers, so it can't be there.
        comparisons = 0;
        index = linearSearch(large, 5000);
        System.out.println("  for 5,000: " + describe(index) + " after " + withCommas(comparisons) + " comparisons");
    }
}
