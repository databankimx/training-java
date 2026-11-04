## Homework: Word tally: walkthrough

The first loop counts each word with `getOrDefault` and `put`, so each word's count goes up by one as it appears. The `TreeMap` keeps the words sorted, so the printed lines come out in alphabetical order.

The second loop finds the best word. `best` and `bestCount` start empty, and each entry replaces them only when its count is higher. Because the comparison is on counts, the answer doesn't depend on the order of the entries, and `red` wins with 3.
