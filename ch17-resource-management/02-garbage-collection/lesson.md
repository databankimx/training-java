## Garbage collection

Every object in a program is created with `new`, and it stays in memory as long as the program can still reach it. An object is reachable while at least one reference to it exists. When the last reference goes away, the object is garbage, and the garbage collector frees its memory.

Here's the program for this step, in `src/Main.java`. The `Node` class has no cleanup code at all:

```java
static class Node {
    final String label;

    Node(String label) {
        this.label = label;
        System.out.println("Create " + label);
    }
}
```

The `main` method makes two references to one object, and then drops them:

```java
Node first = new Node("first");
Node second = first;
System.out.println("Both refer to: " + second.label);

first = null;
System.out.println("second still refers to: " + second.label);

second = null;
System.out.println("No references left; the collector may free the object at any time.");
```

Press **Run**. The output is:

```text
Create first
Both refer to: first
second still refers to: first
No references left; the collector may free the object at any time.
```

Setting `first` to `null` removes one reference, and the object is still reachable through `second`, so the second line still reads its label. Setting `second` to `null` removes the last one. At that point the object is garbage, but the program has no way to know when the collector will free it. The line about the collector is the honest description of what happens next.

### Nothing to release for objects

You don't free memory yourself, so there's no call to forget. The cost is that the timing is the collector's decision, not the program's. A program must not rely on an object being freed at a particular point, or on any code running when that happens.

### What to use for resources

Memory is handled by the collector. Other resources, such as files, locks, and connections, are not released until the program closes them, so they still need the `try`-with-resources pattern from the previous step. Use the collector for objects, and `AutoCloseable` for anything the program must release at a known point.

---

### Quiz

*These questions are written for this course.*

````quiz
After `second = null;` in this program, when is the `Node` object freed?
○ Immediately, on the same line.
→ Not quite. Java doesn't free the object on the line that drops the last reference.
◉ At a time the collector chooses, once no references to it remain.
→ Yes. The program can't know when, so it doesn't rely on the timing.
○ When `main` ends.
→ Not quite. The object can be freed before the method ends, once it's unreachable.
````

````quiz
Which resource must still be closed by the program, even though the collector handles memory?
○ A String object.
→ Not quite. Strings are objects, and the collector frees them.
◉ A file, which must be released at a known point with try-with-resources.
→ Yes. The collector doesn't close files, so the program must.
○ An int variable.
→ Not quite. Primitive variables are not objects, and they need no release.
````

---

### Try it in the runner

Add a line that prints `second` after `second = null;`, with the message `Label: `, and predict what happens. The reference is null, so `second.label` throws a `NullPointerException`. Replace the line with one that checks `second == null` first, and print `second is gone` in that case. Then predict the output again.

---

Garbage collection frees objects when nothing refers to them, and it chooses when. The next step looks at objects that share references, and what a weak reference does.
