## var and patterns

Recent Java releases added features that cut repetition from code and make its intent clearer. This step covers the ones used most often: `var`, which lets the compiler work out a type from the value, and pattern matching, which tests the shape of a value and names its parts in the same step.

Here's the program for this step, in `src/Main.java`. The first part uses `var`:

```java
var count = 42;
var name = "Ada";
var scores = new int[] {1, 2, 3};
System.out.println(count + " " + name + " " + scores.length);
```

Each variable's type comes from the value on the right, so `count` is an `int`, `name` is a `String`, and `scores` is an `int[]`. The type is still fixed, and the compiler checks every later use against it. `var` only saves writing the type when the value already makes it clear.

Press **Run**. The output begins:

```text
42 Ada 3
```

### Record patterns

A record can be matched against a pattern that names its parts:

```java
Object shape = new Point(3, 4);
if (shape instanceof Point(int x, int y)) {
    System.out.println("x=" + x + " y=" + y);
}
```

The `instanceof` test checks that `shape` is a `Point`, and the pattern `Point(int x, int y)` gives its two components the names `x` and `y`. The names are available inside the `if` block. The output is:

```text
x=3 y=4
```

### Switch expressions with patterns

A `switch` can produce a value, and its cases can test types and name parts:

```java
static String describe(Object value) {
    return switch (value) {
        case Point(int x, int y) -> "point with sum " + (x + y);
        case String text -> "text " + text;
        default -> "other";
    };
}
```

Each case runs only when its pattern matches, and the first match gives the result. The `default` case handles everything else. The three calls in `main` print:

```text
point with sum 7
text hi
other
```

The `switch` is an expression, so the method can return its value directly. Each arm's value must be a `String` here, and the compiler checks that the arms agree.

---

### Quiz

*These questions are written for this course.*

````quiz
After `var count = 42;`, what type does `count` have?
○ Object
→ Not quite. The type is taken from the value, which is an int.
◉ int
→ Yes. var takes the type from the value on the right, and the type stays fixed.
○ There is no type, so count can hold anything.
→ Not quite. Every variable has a type, and later uses are checked against it.
````

````quiz
In `case Point(int x, int y) ->`, what does the pattern do?
○ It creates a new Point with those values.
→ Not quite. The pattern tests an existing value and names its parts.
◉ It checks that the value is a Point, and names its two components x and y.
→ Yes. The names are available in the arm that matched.
○ It compares the value with the numbers 0 and 0.
→ Not quite. The names are variables that take the components, not values to compare.
````

---

### Try it in the runner

Change `describe(5)` to `describe(new Point(1, 2))`, and predict the output before you press **Run**. It prints `point with sum 3`, since the record pattern matches a Point. Then move the `String text` case below `default`, and predict the result. The compiler reports an error, because a pattern case can't come after the `default` case. Move it back before you continue.

---

`var` and patterns let code say what it means with less repetition. The next step covers `Optional`, which states that a result may be missing.
