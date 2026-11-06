import java.util.ArrayList;
import java.util.List;

public class Main {
    // The Fibonacci number F(n), computed with a loop. The list keeps every value, as the recursive memo did.
    static long fibonacciList(int n) {
        List<Long> values = new ArrayList<>(List.of(0L, 1L));
        for (int i = 2; i <= n; i++) {
            values.add(values.get(i - 1) + values.get(i - 2));
        }
        return values.get(n);
    }

    // The same result, keeping only the last two values. This uses the same amount of memory for any n.
    static long fibonacciTwoValues(int n) {
        long a = 0;
        long b = 1;
        for (int i = 0; i < n; i++) {
            long next = a + b;
            a = b;
            b = next;
        }
        return a;
    }

    public static void main(String[] args) {
        int n = 40;
        System.out.println("List version: F(" + n + ") = " + String.format("%,d", fibonacciList(n)));
        System.out.println("Two values:   F(" + n + ") = " + String.format("%,d", fibonacciTwoValues(n)));
    }
}
