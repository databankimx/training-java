record Rectangle(int width, int height) {
    Rectangle {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("size must be positive");
        }
    }

    int area() {
        return width * height;
    }
}

public class Main {
    public static void main(String[] args) {
        Rectangle box = new Rectangle(3, 4);
        System.out.println("Area of 3 by 4: " + box.area());

        try {
            Rectangle broken = new Rectangle(0, 5);
            System.out.println("never printed " + broken);
        } catch (IllegalArgumentException error) {
            System.out.println("Rejected: " + error.getMessage());
        }
    }
}
