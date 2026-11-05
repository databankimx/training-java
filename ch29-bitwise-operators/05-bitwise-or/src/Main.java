public class Main {
    // Shows the lowest 8 bits of a value, with leading zeros, so the patterns line up.
    static String bits8(int value) {
        return String.format("%8s", Integer.toBinaryString(value & 0xFF)).replace(' ', '0');
    }

    public static void main(String[] args) {
        int a = 0b10011100;   // 156
        int b = 0b00110100;   // 52
        System.out.println("a | b: " + bits8(a | b) + " = " + (a | b));

        // Permissions as integers. Read is one bit and write is another, and read-write uses both.
        int read = 1;
        int write = 2;
        int readWrite = 3;

        boolean needToWrite = true;

        // Start with read permission, then add write only when it's needed.
        int permissions = read;
        if (needToWrite) {
            permissions |= write;
        }

        System.out.println(String.format("%4s", Integer.toBinaryString(permissions)).replace(' ', '0')
                + " = " + permissions);
        if (permissions == readWrite) {
            System.out.println("Success!");
        }
    }
}
