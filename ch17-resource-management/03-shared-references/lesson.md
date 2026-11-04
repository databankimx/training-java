## Shared references

Often several parts of a program need the same object. A list holds a reference to a book, a checkout record holds another, and a report holds a third. In Java each of these is simply a reference, and any number of references can refer to one object. There's no owner to pick. The object stays in memory while at least one reference remains, and the collector frees it after the last one goes.

Here's the program for this step, in `src/Main.java`. It makes two references to one object, and checks that they are the same object:

```java
Node shared = new Node("shared");
Node another = shared;
System.out.println("Same object: " + (shared == another));
```

The `==` operator on references compares identity: it's true only when both refer to the same object. Two different objects with the same contents would be unequal under `==`, which is why value comparison is a method of its own, as the strings lesson showed.

Press **Run**. The output begins:

```text
Create shared
Same object: true
```

### Weak references

Sometimes a part of a program wants to refer to an object without keeping it alive. A cache is the usual case: it remembers objects for speed, but it should never be the reason an object stays in memory. A `WeakReference` does that:

```java
WeakReference<Node> weak = new WeakReference<>(shared);
System.out.println("Weak reference sees: " + weak.get().label);
```

The weak reference doesn't count as a reference for the collector. While the object is still reachable through `shared`, `weak.get()` returns it, and the output is:

```text
Weak reference sees: shared
```

Once the object has no other references, the collector may free it, and then `weak.get()` returns `null`. The program must check for `null` before using the result, because it can't know whether the collector has run yet. For that reason, a weak reference is for optional help, such as a cache, not for data the program needs.

### Choosing a kind of reference

Most code uses ordinary references, since they keep the object alive for as long as the code needs it. A weak reference is only for the case where the object's survival should depend on something else.

---

### Quiz

*These questions are written for this course.*

````quiz
What does `shared == another` compare when both variables refer to the same object?
○ The contents of the two objects.
→ Not quite. == on references compares identity, not contents.
◉ Whether both refer to the same object.
→ Yes. The result is true only when the two references point to one object.
○ The lengths of the labels.
→ Not quite. Labels play no part in a reference comparison.
````

````quiz
What does a `WeakReference` do to the object it refers to?
○ It keeps the object alive while the weak reference exists.
→ Not quite. That's what an ordinary reference does. A weak reference doesn't keep the object alive.
◉ It refers to the object without keeping it alive, so `get()` may return null after collection.
→ Yes. The collector ignores weak references when it decides what to free.
○ It copies the object, so the original can be freed.
→ Not quite. A weak reference refers to the same object, and copies nothing.
````

---

### Try it in the runner

Set `another` to `null` after the `==` line, and predict the output of the weak line before you press **Run**. The weak reference still refers to `shared`, which is also still set, so the line reads `Weak reference sees: shared`. Then set `shared` to `null` too. Change the weak line to check `weak.get()` for `null` first, printing `Weak reference is empty` when it is null. The program can't predict which line prints, because the collector may not have run yet, which is why the check is needed.

---

Shared references are the normal case in Java, and the collector frees an object when its last reference goes away. The summary brings the chapter together, and the homework applies the ideas to a small program.
