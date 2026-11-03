public class Main {
    public static void main(String[] args) {
        System.out.println("add(2, 3): " + add(2, 3));
        System.out.println("add(2.5, 1.5): " + add(2.5, 1.5));
        System.out.println("add(1, 2, 3): " + add(1, 2, 3));
        System.out.println("greeting: " + greeting());
        System.out.println("greeting(Ada): " + greeting("Ada"));
    }

    static int add(int a, int b) {
        return a + b;
    }

    static double add(double a, double b) {
        return a + b;
    }

    static int add(int a, int b, int c) {
        return a + b + c;
    }

    static String greeting() {
        return "Hello, world.";
    }

    static String greeting(String name) {
        return "Hello, " + name + ".";
    }
}
