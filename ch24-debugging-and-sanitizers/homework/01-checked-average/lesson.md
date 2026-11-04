## Homework: Checked average

Write a method that returns the average of an array of numbers, and protect it with an assertion. An empty array has no average, so the method's caller must never pass one. That promise is what the assertion checks.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* write `static double average(int[] values)`, which starts with `assert values.length > 0`, adds the values, and returns the total divided by the count as a `double`
* in `main`, pass an array holding 2, 4, 6, and 8, and print `Average: ` followed by the result with two decimal places

### What to check

The output should be:

```text
Average: 5.00
```

The array is not empty, so the assertion holds and the program runs to the end. The division must use a `double`, or the result would be cut to a whole number. The total is kept in a `long`, so a large array can't overflow an `int` while it's added.

### A note on assertions

The runner doesn't start the program with `-ea`, so an assertion doesn't stop the program here, even when it's false. To see the assertion work, run the program yourself with `java -ea`, and pass an empty array. It stops with an `AssertionError` that names the message.

### Extensions

* Call `average` with an empty array, and predict the output with and without `-ea`. Explain why this is a bug in the caller, not a condition the method should handle quietly.
* Replace the assertion with a check that throws an `IllegalArgumentException` for an empty array. Explain why an exception is the right choice when a caller can really pass an empty array.

---

*This exercise is not from the book. It practices the ideas in Chapter 24, with the arrays from Chapter 4.*
