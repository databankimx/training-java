public class Main {
    // A power of two has exactly one bit set, so clearing its lowest set bit leaves zero.
    static boolean isPowerOfTwo(int value) {
        return value > 0 && (value & (value - 1)) == 0;
    }

    public static void main(String[] args) {
        int[] values = {1, 6, 16, 0, 1024, 1000};
        for (int value : values) {
            System.out.println(value + ": " + isPowerOfTwo(value));
        }
    }
}
