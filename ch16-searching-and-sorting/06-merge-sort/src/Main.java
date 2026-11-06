import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

public class Main {
    // Counts kept for the whole run. The sort adds to them, so they are fields rather than parameters.
    static long comparisons = 0;

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

    // Merges the two sorted runs values[lo..mid-1] and values[mid..hi-1] into one sorted run. Each comparison takes the
    // smaller of the two front values, and stops when one run is used up. The rest of the other run is then copied across.
    static void merge(int[] values, int lo, int mid, int hi) {
        int[] merged = new int[hi - lo];
        int k = 0;
        int left = lo;
        int right = mid;
        String range = indexRange(lo, hi - 1);
        while (left < mid && right < hi) {
            comparisons++;
            if (trace != null) {
                String message = "Compare " + values[left] + " from the left run with " + values[right]
                        + " from the right run. Keep the smaller.";
                writeFrame(arrayText(values), indexPair(left, right), "[]", "[]", range, message);
            }
            if (values[left] <= values[right]) {
                merged[k++] = values[left++];
            } else {
                merged[k++] = values[right++];
            }
        }
        while (left < mid) {
            merged[k++] = values[left++];
        }
        while (right < hi) {
            merged[k++] = values[right++];
        }
        for (int i = 0; i < merged.length; i++) {
            values[lo + i] = merged[i];
        }
        if (trace != null) {
            writeFrame(arrayText(values), "[]", indexRange(lo, hi - 1), "[]", range,
                    "These values are now merged into order.");
        }
    }

    // Merge sort. A run of one value is already sorted. Otherwise the run is split into two halves, each half is sorted
    // by the same method, and the two sorted halves are merged. Every level of splitting halves the work.
    static void mergeSort(int[] values, int lo, int hi) {
        if (hi - lo <= 1) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        if (trace != null) {
            writeFrame(arrayText(values), "[]", "[]", "[]", indexRange(lo, hi - 1),
                    "Split these " + (hi - lo) + " values into two halves.");
        }
        mergeSort(values, lo, mid);
        mergeSort(values, mid, hi);
        merge(values, lo, mid, hi);
    }

    public static void main(String[] args) throws IOException {
        // A short list for the visualizer. It holds the values 1 through 20, each once, in a scrambled order. It is
        // non-negative, so each bar's height matches its value.
        int[] small = {14, 3, 19, 7, 1, 12, 20, 5, 16, 9, 2, 17, 6, 11, 18, 4, 13, 8, 15, 10};
        trace = new StringBuilder();
        trace.append("window.trace = {\n");
        trace.append("  \"title\": \"Merge sort\",\n");
        trace.append("  \"frames\": [\n");
        mergeSort(small, 0, small.length);
        writeFrame(arrayText(small), "[]", "[]", indexRange(0, small.length - 1), "[]",
                "Sorted. " + comparisons + " comparisons.");
        trace.append("\n  ]\n};\n");
        Files.writeString(Path.of("trace.js"), trace.toString());
        trace = null;

        System.out.println("Visualized list: " + small.length + " values");
        StringBuilder sorted = new StringBuilder("Sorted:");
        for (int value : small) {
            sorted.append(" ").append(value);
        }
        System.out.println(sorted);
        System.out.println("Comparisons: " + comparisons);

        // A long list of every whole number from -1,000 to 1,000, in a shuffled order. It is counted, not drawn.
        int[] timing = makeSortData(1000);
        comparisons = 0;
        mergeSort(timing, 0, timing.length);

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
        System.out.println("Comparisons: " + withCommas(comparisons));
        System.out.println("Sorted correctly: " + (sortedCorrectly ? "yes" : "no"));

        // Try: sort the values 1 through 20 in order, and predict the comparison count before you press Run.
    }
}
