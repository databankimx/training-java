## Bitwise XOR

The `^` operator is the exclusive OR. It returns 1 in each position where the two bits differ, so a bit is 1 only when one of the two values has a 1 there, but not both. Its truth table is:

| b1 | b2 | b1 ^ b2 |
|---|---|---|
| 0 | 0 | 0 |
| 0 | 1 | 1 |
| 1 | 0 | 1 |
| 1 | 1 | 0 |

### An example

Take 156 and 52 again. Where both numbers have a 1, the XOR gives 0. Where only one has a 1, the XOR gives 1:

```text
a = 1001 1100   (156)
b = 0011 0100   (52)
a ^ b = 1010 1000   (168)
```

The bit worth 16 is set in both numbers, so it's cleared in the result. The bit worth 32 is set only in `b`, so it's kept, and the same goes for 128, which is set only in `a`.

```java
int a = 0b10011100;   // 156
int b = 0b00110100;   // 52
System.out.println("a ^ b: " + bits8(a ^ b) + " = " + (a ^ b));
```

The output is:

```text
a ^ b: 10101000 = 168
```

### Swapping two values

XOR can swap two values without a temporary variable. Each step XORs one variable with the other, and the three steps leave the original values in each other's places:

```java
static int[] swapValues(int a, int b) {
    a = a ^ b;
    b = b ^ a;
    a = a ^ b;
    return new int[] {a, b};
}
```

The method returns the pair in an array. Java passes numbers by value, so a method can't swap the caller's variables directly, and the swapped values are returned instead.

Here's how the steps work on 5 and 8. In eight bits, 5 is `0101` and 8 is `1000`:

- After `a = a ^ b`, `a` holds `1101`, which contains the bits of both.
- After `b = b ^ a`, `b` holds `0101`, which is the original `a`.
- After `a = a ^ b`, `a` holds `1000`, which is the original `b`.

The program prints the values before and after:

```text
x = 5, y = 8
x = 8, y = 5
```

For two ordinary variables, `int temp = x; x = y; y = temp;` does the same job more clearly. The XOR form is worth knowing because it shows how the bits combine.

### Toggling a flag

XOR flips a bit: a bit that's on turns off, and one that's off turns on. That makes it the natural operation for a switch that changes state each time it's used:

```java
int flag = 4;
int state = 5;
state ^= flag;
```

The flag is 4, which is bit 2, and `state` starts as 5, which has that bit set. The first XOR clears it, and the second XOR sets it again. The output is:

```text
After toggling the flag worth 4: 00000001 = 1
After toggling it again: 00000101 = 5
```

---

### Quiz

*These questions are written for this course.*

````quiz
What is `1 ^ 1`?
○ 1
→ Not quite. The bits are the same, and XOR gives 1 only when they differ.
◉ 0
→ Yes. Equal bits give 0 under XOR.
○ 2
→ Not quite. A single bit's XOR gives 0 or 1.
````

````quiz
Why does `swapValues` return an array instead of changing its parameters?
○ Because arrays are faster than ints.
→ Not quite. The reason is how Java passes numbers.
◉ Because Java passes numbers by value, so changes to the parameters don't reach the caller's variables.
→ Yes. The caller has to take the swapped values from the return value.
○ Because XOR can't be used inside a method.
→ Not quite. XOR works inside a method. The problem is passing the values back.
````

---

### Try it in the runner

Change the flag to `8`, and start `state` at `0b1000` (8). Predict the result of the first toggle before you press **Run**. It's 0, since the flag is turned off. Then change `state` to 0, and predict the result of the first toggle. It's 8, since the flag is turned on.

---

XOR flips the bits where its two inputs differ, which makes it a toggle and a way to swap values. The next step uses XOR in a simple cipher, where applying the same key twice gives back the original.
