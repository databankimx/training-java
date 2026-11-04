## Homework: Comma line

Build one line of text from an array of numbers, separated by commas and spaces, using a `StringBuilder`. Print the line and its length.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own numbers.

The program should:

* start with the array `{3, 1, 4, 1, 5}`
* append each number to a `StringBuilder`, with `, ` between numbers, but not before the first one or after the last
* print the finished line, then print `Length: ` followed by its length

### Where to change the code

The `if (i > 0)` check is what keeps the separator out of the front. Without it, the line would begin with a comma. To use different numbers, change the array, and the loop still works for any length.

### What to check

The output should be:

```text
3, 1, 4, 1, 5
Length: 13
```

The five digits take 5 characters, and the four separators of two characters each take 8, so the length is 13.

### Extensions

* Build the same line with `+=` instead, and check that the text is identical. Then time both on a much longer array, and explain what you see.
* Change the separator to a semicolon with a space, and check that the length changes as you expect.

---

*This exercise is not from the book. It practices the ideas in Chapter 15.*
