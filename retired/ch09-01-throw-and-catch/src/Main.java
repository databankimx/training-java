public class Main {
    static int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("divisor must not be zero");
        }
        return a / b;
    }

    public static void main(String[] args) {
        try {
            System.out.println("10 / 2 = " + divide(10, 2));
            System.out.println("10 / 0 = " + divide(10, 0));
            System.out.println("this line is skipped");
        } catch (IllegalArgumentException error) {
            System.out.println("Caught: " + error.getMessage());
        }
        System.out.println("program continues after the catch");
    }
}
