import java.util.HashMap;
import java.util.Map;

public class Main {
    static long naiveCalls = 0;
    static long memoCalls = 0;
    static long iterCalls = 0;
    static long formulaCalls = 0;

    static final Map<Integer, Long> memo = new HashMap<>(Map.of(0, 0L, 1, 1L));
    static final double PHI = (1 + Math.sqrt(5)) / 2;

    static long naive(int n) {
        naiveCalls++;
        if (n == 0 || n == 1) {
            return n;
        }
        return naive(n - 1) + naive(n - 2);
    }

    static long memoized(int n) {
        memoCalls++;
        Long known = memo.get(n);
        if (known != null) {
            return known;
        }
        long value = memoized(n - 1) + memoized(n - 2);
        memo.put(n, value);
        return value;
    }

    static long iterative(int n) {
        iterCalls++;
        long a = 0;
        long b = 1;
        for (int i = 0; i < n; i++) {
            long next = a + b;
            a = b;
            b = next;
        }
        return a;
    }

    static long formula(int n) {
        formulaCalls++;
        if (n == 0 || n == 1) {
            return n;
        }
        double psi = 1 - PHI;
        return Math.round((Math.pow(PHI, n) - Math.pow(psi, n)) / (PHI - psi));
    }

    public static void main(String[] args) {
        int n = 40;
        System.out.println("Algorithm, F(" + n + "), and the function calls it made:");
        System.out.println();

        long value = naive(n);
        System.out.println("Naive recursive:    F(40) = " + String.format("%,d", value)
                + ", calls = " + String.format("%,d", naiveCalls));

        value = memoized(n);
        System.out.println("Memoized recursive: F(40) = " + String.format("%,d", value)
                + ", calls = " + String.format("%,d", memoCalls));

        value = iterative(n);
        System.out.println("Iterative:          F(40) = " + String.format("%,d", value)
                + ", calls = " + String.format("%,d", iterCalls));

        value = formula(n);
        System.out.println("Formula-based:      F(40) = " + String.format("%,d", value)
                + ", calls = " + String.format("%,d", formulaCalls));
    }
}
