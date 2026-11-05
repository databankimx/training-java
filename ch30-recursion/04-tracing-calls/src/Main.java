public class Main {
    // The number of factorial calls on the call stack right now. Each call adds one, and each return removes one.
    static int depth = 0;

    // The factorial, with a trace. Each call prints when it starts and when it returns, indented by the depth, so the
    // calls show up as a stack on the screen.
    static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be a non-negative integer!");
        }

        String indent = "  ".repeat(depth);
        System.out.println(indent + "factorial(" + n + ") called...");

        depth++;
        long f = n < 2 ? 1 : n * factorial(n - 1);
        depth--;

        System.out.println(indent + "factorial(" + n + ") returns " + f + "...");
        return f;
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println("\n" + n + "! = " + factorial(n));
    }
}
