import java.util.regex.Pattern;

public class Main {
    // Prints each phone number that the pattern matches, under a line showing the pattern.
    static void tryPattern(String pattern, String[] phones) {
        Pattern regex = Pattern.compile(pattern);
        System.out.println("Pattern: " + pattern);
        for (String phone : phones) {
            if (regex.matcher(phone).find()) {
                System.out.println("  matches: " + phone);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String[] phones = {
            "7134833111",
            "713-483-3111",
            "713.483.3111",
            "713,483,3111",
            "(713) 483-3111",
            "(713) 483-3111 ext: 42",
            "Don't match this one: (713) 483-3111 - BAD"
        };

        // Each attempt improves on the one before it.
        tryPattern("\\d{10}", phones);
        tryPattern("\\d{3}-\\d{3}-\\d{4}", phones);
        tryPattern("\\d{3}[-.,]\\d{3}[-.,]\\d{4}", phones);
        tryPattern("\\(?\\d{3}\\)?\\s?[-.,]?\\d{3}[-.,]?\\d{4}", phones);
        tryPattern("^\\(?\\d{3}\\)?\\s?[-.,]?\\d{3}[-.,]?\\d{4}$", phones);
        tryPattern("^\\(?\\d{3}\\)?\\s?[-.,]?\\d{3}[-.,]?\\d{4}\\s?e?x?t?:?\\s?\\d*$", phones);
    }
}
