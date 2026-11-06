## Custom iterables

The enhanced `for` loop works on arrays and on the collections from the collections chapter. It also works on your own classes, if they implement `Iterable`. A class that implements `Iterable` says that it can produce a sequence of values one at a time, and the loop asks for them.

Here's the class from `src/Main.java`. A countdown holds a starting value and produces the values down to 1:

```java
static class Countdown implements Iterable<Integer> {
    private final int start;

    Countdown(int start) {
        this.start = start;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int next = start;

            @Override
            public boolean hasNext() {
                return next > 0;
            }

            @Override
            public Integer next() {
                return next--;
            }
        };
    }
}
```

The `iterator` method returns an `Iterator`, an object that remembers its place in the sequence. `hasNext` says whether any values are left, and `next` gives the next value and moves on.

The inner part of this code is an anonymous class: an object written in place, with no name, that implements `Iterator`. It's used once, and it keeps its own `next` field, separate from the countdown's `start`. Anonymous classes are common when an object is needed only in one place.

### Using the countdown

The enhanced `for` loop calls `iterator`, then `hasNext` and `next` on each pass:

```java
for (int value : new Countdown(3)) {
    System.out.println(value);
}
System.out.println("Liftoff");
```

Press **Run**. The output is:

```text
3
2
1
Liftoff
```

A countdown from 0 has no values, so `hasNext` is false at once, and the second loop prints nothing:

```text
The empty countdown printed nothing
```

### When to implement Iterable

A class that holds a collection of values, or produces them, is a good candidate. Implementing `Iterable` lets callers use the enhanced `for` loop, and it hides how the values are stored. The caller doesn't need to know whether the values come from a list, a file, or a calculation.

---

### Quiz

*These questions are written for this course.*

````quiz
What does a `for` loop over an `Iterable` call on the object to get each value?
○ get
→ Not quite. The loop asks an Iterator for the values, using hasNext and next.
◉ iterator, and then hasNext and next on the iterator it returns.
→ Yes. The class supplies the iterator, and the loop drives it.
○ size
→ Not quite. The loop doesn't need the number of values in advance.
````

````quiz
Why does the countdown from 0 print nothing?
○ Because the loop runs once with the value 0.
→ Not quite. The loop checks hasNext before it gives any value.
◉ Because hasNext returns false at once, so no value is produced.
→ Yes. The iterator is finished before the first value.
○ Because iterators cannot start at 0.
→ Not quite. A starting value of 0 is allowed, and it simply gives no values.
````

---

### Try it in the runner

Change `new Countdown(3)` to `new Countdown(5)`, and predict the output before you press **Run**. It prints 5, 4, 3, 2, 1, then `Liftoff`. Then change `hasNext` to `return next >= 0;`, and predict the result for `new Countdown(3)`. It also prints 0 at the end, since the iterator now gives a value for 0.

---

Implementing `Iterable` lets your own class work with the enhanced `for` loop, and the caller needs to know nothing about how its values are stored.
