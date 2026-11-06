public class Main {
    public static void main(String[] args) {
        System.out.printf("%.2f C is %.2f F%n", 0.0, TemperatureConverter.celsiusToFahrenheit(0));
        System.out.printf("%.2f C is %.2f F%n", 100.0, TemperatureConverter.celsiusToFahrenheit(100));
        System.out.printf("%.2f C is %.2f F%n", -40.0, TemperatureConverter.celsiusToFahrenheit(-40));
        System.out.printf("%.2f F is %.2f C%n", 212.0, TemperatureConverter.fahrenheitToCelsius(212));
        System.out.printf("%.2f F is %.2f C%n", 32.0, TemperatureConverter.fahrenheitToCelsius(32));
    }
}
