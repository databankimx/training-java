public class Main {
    // n! is the product of the numbers from 1 to n. The formula n! = n * (n - 1)! gives the recursive case.
    // The base case covers 0 and 1, which both have the factorial 1.
    static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be a non-negative integer!");
        }
        if (n < 2) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        for (int n = 0; n < 10; n++) {
            System.out.println(n + "! = " + factorial(n));
        }

        // A long holds values up to about 9.2 * 10^18, which is enough for 20!.
        System.out.println("20! = " + factorial(20));

        try {
            System.out.println(factorial(-1));
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
        }
    }
}
