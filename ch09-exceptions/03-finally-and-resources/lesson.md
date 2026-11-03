## Finally and resources

Some code must run whatever happens: releasing a lock, closing a file, or printing a final status. Java gives you two tools for that, the `finally` block and try-with-resources.

Here's the program for this step, in `src/Main.java`. The first part is a method with a `finally` block:

```java
static void work(boolean fail) {
    System.out.println("start work");
    try {
        if (fail) {
            throw new IllegalStateException("something went wrong");
        }
        System.out.println("work finished");
    } finally {
        System.out.println("cleanup always runs");
    }
}
```

The `main` method calls it twice, once to succeed and once to fail:

```java
work(false);
try {
    work(true);
} catch (IllegalStateException error) {
    System.out.println("Caught: " + error.getMessage());
}
```

Press **Run**. The output is:

```text
start work
work finished
cleanup always runs
start work
cleanup always runs
Caught: something went wrong
```

In the first call, the `try` finishes normally, and `finally` runs after it. In the second call, the exception stops the `try` at the `throw`, but `finally` still runs before the exception leaves the method. Then the exception reaches the `catch` in `main`.

### The rule for finally

The `finally` block runs whether the `try` finished, returned, or threw. It's the place for cleanup that must happen on every path. Java runs it even when the exception isn't caught, so the cleanup happens before the program stops.

### Try-with-resources

Closing a file by hand is easy to forget, and a missed close can leave a file locked. The last part of the program uses a resource in the header of a `try`:

```java
Path file = Path.of("resource-demo.txt");
Files.writeString(file, "data\n");
try (BufferedReader reader = Files.newBufferedReader(file)) {
    System.out.println("read: " + reader.readLine());
}
System.out.println("reader closed automatically");
```

The output ends with:

```text
read: data
reader closed automatically
```

A resource declared in the parentheses after `try` is closed automatically when the block ends, even if an exception was thrown inside it. Any object that implements `AutoCloseable` can be used this way, and the readers and writers of the file classes do. Prefer it to a `finally` that closes by hand, since it's shorter and it's correct in more cases.

---

### Quiz

*These questions are written for this course.*

````quiz
When does a `finally` block run?
○ Only when no exception is thrown.
→ Not quite. finally runs on every path, including after an exception.
◉ Whether the try finishes normally or throws, before control leaves the method.
→ Yes. finally is the place for cleanup that must always happen.
○ Only when the exception is caught.
→ Not quite. finally also runs when the exception isn't caught.
````

````quiz
What closes the `reader` in `try (BufferedReader reader = ...)`?
○ The programmer, with a close call in a finally block.
→ Not quite. The try-with-resources form closes it automatically.
◉ Java does, when the block ends, even if an exception was thrown.
→ Yes. Resources in the try header are closed automatically.
○ The garbage collector, at some later time.
→ Not quite. The resource is closed when the block ends, not later.
````

---

### Try it in the runner

Remove the `if (fail)` check, so the `throw` always runs, and press **Run**. Predict the output. `work(false)` now throws too, so the first call prints `start work`, then `cleanup always runs`, and the exception reaches `main`, where it's not caught, and the program stops. Restore the `if` before you continue.

Then remove the `finally` block from `work`, and predict the output for `work(true)`. The exception still leaves the method, but `cleanup always runs` no longer prints, because nothing runs it. Restore the `finally` before you continue.

---

`finally` runs cleanup on every path, and try-with-resources closes what it opens. The summary brings the chapter together, and the homework uses both in a file program.
