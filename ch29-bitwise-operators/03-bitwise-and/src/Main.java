public class Main {
    // Shows the lowest 8 bits of a value, with leading zeros, so the patterns line up.
    static String bits8(int value) {
        return String.format("%8s", Integer.toBinaryString(value & 0xFF)).replace(' ', '0');
    }

    // The ones place is the only odd power of two, so the lowest bit is 1 exactly when the number is odd.
    static boolean isEvenWithModulo(int n) {
        return n % 2 == 0;
    }

    static boolean isEvenWithAnd(int n) {
        return (n & 1) == 0;
    }

    public static void main(String[] args) {
        int a = 0b10011100;   // 156
        int b = 0b00110100;   // 52
        System.out.println("a: " + bits8(a) + " = " + a);
        System.out.println("b: " + bits8(b) + " = " + b);
        System.out.println("a & b: " + bits8(a & b) + " = " + (a & b));

        int[] numbers = {42, 73, 0, 1000, -7};
        for (int n : numbers) {
            System.out.println("Using n % 2, " + n + " is " + (isEvenWithModulo(n) ? "even" : "odd"));
            System.out.println("Using n & 1, " + n + " is " + (isEvenWithAnd(n) ? "even" : "odd"));
        }
    }
}
