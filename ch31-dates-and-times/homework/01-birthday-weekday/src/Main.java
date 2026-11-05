import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        // A birthday in 2026, and the weekday it falls on.
        LocalDate birthday = LocalDate.of(2026, 7, 4);
        DateTimeFormatter us = DateTimeFormatter.ofPattern("M/d/yyyy", Locale.US);
        DateTimeFormatter weekday = DateTimeFormatter.ofPattern("EEEE", Locale.US);

        System.out.println("Birthday: " + birthday.format(us));
        System.out.println("Weekday: " + birthday.format(weekday));

        // The date 100 days later, and its weekday.
        LocalDate later = birthday.plusDays(100);
        System.out.println("100 days later: " + later.format(us) + " (" + later.format(weekday) + ")");
    }
}
