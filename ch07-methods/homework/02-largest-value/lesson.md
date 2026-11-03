## Largest value

Write a method that takes an array of `int` values and returns the largest one. Use it on two arrays, and print each result.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own arrays.

The program should:

* write `largest(int[] values)`, which returns the greatest value in the array
* start with the first element as the best value so far, and compare each later element with it
* call `largest` on the scores array, `{72, 95, 88, 61}`, and print `Largest score: ` followed by the result
* call `largest` on the temperatures array, `{-3, 4, 0}`, and print `Largest temperature: ` followed by the result

### Where to change the code

The method's loop is where the work happens. It starts at index 1, because index 0 is already the starting best value. The line that replaces the best value is the only place that changes it:

```java
if (values[i] > best) {
    best = values[i];
}
```

To find the smallest value instead, change `>` to `<`.

### What to check

The output should be:

```text
Largest score: 95
Largest temperature: 4
```

The temperature array has a negative value, `-3`, so it checks that the starting value and the comparisons handle negatives correctly. The largest there is 4.

### Extensions

* Return the index of the largest value, not the value itself. You'll need to remember the index as well as the best value.
* Make the method return `0` for an empty array, and think about why `values[0]` would fail if it weren't checked first.

---

*This exercise is not from the book. It practices the ideas in Chapter 7.*
