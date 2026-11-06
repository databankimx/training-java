public class Main {
    static long linearOperations(int n) {
        long count = 0;
        for (int i = 0; i < n; i++) {
            count++;
        }
        return count;
    }

    static long quadraticOperations(int n) {
        long count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] sizes = {10, 100, 1000};
        for (int n : sizes) {
            System.out.printf("n = %,d: linear %,d, quadratic %,d%n", n, linearOperations(n), quadraticOperations(n));
        }
    }
}
