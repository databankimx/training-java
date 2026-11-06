## Ch30 · Recursion

Recursion is a method that calls itself. Many problems have a recursive shape: a formula defined in terms of smaller values, a tree of lists or folders, a sorting method that divides a list. This chapter covers how recursive methods work and where they stop, how their cost shows up in the number of calls, and how to write the same work with a loop or with memoization when recursion is too expensive.

The steps are:

* [What is recursion?](#goto/01-what-is-recursion): a method that calls itself, and what happens when nothing stops it.
* [Stopping recursion](#goto/02-stopping-recursion): the base case, and the recursive case that moves towards it.
* [Recursive factorial](#goto/03-recursive-factorial): a recursive formula turned into a method, and the limits of its result type.
* [Tracing recursive calls](#goto/04-tracing-calls): watching the call stack grow and unwind, one call at a time.
* [Iterative factorial](#goto/05-iterative-factorial): the same result with a loop, and values too large for a long.
* [Comparing performance](#goto/06-comparing-performance): timing the recursive, iterative, and stream versions, and why measuring matters.
* [Traversing a nested list](#goto/07-nested-lists): counting the leaves of a tree of lists, where each branch is handled the same way as the whole.
* [An iterative solution for nested lists](#goto/08-iterative-lists): the same count with an explicit stack of iterators.
* [Detecting palindromes](#goto/09-palindromes): cleaning text, then checking it recursively and with a loop.
* [Recursive quicksort](#goto/10-quicksort): dividing a list around a pivot, and sorting each part the same way.
* [Counting the calls](#goto/11-call-counter): a counter that shows what a recursive method costs.
* [A naive Fibonacci algorithm](#goto/12-fibonacci-naive): a direct translation of the formula, and the repeated work it does.
* [Storing previous values: memoization](#goto/13-fibonacci-memoized): remembering each value, so it's worked out once.
* [Do I have to use recursion?](#goto/14-fibonacci-iterative): the same Fibonacci numbers with a loop.
* [A formula for Fibonacci](#goto/15-fibonacci-formulaic): the closed-form formula, and where its rounding error appears.
* [Conclusions](#goto/16-conclusions): the four Fibonacci methods compared by their calls.
* [Recursion on the file system](#goto/17-file-system): listing and measuring a folder tree.
* [Summary](#goto/18-summary): a recap of the chapter and a short quiz. The homework is listed under the chapter.

---

*The examples and explanations are new, and the chapter follows the recursion lessons of the Python course's Beyond the Book track.*
