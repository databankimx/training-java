public class Main {
    // Adds one to the shared total, the given number of times. The lock is taken for each addition.
    static void addMany(int[] total, Object lock, int times) {
        for (int i = 0; i < times; i++) {
            synchronized (lock) {
                total[0]++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int[] total = {0};
        Object lock = new Object();

        Thread first = new Thread(() -> addMany(total, lock, 100000));
        Thread second = new Thread(() -> addMany(total, lock, 100000));
        first.start();
        second.start();
        first.join();
        second.join();

        System.out.println("Total: " + total[0]);
    }
}
