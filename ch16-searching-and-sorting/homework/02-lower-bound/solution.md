## Homework: Lower bound: walkthrough

The range `[low, high)` starts as the whole array. Each pass checks the middle. If the middle value is smaller than the target, the answer must be after it, so `low` moves past the middle. Otherwise the middle could be the answer, or an earlier value could be, so `high` moves to the middle, keeping it in range.

When the duplicated 3s are checked, the search moves `high` to the middle each time it finds a 3, so it keeps the first one. The loop ends when the range is empty, and `low` is the first position not smaller than the target.
