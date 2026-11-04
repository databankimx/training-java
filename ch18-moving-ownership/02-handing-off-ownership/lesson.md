## Handing off ownership

Some objects can't be copied, because a copy would be wrong. A file, a connection, or a lock belongs to one place in the program at a time. Two holders of the same file would both think they could close it. When such an object has to be given to another part of the program, the first holder must let go of it. The hand-off is the only way to move the object, and the first holder must not use it afterwards.

Java has no compiler-enforced rule for this. Any number of references can refer to one object, as the previous chapter's step showed, so the rule is one the program keeps. A common way to keep it is to give the holder a method that hands the object over and empties the holder at the same time.

Here's the holder from `src/Main.java`. A `Box` holds at most one resource:

```java
static class Box {
    private Resource contents;

    Box(Resource contents) {
        this.contents = contents;
    }

    Resource take() {
        Resource taken = contents;
        contents = null;
        return taken;
    }

    boolean isEmpty() {
        return contents == null;
    }
}
```

`take` returns the resource and sets the box's own reference to `null`, so the box no longer refers to it. A second call to `take` returns `null`, and `isEmpty` tells the caller whether there's anything to take.

The program hands the resource from one box to another:

```java
Box a = new Box(new Resource("file E"));
Box b = new Box(a.take());
System.out.println("a holds nothing: " + a.isEmpty());
System.out.println("b holds the resource: " + !b.isEmpty());
```

Press **Run**. The output is:

```text
Start
Acquire file E
a holds nothing: true
b holds the resource: true
End
```

The resource is acquired once. After the hand-off, `a` holds nothing, and `b` holds the only reference the box owns.

### Checking before use

`take` returns `null` from an empty box, so the caller must check before using the result. `isEmpty` is the safe test, and a program that skips it will get a `NullPointerException` as soon as it uses a value that isn't there.

### What Java doesn't do

The `Resource` class has no release message here, because Java doesn't report when an object is freed. If the resource must be released at a known point, it should implement `AutoCloseable`, as the resource management chapter showed. The hand-off decides who may use the resource. The close decides when it's released.

---

### Quiz

*These questions are written for this course.*

````quiz
After `Box b = new Box(a.take());`, what does `a` hold?
○ The same resource as b, so both can use it.
→ Not quite. take() removed the resource from a, so a no longer refers to it.
◉ Nothing. a is empty, and b holds the resource.
→ Yes. The hand-off leaves the first holder empty.
○ A copy of the resource.
→ Not quite. take() moves the reference, and no copy is made.
````

````quiz
What happens if `take()` is called on an empty box?
○ It throws an exception.
→ Not quite. take() returns null, and the program has to check for it.
◉ It returns null, so the caller must check with isEmpty() before using the result.
→ Yes. An empty box has nothing to hand over.
○ It returns a new resource.
→ Not quite. A box does not make a resource when it's emptied.
````

---

### Try it in the runner

Call `a.take()` a second time, and store the result in a variable. Predict the value before you press **Run**. It's `null`, since `a` was emptied by the first call. Then print `a.isEmpty()` again, and check that it reads `true`.

---

The hand-off gives an object to one new holder, and empties the old one. Java can't enforce it, so the program has to keep the rule itself. The chapter summary follows.
