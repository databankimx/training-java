public class Main {
    public static void main(String[] args) {
        System.out.printf("Circle with radius 2: %.2f%n", area(2.0));
        System.out.printf("Rectangle 3 by 4: %.2f%n", area(3.0, 4.0));
    }

    static double area(double radius) {
        return Math.PI * radius * radius;
    }

    static double area(double width, double height) {
        return width * height;
    }
}
