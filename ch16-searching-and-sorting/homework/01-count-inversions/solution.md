## Homework: Count inversions: walkthrough

`countInversions` checks each pair of positions once, with `j` starting one place after `i`, so every pair is counted exactly once. The pair counts when the earlier value is larger.

`insertionMoves` works on a clone, so the caller's array is unchanged. Each swap in the insertion sort fixes exactly one out-of-order pair, so the number of moves equals the number of inversions. For {5, 1, 4, 2, 3}, both come to 6.
