public class Main {
    static int addFive(int x) {
        return 5 + x;
    }

    static int add(int x, int y) {
        return x + y;
    }

    static double add(double x, double y) {
        return x + y;
    }

    public static void main(String[] args) {
        int res = addFive(3);
        System.out.println(res);

        int whole = add(8, 5);
        double decimal = add(4.3, 6.26);
        System.out.println("Int: " + whole);
        System.out.printf("Double: %.2f%n", decimal);
    }
}
