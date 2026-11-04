## Throwing and catching

Some problems can't be solved where they're found. A method asked to divide by zero can't give a sensible answer, and it shouldn't pretend to. An exception lets it stop, and lets the code that called it decide what to do.

Here's the program for this step, in `src/Main.java`:

```java
static int divide(int a, int b) {
    if (b == 0) {
        throw new IllegalArgumentException("divisor must not be zero");
    }
    return a / b;
}
```

`throw` starts the exception. It creates an object of an exception class, here `IllegalArgumentException`, and gives it a message. As soon as the `throw` runs, the normal flow of the method stops, and the `return` is never reached.

### Catching the exception

The code that calls `divide` uses `try` and `catch`:

```java
try {
    System.out.println("10 / 2 = " + divide(10, 2));
    System.out.println("10 / 0 = " + divide(10, 0));
    System.out.println("this line is skipped");
} catch (IllegalArgumentException error) {
    System.out.println("Caught: " + error.getMessage());
}
System.out.println("program continues after the catch");
```

Press **Run**. The output is:

```text
10 / 2 = 5
Caught: divisor must not be zero
program continues after the catch
```

The first call returns 5, so its line prints. The second call throws. Control leaves the `try` block at that point, so `this line is skipped` never prints. The `catch` block runs, and the program carries on after it.

### The exception crosses the method

The throw happens inside `divide`, but the catch is in `main`. The exception travels up through the calls until a `catch` matches its type. If no matching `catch` exists anywhere, the program stops, and the runner shows the stack trace. The method that throws doesn't need to know who will catch it.

### Choosing what to catch

A `catch` names the exception type it handles. It catches that type and any subclass of it. Each exception object has a message, which `getMessage()` returns, and the `catch` block can use it.

Catch the most specific type you can handle. A `catch` for `Exception`, the general class, would also catch problems you didn't expect, and hide them.

---

### Quiz

*These questions are written for this course.*

````quiz
What happens to the rest of a method after `throw` runs?
○ It carries on, with the exception ignored.
→ Not quite. A throw stops the normal flow of the method at that point.
◉ It stops, and control goes to the nearest matching catch.
→ Yes. The exception travels up the calls until a catch handles it.
○ It runs in a new thread.
→ Not quite. Throwing has nothing to do with threads.
````

````quiz
Why does `divide(10, 0)` not print its line?
○ Because the line comes before the try.
→ Not quite. It's inside the try, after the call that succeeded.
◉ Because the exception stops the try block before the print runs.
→ Yes. The call throws before its result is printed.
○ Because division by zero returns zero.
→ Not quite. In this program, it throws instead.
````

---

### Try it in the runner

Change the `catch` type from `IllegalArgumentException` to `ArithmeticException`, and predict the output. The thrown exception is an `IllegalArgumentException`, which doesn't match, so no catch runs. The program stops with the stack trace, and `program continues after the catch` doesn't print. Restore the original type before you continue.

Then change `divide(10, 0)` to `10 / 0` in the `try` block, leaving the method out of it. The `int` division by zero throws an `ArithmeticException` instead, and the original `catch` doesn't match it. Predict the result before you press Run, and restore the original line afterwards.

---

A `throw` stops normal flow at the problem, and a `catch` decides what to do about it. The next step covers the two families of exceptions, and why the compiler handles them differently.
