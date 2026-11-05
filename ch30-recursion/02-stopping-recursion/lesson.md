## Stopping recursion

The base case is the condition that makes a recursive method return without calling itself. Without it, the calls never stop, as the previous step showed. With it, each call does a small piece of the work and hands the rest to the next call, until the base case is reached.

Here's a countdown from the program in `src/Main.java`:

```java
static void countdown(int n) {
    if (n == 0) {
        System.out.println("Liftoff");
        return;
    }
    System.out.println(n);
    countdown(n - 1);
}
```

Each call checks whether `n` is zero. If it isn't, it prints `n` and calls itself with `n - 1`, which is one step closer to zero. When `n` reaches zero, the `if` runs, prints the message, and returns. Nothing is left to do, so the calls unwind.

Press **Run**. The output is:

```text
3
2
1
Liftoff
A countdown from 0:
Liftoff
```

The second call starts at zero, so the base case is reached immediately. It prints only `Liftoff`.

### The two parts of every recursive method

Each recursive method has the same two parts:

* A **base case** that returns a result without recursing.
* A **recursive case** that makes the problem smaller and calls the method again.

The recursive case must move towards the base case. In `countdown`, each call reduces `n` by one, so zero is always reached for a non-negative start. A recursive call that doesn't get closer to the base case is the same mistake as the infinite recursion from the first step.

### Choosing the base case

The base case should cover the smallest version of the problem, the one that needs no further work. For a countdown, that's zero. The base case is checked first, before the recursive call, so that no call starts with a value the method can't handle.

---

### Quiz

*These questions are written for this course.*

````quiz
What does `countdown(0)` print?
○ 0
→ Not quite. The base case prints the message, not the number.
◉ Liftoff
→ Yes. The base case is checked first, so the method returns straight away.
○ Nothing at all.
→ Not quite. The base case prints Liftoff before it returns.
````

````quiz
Why does the recursive call use `n - 1` rather than `n + 1`?
○ Because Java doesn't allow adding in a method call.
→ Not quite. Adding would be allowed, but it would not reach the base case.
◉ Because each call must move closer to the base case, and zero is below n.
→ Yes. A recursive call that moves away from the base case never ends.
○ Because the method prints the values in reverse order.
→ Not quite. The order of printing comes from the calls, and the direction is set by the base case.
````

---

### Try it in the runner

Change `countdown(3)` to `countdown(5)`, and predict the output before you press **Run**. It prints 5, 4, 3, 2, 1, then Liftoff. Then change the recursive call to `countdown(n + 1)` and predict what happens. The value never reaches zero, so the method overflows the stack, as in the previous step.

---

A recursive method needs a base case, and each call must move towards it. The next step traces the calls, to show how the values build up and unwind.
