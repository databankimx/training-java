import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        // Parentheses make groups. Each group keeps the text it matched, so the parts can be read separately.
        Pattern date = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");
        Matcher matcher = date.matcher("Due 2026-11-05.");
        if (matcher.find()) {
            System.out.println("Year " + matcher.group(1) + " month " + matcher.group(2) + " day " + matcher.group(3));
        }

        // replaceAll returns a new string with every match replaced.
        String spaced = "  many   spaces here ";
        System.out.println("Cleaned: " + spaced.trim().replaceAll("\\s+", " "));
        System.out.println("Masked: " + "phone 555-0101".replaceAll("\\d", "#"));
    }
}
