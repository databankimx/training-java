public class Main {
    // Adds the numbers from first up to, but not including, last, and stores the result in total[0].
    // Each thread is given its own array, so the threads share no data.
    static void sumRange(int first, int last, long[] total) {
        long sum = 0;
        for (int i = first; i < last; i++) {
            sum += i;
        }
        total[0] = sum;
    }

    public static void main(String[] args) throws InterruptedException {
        long[] firstHalf = {0};
        long[] secondHalf = {0};

        Thread first = new Thread(() -> sumRange(1, 501, firstHalf));
        Thread second = new Thread(() -> sumRange(501, 1001, secondHalf));
        first.start();
        second.start();
        first.join();
        second.join();

        System.out.println("Sum: " + (firstHalf[0] + secondHalf[0]));
    }
}
