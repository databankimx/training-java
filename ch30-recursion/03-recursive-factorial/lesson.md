## Recursive factorial

A factorial, written n!, is the product of all the whole numbers from 1 to n. The factorial of 4 is 1 × 2 × 3 × 4 = 24, and by definition 0! is 1.

The definition has three cases:

* 0! = 1
* 1! = 1
* n! = n × (n − 1)! for n > 1

The first two cases give the same answer, so they form one base case: any n from 0 to 1 returns 1. The third case contains `(n − 1)!`, which is a smaller factorial. That makes the formula a natural recursive solution.

### The recursive method

Here's the method from `src/Main.java`:

```java
static long factorial(int n) {
    if (n < 0) {
        throw new IllegalArgumentException("n must be a non-negative integer!");
    }
    if (n < 2) {
        return 1;
    }
    return n * factorial(n - 1);
}
```

The first check handles invalid input. A negative number has no factorial, so the method throws an exception. The second check is the base case. The last line is the recursive case, and it's the formula almost word for word.

The Python version of this lesson prints a message and returns `None` for invalid input, so every caller has to check the result. Java programs report invalid input with an exception instead, so a caller that forgets to handle it fails at once rather than carrying on with a missing value. This lesson uses the Java approach.

### The result type

The method returns a `long` rather than an `int`. A 32-bit `int` overflows at 13!, which is larger than its maximum value. A `long` holds values up to about 9.2 × 10^18, which is enough for 20!. Even so, the factorial grows so quickly that a `long` overflows at 21!. The overflow would be silent, so a program that needs larger values should use `BigInteger`, which the course doesn't cover here.

Press **Run**. The output is:

```text
0! = 1
1! = 1
2! = 2
3! = 6
4! = 24
5! = 120
6! = 720
7! = 5040
8! = 40320
9! = 362880
20! = 2432902008176640000
n must be a non-negative integer!
```

The last line is the message from the exception. The program catches it and prints the message, so the rest of the program continues.

### The recursion depth

The method calls itself once for each step down from n to 1. For n = 20, that's 20 calls, which is far below the depth where a `StackOverflowError` would occur. The recursion depth is the same as the value of n, so the method is safe for any n that the `long` result can hold. The next steps look at the cost of this approach, and at a loop that does the same work.

---

### Quiz

*These questions are written for this course.*

````quiz
Why is the recursive case `n * factorial(n - 1)` rather than `n * factorial(n + 1)`?
○ Because multiplying by a larger number is not allowed.
→ Not quite. The multiplication is fine. The problem is the direction of the call.
◉ Because each call must move towards the base case, and n - 1 is one step closer to 1.
→ Yes. A call that moves away from the base case never ends.
○ Because factorial is defined only for even numbers.
→ Not quite. The definition works for any non-negative whole number.
````

````quiz
Why does the method return a `long`?
○ Because `int` can't hold the number 1.
→ Not quite. An int holds 1 without difficulty.
◉ Because an int overflows at 13!, while a long holds 20! without overflow.
→ Yes. The wider type is needed for the larger factorials.
○ Because `long` is faster than `int`.
→ Not quite. The reason is the range of values the type can hold.
````

---

### Try it in the runner

Change `factorial(20)` to `factorial(21)`, and predict the output before you press **Run**. The result is negative: 21! is larger than the largest `long`, so it wraps around. That's the silent overflow the lesson describes.

---

A recursive definition translates directly into a recursive method, and the base case and the error check go first. The next step traces the calls, to show how each one waits for the next.
