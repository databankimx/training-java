import java.util.Arrays;

public class Main {
    // Builds one 32-bit integer from four bytes, least significant byte first (little-endian).
    // Each byte is masked with 0xFF first, because Java bytes are signed and would otherwise sign-extend.
    static int reconstructInteger(byte[] data) {
        int num = 0;
        for (int i = 0; i < data.length; i++) {
            num |= (data[i] & 0xFF) << (i * 8);
        }
        return num;
    }

    // Builds every integer in a stream of bytes. The stream's length must be a multiple of four.
    static int[] reconstructIntegers(byte[] bytes) {
        if (bytes.length % 4 != 0) {
            throw new IllegalArgumentException("Invalid data!");
        }
        int count = bytes.length / 4;
        int[] values = new int[count];
        for (int n = 0; n < count; n++) {
            byte[] data = Arrays.copyOfRange(bytes, n * 4, n * 4 + 4);
            values[n] = reconstructInteger(data);
        }
        return values;
    }

    public static void main(String[] args) {
        // The first four bytes are the little-endian form of 1,234,567,890. The others are the examples from the
        // Python lesson: zero, 123,456,789, and the largest 32-bit value. Java bytes are signed, so the casts keep
        // each bit pattern exactly as written.
        byte[] bytes = {
            (byte) 0b00000000, (byte) 0b00000000, (byte) 0b00000000, (byte) 0b00000000,
            (byte) 0b11010010, (byte) 0b00000010, (byte) 0b10010110, (byte) 0b01001001,
            (byte) 0b00010101, (byte) 0b11001101, (byte) 0b01011011, (byte) 0b00000111,
            (byte) 0b11111111, (byte) 0b11111111, (byte) 0b11111111, (byte) 0b11111111
        };

        for (int value : reconstructIntegers(bytes)) {
            // Integer.toUnsignedLong shows the value as the unsigned number it represents.
            System.out.println(String.format("%,13d", Integer.toUnsignedLong(value)));
        }
    }
}
