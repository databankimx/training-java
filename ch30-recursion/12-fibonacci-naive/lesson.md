## A naive Fibonacci algorithm

The Fibonacci formula is already recursive, so the most direct program is a translation of the formula into code:

```java
static long fibonacci(int n) {
    calls++;
    if (n == 0 || n == 1) {
        return n;
    }
    return fibonacci(n - 1) + fibonacci(n - 2);
}
```

The base cases are F(0) = 0 and F(1) = 1. Every other value is the sum of the two before it, which is the formula itself. The `calls++` line counts each call, as the previous step did.

### Running it

Press **Run**. The program computes F(40). Java is much faster than the Python version of this lesson, but the number of calls is the same, so the run takes a few seconds. The output is:

```text
Calculated F(40) = 102,334,155
calls = 331,160,281
```

The method made over 331 million calls to compute a single number. The count is the real cost of the method, and it's why the next steps exist.

### Why so many calls?

The problem is repetition. `F(5)` calls `F(4)` and `F(3)`. `F(4)` calls `F(3)` again, and `F(3)` is then worked out a second time from scratch. The same values are calculated over and over, and the waste grows quickly with n.

Counting how often each value is requested for F(5) makes this visible:

| n | calls |
|:-:|:-:|
| 5 | 1 |
| 4 | 1 |
| 3 | 2 |
| 2 | 3 |
| 1 | 5 |
| 0 | 3 |

F(3) is calculated twice, and F(2) three times. For F(5), the method makes 15 calls in total.

The number of calls grows exponentially. For this method, the count for F(n) is 2 × F(n + 1) − 1. Since each Fibonacci number is about 1.618 times the one before, the calls grow roughly as 1.618 to the power n. You can check the figure above: 2 × F(41) − 1 = 2 × 165,580,141 − 1 = 331,160,281.

The problem is not the recursion itself. The method recomputes values it has already found, and the next lesson stops that.

---

### Quiz

*These questions are written for this course.*

````quiz
How many calls does the naive method make for F(5)?
○ 5
→ Not quite. That's the number of distinct values from F(5) down, but many calls repeat them.
◉ 15
→ Yes. The table of calls by value adds up to 15.
○ 32
→ Not quite. That's a power of two, and the count isn't one.
````

````quiz
Why does the call count grow so fast?
○ Because each call takes longer to run.
→ Not quite. The problem is how many calls are made, not how long each takes.
◉ Because each value is recalculated from scratch every time it's needed, and that repeats more as n grows.
→ Yes. Repeated work multiplies, so the count grows exponentially.
○ Because Fibonacci numbers are negative.
→ Not quite. Fibonacci numbers are non-negative.
````

---

### Try it in the runner

Change `n` to 10, and predict the call count before you press **Run**. The formula gives 2 × F(11) − 1 = 2 × 89 − 1 = 177. Then change `n` to 20, and predict the count. It's 2 × F(21) − 1 = 2 × 10,946 − 1 = 21,891, which runs in a moment.

---

The formula gives a correct answer, but it repeats work it has already done. The next lesson keeps the answers it has found, so each value is calculated only once.
