import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

public class Main {
    // Counts kept for the whole run. The sort adds to them, so they are fields rather than parameters.
    static long comparisons = 0;
    static long swaps = 0;

    // The trace text for trace.js, or null when no trace is being written, as for the timing list.
    static StringBuilder trace = null;
    static boolean firstFrame = true;

    // Formats a number with a comma between each group of three digits, such as 1,000,000. Negative numbers keep their sign.
    static String withCommas(long value) {
        return String.format(Locale.US, "%,d", value);
    }

    // Builds every whole number from -n to n exactly once, in an order that looks random. The seed is fixed, so the
    // order is the same on every run. Used for the timing list, which is counted and not drawn.
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

    // The values as text, such as [5, 2, 8].
    static String arrayText(int[] values) {
        StringBuilder text = new StringBuilder("[");
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                text.append(", ");
            }
            text.append(values[i]);
        }
        return text.append("]").toString();
    }

    // The indexes from first to last, inclusive, as text such as [3, 4, 5]. Empty when first is past last.
    static String indexRange(int first, int last) {
        StringBuilder text = new StringBuilder("[");
        for (int i = first; i <= last; i++) {
            if (i > first) {
                text.append(", ");
            }
            text.append(i);
        }
        return text.append("]").toString();
    }

    // Writes one frame of trace.js. A comma goes before every frame except the first.
    static void writeFrame(String array, String compare, String swap, String sorted, String message) {
        if (trace == null) {
            return;
        }
        if (!firstFrame) {
            trace.append(",\n");
        }
        firstFrame = false;
        trace.append("    {\"array\": ").append(array).append(", \"compare\": ").append(compare)
                .append(", \"swap\": ").append(swap).append(", \"sorted\": ").append(sorted)
                .append(", \"message\": \"").append(message).append("\"}");
    }

    // Bubble sort. Each pass walks along the list, comparing neighbors and swapping them when they're out of order.
    // Every pass puts the largest remaining value in its final place at the end. A pass with no swaps means the list
    // is sorted, so the sort stops early. When trace is set, every comparison and swap is written as a frame.
    static void bubbleSort(int[] values) {
        int n = values.length;
        for (int pass = 0; pass + 1 < n; pass++) {
            boolean swapped = false;
            int placed = n - pass;   // positions placed..n-1 already hold their final values
            for (int j = 0; j + 1 < n - pass; j++) {
                comparisons++;
                int left = values[j];
                int right = values[j + 1];
                boolean outOfOrder = left > right;
                if (trace != null) {
                    String message = "Compare " + left + " and " + right + ". "
                            + (outOfOrder ? "They are out of order, so swap them." : "They are in order, so leave them.");
                    writeFrame(arrayText(values), indexRange(j, j + 1), "[]", indexRange(placed, n - 1), message);
                }
                if (outOfOrder) {
                    values[j] = right;
                    values[j + 1] = left;
                    swaps++;
                    swapped = true;
                    if (trace != null) {
                        writeFrame(arrayText(values), "[]", indexRange(j, j + 1), indexRange(placed, n - 1),
                                "The two values are now in order.");
                    }
                }
            }
            if (!swapped) {
                break;
            }
        }
        if (trace != null) {
            writeFrame(arrayText(values), "[]", "[]", indexRange(0, n - 1),
                    "Sorted. " + comparisons + " comparisons and " + swaps + " swaps.");
        }
    }

    public static void main(String[] args) throws IOException {
        // A short list for the visualizer. It holds only non-negative values, each of 1 through 20 once, so each bar's
        // height matches its value.
        int[] small = {14, 3, 19, 7, 1, 12, 20, 5, 16, 9, 2, 17, 6, 11, 18, 4, 13, 8, 15, 10};
        trace = new StringBuilder();
        trace.append("window.trace = {\n");
        trace.append("  \"title\": \"Bubble sort\",\n");
        trace.append("  \"frames\": [\n");
        bubbleSort(small);
        trace.append("\n  ]\n};\n");
        Files.writeString(Path.of("trace.js"), trace.toString());
        trace = null;

        System.out.println("Visualized list: " + small.length + " values");
        StringBuilder sorted = new StringBuilder("Sorted:");
        for (int value : small) {
            sorted.append(" ").append(value);
        }
        System.out.println(sorted);
        System.out.println("Comparisons: " + comparisons + ", swaps: " + swaps);

        // A long list of every whole number from -1,000 to 1,000, in a shuffled order. It is counted, not drawn.
        int[] timing = makeSortData(1000);
        comparisons = 0;
        swaps = 0;
        bubbleSort(timing);

        boolean sortedCorrectly = true;
        for (int i = 1; i < timing.length; i++) {
            if (timing[i - 1] > timing[i]) {
                sortedCorrectly = false;
            }
        }
        System.out.println();
        System.out.println("Timing list: " + withCommas(timing.length) + " values, from -1,000 to 1,000");
        System.out.println("First three after sorting: " + timing[0] + " " + timing[1] + " " + timing[2]);
        System.out.println("Last three after sorting: " + timing[timing.length - 3] + " " + timing[timing.length - 2]
                + " " + timing[timing.length - 1]);
        System.out.println("Comparisons: " + withCommas(comparisons) + ", swaps: " + withCommas(swaps));
        System.out.println("Sorted correctly: " + (sortedCorrectly ? "yes" : "no"));

        // Try: change the visualized list to 20 down to 1, in reverse order. Predict the comparison count before you press
        // Run. Reversed input is the worst case for bubble sort.
    }
}
