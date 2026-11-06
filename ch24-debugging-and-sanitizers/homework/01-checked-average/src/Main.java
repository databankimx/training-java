public class Main {
    // The average of a non-empty array. The caller must never pass an empty array.
    static double average(int[] values) {
        assert values.length > 0 : "average needs at least one value";
        long total = 0;
        for (int value : values) {
            total += value;
        }
        return (double) total / values.length;
    }

    public static void main(String[] args) {
        System.out.printf("Average: %.2f%n", average(new int[] {2, 4, 6, 8}));
    }
}
