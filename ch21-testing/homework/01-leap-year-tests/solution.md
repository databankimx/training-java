## Homework: Leap-year tests: walkthrough

`isLeapYear` applies the rule in two parts. A year divisible by 4 and not by 100 is a leap year. A year divisible by 400 is a leap year too, which covers the centuries that are leap years, such as 2000. 1900 is divisible by 4 and by 100, but not by 400, so it isn't.

The `Tests` class checks the same four cases, and it counts failures in the same way. Its exit code is 0 when all four pass, which the runner reports as a passing test.
