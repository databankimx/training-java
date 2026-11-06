public class Main {
    static int add(int a, int b) {
        return a + b;
    }

    // Prints one line for the check, and counts the failures. The count is a field, so every check adds to it.
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
        check(add(2, 3) == 5, "add 2 and 3");
        check(add(-4, 4) == 0, "add -4 and 4");
        check(add(0, 0) == 0, "add 0 and 0");

        // The last check is wrong on purpose. The expected answer, 6, is not what add returns for 2 and 3.
        check(add(2, 3) == 6, "add 2 and 3 expects 6");

        System.out.println("Failures: " + failures);
    }
}
