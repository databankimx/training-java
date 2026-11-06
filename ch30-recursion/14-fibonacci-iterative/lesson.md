## Do I have to use recursion?

No. For any recursive algorithm, there is an equivalent iterative version, usually a loop. Sometimes the loop needs an explicit stack, as the nested-list traversal did. Sometimes the loop is much more complicated. Fibonacci is neither: the loop is short, so it's a good place to see the two versions side by side.

### The list version

The first version keeps every value in a list, the way the recursive memo did:

```java
List<Long> values = new ArrayList<>(List.of(0L, 1L));
for (int i = 2; i <= n; i++) {
    values.add(values.get(i - 1) + values.get(i - 2));
}
return values.get(n);
```

The loop adds each new value to the end of the list, using the two values before it. The answer is the value at position n. This version makes no calls, so the call count is zero. Its time grows in direct proportion to n, and its memory grows too, since the list holds every value up to F(n).

### The two-value version

The list holds more than the method needs. Each step needs only the last two values, so the list can be replaced by two variables:

```java
long a = 0;
long b = 1;
for (int i = 0; i < n; i++) {
    long next = a + b;
    a = b;
    b = next;
}
return a;
```

Each pass moves the pair one place along the sequence. The memory stays the same however large n is, which the list version can't do.

### Running it

Press **Run**. The output is:

```text
List version: F(40) = 102,334,155
Two values:   F(40) = 102,334,155
```

Both methods give the same answer, in a time too short to notice. The naive method took 331 million calls for the same value.

### What the comparison shows

The iterative version has no recursion depth problem, and it has no repeated work, so it's the fastest of the three Fibonacci methods. It's also the least like the formula, which is the cost. The recursive formula is easy to check against its definition, while the loop has to be checked against the result.

The choice depends on the problem. For Fibonacci, the loop is the clear winner in both speed and memory. For a problem such as a tree walk, the recursive method is often easier to write and read, and a loop may be much harder to get right.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does the two-value version use the same memory for any n, while the list version does not?
○ Because the list is always empty.
→ Not quite. The list holds every value, so it's not empty.
◉ Because it keeps only the last two values, so the memory doesn't grow with n.
→ Yes. Two variables are enough, because each new value needs only the two before it.
○ Because it doesn't store any values at all.
→ Not quite. It stores two values, which are replaced at each step.
````

````quiz
How many method calls does the iterative version make for F(40)?
○ 40
→ Not quite. The loop runs 40 times, but the method itself is called only once.
◉ 0 calls to itself, since it doesn't recurse.
→ Yes. The method is called once from main, and the loop does all of the work.
○ 331,160,281
→ Not quite. That's the naive recursive version.
````

---

### Try it in the runner

Change `n` to 10, and predict the value of both methods before you press **Run**. It's 55. Then change `n` to 0, and predict the value. It's 0, since F(0) is 0, and both loops run zero times.

---

A loop can replace recursion whenever each step needs only the results before it. The next step writes the formula using a closed-form expression, which avoids both recursion and the loop.
