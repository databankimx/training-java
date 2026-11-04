## Homework: Rectangle scale: walkthrough

`area` multiplies the two components of the rectangle it receives, and it doesn't change the record.

`scale` multiplies both sides by the factor, and returns a new rectangle. `main` stores the result in `r`, so the second area is computed from the scaled rectangle. Both sides are doubled, so the area is four times the first.

The `printf` with `%.2f` prints the two decimal places the output expects.
