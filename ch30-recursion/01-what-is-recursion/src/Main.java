public class Main {
    static int calls = 0;

    // A method that calls itself with no base case. It can never finish, so the call stack runs out.
    static void recursionOverflow() {
        calls++;
        recursionOverflow();
    }

    public static void main(String[] args) {
        try {
            recursionOverflow();
        } catch (StackOverflowError error) {
            System.out.println("Recursion stopped with a StackOverflowError");
            System.out.println("More than 1000 calls were made: " + (calls > 1000));
        }
    }
}
