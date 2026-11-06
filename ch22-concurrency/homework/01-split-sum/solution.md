## Homework: Split sum: walkthrough

`sumRange` adds its range into a local variable, then stores the total in its own array element once. The first thread covers 1 through 500, which sums to 125,250, and the second covers 501 through 1,000, which sums to 375,250.

The main method reads both arrays only after `join` has returned, so both threads are finished before the results are added together. The total is 500,500.
