## Summary: Recursion

This chapter covered methods that call themselves, and the ways to make them correct, efficient, and readable.

* **A recursive method needs a base case.** Without one, the calls never stop, and the call stack runs out with a `StackOverflowError`.
* **Each recursive call must move towards the base case.** The recursive case reduces the problem, such as `n - 1`, so the base case is always reached.
* **A recursive formula can translate directly into code.** Factorial and palindromes both follow their definitions closely.
* **Each call adds a frame to the stack.** The depth of the recursion, not the number of steps, decides when the stack runs out.
* **An iterative version does the same work with a loop.** A loop uses fixed stack space, and an explicit stack can replace the call stack when needed.
* **Repeated work makes recursion expensive.** The naive Fibonacci method calls itself over 331 million times for F(40).
* **Memoization stores each result.** With a memo, each value is worked out once, and the calls grow in a straight line.
* **Measuring is the only reliable test.** Count the calls, and time the methods, before choosing one.
* **Trees are naturally recursive.** Nested lists and folders are handled by treating each branch as a smaller copy of the whole.

### Check your understanding

*These questions are written for this course.*

````quiz
What must a recursive method always have to finish?
○ A loop inside it.
→ Not quite. A loop isn't required, and a recursive method can finish without one.
◉ A base case, and a recursive case that moves towards it.
→ Yes. The base case stops the calls, and the recursive case brings the problem closer to it.
○ A static variable.
→ Not quite. Static variables aren't needed for recursion.
````

````quiz
Why does the memoized Fibonacci method make far fewer calls than the naive method?
○ Because it uses a loop instead of calls.
→ Not quite. The memoized method still recurses. The difference is what it stores.
◉ Because each value is stored after it's worked out, so a repeated request is answered at once.
→ Yes. The stored value stops the method from repeating its own calculation.
○ Because it returns earlier values without computing them.
→ Not quite. Each value is computed once, the first time it's needed.
````

````quiz
Why does the file system program leave out the `classes` folder when it adds up sizes?
○ Because the classes folder can't be read.
→ Not quite. The folder can be read, and the reason is what it contains.
◉ Because the compiled classes aren't part of the project's own size, and they would be counted without the skip.
→ Yes. The skip list limits the total to the project's own files.
○ Because folders named classes are always empty.
→ Not quite. The folder can contain files, which is why it's skipped.
````
