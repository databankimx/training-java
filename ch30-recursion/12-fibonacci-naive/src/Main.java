public class Main {
    // The number of calls made to fibonacci. Reset it before each test.
    static long calls = 0;

    // The Fibonacci number F(n), translated directly from its formula. F(0) and F(1) are the base cases.
    static long fibonacci(int n) {
        calls++;
        if (n == 0 || n == 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        int n = 40;
        calls = 0;
        long result = fibonacci(n);
        System.out.println("Calculated F(" + n + ") = " + String.format("%,d", result));
        System.out.println("calls = " + String.format("%,d", calls));
    }
}
