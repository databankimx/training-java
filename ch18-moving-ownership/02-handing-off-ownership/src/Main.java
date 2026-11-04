public class Main {
    static class Resource {
        private final String name;

        Resource(String name) {
            this.name = name;
            System.out.println("Acquire " + name);
        }

        String name() {
            return name;
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

        boolean isEmpty() {
            return contents == null;
        }
    }

    public static void main(String[] args) {
        System.out.println("Start");
        Box a = new Box(new Resource("file E"));

        Box b = new Box(a.take());
        System.out.println("a holds nothing: " + a.isEmpty());
        System.out.println("b holds the resource: " + !b.isEmpty());
        System.out.println("End");
    }
}
