public class Main {
    // Removes everything except letters, and converts the rest to lower case.
    static String cleanText(String text) {
        StringBuilder clean = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                clean.append(Character.toLowerCase(c));
            }
        }
        return clean.toString();
    }

    // The easy way: a palindrome is the same forwards and backwards.
    static boolean isPalindromeSimple(String text) {
        String clean = cleanText(text);
        return clean.equals(new StringBuilder(clean).reverse().toString());
    }

    // The recursive way. A text of fewer than two characters is a palindrome. Otherwise the first and last characters
    // must match, and the middle must be a palindrome.
    static boolean isPalindromeRecursive(String text) {
        if (text.length() < 2) {
            return true;
        }
        return text.charAt(0) == text.charAt(text.length() - 1)
                && isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    // The same check with a loop. Two indexes move in from the ends, instead of copying the middle of the text.
    static boolean isPalindromeIterative(String text) {
        int low = 0;
        int high = text.length() - 1;
        while (low < high) {
            if (text.charAt(low) != text.charAt(high)) {
                return false;
            }
            low++;
            high--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(cleanText("Madam, I'm Adam."));

        String[] words = {"foo", "race car", "boot", "toot", "Madam, I'm Adam."};
        System.out.println("Recursive:");
        for (String word : words) {
            String infix = isPalindromeRecursive(cleanText(word)) ? "" : "not ";
            System.out.println("- '" + word + "' is " + infix + "a palindrome");
        }

        System.out.println("Iterative:");
        for (String word : words) {
            String infix = isPalindromeIterative(cleanText(word)) ? "" : "not ";
            System.out.println("- '" + word + "' is " + infix + "a palindrome");
        }
    }
}
