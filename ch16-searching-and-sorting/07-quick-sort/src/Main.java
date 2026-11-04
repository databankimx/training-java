import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

public class Main {
    // Counts kept for the whole run. The sort adds to them, so they are fields rather than parameters.
    static long comparisons = 0;
    static long swaps = 0;

    // Which places hold their final values. Set before each run, and read when a trace frame is written.
    static boolean[] finalized;

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

    // The indexes whose values are in their final places, as text.
    static String finalText() {
        StringBuilder text = new StringBuilder("[");
        boolean any = false;
        for (int i = 0; i < finalized.length; i++) {
            if (finalized[i]) {
                if (any) {
                    text.append(", ");
                }
                text.append(i);
                any = true;
            }
        }
        return text.append("]").toString();
    }

    // Writes one frame of trace.js. A comma goes before every frame except the first. The range is the part of the
    // list being worked on; the visualizer fades everything outside it.
    static void writeFrame(String array, String compare, String swap, String sorted, String range, String message) {
        if (trace == null) {
            return;
        }
        if (!firstFrame) {
            trace.append(",\n");
        }
        firstFrame = false;
        trace.append("    {\"array\": ").append(array).append(", \"compare\": ").append(compare)
                .append(", \"swap\": ").append(swap).append(", \"sorted\": ").append(sorted)
                .append(", \"range\": ").append(range).append(", \"message\": \"").append(message).append("\"}");
    }

    // Partition. The last value in values[lo..hi-1] is the pivot. Every value smaller than the pivot is moved to the
    // left of it, so the pivot can be placed between the two groups. Returns the pivot's final index. When trace is
    // set, every comparison and swap is written as a frame.
    static int partition(int[] values, int lo, int hi) {
        int pivotIndex = hi - 1;
        int pivot = values[pivotIndex];
        int next = lo;   // the place where the next smaller value should go
        String range = indexRange(lo, hi - 1);
        for (int j = lo; j + 1 < hi; j++) {
            comparisons++;
            boolean smaller = values[j] < pivot;
            if (trace != null) {
                String message = "Is " + values[j] + " smaller than the pivot " + pivot + "? "
                        + (smaller ? "Yes, so it goes to the left." : "No.");
                writeFrame(arrayText(values), indexPair(j, pivotIndex), "[]", finalText(), range, message);
            }
            if (smaller) {
                if (next != j) {
                    int temp = values[next];
                    values[next] = values[j];
                    values[j] = temp;
                    swaps++;
                    if (trace != null) {
                        writeFrame(arrayText(values), "[]", indexPair(next, j), finalText(), range,
                                "Move the smaller value to the left.");
                    }
                }
                next++;
            }
        }
        if (next != pivotIndex) {
            int temp = values[next];
            values[next] = values[pivotIndex];
            values[pivotIndex] = temp;
            swaps++;
            if (trace != null) {
                writeFrame(arrayText(values), "[]", indexPair(next, pivotIndex), finalText(), range,
                        "Swap the pivot into its place.");
            }
        }
        finalized[next] = true;
        if (trace != null) {
            writeFrame(arrayText(values), "[]", "[]", finalText(), range,
                    "The pivot " + pivot + " is now in its final place.");
        }
        return next;
    }

    // Quick sort. A run of one value is already in place. Otherwise the run is partitioned around its pivot, and each
    // side of the pivot is sorted the same way.
    static void quickSort(int[] values, int lo, int hi) {
        if (hi - lo <= 1) {
            if (hi - lo == 1) {
                finalized[lo] = true;
            }
            return;
        }
        int pivotIndex = partition(values, lo, hi);
        quickSort(values, lo, pivotIndex);
        quickSort(values, pivotIndex + 1, hi);
    }

    public static void main(String[] args) throws IOException {
        // A short list for the visualizer. It holds the values 1 through 20, each once, in a scrambled order. It is
        // non-negative, so each bar's height matches its value.
        int[] small = {14, 3, 19, 7, 1, 12, 20, 5, 16, 9, 2, 17, 6, 11, 18, 4, 13, 8, 15, 10};
        finalized = new boolean[small.length];
        trace = new StringBuilder();
        trace.append("window.trace = {\n");
        trace.append("  \"title\": \"Quick sort\",\n");
        trace.append("  \"frames\": [\n");
        quickSort(small, 0, small.length);
        writeFrame(arrayText(small), "[]", "[]", indexRange(0, small.length - 1), "[]",
                "Sorted. " + comparisons + " comparisons and " + swaps + " swaps.");
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
        finalized = new boolean[timing.length];
        quickSort(timing, 0, timing.length);

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

        // Try: sort the values 1 through 20 in order, and predict the comparison count before you press Run.
    }
}
