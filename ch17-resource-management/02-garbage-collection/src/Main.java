public class Main {
    static class Node {
        final String label;

        Node(String label) {
            this.label = label;
            System.out.println("Create " + label);
        }
    }

    public static void main(String[] args) {
        Node first = new Node("first");
        Node second = first;
        System.out.println("Both refer to: " + second.label);

        first = null;
        System.out.println("second still refers to: " + second.label);

        second = null;
        System.out.println("No references left; the collector may free the object at any time.");
    }
}
