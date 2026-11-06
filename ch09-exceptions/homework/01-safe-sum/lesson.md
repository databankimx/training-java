## Homework: Safe sum

The program has five text values, some of which aren't numbers. Add up the ones that are, skip the ones that aren't, and report both the total and the number skipped.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own values.

The program should:

* start with the array `{"12", "abc", "30", "", "7"}`
* convert each value with `Integer.parseInt`, and add it to a total
* catch the `NumberFormatException` for a value that isn't a number, and count it as skipped instead
* print `Total: ` followed by the total, and `Skipped: ` followed by the count

### Where to change the code

The `try` wraps only the conversion and the addition, so the catch handles exactly the failure it expects. To use different values, change the array. The catch doesn't change, because it works for any value that fails to convert.

### What to check

The output should be:

```text
Total: 49
Skipped: 2
```

The total is 12 plus 30 plus 7, which is 49. The values `"abc"` and the empty string `""` are skipped, so the skipped count is 2.

### Extensions

* Print each skipped value, using the array index, as the exception is caught. You'll need the index, so think about how the loop gets it.
* Check whether a `catch` for the general `Exception` type would also work here, and decide why the specific type is the better choice.

---

*This exercise is not from the book. It practices the ideas in Chapter 9.*
