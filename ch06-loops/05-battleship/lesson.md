## Battleship

This step puts the chapter to work in one program. A Battleship board is a grid, so it's a two-dimensional array. The ship is placed on the board, the shots are checked against it, and the board is printed. Each of those jobs is a loop.

Here's the complete program, in `src/Main.java`. Read it in parts, since each part uses a different loop.

```java
char[][] board = new char[4][4];
for (int row = 0; row < 4; row++) {
    for (int col = 0; col < 4; col++) {
        board[row][col] = '.';
    }
}
```

The first loops fill the board with `.` for empty water. The outer loop goes over the rows, and the inner loop goes over the columns in each row. Every cell is visited exactly once.

```java
board[2][1] = 'S';
board[2][2] = 'S';
board[2][3] = 'S';
```

Three cells of row 2 hold the ship, written with `S`. The ship is fixed here, so the same game plays out every time.

```java
int[][] shots = {{0, 0}, {2, 2}, {1, 3}, {2, 3}};
int hits = 0;
for (int[] shot : shots) {
    int row = shot[0];
    int col = shot[1];
    if (board[row][col] == 'S') {
        System.out.println("Hit at " + row + "," + col);
        board[row][col] = 'X';
        hits++;
    } else {
        System.out.println("Miss at " + row + "," + col);
    }
}
```

Each shot is a pair of numbers, the row and the column. The enhanced `for` goes through the shots in order. The `if` checks the cell: a `'S'` is a hit, and the cell is marked `'X'` so it can't be counted twice. Anything else is a miss.

Press **Run**. The output is:

```text
Miss at 0,0
Hit at 2,2
Miss at 1,3
Hit at 2,3
Hits: 2
```

The last four lines show the board after the shots. Each row is printed on one line, with a loop over its columns.

```text
....
....
.SXX
....
```

Row 2 shows the ship: the `S` at column 1 hasn't been hit yet, and the two `X` marks are the hits.

### What the nested loops do

The board is printed by two loops, one inside the other. The outer loop runs once for each row, and the inner loop prints the cells of that row. `System.out.print` keeps the cells on one line, and `System.out.println()` moves to the next row.

The same pattern appears in the setup, where the outer loop picks the row and the inner loop picks the column. Whenever you work on every cell of a grid, the nested loop is the form to reach for.

### Changing the game

The ship and the shots are data, so you change the game by changing the data. Move the ship by changing the `S` cells, or add a shot to the `shots` array. The loops don't need to change at all, because they read the board and the shots as they are. That's the point of keeping the rules in loops rather than in one line for each cell.

---

### Quiz

*These questions are written for this course.*

````quiz
How many times does the inner loop in the board setup run, in total, for a 4 by 4 board?
○ 4
→ Not quite. The inner loop runs 4 times for each row, once per column.
◉ 16
→ Yes. There are 4 rows, and the inner loop runs 4 times for each one.
○ 8
→ Not quite. That would be 4 rows of 2 columns, which isn't the board.
````

````quiz
Why does the program change a hit cell from `S` to `X`?
○ So the board looks more colorful.
→ Not quite. The change has a job in the rules, not in the appearance.
◉ So the same cell can't be counted as a hit twice.
→ Yes. Once the cell isn't `S`, a second shot at it is a miss.
○ Because `X` is the only character the printer accepts.
→ Not quite. The printer prints any character.
````

---

### Try it in the runner

Change the last shot from `{2, 3}` to `{2, 1}`, and predict the output. The cell at row 2, column 1 still holds the ship, so that shot is a hit. The shot at column 3 is no longer made, so the total is 2, not 3. The board shows the cell at column 1 as `X`.

Then add a shot `{2, 3}` at the end of the `shots` array, and predict the output for that shot. It's a miss, because the cell is already `X`, not `S`. Remove the added shot before you continue.

---

A nested loop works over a grid, one row at a time. The summary gathers the chapter together, and the homework uses loops on a board of your own.
