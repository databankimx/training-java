## Ch16 · Searching and sorting

Finding a value in a list, and putting a list in order, are among the most common things a program does. This chapter covers how a search works, why the order of the data changes how fast a search can be, and the main sorting methods. You'll count comparisons and steps as you go, so you can see the cost of each method, not just its result.

The steps are:

* [Linear search](#goto/01-linear-search): checking each value in turn, and the cost of a miss.
* [Binary search](#goto/02-binary-search): halving sorted data at each step.
* [Bubble sort](#goto/03-bubble-sort): repeated swaps of neighbours.
* [Selection sort](#goto/04-selection-sort): choosing the smallest value for each position.
* [Insertion sort](#goto/05-insertion-sort): placing each value into a sorted part of the list.
* [Merge sort](#goto/06-merge-sort): sorting halves and combining them.
* [Quick sort](#goto/07-quick-sort): partitioning around a pivot.
* [The standard library](#goto/08-standard-library): the library's own search and sort methods.
* [Summary](#goto/09-summary): a recap of the chapter and a short quiz. The homework is listed under the chapter.

---

*The examples and explanations are new.*
