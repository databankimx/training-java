public class Main {
    static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
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
        check(isLeapYear(2024), "2024 is a leap year");
        check(!isLeapYear(1900), "1900 is not a leap year");
        check(isLeapYear(2000), "2000 is a leap year");
        check(!isLeapYear(2023), "2023 is not a leap year");

        System.out.println("Failures: " + failures);
    }
}
