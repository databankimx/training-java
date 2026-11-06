public class Main {
    // Shows the lowest 8 bits of a value, with leading zeros, so the patterns line up.
    static String bits8(int value) {
        return String.format("%8s", Integer.toBinaryString(value & 0xFF)).replace(' ', '0');
    }

    // The complement of a value, limited to the given number of bits. The mask keeps only the low bits, so the
    // leading ones that the complement adds are discarded.
    static int bitwiseNot(int n, int numBits) {
        return ~n & ((1 << numBits) - 1);
    }

    // Sets one bit of a word to 0 or 1. Clearing uses NOT of the bit's mask, and setting uses OR.
    static int setBit(int word, int position, int value) {
        int mask = 1 << position;
        if (value == 0) {
            return word & ~mask;
        }
        if (value == 1) {
            return word | mask;
        }
        return word;
    }

    public static void main(String[] args) {
        int b = 0b10011100;   // 156
        System.out.println("b: " + bits8(b));

        // In Java an int has 32 bits, so NOT of 156 is the negative value -157. It isn't a useful 8-bit pattern yet.
        System.out.println("~b as an int: " + (~b));

        // Limiting the complement to 8 bits gives the answer we expect.
        System.out.println("~b in 8 bits: " + bits8(bitwiseNot(b, 8)) + " = " + bitwiseNot(b, 8));

        // Setting one bit at a time: each position gives one power of two.
        for (int position = 0; position < 8; position++) {
            System.out.println(setBit(0, position, 1));
        }

        System.out.println("setBit(255, 3, 0) = " + setBit(255, 3, 0));
    }
}
