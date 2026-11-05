## Ch30 · Recursion

Recursion is a method that calls itself. Many problems are naturally recursive: walking through nested folders, sorting, and some sequences such as Fibonacci. This chapter covers how recursive methods work, how to make them stop, how they compare with loops, and where recursion helps and where it wastes time.

The steps are:

* [What is recursion?](#goto/01-what-is-recursion): a method that calls itself, and what happens when nothing stops it.
* [Stopping recursion](#goto/02-stopping-recursion): the base case, and the recursive case that moves towards it.
* [Recursive factorial](#goto/03-recursive-factorial): a recursive formula turned into a method, and the limits of its result type.
* [Tracing recursive calls](#goto/04-tracing-calls): watching the call stack grow and unwind, one call at a time.
* [Iterative factorial](#goto/05-iterative-factorial): the same result with a loop, and values too large for a long.
* [Comparing performance](#goto/06-comparing-performance): timing the recursive, iterative, and stream versions, and why measuring matters.
* [Traversing a nested list](#goto/07-nested-lists): counting the leaves of a tree of lists, where each branch is handled the same way as the whole.

---

*The examples and explanations are new, and the chapter follows the recursion lessons of the Python course's Beyond the Book track.*
