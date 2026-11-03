## Abstract classes

Sometimes a parent class describes a kind of thing without being a usable thing itself. A shape is a useful idea, but there's no shape to draw until it's a square or a circle. An abstract class captures that: it can declare methods that every subclass must provide, and it can't be created directly.

Here's the program for this step, in `src/Main.java`. The `Shape` class declares `area` with no body:

```java
abstract class Shape {
    abstract double area();

    String describe() {
        return getClass().getSimpleName() + " with area " + String.format("%.2f", area());
    }
}
```

Each shape overrides `area` with its own formula:

```java
class Square extends Shape {
    private final double side;

    Square(double side) {
        this.side = side;
    }

    @Override
    double area() {
        return side * side;
    }
}
```

Press **Run**. The output is:

```text
Square with area 4.00
Circle with area 3.14
```

The `main` method creates a square and a circle, stores them in an array of `Shape`, and calls `describe` on each. `describe` is written once in `Shape`, and it works for every subclass, because it calls `area()` and the subclass supplies that.

### The abstract keyword

`abstract` appears twice in the program. On the class, it means the class can't be created with `new`. On a method, it means the method has no body, and every concrete subclass must override it. A class with an abstract method must itself be abstract.

Trying to create a `Shape` directly, with `new Shape()`, is a compile error. That's the point: a shape with no kind has no area to compute.

### A shared method

`describe` is a normal method, not an abstract one. It gives every shape the same output format, and it uses `area` for the part that varies. This pattern, a template method with a varying step, is common in abstract classes: the shared structure is written once, and each subclass fills in its own part.

`getClass().getSimpleName()` gives the name of the object's real class, so the output names `Square` and `Circle`, not `Shape`. That's the polymorphism from the last step at work.

---

### Quiz

*These questions are written for this course.*

````quiz
What happens if you write `new Shape()` when Shape is abstract?
○ It creates a shape with an area of zero.
→ Not quite. An abstract class can't be created at all.
◉ It fails to compile, because an abstract class can't be instantiated.
→ Yes. Only concrete subclasses can be created with new.
○ It runs, and the area is taken from the first subclass.
→ Not quite. The compiler refuses the call before the program runs.
````

````quiz
Why does `describe` work for both a Square and a Circle, although it's written only once?
○ Because it has a body that every shape shares, and it calls area, which each subclass supplies.
→ Yes. The shared method depends on the abstract method, and each subclass provides its own version of that.
○ Because it's declared static.
→ Not quite. It's an instance method, so it works on each object.
○ Because it creates a new shape each time.
→ Not quite. It doesn't create anything. It uses the object it's called on.
````

---

### Try it in the runner

Add `Shape plain = new Shape();` to `main`, and predict the result. The build fails, because `Shape` is abstract. Remove the line before you continue.

Then remove the `area` method from `Circle`, and predict the result. The build fails, because `Circle` is concrete and doesn't implement the inherited abstract method `area`. Restore the method before you continue.

---

An abstract class shares a structure, and leaves the varying step to its subclasses. The summary gathers the chapter together, and the homework builds a small hierarchy of its own.
