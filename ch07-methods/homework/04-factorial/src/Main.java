public class Main {
    static int factorialRecursive(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorialRecursive(n - 1);
    }

    static int factorialLoop(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        for (int n = 0; n <= 10; n++) {
            int recursive = factorialRecursive(n);
            int loop = factorialLoop(n);
            String agreement = recursive == loop ? "(agree)" : "(disagree)";
            System.out.println(n + "! = " + recursive + " " + agreement);
        }
    }
}
