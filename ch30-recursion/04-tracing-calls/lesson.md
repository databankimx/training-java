## Tracing recursive calls

A recursive method looks simple on the page, but at run time each call waits for the next. Those waiting calls form a stack, and tracing the calls makes the stack visible.

The `factorial` method in this step prints a line when each call starts, and another when it returns. It also keeps a count called `depth`, which holds the number of calls on the stack right now. Each call adds one to the depth before the recursive call, and removes it again after the call returns:

```java
static int depth = 0;

static long factorial(int n) {
    // ...
    String indent = "  ".repeat(depth);
    System.out.println(indent + "factorial(" + n + ") called...");

    depth++;
    long f = n < 2 ? 1 : n * factorial(n - 1);
    depth--;

    System.out.println(indent + "factorial(" + n + ") returns " + f + "...");
    return f;
}
```

The `indent` is worked out when the call starts, so the `returns` line lines up with the `called` line for the same call. Each deeper call is indented two more spaces.

### Running the trace

Press **Run**. The output for 5! is:

```text
factorial(5) called...
  factorial(4) called...
    factorial(3) called...
      factorial(2) called...
        factorial(1) called...
        factorial(1) returns 1...
      factorial(2) returns 2...
    factorial(3) returns 6...
  factorial(4) returns 24...
factorial(5) returns 120...

5! = 120
```

The calls go down the page as the stack grows, until the base case is reached at `factorial(1)`. Then the returns come back up the page in the reverse order. Each return multiplies the value it receives by its own `n`, so 2 × 1 gives 2, then 3 × 2 gives 6, and so on up to 120.

### What the stack costs

Every call adds a frame to the stack, and each frame keeps its own copy of `n` and `f` until it returns. For 5!, the stack holds five frames at its deepest. For a larger value, such as 1000, the stack would need 1000 frames, and the calls might fail before finishing. The depth grows with `n`, which is why a recursive factorial is a poor choice for large inputs, even though its formula is correct.

The Python trace uses the same two rules: `s` goes up when a call starts, and down when it returns. The Java version adds the indentation so the rules can be seen as well as counted.

---

### Quiz

*These questions are written for this course.*

````quiz
At the deepest point of the trace for 5!, how many `factorial` calls are on the stack?
○ 1
→ Not quite. At the moment `factorial(1)` starts, the calls for 5, 4, 3, and 2 are still waiting.
◉ 5
→ Yes. One call for each value from 5 down to 1 is on the stack at the deepest point.
○ 120
→ Not quite. 120 is the result, not the number of calls on the stack.
````

````quiz
Why does the `returns` line use the same indent as its `called` line?
○ Because the indent is a style choice that has no meaning.
→ Not quite. The indent shows which call is which.
◉ Because the indent is computed when the call starts, and it matches the depth of that call.
→ Yes. The returns line for each call lines up with its own called line.
○ Because the returns line is printed by the caller.
→ Not quite. Each call prints its own returns line, using the indent it saved.
````

---

### Try it in the runner

Change `n` to 3, and predict the number of lines printed by the trace before you press **Run**. Each value from 3 down to 1 gives one called line and one returns line, so there are six trace lines, followed by the blank line and the result.

---

The stack grows by one frame for each call, and it shrinks by one as each call returns. The next step writes the same factorial as a loop, which uses a single frame however large n is.
