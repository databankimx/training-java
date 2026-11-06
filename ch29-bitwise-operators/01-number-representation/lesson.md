## Number representation

Bitwise operators act on the bits that store a number, so the rest of this chapter depends on how those bits are read. This step summarizes that background: place values, negative numbers, and what happens when a number runs out of room.

### Place values

Ordinary numbers are base ten: each digit is worth ten times the digit to its right. Binary is base two, so each bit is worth twice the bit to its right. Reading from the right, the bits are worth 1, 2, 4, 8, 16, 32, and so on. The pattern `1001 1100` means 128 + 16 + 8 + 4 = 156.

### Fixed width

A Java `int` stores 32 bits, and a `byte` stores 8. Every value has a fixed number of bits, so the patterns used in these lessons are written at a fixed width, with leading zeros. The program's `bits8` method shows the lowest eight bits of a value in this way:

```java
static String bits8(int value) {
    return String.format("%8s", Integer.toBinaryString(value & 0xFF)).replace(' ', '0');
}
```

Press **Run**. The first lines of output are:

```text
7 in 8 bits: 00000111
-1 in 8 bits: 11111111
-128 in 8 bits: 10000000
```

### Negative numbers

Java stores negative numbers in two's complement. To find the two's complement of a number, flip every bit and add 1. For an 8-bit value, the leftmost bit is the sign: it's 0 for positive numbers and 1 for negative ones. The range of a signed 8-bit value is -128 to 127. The pattern `1111 1111` is -1, and `1000 0000` is -128. The same pattern means a different number in other systems, so the rule has to be fixed before the bits can be read.

Two's complement has a useful property: addition works the same way for positive and negative numbers. The adder circuit doesn't need to know the signs, and the result is correct whenever it falls within the range the type can hold.

### Overflow

A value outside the range of its type wraps around. Adding 1 to 127 in a `byte` gives -128, because the carry moves into the sign bit:

```java
byte small = 127;
small++;
System.out.println("127 + 1 as a byte: " + small);
```

The output is `127 + 1 as a byte: -128`. The same wraparound happens to an `int` at `Integer.MAX_VALUE`, and it's defined in Java: the program keeps running with the wrapped value rather than stopping or giving an undefined result.

---

### Quiz

*These questions are written for this course.*

````quiz
What is the value of the bit pattern `1111 1111` in 8-bit two's complement?
○ 255
→ Not quite. That's the reading if the pattern were unsigned, and this is a signed value.
◉ -1
→ Yes. Flipping the bits of 1 gives 1111 1110, and adding 1 gives 1111 1111, so the pattern is -1.
○ -127
→ Not quite. -127 is the pattern 1000 0001.
````

````quiz
What does `byte small = 127; small++;` leave in `small`?
○ 128
→ Not quite. A byte can't hold 128. The value wraps around.
◉ -128
→ Yes. The carry moves into the sign bit, so the result is the most negative byte.
○ 0
→ Not quite. The value wraps to the lowest value of the type, not to zero.
````

---

### Try it in the runner

Add a line that prints `bits8(127)`, and predict the output before you press **Run**. It's `01111111`, the largest positive 8-bit value. Then add `bits8(-128)` after it, and predict whether the output has eight digits. It does, since the mask keeps only the eight bits being shown.

---

Every value is a pattern of bits, read with a fixed width and a fixed convention for negative numbers. The next step introduces the logical operations, which work on those bits one position at a time.
