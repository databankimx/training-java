import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        // A date and a time together, with no time zone attached.
        LocalDateTime meeting = LocalDateTime.of(2024, 3, 15, 14, 30);

        // The US date first, then the time on a 24-hour clock.
        DateTimeFormatter us = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm", Locale.US);
        System.out.println(meeting.format(us));

        // The weekday name, the month name, and the time.
        DateTimeFormatter spoken = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy 'at' HH:mm", Locale.US);
        System.out.println(meeting.format(spoken));
    }
}
