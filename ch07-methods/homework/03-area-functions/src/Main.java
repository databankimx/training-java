import java.util.Scanner;

public class Main {
    static double rectangleArea(double width, double height) {
        return width * height;
    }

    static double circleArea(double radius) {
        return 3.14159 * radius * radius;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a width: ");
        System.out.flush();
        double width = input.nextDouble();

        System.out.print("Enter a height: ");
        System.out.flush();
        double height = input.nextDouble();

        System.out.print("Enter a radius: ");
        System.out.flush();
        double radius = input.nextDouble();

        System.out.printf("Rectangle area: %.2f%n", rectangleArea(width, height));
        System.out.printf("Circle area: %.2f%n", circleArea(radius));
    }
}
