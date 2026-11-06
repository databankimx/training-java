import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String receipt = "Apples $3.50, pears $2.25, and a melon for $4.00.";

        // A price is a dollar sign, one or more digits, a dot, and two digits. The digits are the first group.
        Pattern price = Pattern.compile("\\$(\\d+)\\.(\\d{2})");
        Matcher matcher = price.matcher(receipt);

        int cents = 0;
        while (matcher.find()) {
            int dollars = Integer.parseInt(matcher.group(1));
            int centPart = Integer.parseInt(matcher.group(2));
            cents += dollars * 100 + centPart;
        }
        System.out.printf("Total: %.2f%n", cents / 100.0);
    }
}
