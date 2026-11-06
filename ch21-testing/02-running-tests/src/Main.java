public class Main {
    // Limits a value to the range from low to high. A value inside the range is returned unchanged.
    static int clamp(int value, int low, int high) {
        if (value < low) {
            return low;
        }
        if (value > high) {
            return high;
        }
        return value;
    }

    static int failures = 0;

    static void check(boolean passed, String name) {
        if (passed) {
            System.out.println("PASS " + name);
        } else {
            System.out.println("FAIL " + name);
            failures++;
        }
    }

    public static void main(String[] args) {
        check(clamp(5, 0, 10) == 5, "inside the range stays the same");
        check(clamp(-3, 0, 10) == 0, "below the range becomes the low end");
        check(clamp(15, 0, 10) == 10, "above the range becomes the high end");
        check(clamp(0, 0, 10) == 0, "the low end stays the same");
        check(clamp(10, 0, 10) == 10, "the high end stays the same");

        System.out.println("Failures: " + failures);
    }
}
