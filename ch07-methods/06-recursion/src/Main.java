public class Main {
    public static void main(String[] args) {
        System.out.println("countdown from 3:");
        countdown(3);
        System.out.println("factorial(5) = " + factorial(5));
    }

    static void countdown(int n) {
        if (n == 0) {
            System.out.println("Liftoff");
            return;
        }
        System.out.println(n);
        countdown(n - 1);
    }

    static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
