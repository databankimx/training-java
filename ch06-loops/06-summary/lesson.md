## Summary: Loops

This chapter covered the loops that repeat work, and the statements that change how they run.

* **`while` checks before each pass.** It may run zero times.
* **`do-while` checks after each pass.** Its block always runs at least once.
* **Every `while` needs a change.** Something in the body must eventually make the condition false.
* **`for` puts the counter in one line.** It has a start, a condition, and a step, and the counter is local to the loop.
* **Nested loops multiply.** The inner loop runs fully for each pass of the outer one.
* **The enhanced `for` visits each element.** `for (int x : array)` needs no index, and it can't go past the end.
* **The enhanced loop variable is a copy.** Changing it doesn't change an `int` element of the array.
* **`break` ends the loop.** The program continues after the loop's closing brace.
* **`continue` skips to the next pass.** The rest of the body is skipped, and a `for` loop still runs its step.
* **Grids use nested loops.** The outer loop picks the row, and the inner loop picks the column.

### Check your understanding

*These questions are written for this course.*

````quiz
Which loop is the right choice when the block must run at least once, whatever the condition?
○ while
→ Not quite. A while loop may run zero times.
◉ do-while
→ Yes. Its condition is checked after the first pass.
○ enhanced for
→ Not quite. An enhanced for runs once for each element, and it may run zero times for an empty array.
````

````quiz
How many lines does this print? `for (int i = 1; i <= 5; i++) { if (i == 2) { continue; } System.out.println(i); }`
○ 4
→ Yes. The value 2 is skipped, and 1, 3, 4, and 5 are printed.
○ 5
→ Not quite. continue skips the println for i equal to 2.
○ 3
→ Not quite. Only the pass for 2 is skipped.
````

````quiz
In a nested loop over a 3 by 4 grid, how many times does the innermost statement run?
○ 7
→ Not quite. That's the sum of the rows and columns, not the number of cells.
◉ 12
→ Yes. There are 3 rows, and 4 cells in each, so 3 times 4 is 12.
○ 3
→ Not quite. That's the number of rows, and the innermost statement runs once per cell.
````
