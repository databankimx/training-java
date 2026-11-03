## Summary: Arrays

This chapter covered storing a list of values of one type under one name.

* **An array holds a fixed number of values of one type.** `int[] scores = new int[5];` creates room for five `int` values.
* **Indexes start at 0.** The first element is at 0, and the last is at `length - 1`.
* **`length` is a field.** `scores.length` has no parentheses. A `String` uses `length()`, which is a method.
* **Java checks every index.** An index outside the array throws an `ArrayIndexOutOfBoundsException` when the program runs, rather than reading unrelated memory.
* **Curly braces fill an array as it's created.** `{2, 3, 5}` sets the values and the size together.
* **Defaults fill every new element.** Numbers start at 0, `boolean` values at `false`, and object references at `null`.
* **`null` means no object.** Calling a method on a `null` element throws a `NullPointerException`.
* **A two-dimensional array is an array of rows.** `table[row][column]` reads one element, counting from 0 in both.
* **Each dimension has its own length.** `table.length` is the number of rows, and `table[0].length` is the number of columns in that row.
* **Rows can differ in length.** Java allows ragged arrays, but most tables keep every row the same length.

### Check your understanding

*These questions are written for this course.*

````quiz
Which index is past the end of `int[] values = new int[4];`?
○ 3
→ Not quite. The last index of a four-element array is 3.
◉ 4
→ Yes. The valid indexes are 0 to 3, so 4 is past the end.
○ 0
→ Not quite. The first index is 0, and it's valid.
````

````quiz
What does an unset `String` element of an array hold?
○ An empty string.
→ Not quite. Its default is null, not an empty string.
◉ null
→ Yes. Object elements start as null.
○ The character 0.
→ Not quite. That's the default for a char, not a String.
````

````quiz
For `int[][] grid = new int[3][5];`, what does `grid[0].length` give?
○ 3
→ Not quite. That's the number of rows, which is grid.length.
◉ 5
→ Yes. grid[0] is the first row, and it has 5 columns.
○ 15
→ Not quite. That's the total number of elements, not the length of one row.
````
