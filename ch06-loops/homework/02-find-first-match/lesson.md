## Find the first match

The array holds 12 in two places, at indexes 2 and 4. Write a program that finds the first index of the target value, and stops looking once it's found.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for a different search.

The program should:

* create an array of `int` values: 4, 9, 12, 7, 12, and 3
* set the target to 12
* loop over the array by index, and stop at the first element equal to the target, using `break`
* print `12 first found at index 2.` when it's found, or `12 was not found.` when it isn't

### Where to change the code

The search is the loop with its `if` and `break`. The `foundAt` variable starts at -1, which can't be a real index, so the program can tell afterwards whether the search succeeded. To search for a different value, change `target`.

### What to check

The output should be:

```text
12 first found at index 2.
```

The second 12, at index 4, is never reached, because `break` stops the loop at the first match. Change `target` to 5, and the output should be `5 was not found.`

### Extensions

* Print the index of the last match instead, by removing the `break` and checking whether the search keeps going. Predict which index is printed before you run it.
* Search for a value that isn't in the array, and check that the `-1` is still reported as not found.

---

*This exercise is not from the book. It practices the ideas in Chapter 6.*
