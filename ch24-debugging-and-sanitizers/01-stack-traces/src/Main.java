public class Main {
    static int divide(int a, int b) {
        return a / b;
    }

    static int compute(int a, int b) {
        return divide(a, b) + 1;
    }

    public static void main(String[] args) {
        try {
            System.out.println("Result: " + compute(10, 2));
            System.out.println("Result: " + compute(10, 0));
        } catch (ArithmeticException error) {
            System.out.println("Error: " + error.getMessage());
            System.out.println("Called through:");
            for (StackTraceElement frame : error.getStackTrace()) {
                if (frame.getClassName().equals("Main")) {
                    System.out.println("  " + frame.getMethodName());
                }
            }
        }
    }
}
