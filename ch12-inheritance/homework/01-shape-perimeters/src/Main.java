abstract class Shape {
    abstract double perimeter();
}

class Rectangle extends Shape {
    private final double width;
    private final double height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    double perimeter() {
        return 2 * (width + height);
    }
}

class Circle extends Shape {
    private final double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double perimeter() {
        return 2 * Math.PI * radius;
    }
}

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {new Rectangle(3, 4), new Circle(1)};
        for (Shape shape : shapes) {
            System.out.printf("%s perimeter: %.2f%n", shape.getClass().getSimpleName(), shape.perimeter());
        }
    }
}
