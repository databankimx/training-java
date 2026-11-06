public class Main {
    // Counts the values that are at least low and at most high. Both ends are included.
    static int countInRange(int[] values, int low, int high) {
        int count = 0;
        for (int value : values) {
            if (value >= low && value <= high) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] values = {1, 2, 3, 4, 5};
        System.out.println("Count between 2 and 4: " + countInRange(values, 2, 4));
        System.out.println("Count between 1 and 5: " + countInRange(values, 1, 5));
    }
}
