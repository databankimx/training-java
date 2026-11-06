## Times table: walkthrough

The outer loop picks the row, and the inner loop picks the column. Each cell prints `row * col`, followed by `\t`, which moves to the next tab stop, so the numbers in the columns line up. After the inner loop finishes, `System.out.println()` moves the output to the next line.

The inner loop starts again from 1 for every row, which is why each row is a full set of products for that row number.
