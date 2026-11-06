import java.lang.ref.WeakReference;

public class Main {
    static class Node {
        final String label;

        Node(String label) {
            this.label = label;
            System.out.println("Create " + label);
        }
    }

    public static void main(String[] args) {
        Node shared = new Node("shared");
        Node another = shared;
        System.out.println("Same object: " + (shared == another));

        // A weak reference refers to the object without keeping it alive.
        WeakReference<Node> weak = new WeakReference<>(shared);
        System.out.println("Weak reference sees: " + weak.get().label);
    }
}
