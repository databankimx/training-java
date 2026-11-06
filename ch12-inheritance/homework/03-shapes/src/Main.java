class Shape {
    double area() {
        return 0.0;
    }
}

class Rectangle extends Shape {
    private final double width;
    private final double height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    double area() {
        return width * height;
    }
}

class Circle extends Shape {
    private final double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return 3.14159 * radius * radius;
    }
}

public class Main {
    static void printArea(Shape shape) {
        System.out.printf("Area: %.2f%n", shape.area());
    }

    public static void main(String[] args) {
        printArea(new Rectangle(3, 2));
        printArea(new Circle(1));
        printArea(new Shape());
    }
}
