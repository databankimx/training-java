## Homework: Safe division

Division by zero has no answer, so a method that divides should say so, rather than fail or return a placeholder. In this exercise, you write a division that returns an `Optional<Integer>`, and print each result, handling the empty case.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* write `static Optional<Integer> safeDivide(int a, int b)`, which returns the quotient wrapped in `Optional.of` when `b` isn't zero, and `Optional.empty()` when it is
* write `report(int a, int b)`, which calls `safeDivide` and prints `a / b = ` followed by the result, checking with `isPresent` before using `get`
* when the result is empty, print `a / b = no answer` in place of the number
* call `report` for `10, 2`, then `1, 0`, then `9, 3`

### What to check

The output should be:

```text
10 / 2 = 5
1 / 0 = no answer
9 / 3 = 3
```

The check with `isPresent` comes before any use of the value. Calling `get` on an empty `Optional` throws a `NoSuchElementException`, which the program doesn't catch in this exercise, so the check is what keeps it running.

### Extensions

* Replace the `isPresent` check in `report` with `ifPresentOrElse`, and check that the output doesn't change.
* Write `safeDivide` so it returns `Optional.empty()` for a result that would overflow. Think about which operations on `int` values can overflow, and what the caller should be told.

---

*This exercise is not from the book. It practices the ideas in Chapter 25, with the methods from Chapter 7.*
