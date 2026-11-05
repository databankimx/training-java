## Homework: Count set bits

Write a method that counts how many bits of a number are 1. The number 45 is `101101` in binary, which has four 1 bits, so the answer for 45 is 4.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* write `static int countSetBits(int value)`, which counts the 1 bits by looping, once for each 1 found
* print the count for 45, 8, and 255
* print whether your count for 45 matches `Integer.bitCount(45)`

### What to check

The output should be:

```text
Set bits in 45: 4
Set bits in 8: 1
Set bits in 255: 8
Matches Integer.bitCount: true
```

### The trick

The expression `value &= value - 1` clears the lowest 1 bit of `value`. Subtracting 1 changes the lowest 1 bit to 0, and the bits below it to 1, and the AND keeps only the bits above that. So each pass removes one 1 bit, and the loop ends when no 1 bits are left.

### Extensions

* Write the count with a plain loop that tests each bit with `(value >> i) & 1`, for `i` from 0 to 31. Compare how many passes it makes with the version above.
* Check a negative number with both methods. Explain why the loop version still works on a negative `int`.

---

*This exercise is not from the book. It practices the ideas in Chapter 29.*
