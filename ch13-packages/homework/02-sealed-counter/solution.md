## Sealed counter: walkthrough

`total` is private, so `Main` can only read it through `total()`, and can only change it through `addOne()`. Because the step is a `private static final` constant, no caller can alter how much each addition adds.

`resetInternal` has package access. `Main` is in the default package, so it can't call that method, which is the protection the exercise is about. A class in the `tally` package could call it, and nothing else could.

The output is 10, because each `addOne` adds the private step of 5, and two calls are made.
