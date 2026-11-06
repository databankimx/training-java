public class Main {
    public static void main(String[] args) {
        long start = System.nanoTime();

        long sum = 0;
        for (int i = 1; i <= 1_000_000; i++) {
            sum += i;
        }

        long elapsed = System.nanoTime() - start;
        System.out.println("sum: " + sum);
        System.out.println("elapsed is not negative: " + (elapsed >= 0));
    }
}
