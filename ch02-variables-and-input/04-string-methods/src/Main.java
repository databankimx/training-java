public class Main {
    public static void main(String[] args) {
        String title = "  Java Basics  ";
        String trimmed = title.trim();

        System.out.println("Trimmed: [" + trimmed + "]");
        System.out.println("Upper: " + trimmed.toUpperCase());
        System.out.println("Length: " + trimmed.length());
        System.out.println("Contains Basics: " + trimmed.contains("Basics"));
        System.out.println("Index of Basics: " + trimmed.indexOf("Basics"));
        System.out.println("Substring: " + trimmed.substring(0, 4));
        System.out.println("Replaced: " + trimmed.replace("Basics", "Methods"));

        String[] words = trimmed.split(" ");
        System.out.println("Words: " + words.length);
        System.out.println("Joined: " + String.join("-", words));

        System.out.println("Equal ignoring case: " + "JAVA".equalsIgnoreCase("java"));
        System.out.println("Blank? " + "   ".isBlank());
        System.out.println("Repeat: " + "ab".repeat(3));
        System.out.println("Formatted: " + String.format("%-8s|%5.2f", "pi", 3.14159));
    }
}
