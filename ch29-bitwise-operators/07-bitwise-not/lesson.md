## Bitwise NOT

The `~` operator takes a single value and reverses every bit: each 0 becomes 1, and each 1 becomes 0. Its truth table is the simplest of all:

| b | ~b |
|---|---|
| 0 | 1 |
| 1 | 0 |

### An example

Take 156, which is `1001 1100` in eight bits. Its complement, flipping each bit, is `0110 0011`, which is 99:

```text
b  = 1001 1100   (156)
~b = 0110 0011   (99)
```

The program prints the value first, and then the complement:

```java
int b = 0b10011100;   // 156
System.out.println("b: " + bits8(b));
```

### The sign problem

In Java an `int` has 32 bits, and its leftmost bit is the sign. So `~b` flips all 32 bits, and the leftmost bit of 156's complement is 1. The result is a negative number:

```java
System.out.println("~b as an int: " + (~b));
```

The output is:

```text
~b as an int: -157
```

The result is correct as a 32-bit value, but it isn't the 8-bit pattern we wanted. The Python lesson shows the same problem: an integer that is treated as unsigned gives a result that looks wrong. In Java the width is fixed at 32 bits, so the fix is to limit the result to the bits that matter.

### Limiting the complement

The helper `bitwiseNot` takes the number of bits to keep, and masks the result with `(1 << numBits) - 1`. For eight bits, that mask is `1111 1111`, and the AND keeps only the lowest eight bits of the complement:

```java
static int bitwiseNot(int n, int numBits) {
    return ~n & ((1 << numBits) - 1);
}
```

The output is:

```text
~b in 8 bits: 01100011 = 99
```

For a wider value, pass a larger `numBits`. The mask is what sets the width of the result.

### Clearing and setting a bit

NOT is used to turn a bit off. To clear one bit, AND the value with the complement of that bit's mask. To set a bit, OR it with the mask. The helper `setBit` does both, so a single function covers each case:

```java
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
```

Setting each position in turn, starting from zero, gives one power of two at a time:

```text
1
2
4
8
16
32
64
128
```

The last line of the program clears bit 3 of 255. Its complement mask is `1111 0111`, so the AND gives `1111 0111`, which is 247.

---

### Quiz

*These questions are written for this course.*

````quiz
What is `~0` as an 8-bit value, using `bitwiseNot(0, 8)`?
○ 0
→ Not quite. NOT flips every bit, so zero becomes all ones.
◉ 255
→ Yes. Every bit of zero becomes 1, which is 1111 1111.
○ -1
→ Not quite. That's the 32-bit result of `~0`, and the mask limits the answer to eight bits.
````

````quiz
Why does `bitwiseNot` use `&` with a mask, rather than returning `~n` directly?
○ Because `~` can't be applied to an int.
→ Not quite. `~` works on an int. The problem is its width.
◉ Because `~n` has 32 bits, and the mask keeps only the bits that the value is meant to have.
→ Yes. The mask discards the leading ones that the complement adds.
○ Because the mask makes the result faster.
→ Not quite. The reason is the width of the result, not speed.
````

---

### Try it in the runner

Change the `setBit` call to `setBit(0, 5, 1)`, and predict the output before you press **Run**. It's 32, the bit worth 2 to the fifth power. Then change the call to `setBit(255, 5, 0)`, and predict the result. It's 223, since clearing bit 5 takes 32 away from 255.

---

NOT flips every bit, and masking limits the result to the width you need. The next step uses XOR, which flips bits selectively, as a toggle.
