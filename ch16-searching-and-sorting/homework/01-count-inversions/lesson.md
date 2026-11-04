## Homework: Count inversions

An inversion is a pair of values that are out of order: a value at an earlier index that is larger than a value at a later index. The list {5, 1, 4, 2, 3} has six inversions, one for each of these pairs: (5, 1), (5, 4), (5, 2), (5, 3), (4, 2), and (4, 3).

Insertion sort makes one move for each inversion, since every move swaps an out-of-order pair. So the number of inversions is also the number of moves insertion sort makes.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* write `countInversions(int[] values)`, which checks every pair of positions with two nested loops, and returns the number of inversions
* write `insertionMoves(int[] original)`, which copies the list with `clone`, runs insertion sort on the copy, and returns the number of moves it makes; the copy keeps the caller's list unchanged
* print the count from each method for the list {5, 1, 4, 2, 3}, and say whether they match

### What to check

The output should be:

```text
Inversions: 6
Insertion sort moves: 6
Match: yes
```

The second list in the example is {1, 2, 3, 4, 5}, which is already in order. Both numbers should be 0.

### Why clone

Java passes an array by reference, so `insertionMoves` would change the caller's list if it sorted the array it was given. `clone` makes a copy first, so the list that `main` holds is the same before and after the call. This is the reference behavior from the references chapter.

### Extensions

* Count the comparisons that the nested loops make for a list of n values. Say how that count compares with insertion sort's comparisons in the worst case.
* Explain why the nested loop is O(n²), and what a merge-sort-based count would cost instead. You don't need to write it; the merge sort step shows the approach.

---

*This exercise is not from the book. It practices the ideas in Chapter 16.*
