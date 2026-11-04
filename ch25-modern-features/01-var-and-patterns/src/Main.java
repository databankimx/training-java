public class Main {
    record Point(int x, int y) {
    }

    // A switch expression picks a result by the shape of the value. Record patterns name the parts of a Point.
    static String describe(Object value) {
        return switch (value) {
            case Point(int x, int y) -> "point with sum " + (x + y);
            case String text -> "text " + text;
            default -> "other";
        };
    }

    public static void main(String[] args) {
        // var lets the compiler work out each type from the value on the right.
        var count = 42;
        var name = "Ada";
        var scores = new int[] {1, 2, 3};
        System.out.println(count + " " + name + " " + scores.length);

        // An instanceof with a record pattern checks the type and names the parts in one step.
        Object shape = new Point(3, 4);
        if (shape instanceof Point(int x, int y)) {
            System.out.println("x=" + x + " y=" + y);
        }

        System.out.println(describe(new Point(3, 4)));
        System.out.println(describe("hi"));
        System.out.println(describe(5));
    }
}
