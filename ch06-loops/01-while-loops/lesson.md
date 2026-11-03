## While loops

A loop repeats a block of code while a condition is true. The simplest is `while`. It checks the condition before each pass, so if the condition is false at the start, the block never runs.

Here's the start of this step's program, in `src/Main.java`:

```java
int count = 1;
while (count <= 3) {
    System.out.println("while pass " + count);
    count++;
}
```

Press **Run**. The output is:

```text
while pass 1
while pass 2
while pass 3
```

The condition in parentheses is checked first. If it's true, the braces run once, and the condition is checked again. The loop ends when the condition is false, and the program continues after the closing brace.

The line `count++;` is what makes the loop finish. Without it, `count` would stay at 1 forever, and the program would never leave the loop. Every `while` needs something inside the block that eventually makes the condition false.

### do-while

A `do-while` loop checks the condition after each pass, not before. The block always runs at least once:

```java
int attempts = 5;
do {
    System.out.println("do-while pass, attempts left: " + attempts);
    attempts--;
} while (attempts > 5);
```

The output is:

```text
do-while pass, attempts left: 5
```

The block runs once, and `attempts` drops to 4. The condition `attempts > 5` is then false, so the loop ends. Notice the semicolon after the closing parenthesis of the condition, which the `do-while` form requires.

Use `do-while` when the first pass must happen whatever the condition, such as asking the user for input at least once.

### A loop that never runs

The last part of the program has a `while` whose condition is false from the start:

```java
int countdown = 0;
while (countdown > 0) {
    System.out.println("never printed");
    countdown--;
}
System.out.println("Countdown finished at " + countdown);
```

The condition is checked, it's false, and the block is skipped. The output is:

```text
Countdown finished at 0
```

That's the difference from `do-while`. A `while` may run zero times, and a `do-while` always runs at least once.

### Infinite loops

A `while (true)` loop never ends on its own, and it needs a `break` to leave it. The next steps cover `break`. Until then, be careful that the condition can become false. A loop that never ends stops the runner, and the Stop button ends it.

---

### Quiz

*These questions are written for this course.*

````quiz
How many times does `while (x > 10) { ... }` run when `x` is 5 at the start?
○ Once.
→ Not quite. The condition is checked first, and 5 is not greater than 10.
◉ None.
→ Yes. A while loop checks its condition before the first pass.
○ Forever.
→ Not quite. A loop only runs forever when its condition never becomes false.
````

````quiz
Which loop always runs its block at least once?
○ while
→ Not quite. A while loop can skip its block entirely.
◉ do-while
→ Yes. Its condition is checked after each pass, so the first pass always runs.
○ for
→ Not quite. A for loop checks its condition before each pass, like while.
````

---

### Try it in the runner

Change `countdown` to `3`, and then predict how many times the block runs, and the final value of `countdown`. The block runs three times, with the messages printed each time, and `countdown` ends at 0. Restore `countdown` to 0 before you continue.

Then change the `do-while` condition from `attempts > 5` to `attempts > 3`, and predict how many passes run. The first pass prints 5, and `attempts` drops to 4, which is still greater than 3, so the second pass prints 4. Then `attempts` drops to 3, the condition is false, and the loop ends. Two passes, printing 5 and then 4. Restore the original condition before you continue.

---

A `while` loop checks first, and a `do-while` loop checks last. The next step covers `for`, which is the usual choice when you know how many times to repeat.
