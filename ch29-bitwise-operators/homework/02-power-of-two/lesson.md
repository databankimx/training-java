## Homework: Power of two

A power of two, such as 1, 2, 4, 8, or 16, has exactly one bit set. Clearing the lowest set bit of such a number leaves zero, so a bit trick can check for a power of two without counting bits or dividing.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* write `static boolean isPowerOfTwo(int value)`, which returns true only for values greater than zero whose lowest set bit is their only set bit
* print each of these values, with `true` or `false`: 1, 6, 16, 0, 1024, and 1000

### What to check

The output should be:

```text
1: true
6: false
16: true
0: false
1024: true
1000: false
```

Zero isn't a power of two, and the check for `value > 0` is what rejects it. Without that check, zero would pass the bit test, since `0 & -1` is zero.

### Extensions

* Write a method that returns the next power of two at or above a value, using repeated shifts. Check it on 1, 5, and 1000.
* Explain why the test `value & (value - 1)` also works for `Integer.MIN_VALUE`, and why the `value > 0` check still matters for it.

---

*This exercise is not from the book. It practices the ideas in Chapter 29.*
