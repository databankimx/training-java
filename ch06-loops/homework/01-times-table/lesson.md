## Times table

Write a program that prints a 3 by 3 multiplication table, with each product in its own column.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for a bigger table.

The program should:

* use an outer loop for the rows, from 1 to 3, and an inner loop for the columns, from 1 to 3
* print the product of the row and the column, followed by a tab, for each cell
* end each row with a new line, so the table has three rows

### Where to change the code

The loops set the table's size. To print a bigger table, change the two conditions: `row <= 3` and `col <= 3`. The product is calculated in one line, `row * col`, and the tab character `\t` keeps the columns lined up.

### What to check

The output should be (with tabs between the numbers):

```text
1	2	3	
2	4	6	
3	6	9	
```

Each row has three products, and a row ends with a tab before its new line. The first row is 1 times 1, 1 times 2, and 1 times 3.

### Extensions

* Print the table from 1 to 10, using a single change to the conditions.
* Print a header row with the numbers 1 to 3 above the columns, before the table starts.

---

*This exercise is not from the book. It practices the ideas in Chapter 6.*
