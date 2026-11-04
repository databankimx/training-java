## Checked and unchecked

Java sorts exceptions into two families, and the compiler treats them differently. The difference decides whether a method must mention an exception in its declaration, or is free to ignore it.

Here's the program for this step, in `src/Main.java`:

```java
static void readConfig() throws IOException {
    Files.readString(Path.of("missing-config.txt"));
}
```

Press **Run**. The output is:

```text
Checked exception caught: NoSuchFileException
Unchecked exception caught: NumberFormatException
```

The first is a checked exception. `readConfig` reads a file, and reading can fail for reasons outside the program's control, such as the file being missing. So the method declares `throws IOException`, which tells every caller that it may fail. The caller then has to catch the exception, or declare it in turn. The compiler checks this, and it refuses to build code that does neither.

The second is an unchecked exception. `Integer.parseInt("forty-two")` throws `NumberFormatException`, which is a `RuntimeException`. Unchecked exceptions are usually bugs in the code, such as a bad number or a bad index, so Java doesn't force every method to declare them. You can still catch them, as the program does.

### The rule

A checked exception is one that describes a situation the program has to expect, such as a file that might be missing. The compiler insists the code handles it. An unchecked exception is one that usually means the code is wrong, and it's fixed by correcting the code rather than by handling it everywhere.

### Why the name appears in the output

The program prints `getClass().getSimpleName()`, which gives the name of the exception's class without its package. That's the quickest way to see which exception was caught, and it's useful when a catch matches more than one kind.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does `readConfig` declare `throws IOException`?
○ Because every method must declare the exceptions it throws.
→ Not quite. Only checked exceptions have to be declared.
◉ Because IOException is a checked exception, and the method might throw it.
→ Yes. The compiler requires the declaration, so callers know to handle it.
○ Because the file is missing.
→ Not quite. The declaration describes what the method might throw. It doesn't record what happened.
````

````quiz
Which kind of exception is `NumberFormatException`?
○ Checked, so the compiler forces every caller to handle it.
→ Not quite. It's unchecked, which is why parseInt doesn't declare it.
◉ Unchecked, because it's a RuntimeException.
→ Yes. Unchecked exceptions don't need a throws declaration.
○ Neither checked nor unchecked.
→ Not quite. Every exception is one or the other.
````

---

### Try it in the runner

Remove the `try` around `readConfig()`, leaving the call on its own in `main`, and press **Run**. Predict the result. The build fails, with an error saying the exception `IOException` is never caught and not declared. The compiler reports it before the program runs. Restore the `try` before you continue.

Then remove the whole second `try` block, leaving only `int value = Integer.parseInt("forty-two");` and its print in `main`. Predict the result. The program compiles, because the exception is unchecked, and it stops at run time with the stack trace. Restore the block before you continue.

---

Checked exceptions describe what a program must expect, and the compiler makes sure they're handled. The next step covers code that must run whatever happens, and resources that must be closed.
