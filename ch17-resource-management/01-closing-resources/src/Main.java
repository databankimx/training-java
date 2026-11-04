public class Main {
    // A resource that must be closed when the program is finished with it. AutoCloseable lets try-with-resources close it.
    static class Resource implements AutoCloseable {
        private final String name;

        Resource(String name) {
            this.name = name;
            System.out.println("Open " + name);
        }

        void use() {
            System.out.println("Use " + name);
        }

        @Override
        public void close() {
            System.out.println("Close " + name);
        }
    }

    static void work(boolean fail) {
        try (Resource file = new Resource("file A")) {
            file.use();
            if (fail) {
                throw new IllegalStateException("something went wrong");
            }
            System.out.println("Finished normally");
        }
    }

    public static void main(String[] args) {
        work(false);
        try {
            work(true);
        } catch (IllegalStateException error) {
            System.out.println("Caught: " + error.getMessage());
        }
    }
}
