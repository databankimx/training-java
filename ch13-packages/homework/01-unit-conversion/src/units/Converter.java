package units;

public class Converter {
    private Converter() {
    }

    public static double celsiusToFahrenheit(double celsius) {
        return celsius * 9 / 5 + 32;
    }

    public static double kilometersToMiles(double kilometers) {
        return kilometers * 0.621371;
    }
}
