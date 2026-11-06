import java.util.HashMap;
import java.util.Map;

public class Main {
    static long waysNaiveHelper(int n, long[] calls) {
        calls[0]++;
        if (n < 2) {
            return 1;
        }
        return waysNaiveHelper(n - 1, calls) + waysNaiveHelper(n - 2, calls);
    }

    static long waysNaive(int n, long[] calls) {
        if (n < 0) {
            throw new IllegalArgumentException("n must not be negative");
        }
        return waysNaiveHelper(n, calls);
    }

    static long waysMemoHelper(int n, Map<Integer, Long> memo, long[] calls) {
        calls[0]++;
        if (n < 2) {
            return 1;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        long result = waysMemoHelper(n - 1, memo, calls) + waysMemoHelper(n - 2, memo, calls);
        memo.put(n, result);
        return result;
    }

    static long waysMemo(int n, Map<Integer, Long> memo, long[] calls) {
        if (n < 0) {
            throw new IllegalArgumentException("n must not be negative");
        }
        return waysMemoHelper(n, memo, calls);
    }

    public static void main(String[] args) {
        int[] sizes = {5, 10};
        for (int n : sizes) {
            long[] naiveCalls = {0};
            long naive = waysNaive(n, naiveCalls);

            long[] memoCalls = {0};
            long memoized = waysMemo(n, new HashMap<>(), memoCalls);

            System.out.printf("n = %d: ways %,d, naive calls %,d, memoized calls %,d%n",
                    n, naive, naiveCalls[0], memoCalls[0]);
        }
    }
}
