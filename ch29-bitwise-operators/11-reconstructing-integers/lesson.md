## Reconstructing integers

Shifts are useful whenever a stream of bytes stands for larger values. A file, a network packet, or a sensor often stores a 32-bit number as four separate bytes, and a program has to put them back together.

### The scenario

Suppose you read a data stream into a byte array, and you know that each group of four bytes is a 32-bit integer. Within each group, the least significant byte comes first. That order is called little-endian. Here is one integer, 1,234,567,890, stored that way:

```java
byte[] bytes = {
    (byte) 0b11010010, (byte) 0b00000010, (byte) 0b10010110, (byte) 0b01001001
};
```

### Building the value

The first byte holds bits 0 to 7, so it's added as it is. The second byte holds bits 8 to 15, which are worth 256 each, so it must be shifted left by 8 places before it's added. The third byte shifts by 16, and the fourth by 24:

| Byte | Bits | Shift | Value of its lowest bit |
|:-:|:-:|:-:|-:|
| 0 | 0 to 7 | none | 1 |
| 1 | 8 to 15 | 8 | 256 |
| 2 | 16 to 23 | 16 | 65,536 |
| 3 | 24 to 31 | 24 | 16,777,216 |

The loop in `reconstructInteger` does this for every byte:

```java
static int reconstructInteger(byte[] data) {
    int num = 0;
    for (int i = 0; i < data.length; i++) {
        num |= (data[i] & 0xFF) << (i * 8);
    }
    return num;
}
```

### Two details that differ in Java

The first is the sign. A Java `byte` is signed, so the byte 210 (`1101 0010`) is stored as -46. When it's widened to an `int` for the shift, the sign bit is copied into all the upper bits, and the result is wrong. The mask `& 0xFF` keeps only the eight bits of the byte, and removes the copied sign.

The second is the combining operator. The Python lesson adds each shifted byte, and this code uses `|=`. The two give the same answer here, because no two bytes share a bit position. OR is the clearer choice, since it says that the bits are being combined, not added.

### Reading the stream

The method `reconstructIntegers` splits the stream into groups of four, and rebuilds each one. It refuses a stream whose length isn't a multiple of four, since the last group would be incomplete:

```java
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
```

The program's stream holds four groups, which are zero, 1,234,567,890, 123,456,789, and the largest 32-bit value. Press **Run**. The output is:

```text
            0
1,234,567,890
  123,456,789
4,294,967,295
```

The last value is -1 as a Java `int`, since all 32 bits are 1. `Integer.toUnsignedLong` shows it as the unsigned number 4,294,967,295, which is the value the bits represent.

---

### Quiz

*These questions are written for this course.*

````quiz
Why is `(data[i] & 0xFF)` used instead of `data[i]` in the shift?
○ Because `&` makes the shift faster.
→ Not quite. The mask is there for the sign, not for speed.
◉ Because a signed byte would copy its sign bit into the upper bits when widened, and the mask removes that.
→ Yes. Keeping only the eight bits of the byte gives the unsigned value.
○ Because the byte must be converted to a string first.
→ Not quite. The byte is used as a number, and the mask keeps that number correct.
````

````quiz
In a four-byte group, what shift does the third byte (index 2) need?
○ 8
→ Not quite. That's the shift for the second byte.
◉ 16
→ Yes. Each byte is worth 256 times the byte before it, so the third is shifted by 16.
○ 24
→ Not quite. That's the shift for the fourth byte.
````

---

### Try it in the runner

Replace the first group of bytes with `(byte) 0b00000001` followed by three zero bytes, and predict the first output line before you press **Run**. It's 1, since only the lowest bit is set. Then change the second group to the bytes `(byte) 0b00000000`, `(byte) 0b00000000`, `(byte) 0b00000000`, `(byte) 0b10000000`, and predict the value. It's 2,147,483,648 as an unsigned number, which is the value of the top bit.

---

Shifts and OR take a stream of bytes apart and put it together, one byte at a time. The homework and summary bring the chapter's operations together.
