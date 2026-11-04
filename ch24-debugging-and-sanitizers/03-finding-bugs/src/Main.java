public class Main {
    // Adds the first count values. The loop runs from index 0 up to, but not including, count.
    static int sumFirst(int[] values, int count) {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += values[i];
        }
        return total;
    }

    public static void main(String[] args) {
        int[] values = {4, 8, 15, 16};
        System.out.println("Sum of first 3: " + sumFirst(values, 3));
        System.out.println("Sum of first 4: " + sumFirst(values, 4));
    }
}
