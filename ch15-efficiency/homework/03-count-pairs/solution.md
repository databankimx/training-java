## Homework: Count pairs: walkthrough

The inner loop starts at `i + 1`, so each pair is counted once, with the smaller position first. Starting at 0 would count every pair twice, and would also count each item paired with itself.

`pairFormula` multiplies before it divides. The cast to `long` makes the multiplication happen in `long` arithmetic, so the product doesn't overflow for larger n. The division by 2 is exact, because n times (n − 1) is always even.
