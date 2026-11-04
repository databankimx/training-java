public class Main {
    // Runs one piece of code, and reports either success or the kind of exception it threw.
    static void report(String label, Runnable action) {
        try {
            action.run();
            System.out.println(label + ": no error");
        } catch (RuntimeException error) {
            System.out.println(label + ": " + error.getClass().getSimpleName());
        }
    }

    public static void main(String[] args) {
        int[] values = {1, 2, 3};
        String text = null;
        Object item = "hello";

        report("Index", () -> System.out.println(values[5]));
        report("Null", () -> System.out.println(text.length()));
        report("Divide", () -> System.out.println(10 / (values[0] - 1)));
        report("Cast", () -> {
            Integer number = (Integer) item;
            System.out.println(number);
        });
    }
}
