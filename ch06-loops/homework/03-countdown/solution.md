## Homework: Countdown: walkthrough

The loop starts at the number the user typed and runs while `i` is above zero. Each pass prints the counter, then `i--` moves it down by one. When the counter reaches 0 the condition is false, so the loop stops, and `Liftoff!` is printed after it.

Using `i > 0` is what stops the countdown at 1. With `i >= 0`, the loop would also print 0, which isn't wanted. If the user enters 0, the condition is false from the start, so the loop never runs and only `Liftoff!` is printed.
