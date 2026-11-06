## Homework: Recursive power

Write a recursive method that raises a number to a whole-number power, and check it on a few values.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* write `static long power(long base, int exp)`, which returns 1 when `exp` is 0, and otherwise multiplies `base` by the result of `power(base, exp - 1)`
* print 2 to the power 10, 3 to the power 4, and 5 to the power 0

### What to check

The output should be:

```text
2^10 = 1024
3^4 = 81
5^0 = 1
```

Each call moves `exp` one step towards zero, which is the base case, so the recursion always ends for a non-negative exponent.

### Extensions

* Halve the work by using `base * base` when the exponent is even. Work out how many calls this version makes for 2 to the power 10, and compare it with the plain version.
* Add a check that throws an exception for a negative exponent, and explain why the recursion would never end for one.

---

*This exercise is not from the book. It practices the ideas in Chapter 30.*
