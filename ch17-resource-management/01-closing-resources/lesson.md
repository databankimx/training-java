## Closing resources

A program holds resources: files, network connections, locks. Each one has to be released when the program is done with it, and the release has to happen on every way out of the code, including when an exception is thrown. Releasing by hand, with a `close` call at the end of the block, is where bugs come from: a return or an exception in the middle skips the call.

Java's answer is `try`-with-resources. A resource declared in the header of a `try` is closed automatically when the block ends, whether it ends normally, with a `return`, or with an exception. The class that holds the resource says how to close it by implementing `AutoCloseable`.

Here's the program for this step, in `src/Main.java`. The `Resource` class prints when it opens and when it closes, so you can follow the order:

```java
static class Resource implements AutoCloseable {
    private final String name;

    Resource(String name) {
        this.name = name;
        System.out.println("Open " + name);
    }

    void use() {
        System.out.println("Use " + name);
    }

    @Override
    public void close() {
        System.out.println("Close " + name);
    }
}
```

The method that uses it declares the resource in the `try` header:

```java
static void work(boolean fail) {
    try (Resource file = new Resource("file A")) {
        file.use();
        if (fail) {
            throw new IllegalStateException("something went wrong");
        }
        System.out.println("Finished normally");
    }
}
```

Press **Run**. The output is:

```text
Open file A
Use file A
Finished normally
Close file A
Open file A
Use file A
Close file A
Caught: something went wrong
```

The first call finishes normally, and the resource is closed after the last line of the block. The second call throws, and the resource is still closed before the exception reaches the `catch` in `main`. The close happens on the way out, so the code never has to remember it.

### The file streams

The file readers and writers from the files chapter are resources in this sense. `BufferedReader`, `PrintWriter`, and `Scanner` all implement `AutoCloseable`, so declaring them in a `try` header closes them. The file chapter's `try` blocks with resources in their headers were doing exactly this.

### Several resources

A `try` header can declare more than one resource, separated by semicolons. They're closed in the reverse order from the order they were opened, so a resource that depends on an earlier one is closed first.

### What the language guarantees

The guarantee is the one the course needs: the `close` method runs when the block ends, on every path. It does not say anything about memory. Objects that aren't resources are freed by the garbage collector, which is the subject of the next step.

---

### Quiz

*These questions are written for this course.*

````quiz
When does the `close` method of a resource declared in a `try` header run?
○ When the program ends.
→ Not quite. It runs when the block that declared the resource ends.
◉ When the block ends, whether it ends normally or with an exception.
→ Yes. The close runs on every way out of the block.
○ Only if the block finishes without an exception.
→ Not quite. The close runs even when an exception is thrown.
````

````quiz
What must a class implement so that try-with-resources can close its objects?
○ Runnable
→ Not quite. Runnable is for code that runs in a thread, and it has nothing to do with closing.
◉ AutoCloseable
→ Yes. AutoCloseable declares the close method that the try header calls.
○ Serializable
→ Not quite. Serializable is for writing an object to a stream, not for closing it.
````

---

### Try it in the runner

Add a second resource to the `try` header, named `file B`, and predict the order of the close lines before you press **Run**. The resources close in reverse order of opening, so `file B` closes before `file A`. Then add `if (!fail) { return; }` just before the line that prints `Finished normally`, and predict whether `Close file A` still prints when `fail` is false. It does, since the close runs on every exit from the block, and `Finished normally` no longer prints.

---

Closing resources in a `try` header gives release at a known point, the end of the block. The next step covers the other half of the picture: objects that have no resource to close, and how the collector frees them.
