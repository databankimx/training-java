import java.util.Optional;

public class Main {
    // The quotient when b is not zero, and an empty Optional when it is.
    static Optional<Integer> safeDivide(int a, int b) {
        if (b == 0) {
            return Optional.empty();
        }
        return Optional.of(a / b);
    }

    static void report(int a, int b) {
        Optional<Integer> result = safeDivide(a, b);
        if (result.isPresent()) {
            System.out.println(a + " / " + b + " = " + result.get());
        } else {
            System.out.println(a + " / " + b + " = no answer");
        }
    }

    public static void main(String[] args) {
        report(10, 2);
        report(1, 0);
        report(9, 3);
    }
}
