## Stack traces

When a program fails, the first question is how it got there. The call stack answers it: the list of methods that were running when the error happened, with the method that failed at the top. Java records the stack when an exception is created, so every exception carries its own trace.

Here's the program for this step, in `src/Main.java`. The `divide` method fails when its divisor is zero, and it's called by `compute`, which `main` calls:

```java
static int divide(int a, int b) {
    return a / b;
}

static int compute(int a, int b) {
    return divide(a, b) + 1;
}
```

`main` catches the exception and walks through the trace:

```java
for (StackTraceElement frame : error.getStackTrace()) {
    if (frame.getClassName().equals("Main")) {
        System.out.println("  " + frame.getMethodName());
    }
}
```

Press **Run**. The output is:

```text
Result: 6
Error: / by zero
Called through:
  divide
  compute
  main
```

The first line of the trace is the method where the error happened, `divide`. The last is `main`, the method that started the chain. The middle entries show the path between them.

### Reading an uncaught exception

When no `catch` handles an exception, the runner prints the whole trace in the Output pane, under the message. The lines look like this, with one line for each method on the stack:

```text
Exception in thread "main" java.lang.ArithmeticException: / by zero
    at Main.divide(Main.java:3)
    at Main.compute(Main.java:7)
    at Main.main(Main.java:11)
```

Each line gives a class, a method, and the file and line number. The first line is the place the error happened, and the lines below it show how the program got there. The line numbers in the trace are the lines of the source, so the trace points you to the code to read. The numbers in the example are illustrative: your run shows the numbers for your file.

### Printing a trace

The `printStackTrace` method on any exception prints its trace to the error stream. A program that wants to record the trace, rather than print it, can read `getStackTrace` as this step does, and keep the frames it needs.

---

### Quiz

*These questions are written for this course.*

````quiz
In a stack trace, which line shows where the error happened?
○ The last line, which is main.
→ Not quite. The last line is where the chain of calls started.
◉ The first line, which is the method that was running when the exception was created.
→ Yes. Reading down the trace moves from the failure back to the start.
○ The line that prints the message.
→ Not quite. The message is printed separately, and the trace shows the methods.
````

````quiz
What does `error.getStackTrace()` return?
○ The message of the exception.
→ Not quite. That is getMessage.
◉ An array of frames, one for each method on the stack when the exception was created.
→ Yes. Each frame gives a class name, a method name, and a line number.
○ The method that caught the exception.
→ Not quite. The trace lists the methods the exception passed through, starting from where it was created.
````

---

### Try it in the runner

Change `compute(10, 0)` to `compute(10, 0 + 1)`, so the call succeeds, and predict the output. It prints `Result: 6` and `Result: 11`, and no error. Then add a third call, `compute(4, 0)`, after the second, and predict the trace. The third call fails, so the trace lists `divide`, `compute`, and `main` again. The `catch` stops the chain, so the program doesn't print a result for that call.

---

The stack shows how a program reached an error, and it's the first thing to read when something fails. The next step covers assertions, which check conditions that must hold.
