abstract class Shape {
    abstract double area();

    String describe() {
        return getClass().getSimpleName() + " with area " + String.format("%.2f", area());
    }
}

class Square extends Shape {
    private final double side;

    Square(double side) {
        this.side = side;
    }

    @Override
    double area() {
        return side * side;
    }
}

class Circle extends Shape {
    private final double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }
}

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {new Square(2), new Circle(1)};
        for (Shape shape : shapes) {
            System.out.println(shape.describe());
        }
    }
}
