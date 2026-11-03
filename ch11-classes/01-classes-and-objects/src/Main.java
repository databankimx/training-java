class Counter {
    int count;

    void increment() {
        count++;
    }

    int current() {
        return count;
    }
}

public class Main {
    public static void main(String[] args) {
        Counter clicks = new Counter();
        Counter visits = new Counter();

        clicks.increment();
        clicks.increment();
        visits.increment();

        System.out.println("clicks: " + clicks.current());
        System.out.println("visits: " + visits.current());
    }
}
