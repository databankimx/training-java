public class Main {
    // The number of calls made to countdown. Reset it before each test, since it keeps counting across calls.
    static int calls = 0;

    // Counts down to zero, recursively. Returns true when the countdown completes, and false for a negative start.
    static boolean countdown(int n) {
        calls++;
        if (n < 0) {
            System.out.println("Cannot count down from a negative number!");
            return false;
        }
        if (n == 0) {
            System.out.println("Liftoff!");
            return true;
        }
        System.out.println(n);
        return countdown(n - 1);
    }

    public static void main(String[] args) {
        int n = 3;
        calls = 0;
        if (countdown(n)) {
            System.out.println("\nrecursions complete after " + calls + " calls...");
        } else {
            System.out.println("\nrecursions failed after " + calls + " calls...");
        }
    }
}
