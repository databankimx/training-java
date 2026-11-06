## Homework: Parallel squares: walkthrough

Each future computes one square, and the futures run in the background at the same time. `allOf(...).join()` waits until all five are ready, and then each `join` returns its square at once.

The squares are 1, 4, 9, 16, and 25, so the total is 55.
