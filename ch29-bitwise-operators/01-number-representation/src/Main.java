public class Main {
    // Shows the lowest 8 bits of a value, with leading zeros, so the patterns line up.
    static String bits8(int value) {
        return String.format("%8s", Integer.toBinaryString(value & 0xFF)).replace(' ', '0');
    }

    public static void main(String[] args) {
        System.out.println("7 in 8 bits: " + bits8(7));
        System.out.println("-1 in 8 bits: " + bits8(-1));
        System.out.println("-128 in 8 bits: " + bits8(-128));

        // A byte is an 8-bit signed number, so it holds -128 to 127. Adding 1 to 127 wraps around to -128.
        byte small = 127;
        small++;
        System.out.println("127 + 1 as a byte: " + small);

        // An int is 32 bits, with the same wraparound at its largest value.
        System.out.println("Int range: " + Integer.MIN_VALUE + " to " + Integer.MAX_VALUE);
        int large = Integer.MAX_VALUE;
        large++;
        System.out.println("MAX_VALUE + 1: " + large);
    }
}
