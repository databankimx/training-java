public class Main {
    // Shows the lowest 8 bits of a value, with leading zeros, so the patterns line up.
    static String bits8(int value) {
        return String.format("%8s", Integer.toBinaryString(value & 0xFF)).replace(' ', '0');
    }

    // Swaps two values using XOR only, with no temporary variable. Returns the new pair as {a, b}.
    // Java passes numbers by value, so the swap is returned rather than made on the caller's variables.
    static int[] swapValues(int a, int b) {
        a = a ^ b;
        b = b ^ a;
        a = a ^ b;
        return new int[] {a, b};
    }

    public static void main(String[] args) {
        int a = 0b10011100;   // 156
        int b = 0b00110100;   // 52
        System.out.println("a ^ b: " + bits8(a ^ b) + " = " + (a ^ b));

        int x = 5;
        int y = 8;
        System.out.println("x = " + x + ", y = " + y);
        int[] swapped = swapValues(x, y);
        System.out.println("x = " + swapped[0] + ", y = " + swapped[1]);

        // XOR with a flag toggles that bit: on becomes off, and off becomes on.
        int flag = 4;
        int state = 5;
        state ^= flag;
        System.out.println("After toggling the flag worth 4: " + bits8(state) + " = " + state);
        state ^= flag;
        System.out.println("After toggling it again: " + bits8(state) + " = " + state);
    }
}
