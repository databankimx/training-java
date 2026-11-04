class Counter {
    private int count;

    Counter() {
        this(0);
    }

    Counter(int startingCount) {
        count = startingCount;
    }

    void increment() {
        count++;
    }

    void reset() {
        count = 0;
    }

    int value() {
        return count;
    }
}

public class Main {
    public static void main(String[] args) {
        Counter c = new Counter();
        c.increment();
        c.increment();
        c.increment();
        System.out.println("Count: " + c.value());

        c.reset();
        System.out.println("Count: " + c.value());

        Counter second = new Counter(10);
        second.increment();
        System.out.println("Count: " + second.value());
    }
}
