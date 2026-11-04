public class Main {
    // A guard whose close method undoes something when the try block ends, whether it ends normally or with an exception.
    static class Guard implements AutoCloseable {
        private final String name;

        Guard(String name) {
            this.name = name;
            System.out.println("Enter " + name);
        }

        @Override
        public void close() {
            System.out.println("Leave " + name);
        }
    }

    static void process(boolean fail) {
        try (Guard section = new Guard("section")) {
            System.out.println("Working");
            if (fail) {
                throw new RuntimeException("work failed");
            }
            System.out.println("Finished working");
        }
    }

    public static void main(String[] args) {
        try {
            process(false);
            process(true);
        } catch (RuntimeException error) {
            System.out.println("Caught: " + error.getMessage());
        }
        System.out.println("End");
    }
}
