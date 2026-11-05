## Homework: Power of two: walkthrough

`value & (value - 1)` clears the lowest 1 bit. A power of two has only one 1 bit, so the result is zero. The `value > 0` check comes first, since zero would otherwise pass the test.

For the values in the program, 1, 16, and 1024 are powers of two, and 6 and 1000 aren't. Zero and every negative number are rejected by the first check.
