public class Main {
    // Counts the lowercase vowels a, e, i, o, and u in a word.
    static int countVowels(String word) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if ("aeiou".indexOf(word.charAt(i)) >= 0) {
                count++;
            }
        }
        return count;
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
        check(countVowels("hello") == 2, "hello has 2 vowels");
        check(countVowels("") == 0, "an empty word has no vowels");
        check(countVowels("rhythm") == 0, "rhythm has no vowels");
        check(countVowels("education") == 5, "education has 5 vowels");

        System.out.println("Failures: " + failures);
    }
}
