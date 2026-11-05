## Bit shifts

A shift moves every bit of a value a given number of places. `<<` shifts left, and `>>` shifts right. Each place to the left multiplies the value by 2, and each place to the right divides it by 2, rounding down.

### Right shift is division by two

Shifting right by `p` places divides by 2 to the power `p`:

```text
128 >> 3 = 128 / 8 = 16
```

In binary, the single 1 bit of 128 moves three places to the right:

```text
1000 0000   (128)
0001 0000   (16)
```

The program's first line prints this:

```java
System.out.println("128 >> 3 = " + (128 >> 3));
```

Negative numbers round toward negative infinity. `-1 >> 1` is -1, not zero, because the shift keeps the sign bit.

### Left shift is multiplication by two

Shifting left by `p` places multiplies by 2 to the power `p`:

```text
16 << 3 = 16 * 8 = 128
```

### Java has two right shifts

Java has `>>` and `>>>`. The first copies the sign bit into the places it fills, so negative numbers stay negative. The second fills the top with zeros:

```java
System.out.println("-1 >> 1 = " + (-1 >> 1));
System.out.println("-1 >>> 1 = " + (-1 >>> 1));
```

The output is:

```text
-1 >> 1 = -1
-1 >>> 1 = 2147483647
```

Use `>>>` when the value is a bit pattern rather than a signed number, and `>>` when the value is a signed quantity that should stay signed.

### Losing bits

A shift can push a 1 past the end of its type, and the bit is lost. The width of the type decides where the end is.

An `int` has 32 bits, so `64 << 3` is 512, and nothing is lost:

```text
64 << 3 = 512
```

To see what happens in eight bits, the program keeps only the lowest eight bits with a mask. The 1 moves into the ninth place, and the mask removes it:

```java
System.out.println("64 << 3 in 8 bits = " + ((64 << 3) & 0xFF));
```

The output is `64 << 3 in 8 bits = 0`. The bit that moved to the ninth place is lost.

Right shifts lose bits too. `4 >> 3` is 0, because the 1 in the ones place falls off the right end:

```text
4 >> 3 = 0
```

Java's `int` and `long` are fixed-width, so a left shift can overflow in the same way a fixed-width C type does. The result is defined in Java: the bits that move past the top are dropped, and the program keeps running.

### Reading the bits of a flag value

A shift can also be used to visit each bit of a value in turn. The `printSettings` method checks the ones place, then shifts the whole value one place to the right, so the next bit moves into the ones place. It keeps a second number, `currentValue`, that doubles each time, so it always holds the value of the bit being checked:

```java
static void printSettings(int settings) {
    int currentValue = 1;
    while (settings > 0) {
        if ((settings & 1) != 0) {
            System.out.println("- " + nameOf(currentValue));
        }
        settings >>= 1;
        currentValue <<= 1;
    }
}
```

Each pass does three things. `settings & 1` tests the ones-place bit. `settings >>= 1` brings the next bit into the ones place. `currentValue <<= 1` doubles the value, so it matches the next setting's bit. The loop ends when no set bits are left.

For `settings = 10`, which is `0000 1010`, the output is:

```text
With settings 10, the following are set:
- INTERACTIVE_MODE
- OPTIMIZE
```

The two set bits are worth 2 and 8, and those are the two settings with those values.

---

### Quiz

*These questions are written for this course.*

````quiz
What is `5 << 2`?
○ 7
→ Not quite. A left shift multiplies, it doesn't add.
◉ 20
→ Yes. Shifting left by two multiplies by 4, so 5 times 4 is 20.
○ 10
→ Not quite. That would be a shift by one place.
````

````quiz
What does `-8 >>> 1` give?
○ -4
→ Not quite. That's the result of `>>`, which keeps the sign.
◉ 2147483644
→ Yes. The shift fills the top bit with zero, so the result is a large positive number.
○ 8
→ Not quite. The shift doesn't remove the sign by discarding it.
````

---

### Try it in the runner

Change `printSettings(10)` to `printSettings(15)`, and predict the output before you press **Run**. All four settings are listed, since 15 is `1111`. Then change it to `printSettings(4)`, and predict the list. Only REMEMBER_SETTINGS is printed.

---

Shifts multiply and divide by powers of two, and they let a loop visit each bit in turn. The last operation step puts bits together to rebuild a number, which is the reverse of this.
