## Summary: Resources and references

This chapter covered how Java handles the resources and objects a program uses, and when they are released.

* **A resource that must be closed is `AutoCloseable`.** Declaring it in a `try` header closes it when the block ends, on every path, including an exception.
* **Several resources close in reverse order.** A resource that depends on an earlier one is closed first.
* **Objects are freed by the garbage collector.** An object is freed after its last reference is gone, but the program can't know when.
* **Objects need no release call.** Memory is freed by the collector, and no code runs when an object is freed.
* **Several references can refer to one object.** `==` on references compares identity, and the object stays alive while any reference remains.
* **A `WeakReference` doesn't keep its object alive.** Its `get` returns `null` once the object has been collected, so the program must check before using the result.
* **Use `AutoCloseable` for resources and the collector for objects.** A resource needs a known release point, and an ordinary object does not.

### Check your understanding

*These questions are written for this course.*

````quiz
Which pattern gives a resource a release at a known point, on every path out of a block?
○ Leaving the release to the garbage collector.
→ Not quite. The collector frees objects at a time it chooses, so it can't give a known release point.
◉ Declaring the resource in a try header, with a class that implements AutoCloseable.
→ Yes. The close runs when the block ends, on every path.
○ Calling a finalize method on each object.
→ Not quite. finalize is not a reliable way to release resources in Java, and it's no longer recommended.
````

````quiz
What does a `WeakReference` need the program to check before it uses `get()`?
○ Whether the reference is equal to another one.
→ Not quite. The check is whether the object still exists.
◉ Whether get() returned null, since the object may have been collected.
→ Yes. A weak reference returns null once its object is gone.
○ Whether the object has a label.
→ Not quite. The check is for null, not for the contents of the object.
````

````quiz
When an object's last ordinary reference is set to null, when is the object freed?
○ At the end of the line that sets it to null.
→ Not quite. Java doesn't free the object on that line.
◉ At a time the collector chooses, after the last reference is gone.
→ Yes. The program can't rely on the timing, so it doesn't need to.
○ When the program ends.
→ Not quite. The object can be freed before the program ends.
````
