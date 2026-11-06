public class Main {
    // Limits a value to the range from low to high.
    static int clamp(int value, int low, int high) {
        if (value < low) {
            return low;
        }
        if (value > high) {
            return high;
        }
        return value;
    }

    public static void main(String[] args) {
        System.out.println("clamp(15, 0, 10) = " + clamp(15, 0, 10));
        System.out.println("clamp(-3, 0, 10) = " + clamp(-3, 0, 10));
    }
}
