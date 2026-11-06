import java.util.HashMap;
import java.util.Map;

public class Main {
    static long fibNaiveHelper(int n, long[] calls) {
        calls[0]++;
        if (n < 2) {
            return n;
        }
        return fibNaiveHelper(n - 1, calls) + fibNaiveHelper(n - 2, calls);
    }

    static long fibNaive(int n, long[] calls) {
        if (n < 0) {
            throw new IllegalArgumentException("n must not be negative");
        }
        return fibNaiveHelper(n, calls);
    }

    static long fibMemoHelper(int n, Map<Integer, Long> memo, long[] calls) {
        calls[0]++;
        if (n < 2) {
            return n;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        long result = fibMemoHelper(n - 1, memo, calls) + fibMemoHelper(n - 2, memo, calls);
        memo.put(n, result);
        return result;
    }

    static long fibMemo(int n, Map<Integer, Long> memo, long[] calls) {
        if (n < 0) {
            throw new IllegalArgumentException("n must not be negative");
        }
        return fibMemoHelper(n, memo, calls);
    }

    static long fibIterative(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must not be negative");
        }
        long previous = 0;
        long current = 1;
        for (int i = 0; i < n; i++) {
            long next = previous + current;
            previous = current;
            current = next;
        }
        return previous;
    }

    public static void main(String[] args) {
        int[] sizes = {10, 20, 30};
        for (int n : sizes) {
            long[] naiveCalls = {0};
            long naive = fibNaive(n, naiveCalls);

            long[] memoCalls = {0};
            long memoized = fibMemo(n, new HashMap<>(), memoCalls);

            long iterative = fibIterative(n);
            if (naive != memoized || naive != iterative) {
                throw new IllegalStateException("the three versions disagree");
            }
            System.out.printf("n = %d: value %,d, naive calls %,d, memoized calls %,d%n",
                    n, naive, naiveCalls[0], memoCalls[0]);
        }

        try {
            fibNaive(-1, new long[]{0});
        } catch (IllegalArgumentException error) {
            System.out.println("n = -1 refused: " + error.getMessage());
        }
    }
}
