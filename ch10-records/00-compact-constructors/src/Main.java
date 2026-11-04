record Temperature(double celsius) {
    Temperature {
        if (celsius < -273.15) {
            throw new IllegalArgumentException("below absolute zero: " + celsius);
        }
    }
}

record Point(int x, int y) {
}

public class Main {
    public static void main(String[] args) {
        Temperature room = new Temperature(21.5);
        System.out.println("room: " + room);

        Point a = new Point(3, 4);
        Point b = new Point(3, 4);
        System.out.println("a == b: " + (a == b));
        System.out.println("a.equals(b): " + a.equals(b));

        try {
            Temperature impossible = new Temperature(-300.0);
            System.out.println("never printed " + impossible);
        } catch (IllegalArgumentException error) {
            System.out.println("Rejected: " + error.getMessage());
        }
    }
}
