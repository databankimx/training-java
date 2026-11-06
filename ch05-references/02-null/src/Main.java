public class Main {
    public static void main(String[] args) {
        int[] food = {1, 2, 3};
        int[] ref = food;
        int[] empty = null;

        System.out.println("food[0]: " + food[0]);
        System.out.println("ref[0]: " + ref[0]);

        if (empty == null) {
            System.out.println("The reference is null.");
        }

        empty = food;
        System.out.println("empty[0] after it is set: " + empty[0]);
    }
}
