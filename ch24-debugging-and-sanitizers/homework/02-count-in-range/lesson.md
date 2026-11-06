## Homework: Count in range

Write a method that counts how many values in an array fall within a range, including both ends. Getting the boundaries right is the point of the exercise, since an off-by-one error here is easy to make and hard to spot.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* write `static int countInRange(int[] values, int low, int high)`, which counts the values that are at least `low` and at most `high`
* in `main`, create an array holding 1, 2, 3, 4, and 5, and print `Count between 2 and 4: ` followed by `countInRange(values, 2, 4)`
* print `Count between 1 and 5: ` followed by `countInRange(values, 1, 5)`

### What to check

The output should be:

```text
Count between 2 and 4: 3
Count between 1 and 5: 5
```

The values 2, 3, and 4 are all counted, so the first answer is 3. Both ends are included, which is what the second check confirms, since 1 and 5 are counted when they're the range's limits.

### Extensions

* Write a version that counts only the values strictly between the limits, and check it against the same two ranges. Explain the difference in the two answers.
* Add a line that prints each value as it's checked, and step through the output for the range 2 to 4. Note which values are counted and which are not.

---

*This exercise is not from the book. It practices the ideas in Chapter 24, with the loops from Chapter 6.*
