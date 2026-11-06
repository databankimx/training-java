## Bitwise AND

The `&` operator keeps only the bits that are 1 in both numbers. At each bit position, the result is the product of the two bits, and since 1 × 1 is 1 and any number times 0 is 0, the product matches the AND table from the previous step.

### A worked example

Take 156 and 52. In binary they are:

```text
a = 1001 1100   (156)
b = 0011 0100   (52)
a & b = 0001 0100   (20)
```

Only the positions where both numbers have a 1 keep a 1. The bit worth 16 and the bit worth 4 are 1 in both numbers, so they're the only bits kept. Together they make 16 + 4 = 20.

The program reproduces the worked example, with the literals written in binary:

```java
int a = 0b10011100;   // 156
int b = 0b00110100;   // 52
System.out.println("a & b: " + bits8(a & b) + " = " + (a & b));
```

The output is:

```text
a: 10011100 = 156
b: 00110100 = 52
a & b: 00010100 = 20
```

### Checking for even numbers

AND has a common use that shows its behaviour clearly. A number is odd exactly when its lowest bit is 1, because every other bit is worth an even number. So `n & 1` keeps only that bit, and it's 0 for even numbers and 1 for odd ones.

There are two ways to test for an even number:

```java
static boolean isEvenWithModulo(int n) {
    return n % 2 == 0;
}

static boolean isEvenWithAnd(int n) {
    return (n & 1) == 0;
}
```

Both give the same answers. The modulo version is easier to read, and the AND version shows the bit that decides the answer. Modern compilers handle both equally well, so the choice is about clarity.

Press **Run** to check both methods on the list `{42, 73, 0, 1000, -7}`. The output ends with:

```text
Using n % 2, -7 is odd
Using n & 1, -7 is odd
```

Negative numbers need care with `%`. In Java, `-7 % 2` is -1, not 1, so the test must compare with 0 rather than 1. The AND version is unaffected, since `-7 & 1` is 1.

---

### Quiz

*These questions are written for this course.*

````quiz
What is the value of `12 & 10`?
○ 14
→ Not quite. 14 is the OR of 12 and 10.
◉ 8
→ Yes. Only the bit worth 8 is 1 in both numbers.
○ 22
→ Not quite. That's the sum of the two numbers, which is not an AND.
````

````quiz
Why does `n & 1` tell you whether a number is odd?
○ Because the lowest bit is 1 for odd numbers, and every other bit is worth an even number.
→ Yes. Only the lowest bit can make the value odd, and AND with 1 keeps only that bit.
○ Because AND always returns 1 for odd numbers.
→ Not quite. The result depends on the lowest bit, not on AND as such.
○ Because odd numbers have more bits than even numbers.
→ Not quite. The number of bits doesn't decide oddness. The lowest bit does.
````

---

### Try it in the runner

Add `0b11110000` and `0b10101010` as a pair, with a line that prints their AND in eight bits, and predict the result before you press **Run**. The AND is `10100000`, since only the bits that are 1 in both numbers survive.

---

AND keeps the bits that two numbers share, and that makes it a test for individual bits. The next step uses the same idea to check which flags are set in a set of permissions.
