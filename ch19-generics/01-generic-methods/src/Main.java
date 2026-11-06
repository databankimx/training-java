public class Main {
    // A generic method. T stands for any type that can be compared with another value of its own type.
    static <T extends Comparable<T>> T largerOf(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }

    public static void main(String[] args) {
        System.out.println(largerOf(3, 7));
        System.out.println(largerOf(2.5, 1.5));
        System.out.println(Main.<String>largerOf("apple", "banana"));
    }
}
