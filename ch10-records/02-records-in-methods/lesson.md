## Records in methods

A record is passed to a method in the same way as an array: the method receives a reference to the record, and both the caller and the method refer to the same object. A record differs from an array in one important way, which this step shows.

Here's the program for this step, in `src/Main.java`:

```java
static Point moveRight(Point p, int dx) {
    return new Point(p.x() + dx, p.y());
}

Point start = new Point(1, 2);
Point end = moveRight(start, 5);
System.out.println("start: " + start);
System.out.println("end: " + end);
```

Press **Run**. The first lines of the output are:

```text
start: Point[x=1, y=2]
end: Point[x=6, y=2]
```

`moveRight` doesn't change its argument. It builds a new `Point`, with the x value moved by `dx`, and returns it. The caller stores the result in `end`, and `start` is unchanged.

### Records can't change

A record's components are fixed when the record is created. There are no setters, so there's no method that changes `x` on an existing `Point`. The record's fields are `final`, which means they are assigned once, in the constructor.

To get a different point, a method creates a new one, as `moveRight` does. This is called immutability, and it makes values safe to pass around: a method can't alter a `Point` that its caller is using.

### What a method can change

The last part of the program passes `start` to a method and checks it afterwards:

```java
showInside(start);
System.out.println("start after: " + start);
```

```java
static void showInside(Point p) {
    System.out.println("inside: " + p);
}
```

The output is:

```text
inside: Point[x=1, y=2]
start after: Point[x=1, y=2]
```

`showInside` only reads the point, so there's nothing to change. The rule is the same as for arrays: a method can use a reference to an object, but a record gives it no way to change that object's values.

### Why records help

Because a record never changes, its value can be shared freely. A caller doesn't need to copy the record to protect it, and a method can't break a value that another part of the program relies on. That makes records a good choice for values that represent facts, such as a point, a date, or a measurement.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does `moveRight` return a new Point rather than changing the one it receives?
○ Because records can't be passed to methods.
→ Not quite. Records are passed to methods like any other object.
◉ Because a record can't be changed after it's created, so a new one is needed.
→ Yes. The components are fixed, so a different value means a different record.
○ Because returning is faster than changing.
→ Not quite. The reason is that records are immutable, not speed.
````

````quiz
After `showInside(start)`, what does `start` hold?
○ The value printed inside showInside, which is the same.
→ Not quite. The method only read the record, so it doesn't matter which value is printed.
◉ The same point it held before, since nothing could change it.
→ Yes. A record has no setters, so the call can't alter its values.
○ null, because the method used it.
→ Not quite. Passing a record to a method doesn't clear the caller's variable.
````

---

### Try it in the runner

Add a method `static void setX(Point p, int value) { p.x = value; }`, and predict whether the build succeeds. It fails. The compiler reports that `x` has private access in `Point`, and the field is also `final`, so it could never be assigned. Remove the method before you continue.

Then add `System.out.println(end.x());` after the existing output, and predict the result. It prints 6, the x value of the new point, because `end` holds the point that `moveRight` created. Remove the line before you continue.

---

A record passed to a method is shared, and it can't be changed, so a method that needs a different point returns a new one. The next step covers creating records with checks, and comparing them.
