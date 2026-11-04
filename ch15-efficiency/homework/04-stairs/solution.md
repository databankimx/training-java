## Homework: Stairs: walkthrough

The stairs recursion has the same shape as the Fibonacci recursion, with base cases that return 1 instead of 0 and 1. The call tree is the same, so the naive count matches the Fibonacci count for the same n.

Both base cases return 1, so the recursive helper needs only one test, for n below 2. The memoized helper checks the map before it recurses, and stores each answer once, so each value from 2 up to n is worked out once.
