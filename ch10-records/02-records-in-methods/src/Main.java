record Point(int x, int y) {
}

public class Main {
    static Point moveRight(Point p, int dx) {
        return new Point(p.x() + dx, p.y());
    }

    static void showInside(Point p) {
        System.out.println("inside: " + p);
    }

    public static void main(String[] args) {
        Point start = new Point(1, 2);
        Point end = moveRight(start, 5);
        System.out.println("start: " + start);
        System.out.println("end: " + end);

        showInside(start);
        System.out.println("start after: " + start);
    }
}
