public class Main {
    static int safeDivide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("the divisor must not be zero");
        }
        return a / b;
    }

    public static void main(String[] args) {
        try {
            int first = safeDivide(10, 2);
            System.out.println("10 / 2 = " + first);

            int second = safeDivide(10, 0);
            System.out.println("10 / 0 = " + second);
        } catch (RuntimeException error) {
            System.out.println("Error: " + error.getMessage());
        }
    }
}
