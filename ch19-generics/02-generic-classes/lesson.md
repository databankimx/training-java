## Generic classes

Generic methods let one method work for many types. Generic classes do the same for a whole class. A container, a wrapper, or a pair of values can be written once, with the type left open, and then used with any type. The collections from Chapter 14 are generic classes: `List<Integer>` and `List<String>` come from one definition.

Here's the class from `src/Main.java`:

```java
class Box<T> {
    private T item;

    Box(T value) {
        item = value;
    }

    T get() {
        return item;
    }

    void set(T value) {
        item = value;
    }
}
```

The `<T>` after the class name introduces the type parameter for the whole class, so `T` can appear in the field, the constructor, and the methods. Each use names the type in angle brackets:

```java
Box<Integer> number = new Box<>(42);
Box<String> word = new Box<>("hello");
```

The empty brackets in `new Box<>(42)` ask the compiler to work out the type from the variable on the left, which is `Integer` here. Each variable has a type that the compiler checks on every use.

Press **Run**. The output is:

```text
42
7
hello
```

The program creates a `Box<Integer>` holding 42, changes it to 7 with `set`, and reads the `Box<String>` holding "hello". The two boxes don't interfere with each other, since their types are different.

### One class at run time

A `Box<Integer>` and a `Box<String>` are the same class at run time. Java removes the type argument when it compiles the program, so there's one `Box` class, and the compiler has already checked every use. This is the same erasure the generic methods step described. The compiler does the work of keeping the types apart, so the code stays the same for every type.

### Primitive types need their wrappers

A type argument must be an object type, so `Box<int>` is not allowed. The wrapper class `Integer` stands in for `int`, and `Box<Integer>` holds a boxed `int`. The compiler converts between the two automatically in most places, as the generic methods step showed. The same goes for `Double` and `double`.

### Where the definition goes

A generic class is written in its own file, like any other class in this course. The compiler checks the class once, against its bound, and then checks each use. Nothing needs to be repeated for each type.

---

### Quiz

*These questions are written for this course.*

````quiz
How many classes does the Java compiler produce from `Box<Integer>` and `Box<String>` at run time?
○ Two classes, one for each type.
→ Not quite. Java removes the type argument, so both are the same class at run time.
◉ One class, Box, which the compiler has already checked for each type.
→ Yes. The type argument is removed when the program is compiled.
○ None, since generic classes exist only in the source.
→ Not quite. There is a Box class at run time, used for both types.
````

````quiz
Why can't a generic class be given `int` as its type argument?
○ Because int is too small to hold a value in a box.
→ Not quite. The reason is about object types, not size.
◉ Because a type argument must be an object type, so the wrapper Integer stands in for int.
→ Yes. Primitive types can't be type arguments, and Integer is the object type used instead.
○ Because generic classes only accept Strings as type arguments.
→ Not quite. Generic classes accept any object type as their type argument.
````

---

### Try it in the runner

Create a `Box<Double>` holding 2.5, and call `set` with the int value 4. Predict whether the compiler accepts it before you press **Run**. It refuses. Java doesn't convert an `int` to a `Double` in this position, so the argument must be written as 4.0. Make that change, and predict the result of `get`. It returns 4.0.

---

A generic class is written once, and it works for any type that the program gives it. The next step is the chapter summary, and then two homework exercises that use generics for a container and a pair of values.
