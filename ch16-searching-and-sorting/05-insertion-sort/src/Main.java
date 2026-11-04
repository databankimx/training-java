import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

public class Main {
    // Counts kept for the whole run. The sort adds to them, so they are fields rather than parameters.
    static long comparisons = 0;
    static long moves = 0;

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

    // Two indexes as text, such as [2, 7].
    static String indexPair(int a, int b) {
        return "[" + a + ", " + b + "]";
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

    // Insertion sort. The values before index i are already sorted. Each new value is moved left, one place at a time,
    // past every larger value in front of it, then it stays where it stops. The loop stops early when it finds a value
    // that is not larger, and it needs no comparison once the value reaches the front. When trace is set, every
    // comparison and move is written as a frame.
    static void insertionSort(int[] values) {
        int n = values.length;
        for (int i = 1; i < n; i++) {
            int j = i;
            String placed = indexRange(0, i - 1);
            while (j > 0) {
                comparisons++;
                boolean larger = values[j - 1] > values[j];
                if (trace != null) {
                    String message = values[j - 1] + " is " + (larger ? "larger than " : "not larger than ")
                            + values[j] + ". "
                            + (larger ? "Move " + values[j] + " one place left." : values[j] + " stays here.");
                    writeFrame(arrayText(values), indexPair(j - 1, j), "[]", placed, message);
                }
                if (!larger) {
                    break;
                }
                int temp = values[j - 1];
                values[j - 1] = values[j];
                values[j] = temp;
                moves++;
                if (trace != null) {
                    writeFrame(arrayText(values), "[]", indexPair(j - 1, j), placed, "The value moved one place left.");
                }
                j--;
            }
        }
        if (trace != null) {
            writeFrame(arrayText(values), "[]", "[]", indexRange(0, n - 1),
                    "Sorted. " + comparisons + " comparisons and " + moves + " moves.");
        }
    }

    public static void main(String[] args) throws IOException {
        // A short list for the visualizer. It holds the values 1 through 20, each once, in a scrambled order. It is
        // non-negative, so each bar's height matches its value.
        int[] small = {14, 3, 19, 7, 1, 12, 20, 5, 16, 9, 2, 17, 6, 11, 18, 4, 13, 8, 15, 10};
        trace = new StringBuilder();
        trace.append("window.trace = {\n");
        trace.append("  \"title\": \"Insertion sort\",\n");
        trace.append("  \"frames\": [\n");
        insertionSort(small);
        trace.append("\n  ]\n};\n");
        Files.writeString(Path.of("trace.js"), trace.toString());
        trace = null;

        System.out.println("Visualized list: " + small.length + " values");
        StringBuilder sorted = new StringBuilder("Sorted:");
        for (int value : small) {
            sorted.append(" ").append(value);
        }
        System.out.println(sorted);
        System.out.println("Comparisons: " + comparisons + ", moves: " + moves);

        // A long list of every whole number from -1,000 to 1,000, in a shuffled order. It is counted, not drawn.
        int[] timing = makeSortData(1000);
        comparisons = 0;
        moves = 0;
        insertionSort(timing);

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
        System.out.println("Comparisons: " + withCommas(comparisons) + ", moves: " + withCommas(moves));
        System.out.println("Sorted correctly: " + (sortedCorrectly ? "yes" : "no"));

        // Try: sort the values 20 down to 1, in reverse order. Predict the comparison count before you press Run.
    }
}
