## Homework: Grade book: walkthrough

The highest and lowest scores start from the first score, and each loop replaces the starting value only when it finds a better score. Starting from the first score is what keeps the search correct for any list of values.

The total is kept as an `int`, and it's converted to `double` only for the division. Dividing 315 by 4 as integers would give 78, so the cast is what keeps the fraction. `printf` with `%.2f` shows 78.75.
