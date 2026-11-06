public class Main {
    // base raised to the power exp, for exp of zero or more. The base case is exp == 0, which gives 1.
    static long power(long base, int exp) {
        if (exp == 0) {
            return 1;
        }
        return base * power(base, exp - 1);
    }

    public static void main(String[] args) {
        System.out.println("2^10 = " + power(2, 10));
        System.out.println("3^4 = " + power(3, 4));
        System.out.println("5^0 = " + power(5, 0));
    }
}
