public class Main {
    static class Resource implements AutoCloseable {
        private final String name;

        Resource(String name) {
            this.name = name;
            System.out.println("Acquire " + name);
        }

        String name() {
            return name;
        }

        @Override
        public void close() {
            System.out.println("Release " + name);
        }
    }

    // A box holds at most one resource. Taking it hands the resource to the caller and leaves the box empty.
    static class Box {
        private Resource contents;

        Box(Resource contents) {
            this.contents = contents;
        }

        Resource take() {
            Resource taken = contents;
            contents = null;
            return taken;
        }
    }

    // consume becomes the owner of the resource. Its try header closes the resource when the method returns.
    static void consume(Box box) {
        try (Resource resource = box.take()) {
            if (resource != null) {
                System.out.println("Received " + resource.name());
            }
        }
    }

    public static void main(String[] args) {
        Box handoff = new Box(new Resource("handoff"));
        consume(handoff);
        System.out.println("Back in main");
        System.out.println("End");
    }
}
