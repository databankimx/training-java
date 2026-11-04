## Homework: Leap-year tests

A year is a leap year when it's divisible by 4, except that a year divisible by 100 is not a leap year unless it's also divisible by 400. So 2024 is a leap year, 1900 is not, and 2000 is.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own tests.

In this exercise, you write the method and the checks that test it. The checks are the part that matters, since they're what would catch a mistake later.

The program should:

* write `static boolean isLeapYear(int year)`, following the rule above
* write a `check` method that prints `PASS` or `FAIL` with a name, and counts the failures
* run at least four checks in `main`: 2024 is a leap year, 1900 is not, 2000 is, and 2023 is not
* print `Failures: ` and the count
* write a `Tests` class in the `tests` folder, which checks the same cases and ends with `System.exit` set to 0 when every check passes, and 1 otherwise

### What to check

The output of `Main` should be:

```text
PASS 2024 is a leap year
PASS 1900 is not a leap year
PASS 2000 is a leap year
PASS 2023 is not a leap year
Failures: 0
```

Then press **Test**. The result should show the test as passed, with `Finished with exit code 0`. Test prints only the `Failures` line from `Tests`, so the `PASS` lines come from **Run**, not **Test**.

### Extensions

* Add a check for a year that's a multiple of 400 and of 100, such as 1600, and one that's a multiple of 4 but not 100, such as 2028. Explain which of the rule's cases each one covers.
* Change the method to get 1900 wrong, and see which checks fail. Explain why the edge cases are the ones that show it.

---

*This exercise is not from the book. It practices the ideas in Chapter 21, with the decisions from Chapter 3.*
