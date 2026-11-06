## Built-in checks

The Java Virtual Machine checks every array index, every reference before it's used, every division, and every cast, as the program runs. Each mistake stops the program with an exception that names it. There is no memory that a program can read by accident, so there's no undefined behavior to hunt in ordinary Java code.

Here's the program for this step, in `src/Main.java`. The `report` method runs a piece of code, and prints either a success message or the name of the exception it threw:

```java
static void report(String label, Runnable action) {
    try {
        action.run();
        System.out.println(label + ": no error");
    } catch (RuntimeException error) {
        System.out.println(label + ": " + error.getClass().getSimpleName());
    }
}
```

`main` then runs four pieces of code, each with one kind of mistake:

```java
report("Index", () -> System.out.println(values[5]));
report("Null", () -> System.out.println(text.length()));
report("Divide", () -> System.out.println(10 / (values[0] - 1)));
report("Cast", () -> {
    Integer number = (Integer) item;
    System.out.println(number);
});
```

Press **Run**. The output is:

```text
Index: ArrayIndexOutOfBoundsException
Null: NullPointerException
Divide: ArithmeticException
Cast: ClassCastException
```

### What each check catches

* An array index outside the array throws `ArrayIndexOutOfBoundsException`.
* Using a reference that is `null` throws `NullPointerException`. The `text` variable holds `null`, so `text.length()` fails.
* Dividing an `int` by zero throws `ArithmeticException`. The divisor here is `values[0] - 1`, which is zero.
* A cast to a type the object isn't throws `ClassCastException`. The object is a `String`, and it can't be cast to `Integer`.

Each check happens on every run, not only in a special build. That's why a program in Java rarely fails in a way that is hard to reproduce.

### Why the checks are still useful

The checks stop the program at the mistake, and the exception carries a message and a stack trace. A check that stops a program is still a bug, though. A program that catches these exceptions to keep going must handle the cause, not hide it. Catching `RuntimeException` around everything is the wrong answer to a `NullPointerException`, since the code that produced the null is the thing to fix.

---

### Quiz

*These questions are written for this course.*

````quiz
Which exception does Java throw when an array index is out of range?
○ NullPointerException
→ Not quite. That exception is for a reference that is null.
◉ ArrayIndexOutOfBoundsException
→ Yes. The JVM checks every index before it reads the element.
○ There is no exception, since Java ignores bad indexes.
→ Not quite. Java checks each index and reports a bad one.
````

````quiz
Why do Java programs usually need no separate sanitizer to find these mistakes?
○ Because Java programs are never wrong.
→ Not quite. Java programs can contain bugs, and the checks report them.
◉ Because the JVM checks indexes, references, divisions, and casts as the program runs.
→ Yes. Each check reports its mistake at the point it happens.
○ Because it has only primitive types.
→ Not quite. Arrays and references are objects, and the checks apply to them.
````

---

### Try it in the runner

Change `values[5]` in the first `report` call to `values[2]`, and predict the output before you press **Run**. The index is now valid, so the first line prints `3` followed by `Index: no error`.

---

The JVM checks the common mistakes as the program runs, so a bug is reported where it happens. The chapter's summary follows, and then the homework.
