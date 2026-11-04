## Lambda basics

A function you need once, in one place, is awkward to declare separately. A lambda is an unnamed function written where it's used. It's the feature that lets code say "here is the rule" directly in a call, and it's the usual way to customize the library's sorting and searching.

Here's the simplest lambda from `src/Main.java`:

```java
Function<Integer, Integer> square = x -> x * x;
System.out.println("Square of 4: " + square.apply(4));
```

The arrow `->` separates the parameters from the body. The parameter `x` has no type written, because the compiler works it out from the type `Function<Integer, Integer>`, which takes one `Integer` and returns one `Integer`. The body is a single expression, and its value is the result. The lambda is stored in a variable named `square`, and you call it with `apply`. The output is `Square of 4: 16`.

The `Function` interface is in `java.util.function`, which is why the program imports it. A lambda always has an interface type, and the interface says what shape of function it is. `Function` takes one value and returns one, and other interfaces in the same package take none, or two, or return nothing.

### A lambda as a comparison

A list's `sort` method takes a comparison, which decides whether one value should come before another. A lambda can supply it:

```java
values.sort((a, b) -> Integer.compare(b, a));
```

A Java comparison returns a number, not a yes or no answer. It's negative when `a` should come before `b`, zero when they are equal, and positive when `a` should come after `b`. `Integer.compare` returns that number for two `int` values. Comparing `b` with `a`, as here, puts the largest values first. Press **Run**, and the output is:

```text
Square of 4: 16
Descending: 9 6 5 4 3 2 1 1
```

The sort itself is in the library. The rule that defines the order now comes from the code that calls it, written at the place it's used.

### Comparisons must be consistent

A comparison used for sorting must give a consistent order: if `a` comes before `b`, `b` must not come before `a`, and a value must compare as equal to itself. `Integer.compare` has this property for every pair of values. A rule that doesn't, such as one that gives different answers for the same pair depending on the order of the arguments, can make the sort give a wrong result or fail.

---

### Quiz

*These questions are written for this course.*

````quiz
What does `(a, b) -> Integer.compare(a, b)` return for `a` smaller than `b`?
○ true
→ Not quite. A Java comparison returns an int, not a boolean.
◉ A negative number, which means a comes before b.
→ Yes. The sort puts a before b when the result is negative.
○ A positive number, which means a is larger.
→ Not quite. A positive result means a comes after b, which would put the larger value first.
````

````quiz
Which comparison sorts a list into descending order?
○ (a, b) -> Integer.compare(a, b)
→ Not quite. That sorts ascending, smallest first.
◉ (a, b) -> Integer.compare(b, a)
→ Yes. Swapping the arguments reverses the order.
○ (a, b) -> a == b
→ Not quite. Equality is not a comparison that orders values.
````

---

### Try it in the runner

Change the comparison to `Integer.compare(a, b)`, and predict the order of the output before you press **Run**. The list is sorted from smallest to largest: `1 1 2 3 4 5 6 9`.

---

A lambda is a function written where it's needed, and a comparison lambda gives the sort its rule. The next step covers captures, which let a lambda use the variables around it.
