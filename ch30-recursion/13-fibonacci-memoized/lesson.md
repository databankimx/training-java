## Storing previous values: memoization

Memoization means storing the results of calls in a memo, which is a record of values already worked out. The word comes from the Latin *memorandum*, meaning "to be remembered." When a value is needed again, the method looks it up in the memo instead of computing it again.

This is the fix for the naive method. The naive version recalculated F(3) and F(2) many times. With a memo, each value is worked out once.

### The memo

The memo is a `HashMap` from n to F(n), and it starts with the two base cases:

```java
static final Map<Integer, Long> memo = new HashMap<>(Map.of(0, 0L, 1, 1L));
```

`Map.of` creates a map that can't be changed, so the program copies it into a `HashMap`, which can take new entries. The memo is a `static` field, so it's shared by every call and survives between calls. The Python version uses a default argument for the same job. Java has no default argument of that kind, so the memo is written as a field, where the sharing is explicit.

### The method

```java
static long fibonacci(int n) {
    calls++;
    Long known = memo.get(n);
    if (known != null) {
        return known;
    }
    long value = fibonacci(n - 1) + fibonacci(n - 2);
    memo.put(n, value);
    return value;
}
```

The method first looks for n in the memo. If it's there, the stored value is returned, and no further calls are made. If it isn't, the value is worked out from the two before it, stored in the memo, and returned.

The `Long` type is used for the lookup, since `Map.get` returns `null` when a key is missing. A `long` can't be null, so the lookup must return an object.

### Running it

Press **Run**. The output is:

```text
Calculated F(40) = 102,334,155
calls = 79
calls for a second request = 1
```

F(40) takes 79 calls, compared with the 331 million of the naive method. Each value from 2 to 40 adds two calls: one that works out the value, and one that finds the value before it already in the memo. That's 2n − 1 calls in total, which grows in a straight line with n.

The second request is found in the memo at once, so it takes a single call. The memo is what makes repeated requests cheap.

### Java's built-in option

Java's standard library doesn't provide a memoizing method for a function, so the memo is written by hand, as it is here. Some third-party libraries add memoization, but the idea is the same as the memo in this step.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does the memo use `Long` rather than `long` for the lookup?
○ Because `Long` is faster than `long`.
→ Not quite. The reason is what the lookup can return.
◉ Because `Map.get` returns `null` for a missing key, and only an object can be null.
→ Yes. A `long` can't hold null, so the lookup needs an object type.
○ Because the memo can only store objects.
→ Not quite. The memo stores objects, but that's not the reason the lookup uses Long.
````

````quiz
What is the call count for a second request for F(40), after the first request has filled the memo?
○ 79
→ Not quite. The first request made 79 calls. The second finds the value at once.
◉ 1
→ Yes. The memo holds F(40), so the method returns it without further calls.
○ 0
→ Not quite. The method is still called, so it makes one call.
````

---

### Try it in the runner

Change `n` to 100, and predict the call count before you press **Run**. It's 199, which is 2 × 100 − 1. The value F(100) is far larger than a `long` can hold, so the result wraps around, and the printed number is not the true value. The call count is still correct, since the count doesn't depend on the value.

---

Memoization removes the repeated work, so the calls grow in a straight line. The next lesson gets the same answer with a loop and no calls at all.
