## Classes and objects

A class is a blueprint. It declares the fields an object holds and the methods it can perform. An object is one instance made from the blueprint, with its own copy of the fields. Two objects from the same class have the same shape, but their values can differ.

Here's the program for this step, in `src/Main.java`. The class `Counter` comes first, above `Main`:

```java
class Counter {
    int count;

    void increment() {
        count++;
    }

    int current() {
        return count;
    }
}
```

Press **Run** after the `Main` class is in place, with its two counters. The output is:

```text
clicks: 2
visits: 1
```

`int count;` is a field. Each `Counter` object has its own `count`. `increment` and `current` are instance methods: they belong to an object, and they work on that object's fields.

### Creating objects

`new Counter()` creates an object. The `Counter` class is the blueprint, and `clicks` is one object made from it, while `visits` is a separate object from the same blueprint.

Calling `clicks.increment()` runs `increment` on `clicks`, so only `clicks.count` changes. The `visits` object is not affected, which is why `visits` reads 1 and `clicks` reads 2.

### Instance methods and static methods

The methods in the last two steps were declared `static`. They belong to the class as a whole, and they're called on the class name, with no object. The methods in `Counter` have no `static`, so they belong to each object, and they need an object to call them.

The rule is simple: a method that works on a particular object's data is an instance method, and a method that doesn't depend on any object is static.

### Fields have defaults

A field that isn't given a value gets the default for its type, the same defaults the arrays chapter described. `count` starts at 0 in each new `Counter`, so it's safe to increment without setting it first.

---

### Quiz

*These questions are written for this course.*

````quiz
What is the difference between a class and an object?
○ They are the same thing with different names.
→ Not quite. A class is the blueprint, and an object is one thing made from it.
◉ A class is the blueprint, and an object is one instance with its own fields.
→ Yes. Each object has its own copy of the fields the class declares.
○ An object is a class that has been compiled.
→ Not quite. Compiling produces class files, but the object is created at run time with new.
````

````quiz
After `clicks.increment();` twice and `visits.increment();` once, what does `visits.current()` return?
○ 3, because the counters share a value.
→ Not quite. Each object has its own count, so the counters are separate.
◉ 1, because visits was incremented once.
→ Yes. Only visits's own count changed.
○ 0, because increment doesn't change anything.
→ Not quite. increment changes the object it's called on.
````

---

### Try it in the runner

Add `visits.count = 10;` after the increments, and predict `visits.current()`. The field is accessible from `Main`, because it has no access modifier, so the value is 10. Remove the line before you continue. The next steps explain why this is usually not what a class should allow.

Then add a method `static void reset(Counter c) { c.count = 0; }`, and call `reset(clicks)` before printing. Predict the printed value for clicks. It's 0, because the method receives a reference to the same object, as the methods chapter showed for arrays. Remove the method and the call before you continue.

---

A class describes what each object holds and what it can do, and each object keeps its own data. The next step covers constructors, which set an object's fields when it's created.
