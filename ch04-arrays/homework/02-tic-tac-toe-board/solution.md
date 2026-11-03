## Tic-tac-toe board: walkthrough

The board is a `char[][]`, an array of rows, and each row is an array of three `char` values. Writing the rows in curly braces creates the whole grid at once, with each space written as `' '`.

The move `board[0][2] = 'O';` changes one cell. The first index picks the row, 0, and the second picks the column, 2, so the top-right cell changes.

Each printed row joins its three cells with `|`. The first `+` after a `char` joins to the text on its left, so the whole line is built as one `String`. The diagonal line works the same way: `"Diagonal: "` is text, so each `char` that follows is added to it as a letter, and the result is `Diagonal: XXX`.
