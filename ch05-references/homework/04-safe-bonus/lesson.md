## Homework: Safe bonus

Write a method that adds a bonus to a score, but only when there's a score to add it to.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* define `addBonus`, which takes an `int[]` holding one score, and an `int` bonus
* inside `addBonus`, add the bonus to the score when the reference isn't `null`
* print `No score to update.` when the reference is `null`, and change nothing
* in `main`, declare `int[] points = {50};`, then call `addBonus` with `points` and a bonus of 10, and print `Points: ` followed by `points[0]`
* then call `addBonus` with `null` and a bonus of 5, print `Points: ` again, and check that nothing changed

The order of the check matters. The reference must be checked before it's used, since reading an element through a `null` reference throws a `NullPointerException`.

### What to check

Run the program and compare the output with these lines:

```text
Points: 60
No score to update.
Points: 60
```

The last line should show 60 again, since the second call changed nothing. If it shows 65 or any other value, check the `null` branch.

### Extensions

* Change `addBonus` to refuse a negative bonus, and print a message when it does. Think about whether the check belongs before or after the `null` test.
* Add a second score, `int[] rank = {3};`, and check that only that score changes when `addBonus` is called on it.

---

*This exercise is not from the book. It practices the ideas in Chapter 5.*
