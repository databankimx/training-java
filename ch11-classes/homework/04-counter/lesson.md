## Homework: Counter

Write a class that counts, with a constructor that sets a starting value.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The class should:

* have a constructor that takes a starting count, and stores it in a private field
* have a constructor that takes no arguments, and starts the count at zero
* have an `increment` method that adds one to the count
* have a `reset` method that sets the count back to zero
* have a `value` method that returns the count

In `main`:

* create a counter with `new Counter()`, so the count starts at zero
* call `increment` three times, then print `Count: 3`
* call `reset`, then print `Count: 0`
* create a second counter starting at 10 with `new Counter(10)`, call `increment` once, and print `Count: 11`

### Why two constructors

The no-argument constructor is written as a call to the other one, `this(0)`. The two constructors share one piece of code, so the starting value is set in only one place. This is the overloading idea from the methods chapter, applied to constructors.

### What to check

The output should be:

```text
Count: 3
Count: 0
Count: 11
```

The first line depends on the three calls, and the second on `reset` returning the count to zero. The last shows that the starting value is used.

### Extensions

* Add a `decrement` method that refuses to go below zero, and print a message when it's called at zero.
* Try removing the no-argument constructor, and predict the compiler's message when `new Counter()` is written in `main`. Then think about whether a class with only the starting-value constructor is ever better.

---

*This exercise is not from the book. It practices the ideas in Chapter 11.*
