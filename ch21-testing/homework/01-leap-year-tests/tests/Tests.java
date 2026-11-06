public class Tests {
    static int failures = 0;

    static void check(boolean passed) {
        if (!passed) {
            failures++;
        }
    }

    public static void main(String[] args) {
        check(Main.isLeapYear(2024));
        check(!Main.isLeapYear(1900));
        check(Main.isLeapYear(2000));
        check(!Main.isLeapYear(2023));

        System.out.println("Failures: " + failures);
        System.exit(failures == 0 ? 0 : 1);
    }
}
