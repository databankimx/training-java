## Counting the calls

Timing a recursive method tells you how long it took, but a count of its calls tells you why. The number of calls is what the recursion costs in stack space and in work, and it's often the figure that shows a problem.

### A counter in the method

The Python course uses a decorator, `CountCalls`, which wraps a function and counts its calls. Java has no decorators, so this program uses a static counter that the method increments on each call:

```java
static int calls = 0;

static boolean countdown(int n) {
    calls++;
    // ...
}
```

The increment is the first line of the method, so every call is counted, including the call that finds a negative number or reaches the base case. Before each test, `calls` is reset to zero, since it keeps counting across calls.

### The countdown

The countdown from the second step counts down from `n` to zero. It returns `true` when it reaches zero, and `false` for a negative start:

```java
static boolean countdown(int n) {
    calls++;
    if (n < 0) {
        System.out.println("Cannot count down from a negative number!");
        return false;
    }
    if (n == 0) {
        System.out.println("Liftoff!");
        return true;
    }
    System.out.println(n);
    return countdown(n - 1);
}
```

### Running it

Press **Run**. The output is:

```text
3
2
1
Liftoff!

recursions complete after 4 calls...
```

The call for n = 3 makes four calls in total: `countdown(3)`, `countdown(2)`, `countdown(1)`, and `countdown(0)`. The last one is the base case. So the number of calls is always one more than the starting value, when the start is zero or more.

### Why count calls

A count shows how a recursive method grows. A countdown from n makes n + 1 calls, so its cost grows in a straight line. The methods in the next lessons make many more calls than their input size, and the counts show where the time goes.

### The history of Fibonacci

The next lessons use the Fibonacci sequence, which is famous in computing and mathematics. Its terms are 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, and so on, and each term is the sum of the two before it. It's named after Leonardo Fibonacci, whose book *Liber Abaci* (1202) introduced it to Europe. Sanskrit scholars had described the same numbers earlier, while studying poetic meter.

The sequence is recursive by its definition. The Fibonacci sequence is often the first recursion taught, but it's a poor example of why recursion is useful. It shows how a recursive definition can waste work, which the next lessons measure.

---

### Quiz

*These questions are written for this course.*

````quiz
How many calls does `countdown(3)` make?
○ 3
→ Not quite. The count includes the call that reaches zero.
◉ 4
→ Yes. There is one call for each value from 3 down to 0.
○ 5
→ Not quite. A countdown from 3 stops at zero, so there are four calls.
````

````quiz
Why does the program reset `calls` before the test?
○ Because `calls` is a constant and must be set each time.
→ Not quite. `calls` is a normal variable, and it's set to zero.
◉ Because the counter keeps counting across calls, so an earlier test would be included.
→ Yes. Without the reset, the second count would include the first test's calls.
○ Because the counter must be negative at the start.
→ Not quite. The counter starts at zero.
````

---

### Try it in the runner

Change `n` to 5, and predict the number of calls before you press **Run**. It's six, one for each value from 5 down to 0. Then remove the `calls = 0;` line, and run the countdown twice in a row by copying the test. Predict the second count. It's the first count plus the second, since the counter keeps going.

---

A call counter shows the cost of a recursive method in the same units as its structure. The next lesson applies the count to the Fibonacci sequence, the first naive version.
