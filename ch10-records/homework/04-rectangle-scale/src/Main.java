record Rectangle(double width, double height) {
    Rectangle() {
        this(1.0, 1.0);
    }

    Rectangle(double width) {
        this(width, 1.0);
    }
}

public class Main {
    static double area(Rectangle r) {
        return r.width() * r.height();
    }

    static Rectangle scale(Rectangle r, double factor) {
        return new Rectangle(r.width() * factor, r.height() * factor);
    }

    public static void main(String[] args) {
        Rectangle r = new Rectangle(3.0, 2.0);
        System.out.printf("Area: %.2f%n", area(r));

        r = scale(r, 2.0);
        System.out.printf("Area: %.2f%n", area(r));
    }
}
