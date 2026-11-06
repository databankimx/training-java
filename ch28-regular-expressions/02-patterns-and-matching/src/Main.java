import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        // matches checks the whole string against the pattern.
        System.out.println("Five digits: " + "12345".matches("\\d{5}"));
        System.out.println("Letters only: " + "Java".matches("[A-Za-z]+"));

        // find looks for each place the pattern appears in the text.
        String text = "Call 555-0101 or 555-0199 today.";
        Pattern phone = Pattern.compile("\\d{3}-\\d{4}");
        Matcher matcher = phone.matcher(text);
        while (matcher.find()) {
            System.out.println("Found " + matcher.group() + " at " + matcher.start());
        }
    }
}
