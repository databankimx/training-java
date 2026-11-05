import java.util.function.IntToLongFunction;
import java.util.stream.LongStream;

public class Main {
    // Keeps every result, so the compiler can't remove the work as unused.
    static long sink = 0;

    static long factorialRec(int n) {
        if (n < 2) {
            return 1;
        }
        return n * factorialRec(n - 1);
    }

    static long factorialIter(int n) {
        long f = 1;
        for (int i = 2; i <= n; i++) {
            f *= i;
        }
        return f;
    }

    // The same product as a stream. reduce combines the numbers 1 to n with multiplication.
    static long factorialStream(int n) {
        return LongStream.rangeClosed(1, n).reduce(1, (x, y) -> x * y);
    }

    // Runs the function for n, r times, and returns the time taken in nanoseconds.
    static long checkPerformance(IntToLongFunction func, int n, int r) {
        long start = System.nanoTime();
        for (int i = 0; i < r; i++) {
            sink += func.applyAsLong(n);
        }
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) {
        int n = 10;
        int repeat = 1_000_000;

        System.out.println("Testing " + n + "! " + String.format("%,d", repeat) + " times for each algorithm...");
        System.out.println();

        // A short warm-up run lets the JVM compile each method before the timed run, so the timings are fair.
        checkPerformance(Main::factorialRec, n, repeat / 10);
        checkPerformance(Main::factorialIter, n, repeat / 10);
        checkPerformance(Main::factorialStream, n, repeat / 10);

        printTime("Recursive", checkPerformance(Main::factorialRec, n, repeat));
        printTime("Iterative", checkPerformance(Main::factorialIter, n, repeat));
        printTime("Using a stream reduce", checkPerformance(Main::factorialStream, n, repeat));

        System.out.println("(checksum " + sink + ")");
    }

    static void printTime(String name, long nanoseconds) {
        System.out.println(name + ":");
        System.out.println(nanoseconds / 1_000_000_000.0 + " sec");
        System.out.println();
    }
}
