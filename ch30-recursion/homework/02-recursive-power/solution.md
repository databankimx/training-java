## Homework: Recursive power: walkthrough

`power(2, 10)` makes ten multiplications, each one using the result of the call for the exponent one lower, until the base case returns 1. The result is 1024. For a negative exponent, `exp` would move away from zero and never reach the base case.
