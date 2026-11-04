## Generic methods

Some methods do the same thing for many types. A method that returns the larger of two values works the same way for integers, decimals, and strings. Writing one version per type would repeat the code, and a bug fixed in one copy would be missing from the others. A generic method is written once, with a placeholder for the type, and the same code works for each type the program uses.

Here's the method from `src/Main.java`:

```java
static <T extends Comparable<T>> T largerOf(T a, T b) {
    return a.compareTo(b) > 0 ? a : b;
}
```

The `<T extends Comparable<T>>` before the return type introduces a type parameter named `T`. The bound says that `T` must be a type that can be compared with another value of its own type, which is what `Comparable` means. Inside the method, `T` stands for whatever type is used.

The comparison is `a.compareTo(b)`, not `a > b`. The `>` operator works only on primitive numbers, and `T` can be any object type, so the method calls a method that every `Comparable` provides. A result above zero means `a` comes after `b`, so the method returns `a` in that case.

### Inference

The compiler usually works out `T` from the arguments:

```java
System.out.println(largerOf(3, 7));
System.out.println(largerOf(2.5, 1.5));
```

The first call passes `int` values. Java can't use an `int` directly as a `T`, so each value is boxed into an `Integer`, and `T` is `Integer`. The second call passes `double` values, boxed into `Double`. The output is:

```text
7
2.5
```

### Naming the type

Sometimes the arguments don't settle the type, or you want to be explicit. The type goes in angle brackets before the method name, after the class name:

```java
System.out.println(Main.<String>largerOf("apple", "banana"));
```

Here `T` is `String`, and the output is `banana`, since it comes later in the alphabet.

### One version at run time

Java compiles a generic method once. At run time there's only one `largerOf`, and the type information is removed, which is called erasure. The compiler checks each use against the bound when the program is built, so a mistake is reported before the program runs. The code inside the method works through the bound, which is why `compareTo` is available.

---

### Quiz

*These questions are written for this course.*

````quiz
What does `<T extends Comparable<T>>` require of the type `T`?
○ That T is a primitive number type, such as int.
→ Not quite. T can be any object type, as long as it's comparable.
◉ That T can be compared with another value of its own type, using compareTo.
→ Yes. The bound gives the method access to compareTo.
○ That T is a String.
→ Not quite. Any comparable type can be used, not only String.
````

````quiz
Why does the method use `a.compareTo(b)` instead of `a > b`?
○ Because `>` is slower.
→ Not quite. The reason is that > works only on primitive numbers.
◉ Because T can be an object type, and the > operator does not work on objects.
→ Yes. compareTo is the comparison that Comparable provides for any type.
○ Because compareTo returns a boolean.
→ Not quite. compareTo returns a number, which the method compares with zero.
````

---

### Try it in the runner

Call `largerOf(3, 2.5)` and predict whether the compiler accepts it before you press **Run**. It refuses: the arguments have two different types, `Integer` and `Double`, and the method needs both to be the same `T`. Fix it by writing both as decimals, as in `largerOf(3.0, 2.5)`, so both arguments are `Double`.

---

A generic method is written once for any type that meets its bound. The next step applies the same idea to classes, where a whole container or wrapper can be written once for any type.
