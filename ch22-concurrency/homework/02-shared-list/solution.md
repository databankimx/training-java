## Homework: Shared list: walkthrough

Each thread adds its five numbers one at a time, and each `add` is wrapped in a `synchronized` block on the same lock object. Two `add` calls can't overlap, so the list's internal structure isn't damaged, and every value is added exactly once.

The main method reads the list only after both `join` calls return. The size is 10 and the sum is 55 (1 through 10), whatever order the values arrived in.
