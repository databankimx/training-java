public class Main {
    // The golden ratio, phi = (1 + sqrt(5)) / 2.
    static final double PHI = (1 + Math.sqrt(5)) / 2;

    // The Fibonacci number F(n) from the closed-form formula. No recursion and no loop are needed, but the result uses
    // floating-point arithmetic, so it's only approximate for large n.
    static long fibonacciFormula(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        double psi = 1 - PHI;
        return Math.round((Math.pow(PHI, n) - Math.pow(psi, n)) / (PHI - psi));
    }

    // The loop version, which is exact, used as the reference for checking the formula.
    static long fibonacciLoop(int n) {
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
        long formula = fibonacciFormula(n);
        long loop = fibonacciLoop(n);
        System.out.println("Formula: F(" + n + ") = " + String.format("%,d", formula));
        System.out.println("Matches the loop: " + (formula == loop));

        // At larger n, the rounding error in the formula can make the answers differ.
        int larger = 80;
        System.out.println("Formula: F(" + larger + ") = " + String.format("%,d", fibonacciFormula(larger)));
        System.out.println("Loop:    F(" + larger + ") = " + String.format("%,d", fibonacciLoop(larger)));
    }
}
