## Multidimensional arrays

A list is one dimension. A table, such as a grid of cells or a timetable, needs two: a row and a column. Java builds those as an array of arrays, so `int[][]` is an array whose elements are `int[]` arrays.

Here's the start of this step's program, in `src/Main.java`:

```java
int[][] table = {
    {1, 2, 3},
    {4, 5, 6}
};
System.out.println("Rows: " + table.length);
System.out.println("Columns in row 0: " + table[0].length);
System.out.println("Row 1, column 2: " + table[1][2]);
```

Press **Run**. The output is:

```text
Rows: 2
Columns in row 0: 3
Row 1, column 2: 6
```

The outer curly braces hold the rows, and each inner pair holds one row's values. `table.length` is the number of rows, which is 2. `table[0].length` is the number of columns in row 0, which is 3.

Each element is reached with two indexes, one in each pair of square brackets. The row comes first, then the column, and both count from zero. `table[1][2]` is row 1 and column 2, which holds 6.

### Creating an empty grid

The second half of the program creates a grid with `new`, giving the number of rows and then the number of columns:

```java
int[][] grid = new int[2][3];
grid[0][0] = 7;
grid[1][2] = 9;
System.out.println("grid[0][0]: " + grid[0][0]);
System.out.println("grid[0][1] (not set yet): " + grid[0][1]);
System.out.println("grid[1][2]: " + grid[1][2]);
```

The output is:

```text
grid[0][0]: 7
grid[0][1] (not set yet): 0
grid[1][2]: 9
```

Every element starts at the default for its type, as in the last step, so `grid[0][1]` prints 0 even though nothing set it. The grid has two rows and three columns, so the valid indexes are 0 and 1 for the row, and 0, 1, and 2 for the column.

### Rows can differ in length

Java doesn't require every row to have the same number of columns. Each row is its own array, so a row can be any length:

```java
int[][] ragged = {
    {1},
    {2, 3, 4}
};
```

Here `ragged[0].length` is 1, and `ragged[1].length` is 3. Ragged arrays are rare, and most tables have the same number of columns in every row. Use one only when the data really has that shape.

### The length of each dimension

The length is always found with `.length`, one dimension at a time. `table.length` gives the rows, and `table[0].length` gives the columns in one row. The two can be different, as in the ragged example, so a loop over a grid usually checks the length of each row, not one length for the whole thing.

---

### Quiz

*These questions are written for this course.*

````quiz
In `int[][] table`, what does `table[1][2]` refer to?
○ Row 2, column 1.
→ Not quite. The row index comes first, and it counts from zero.
◉ Row 1, column 2, counting from zero.
→ Yes. The first index is the row, and the second is the column, both from 0.
○ Column 1, row 2.
→ Not quite. The row always comes first.
````

````quiz
For `int[][] grid = new int[2][3];`, what is `grid.length`?
○ 3
→ Not quite. That's the number of columns in each row.
◉ 2
→ Yes. grid.length is the number of rows, which is 2.
○ 6
→ Not quite. There are 6 elements altogether, but the length counts rows.
````

---

### Try it in the runner

Change `grid[1][2] = 9;` to `grid[2][0] = 9;`, and press **Run**. Predict the result. The grid has only two rows, numbered 0 and 1, so row 2 is past the end, and the program throws an `ArrayIndexOutOfBoundsException` at that line. Restore the original line before you continue.

Then add the `ragged` array from the section above to the program, followed by `System.out.println(ragged[0][1]);`, and predict the result. Row 0 has only one column, so index 1 is past its end, and the same exception is thrown. Remove both lines before you continue.

---

A two-dimensional array is an array of rows, so every index is a row and then a column. The summary brings the chapter together.
