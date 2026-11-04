## Standard exceptions

The earlier steps threw objects of classes the program defined itself. That works, but a standard exception class carries a message and a type that most readers already recognize. The Java standard library provides a family of exception classes for the common kinds of error, and they're the usual choice for errors in Java code.

Here's the program for this step, in `src/Main.java`. The division method refuses a zero divisor by throwing a standard exception:

```java
static int safeDivide(int a, int b) {
    if (b == 0) {
        throw new IllegalArgumentException("the divisor must not be zero");
    }
    return a / b;
}
```

`IllegalArgumentException` is in the `java.lang` package, so no import is needed. Its constructor takes a message, which is stored in the exception object. The message describes the problem, so whoever catches the error can report it without knowing the method's internals.

Press **Run**. The output is:

```text
10 / 2 = 5
Error: the divisor must not be zero
```

The first division succeeds, and prints its result. The second throws. The line `10 / 0 = ...` never prints, because the value of `second` is never produced.

### Catching the base class

The `catch` in `main` names `RuntimeException`:

```java
} catch (RuntimeException error) {
    System.out.println("Error: " + error.getMessage());
}
```

`IllegalArgumentException` is a subclass of `RuntimeException`, so this catch handles it. The `error` variable refers to the exception object, and `getMessage` returns the message. Catching a base class means one `catch` covers a whole family of errors, when the handling is the same for each.

### The standard exception classes

Several classes extend `RuntimeException`, and each one names a category of problem:

| Class | Use it for |
|---|---|
| `IllegalArgumentException` | A value passed to a method is unacceptable |
| `IndexOutOfBoundsException` | An index is outside its valid range |
| `IllegalStateException` | An object is not in a state where the operation is allowed |
| `ArithmeticException` | An arithmetic operation is impossible, such as division of an `int` by zero |

Choose the class that describes the problem, and give it a message that helps the reader. Java also has checked exceptions, such as `IOException`, which the earlier step on checked and unchecked exceptions covered. The rule for choosing between them is the same: pick the class that names the problem.

### Why not catch everything

Java has no single "catch anything" form for exceptions in normal code. Catching `Throwable` would also catch errors such as `StackOverflowError`, which a program usually can't recover from. Catch `Exception`, or a narrower class, for the problems a program can handle. Catch `Throwable` only at the outermost level of a program, where the choice is to report the failure and stop.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does the program print `Error:` and never `10 / 0 = ...`?
○ Because the message is printed before the division.
→ Not quite. The division happens first, and then the message is printed.
◉ Because the throw stops the method, so `second` is never set and the line never runs.
→ Yes. The throw leaves the try block before the output line can run.
○ Because System.out can't print after an exception.
→ Not quite. Output works normally, and the program simply skips the line.
````

````quiz
Which class is the best fit for a method that receives an index beyond the end of its data?
○ IllegalArgumentException
→ Possibly, but a range problem is more exactly described by a different class.
◉ IndexOutOfBoundsException
→ Yes. The class names the problem: an index outside its valid range.
○ IllegalStateException
→ Not quite. That describes an object that isn't in a state where the operation is allowed.
````

---

### Try it in the runner

Change the catch to `catch (Exception error)`, and press **Run**. The output doesn't change, since `IllegalArgumentException` is also an `Exception`. Then change the catch to `catch (ArithmeticException error)`, and predict the result. The thrown exception is not an `ArithmeticException`, so the catch doesn't apply. The program stops with the stack trace, and the line `Error:` doesn't print. Change the catch back to `RuntimeException` before you continue.

Then remove the try block entirely, and keep the call to `safeDivide(10, 0)`. Press **Run**. The exception is not caught, so the program stops with the stack trace. This is the same outcome as the mismatched catch above.

---

A standard exception gives an error a type and a message, and a `catch` can name the base class when one handler covers several types. The summary and homework put these together with the file errors from the last chapter.
