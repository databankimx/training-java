## Grade letter

Write a program that turns a numeric score into a letter grade.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* ask for a score from 0 to 100, with the prompt `Enter a score (0-100): `, and read it as an `int`
* print the letter for the score, using these bands:
  * 90 or above is `A`
  * 80 or above is `B`
  * 70 or above is `C`
  * 60 or above is `D`
  * anything below 60 is `F`
* print the result as `Your grade is B`, for example

Use an `if` and `else if` chain, with braces on every block. Don't write a separate test for every score. Let the order of the chain do the work, since each test only needs to check the lower bound of its band.

### Where to change the code

The example stores the letter in a `String` called `grade`, which the chain sets in each branch. The line that does the work is the first condition:

```java
if (score >= 90) {
```

To use different bands, change those numbers. Keep the order from the highest band to the lowest, so the first true test is always the right one.

### What to check

Try these scores:

* `95` should give `A`
* `90` should give `A`, because 90 is in the top band
* `89` should give `B`
* `60` should give `D`
* `59` should give `F`

The boundary values matter most. A score of exactly 90 is an A, and 89 is a B.

### Extensions

* Reject a score outside 0 to 100, and print a message instead of a grade. You'll need a condition that combines two comparisons, using `||` or `&&` from the logical operators lesson.
* Add plus and minus grades, such as `B+` for 87 to 89. Think about where the new tests belong in the chain.

---

*This exercise is not from the book. It practices the ideas in Chapter 3.*
