import java.math.BigInteger;

public class Main {
    // The factorial with a loop instead of recursion. The loop makes one pass per number, and uses one frame.
    static long iterativeFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be a non-negative integer!");
        }
        long f = 1;
        for (int i = 2; i <= n; i++) {
            f *= i;
        }
        return f;
    }

    // The same loop with BigInteger, which has no fixed size, so the result grows as large as memory allows.
    static BigInteger bigFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be a non-negative integer!");
        }
        BigInteger f = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            f = f.multiply(BigInteger.valueOf(i));
        }
        return f;
    }

    public static void main(String[] args) {
        for (int n = 0; n < 10; n++) {
            System.out.println(n + "! = " + iterativeFactorial(n));
        }

        BigInteger large = bigFactorial(1000);
        String digits = large.toString();
        System.out.println("1000! has " + digits.length() + " digits");
        System.out.println("It starts with " + digits.substring(0, 16));
    }
}
