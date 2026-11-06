public class Main {
    // Counts the bits that are 1 in a number. Each pass of the loop clears the lowest set bit.
    static int countSetBits(int value) {
        int count = 0;
        while (value != 0) {
            value &= value - 1;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("Set bits in 45: " + countSetBits(45));
        System.out.println("Set bits in 8: " + countSetBits(8));
        System.out.println("Set bits in 255: " + countSetBits(255));
        System.out.println("Matches Integer.bitCount: " + (countSetBits(45) == Integer.bitCount(45)));
    }
}
