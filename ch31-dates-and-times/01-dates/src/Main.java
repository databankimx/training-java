import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        LocalDate launch = LocalDate.of(2024, 3, 15);

        // The US form puts the month first: M is the month, d the day, and yyyy the four-digit year.
        DateTimeFormatter us = DateTimeFormatter.ofPattern("M/d/yyyy", Locale.US);
        System.out.println(launch.format(us));

        // The full month name, as in a letter or a printed date.
        DateTimeFormatter written = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.US);
        System.out.println(launch.format(written));

        // Adding 30 days moves past the end of March, so the result is in April.
        System.out.println(launch.plusDays(30).format(us));
    }
}
