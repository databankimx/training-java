## Homework: Tic-tac-toe board

A tic-tac-toe board is a grid of three rows and three columns, where each cell is empty, `X`, or `O`. Store the board in a two-dimensional `char` array, and print it.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own board.

The program should:

* create a `char` array of three rows and three columns, filled as the starting board shows, with a space for each empty cell
* place an `O` in the top-right cell, which is row 0, column 2
* print each row on its own line, with a `|` between the cells
* print the diagonal from the top-left cell to the bottom-right cell, with the text `Diagonal: ` in front

### Where to change the code

The board is created with the rows written out in curly braces, and the move is one assignment:

```java
board[0][2] = 'O';
```

To place a different mark, change the row and column indexes, remembering that both count from 0. Check that the cell is empty first, if you want the program to refuse a move on an occupied cell. The `if` lesson shows how to do that check.

### What to check

With the board above, the output should be:

```text
X|O|O
 |X|O
 | |X
Diagonal: XXX
```

The row 1 line starts with a space, because the top-left cell of the second row is empty. The diagonal is `XXX`, because the three cells on it all hold `X`.

### Extensions

* Print the empty cells as numbers from 1 to 9, so a player can choose a cell by number. You'll need to map each number to a row and a column.
* Check whether the diagonal is all the same mark, and print the winner. Think about which expression gives the test, using the logical operators from the last chapter.

---

*This exercise is not from the book. It practices the ideas in Chapter 4.*
