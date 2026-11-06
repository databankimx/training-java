import java.util.ArrayList;
import java.util.List;

public class Main {
    // Adds each number from first up to, but not including, last. Each add happens while holding the lock.
    static void addValues(List<Integer> list, Object lock, int first, int last) {
        for (int value = first; value < last; value++) {
            synchronized (lock) {
                list.add(value);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        Object lock = new Object();

        Thread first = new Thread(() -> addValues(list, lock, 1, 6));
        Thread second = new Thread(() -> addValues(list, lock, 6, 11));
        first.start();
        second.start();
        first.join();
        second.join();

        int sum = 0;
        for (int value : list) {
            sum += value;
        }
        System.out.println("Size: " + list.size());
        System.out.println("Sum: " + sum);
    }
}
