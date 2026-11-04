## Homework: Lower bound

Java's library has no `lower_bound`, and `Arrays.binarySearch` finds some matching value rather than the first one. When the list has repeated values, that difference matters. In this exercise, you write the search that returns the first position.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

Write `lowerBound(int[] values, int target)`, which returns the index of the first value that is not smaller than `target`, or the length of the array when every value is smaller. Use binary search: keep a range of indexes, check its middle, and throw away the half that can't contain the answer.

The list must be sorted, and it may repeat values. For the list {1, 3, 3, 3, 5, 8}:

* the target 3 gives index 1, the first of the three 3s
* the target 4 gives index 4, the position of the 5
* the target 9 gives index 6, the length of the list, since every value is smaller

The program should print each answer, and check it against a plain linear scan for the same list. The linear scan is the reference, since it's simple enough to be obviously correct.

### What to check

The output should be:

```text
lowerBound(3): 1
lowerBound(4): 4
lowerBound(9): 6
Matches linear scan: yes
```

The duplicated 3s are the test that matters most. A search that stops at the first 3 it finds, rather than the first one in the list, will print 2 or 3.

### Why high is the length

The example keeps `high` as the position just past the range, so the range `[low, high)` is empty when `low` equals `high`. A target smaller than every value makes `high` shrink to 0, and the answer is 0, with no special case. Trace that by hand before you run it.

### Extensions

* Write a version that keeps `high` as the last index of the range instead, with `high = values.length - 1`. Compare how the two versions handle a target larger than every value.
* Explain why this search makes about log₂n comparisons, and why it still works when the list has duplicates.

---

*This exercise is not from the book. It practices the ideas in Chapter 16, with the binary search from the binary search step.*
