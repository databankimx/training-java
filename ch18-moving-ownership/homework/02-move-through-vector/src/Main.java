import java.util.ArrayList;
import java.util.List;

public class Main {
    static class Resource implements AutoCloseable {
        private final String name;

        Resource(String name) {
            this.name = name;
            System.out.println("Acquire " + name);
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

        boolean isEmpty() {
            return contents == null;
        }
    }

    public static void main(String[] args) {
        Box p = new Box(new Resource("vessel"));
        List<Box> holders = new ArrayList<>();

        // The resource moves into a new box in the list, and p is left empty.
        holders.add(new Box(p.take()));
        System.out.println("Size after push: " + holders.size());
        System.out.println("p owns nothing: " + (p.isEmpty() ? "yes" : "no"));

        // The first box gives its resource to a new box, and the emptied box is removed from the list.
        Box back = new Box(holders.get(0).take());
        holders.remove(holders.size() - 1);
        System.out.println("Got back: " + (back.isEmpty() ? "no" : "yes"));

        System.out.println("End");

        // The resource is closed when main ends, which is the last point the program still holds it.
        Resource last = back.take();
        if (last != null) {
            last.close();
        }
    }
}
