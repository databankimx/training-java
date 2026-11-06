## Fibonacci: one problem, three costs

The Fibonacci sequence starts with 0 and 1, and each later number is the sum of the two before it: 0, 1, 1, 2, 3, 5, 8, and so on. Its definition is recursive, since the value for `n` is defined in terms of the values for `n - 1` and `n - 2`. That makes it the usual first example of recursion, and the usual first example of a recursion that is correct but slow.

Here's the program for this step, in `src/Main.java`. It has three versions of the calculation:

```java
static long fibNaiveHelper(int n, long[] calls) {
    calls[0]++;
    if (n < 2) {
        return n;
    }
    return fibNaiveHelper(n - 1, calls) + fibNaiveHelper(n - 2, calls);
}
```

The naive helper follows the definition exactly, and it's easy to read. The `calls` parameter counts how many times the method runs. Java can't pass a `long` by reference, so the counter is a one-element array, and every call adds to the same element. The functions chapter covered why an array passed to a method is shared with the caller, and that's the reason this works.

Press **Run**. The output is:

```text
n = 10: value 55, naive calls 177, memoized calls 19
n = 20: value 6,765, naive calls 21,891, memoized calls 39
n = 30: value 832,040, naive calls 2,692,537, memoized calls 59
n = -1 refused: n must not be negative
```

### The naive count grows fast

Look at the naive column. Ten more steps in `n` multiply the calls by about a hundred. Each call to `fibNaive(n)` makes two more calls, one for `n - 1` and one for `n - 2`, and those make two more each. The calls form a tree, and the tree is nearly as large as the Fibonacci number it computes. The count grows at the same rate as the value itself, which grows exponentially. The program counts the calls directly, so the growth is measured rather than assumed.

Most of that work is repeated. `fibNaive(20)` calls `fibNaive(18)` directly, and it also calls `fibNaive(19)`, which calls `fibNaive(17)` and `fibNaive(18)` again. The value for `18` is worked out twice, and the value for `17` is worked out three times. The repetition is the whole problem.

---

### Remember the answers

The memoized version fixes the repetition. The map stores each value the first time it's worked out, and a later call for the same `n` returns the stored value straight away. Here is the whole helper, as it appears in the program:

```java
static long fibMemoHelper(int n, Map<Integer, Long> memo, long[] calls) {
    calls[0]++;
    if (n < 2) {
        return n;
    }
    if (memo.containsKey(n)) {
        return memo.get(n);
    }
    long result = fibMemoHelper(n - 1, memo, calls) + fibMemoHelper(n - 2, memo, calls);
    memo.put(n, result);
    return result;
}
```

The two lines that matter are the check on `memo.containsKey(n)`, which finds the stored answer, and the line `memo.put(n, result)`, which stores a new one.

Each value from 2 up to `n` is worked out once, and each one makes two calls. The count works out to `2n - 1`, which is why the memoized column grows by 2 per step rather than by a factor. The `HashMap` from the collections chapter is what makes this possible, since it's a place to keep the answers while the recursion runs.

---

### The loop is the cheapest

The loop version doesn't recurse at all. It keeps the last two values, and moves forward one step at a time. Its work is `n` passes through the loop, which is the simplest possible growth: O(n), with no stored map and no call tree.

```java
static long fibIterative(int n) {
    long previous = 0;
    long current = 1;
    for (int i = 0; i < n; i++) {
        long next = previous + current;
        previous = current;
        current = next;
    }
    return previous;
}
```

The three columns in the output agree. The program checks that they do, and it stops with an error if any two of the versions give different values.

Recursion isn't wrong. It's the most direct way to write a definition like this one. The lesson is that a recursive definition can hide a large amount of repeated work, and that a program should be checked for it, either by counting or by reasoning about the tree of calls.

### Checking the input

The Fibonacci sequence is defined for whole numbers from zero upwards, so the input has to be checked. The check goes in a public method, which runs once for each call from `main`. The recursion itself happens in a private helper, which never sees a negative value:

```java
static long fibNaiveHelper(int n, long[] calls) {
    calls[0]++;
    if (n < 2) {
        return n;
    }
    return fibNaiveHelper(n - 1, calls) + fibNaiveHelper(n - 2, calls);
}

static long fibNaive(int n, long[] calls) {
    if (n < 0) {
        throw new IllegalArgumentException("n must not be negative");
    }
    return fibNaiveHelper(n, calls);
}
```

The recursive calls only ever pass `n - 1` and `n - 2` from a value of two or more, so they can't go negative. Checking them would do nothing, so the helper leaves the check out. The loop version has no recursion, so its one check at the top is all it needs.

Without the check, a negative `n` would never reach a base case. `n - 1` and `n - 2` would keep going below zero, and the recursion would run until the program runs out of stack space and fails with a `StackOverflowError`. The exception stops that at the first call, and `main` catches it and prints the message. Because the check comes before `calls[0]++`, a refused call isn't counted.

Java has no unsigned integer type, so the check on a signed value is the only option. A negative value cannot be turned into a valid input by a type, which is why the check is needed.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does the naive Fibonacci recursion repeat so much work?
○ Because the compiler runs each call twice.
→ Not quite. Each call is made once, but the calls overlap with each other.
◉ Because the same smaller values are needed by many different calls, and each one is worked out again.
→ Yes. The tree of calls reaches the same values through different paths.
○ Because the sequence has no base case.
→ Not quite. It has base cases at 0 and 1, which stop the recursion.
````

````quiz
How many calls does the memoized version make for `n = 10`, according to the output?
○ 10
→ Not quite. The count is about twice n, since each value makes two calls.
◉ 19
→ Yes. The count is 2n - 1, so 2 times 10 minus 1.
○ 177
→ Not quite. That's the naive count, which repeats work.
````

---

### Try it in the runner

Add 40 to the `sizes` array, and predict the naive call count before you press **Run**. The formula is 2 times the Fibonacci number for 41, minus 1, which is 331,160,281. That's more than three hundred million calls, so the program takes a few seconds. Predict the memoized count too. It's 79.

Then remove the line `memo.put(n, result);` from `fibMemoHelper`, and predict the memoized count for `n = 10`. The map is then never filled, so the memoized version makes the same calls as the naive one, 177. The stored answers are what make the difference. Restore the line before you continue.

---

The naive recursion is clear and expensive, the memoized recursion keeps the clarity and removes the repeated work, and the loop is cheapest. The next step puts these counts into a summary of how to read a growth rate from a program.
