## Homework: Smallest of

Write a generic method that returns the smallest value in a list, and use it for numbers and for strings.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* write `static <T extends Comparable<T>> T smallestOf(List<T> values)`, which assumes the list has at least one value, starts with the first value as the smallest, and compares each later value with it using `compareTo`
* in `main`, print `Smallest number: ` followed by the result of `smallestOf` on a list holding 5, 2, and 9
* print `Smallest word: ` followed by the result of `smallestOf` on a list holding "pear", "apple", and "fig"

### What to check

The output should be:

```text
Smallest number: 2
Smallest word: apple
```

The same method produces both results. The string comparison is alphabetical, so "apple" comes before "fig" and "pear".

### Extensions

* Add a generic method that returns the largest value, and check that it works for both lists.
* Call `smallestOf` with an empty list. Decide what it should return, since the method as written would fail when it reads `values.get(0)`, and explain why a generic type doesn't solve that problem for you.

---

*This exercise is not from the book. It practices the ideas in Chapter 19, with the lists from Chapter 14.*
