import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        LocalDate launch = LocalDate.of(2024, 3, 15);
        LocalDate christmas = LocalDate.of(2024, 12, 25);

        // The number of whole days from one date to the other.
        long daysBetween = ChronoUnit.DAYS.between(launch, christmas);
        System.out.println("Days from launch to Christmas: " + daysBetween);

        // A Duration is a length of time measured in seconds, which can be shown in hours and minutes.
        Duration length = Duration.ofHours(5).plusMinutes(30);
        System.out.println("Length: " + length);

        // Subtracting one date-time from another gives the duration between them.
        LocalDateTime start = LocalDateTime.of(2024, 3, 15, 9, 0);
        LocalDateTime end = LocalDateTime.of(2024, 3, 15, 14, 30);
        System.out.println("Time between: " + Duration.between(start, end).toMinutes() + " minutes");
    }
}
