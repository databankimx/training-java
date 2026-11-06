## Homework: Highest score: walkthrough

The highest value starts as the first score. Each later score replaces it only when it's greater, so the comparisons work for any three values, including ties. The `>` test means a tie keeps the value already stored, which is the same number in either case.

The scores are read into the array first, so the comparisons work on the stored values. Changing the array's elements later in the program would give the same result, since the code always reads them by index.
