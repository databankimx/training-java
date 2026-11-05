## Homework: Count set bits: walkthrough

Each pass of the loop uses `value & (value - 1)`, which removes the lowest 1 bit. For 45, which is `101101`, the passes give `101100`, `101000`, `100000`, and then 0, so the loop runs four times, and the count is 4.

`Integer.bitCount` does the same job, and the last line checks that the two methods agree.
