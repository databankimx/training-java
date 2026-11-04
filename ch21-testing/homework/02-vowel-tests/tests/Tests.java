public class Tests {
    static int failures = 0;

    static void check(boolean passed) {
        if (!passed) {
            failures++;
        }
    }

    public static void main(String[] args) {
        check(Main.countVowels("hello") == 2);
        check(Main.countVowels("") == 0);
        check(Main.countVowels("rhythm") == 0);
        check(Main.countVowels("education") == 5);

        System.out.println("Failures: " + failures);
        System.exit(failures == 0 ? 0 : 1);
    }
}
