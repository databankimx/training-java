import java.util.HashMap;
import java.util.Map;

public class Main {
    // The memo: values already worked out, keyed by n. It starts with the two base cases.
    static final Map<Integer, Long> memo = new HashMap<>(Map.of(0, 0L, 1, 1L));

    // The number of calls made to fibonacci. Reset it before each test.
    static long calls = 0;

    // F(n), with each value stored in the memo the first time it's worked out. A value already in the memo is
    // returned at once, so the method doesn't repeat its calculation.
    static long fibonacci(int n) {
        calls++;
        Long known = memo.get(n);
        if (known != null) {
            return known;
        }
        long value = fibonacci(n - 1) + fibonacci(n - 2);
        memo.put(n, value);
        return value;
    }

    public static void main(String[] args) {
        int n = 40;
        calls = 0;
        long result = fibonacci(n);
        System.out.println("Calculated F(" + n + ") = " + String.format("%,d", result));
        System.out.println("calls = " + calls);

        // The memo keeps its values between calls, so a second request for F(40) is found at once.
        calls = 0;
        fibonacci(n);
        System.out.println("calls for a second request = " + calls);
    }
}
