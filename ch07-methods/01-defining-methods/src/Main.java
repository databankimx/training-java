public class Main {
    public static void main(String[] args) {
        greet();
        printSquare(4);
        System.out.println("Sum: " + add(2, 3));
    }

    static void greet() {
        System.out.println("Hello from a method.");
    }

    static void printSquare(int n) {
        System.out.println(n + " squared is " + (n * n));
    }

    static int add(int a, int b) {
        return a + b;
    }
}
