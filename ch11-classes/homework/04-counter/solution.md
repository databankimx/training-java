## Homework: Counter: walkthrough

The starting-value constructor stores the count. The no-argument constructor passes zero to it with `this(0)`, so `new Counter()` starts at zero by the same route.

`increment` adds one to the count, and `reset` sets it back to zero. `value` returns the count, and the `println` calls print it after each step. The second counter starts at 10, so one increment gives 11.
