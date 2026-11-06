public class Main {
    static long countPairs(int n) {
        long count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                count++;
            }
        }
        return count;
    }

    static long pairFormula(int n) {
        return (long) n * (n - 1) / 2;
    }

    public static void main(String[] args) {
        int[] sizes = {4, 10, 100};
        for (int n : sizes) {
            System.out.printf("n = %d: pairs %,d, formula %,d%n", n, countPairs(n), pairFormula(n));
        }
    }
}
