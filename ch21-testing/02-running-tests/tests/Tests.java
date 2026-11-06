public class Tests {
    static int failures = 0;

    static void check(boolean passed) {
        if (!passed) {
            failures++;
        }
    }

    public static void main(String[] args) {
        check(Main.clamp(5, 0, 10) == 5);
        check(Main.clamp(-3, 0, 10) == 0);
        check(Main.clamp(15, 0, 10) == 10);
        check(Main.clamp(0, 0, 10) == 0);
        check(Main.clamp(10, 0, 10) == 10);

        System.out.println("Failures: " + failures);
        // The exit code is the test result: 0 means every check passed, and anything else means one failed.
        System.exit(failures == 0 ? 0 : 1);
    }
}
