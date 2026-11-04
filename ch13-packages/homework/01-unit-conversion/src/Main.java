import units.Converter;

public class Main {
    public static void main(String[] args) {
        System.out.printf("100 C is %.1f F%n", Converter.celsiusToFahrenheit(100));
        System.out.printf("10 km is %.2f miles%n", Converter.kilometersToMiles(10));
    }
}
