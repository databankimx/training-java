## Assertions

Some conditions must always be true, if the program is correct. A method might assume its caller passed at least one score, or that an index is in range. An assertion states such a condition in the code, so a broken promise is found where it's broken, not much later where its effect shows up.

Java's `assert` keyword checks a condition, and it can carry a message after a colon:

```java
static double average(int[] scores) {
    assert scores.length > 0 : "average needs at least one score";
    int total = 0;
    for (int score : scores) {
        total += score;
    }
    return (double) total / scores.length;
}
```

If the condition is false, the assertion throws an `AssertionError` with the message. The rest of the method doesn't run.

### Assertions are off by default

Java checks assertions only when the program is started with the `-ea` option, which stands for enable assertions. Without it, the `assert` line does nothing at all, and the program behaves as if the line weren't there. That's a deliberate choice: an assertion is a check for developers, and a program shipped to users isn't slowed by it.

The runner starts the program without `-ea`, so this step's program shows the effect. Here's the program's `main`:

```java
System.out.println("Average: " + average(new int[] {70, 80, 90}));
System.out.println("Empty average: " + average(new int[] {}));
System.out.println("Program continued");
```

Press **Run**. The output is:

```text
Average: 80.0
Empty average: NaN
Program continued
```

The empty list breaks the promise, since `average` needs at least one score. The assertion would have stopped the program with its message, but the runner doesn't enable assertions, so the division runs. Dividing zero by zero gives `NaN`, which means "not a number", and the program carries on with a wrong answer. That's the reason to enable assertions while you're developing and testing.

### Enabling assertions

To check assertions, start the program with `-ea`, for example `java -ea -cp classes Main`. Then the same program stops at the empty list with `java.lang.AssertionError: average needs at least one score`. A test run is the usual place to turn them on, and the next step's bug hunt shows how the trace helps find the cause.

### Assertions or exceptions

An assertion is for a promise the code makes to itself, which should never be broken if the code is correct. A bad value from a user, such as an empty file, is a different case. It should be handled with an exception that the program can report and recover from, as the exceptions chapter showed. Assertions are for bugs, and exceptions are for the situations a correct program must handle.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does the runner's output show `Empty average: NaN` instead of stopping at the assertion?
○ Because the assertion message is printed as NaN.
→ Not quite. NaN comes from dividing zero by zero, not from the message.
◉ Because assertions are off unless the program is started with -ea, and the runner doesn't start it that way.
→ Yes. Without -ea, the assert line does nothing.
○ Because the average is always NaN for an empty list.
→ Not quite. The average is NaN here only because the assertion didn't stop the call.
````

````quiz
When is an assertion the right choice, rather than an exception?
○ When a user types a bad value.
→ Not quite. A bad value from outside the program is a case to handle with an exception.
◉ When the code makes a promise to itself that should never be broken if the code is correct.
→ Yes. Assertions check the code's own promises, not the input from outside.
○ When the program needs to print a message.
→ Not quite. Printing a message is not the purpose of an assertion.
````

---

### Try it in the runner

Add `assert false : "forced failure";` as the first line of `main`, and predict the output before you press **Run**. The line does nothing, since assertions are off, so the program prints the same three lines as before, starting with `Average: 80.0`.

---

An assertion checks a promise the code makes to itself, and it's off unless the program is started with `-ea`. The next step is a bug hunt, where the stack trace and a careful reading find an off-by-one error.
