## Homework: Checked average: walkthrough

The array holds 2, 4, 6, and 8, which add up to 20. Dividing 20 by the count of 4 gives 5.0, and `printf` with `%.2f` prints it as 5.00.

The assertion sits at the start of the method. Its condition holds for this array, so the method runs normally. The division uses the `double` cast on the total, so the result keeps its fraction.
