import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a temperature in Celsius: ");
        System.out.flush();
        double celsius = input.nextDouble();

        final double fahrenheit = celsius * 9 / 5 + 32;
        System.out.printf("%.1f C is %.1f F%n", celsius, fahrenheit);
    }
}
