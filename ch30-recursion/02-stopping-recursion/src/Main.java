public class Main {
    // Counts down from n to 1, then prints Liftoff. The check for zero is the base case: it returns without calling
    // the method again, so the recursion stops.
    static void countdown(int n) {
        if (n == 0) {
            System.out.println("Liftoff");
            return;
        }
        System.out.println(n);
        countdown(n - 1);
    }

    public static void main(String[] args) {
        countdown(3);
        System.out.println("A countdown from 0:");
        countdown(0);
    }
}
