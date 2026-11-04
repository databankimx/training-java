## Homework: Safe division: walkthrough

`safeDivide` checks the divisor first. When it's zero, there's no quotient, so the method returns an empty `Optional`. Otherwise it wraps the quotient in `Optional.of`.

`report` checks `isPresent` before it calls `get`, so the empty case prints `no answer` and never reads a missing value. The three calls print 5, no answer, and 3.
