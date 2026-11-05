import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        LocalDate today = LocalDate.of(2026, 11, 5);
        LocalDate newYear = LocalDate.of(2027, 1, 1);
        LocalDate leapDay = LocalDate.of(2028, 2, 29);

        System.out.println("Days until New Year: " + ChronoUnit.DAYS.between(today, newYear));
        System.out.println("Days until the leap day: " + ChronoUnit.DAYS.between(today, leapDay));
        System.out.println("Days since New Year 2026: " + ChronoUnit.DAYS.between(LocalDate.of(2026, 1, 1), today));
    }
}
