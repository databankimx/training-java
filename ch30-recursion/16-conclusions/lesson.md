## Conclusions

This chapter has looked at several ways to compute Fibonacci numbers. A direct translation of a formula, even one that is naturally recursive, is often not the best algorithm. The better methods avoid repeated work, or avoid recursion altogether.

### The four methods compared

This program runs the four methods on F(40), and counts the calls each one makes:

```text
Naive recursive:    F(40) = 102,334,155, calls = 331,160,281
Memoized recursive: F(40) = 102,334,155, calls = 79
Iterative:          F(40) = 102,334,155, calls = 1
Formula-based:      F(40) = 102,334,155, calls = 1
```

The table shows the trade-off in each method:

* The naive method is the most direct translation of the formula, and the least efficient. It repeats the same work, and its calls grow exponentially.
* The memoized method keeps the formula, and remembers each value. Its calls grow in a straight line with n.
* The iterative method uses a loop and two variables. It makes no recursive calls, and its memory stays fixed.
* The formula-based method does a fixed number of operations, but it is approximate for large n.

The iterative and formula-based methods show one call because they never call themselves. Their work happens inside the one call that the counter records. The counter is an honest measure of the recursion, not of the time each method takes.

### Choosing a method

The right choice depends on the problem and on what it needs. For Fibonacci, the iterative method is exact, fast, and uses fixed memory, so it's the best choice in most programs. The memoized method is worth knowing, since the same idea applies to any recursive problem that repeats its subproblems. The naive method is useful for learning, and for showing why memoization matters. The formula-based method is interesting, but its approximation makes it unsuitable when exact answers matter.

The Python course's chapter shows the same comparison, with the same four results. The counts are the important part of the lesson, since they show why a method's structure matters as much as its formula.

### Measuring before choosing

Each of these conclusions came from counting or timing the methods, not from assuming which one would be best. The steps in this chapter used the same approach on sorting, on factorial, and on the Fibonacci numbers. Measure the work a method does, and compare it, before deciding which method to keep.

---

### Quiz

*These questions are written for this course.*

````quiz
Which method has the fixed memory and no recursion, and is exact for every n that fits in a long?
○ Naive recursive
→ Not quite. The naive method uses the call stack, and its calls grow with n.
◉ Iterative
→ Yes. The loop keeps two values, and it makes no recursive calls.
○ Formula-based
→ Not quite. The formula is approximate for large n.
````

````quiz
Why do the iterative and formula-based methods show one call in the table?
○ Because they don't do any work.
→ Not quite. Both methods do work, which happens inside that one call.
◉ Because they don't call themselves, so the counter records only the call from main.
→ Yes. The count measures recursion, not the time taken.
○ Because the counter is wrong for them.
→ Not quite. The counter is correct, and it measures the calls each method makes.
````

---

### Try it in the runner

Change `n` to 20, and predict the naive call count before you press **Run**. It's 2 × F(21) − 1 = 21,891, which is much smaller. The memoized count is 39, and the iterative and formula counts stay at one.

---

The methods differ most in how much work they repeat. The last two lessons of this chapter move to a problem that is recursive by nature: the file system.
