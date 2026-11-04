## Homework: Count and sum

The `forEach` method of a list calls a lambda once for each value. In this exercise, you use it with a lambda that adds to two totals, so the totals are available after the loop.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own totals.

The program should:

* create a `List<Integer>` holding the numbers 1 through 10
* declare two totals, `evenCount` and `evenSum`, both starting at 0, held in arrays of one element each
* call `forEach` on the list with a lambda that adds 1 to `evenCount` and adds the value to `evenSum` only when the value is even
* print `Even count: ` followed by `evenCount`, and then `Sum of evens: ` followed by `evenSum`

### What to check

The output should be:

```text
Even count: 5
Sum of evens: 30
```

The even numbers are 2, 4, 6, 8, and 10. Their sum is 30. The lambda changes the two totals through their arrays, so the totals are still there after `forEach` returns.

### Why arrays

A lambda can't assign to a local `int`, as the captures step explained. The arrays are never reassigned, so the lambda can capture them, and it changes the one element it needs.

### Extensions

* Replace `forEach` with an enhanced `for` loop, and compare the two versions. Which one makes it easier to see that the totals are updated?
* Explain what happens if you write `evenCount = evenCount + 1;` inside the lambda, and what the compiler reports.

---

*This exercise is not from the book. It practices the ideas in Chapter 20, with the lists from Chapter 14.*
