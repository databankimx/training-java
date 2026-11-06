public class Main {
    static void greet(String name) {
        System.out.println("Hello from " + name);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Start");

        Thread worker = new Thread(() -> greet("worker"));
        worker.start();
        worker.join();

        System.out.println("After join");
    }
}
