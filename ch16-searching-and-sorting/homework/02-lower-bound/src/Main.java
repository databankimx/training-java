public class Main {
    // The index of the first value that is not smaller than target, or the length when every value is smaller.
    // The list must be sorted. The range [low, high) shrinks until it is empty, and low is the answer.
    static int lowerBound(int[] values, int target) {
        int low = 0;
        int high = values.length;
        while (low < high) {
            int middle = (low + high) / 2;
            if (values[middle] < target) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }
        return low;
    }

    // The same answer found by scanning from the start. Used only to check lowerBound.
    static int linearLowerBound(int[] values, int target) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] >= target) {
                return i;
            }
        }
        return values.length;
    }

    public static void main(String[] args) {
        int[] values = {1, 3, 3, 3, 5, 8};
        int[] targets = {3, 4, 9};
        boolean matches = true;
        for (int target : targets) {
            int answer = lowerBound(values, target);
            System.out.println("lowerBound(" + target + "): " + answer);
            if (answer != linearLowerBound(values, target)) {
                matches = false;
            }
        }
        System.out.println("Matches linear scan: " + (matches ? "yes" : "no"));
    }
}
