## Interfaces

A class extends one class, but it can promise to provide the methods of several interfaces. An interface lists method signatures without bodies, and a class that implements it must provide each method. An interface is a description of what a class can do, not a place to store fields or state.

Here's the program for this step, in `src/Main.java`. The interface is declared first:

```java
interface Drawable {
    void draw();

    default void describe() {
        System.out.println("A drawable thing.");
    }
}
```

The `draw` method has no body, so every class that implements `Drawable` must provide one. The `describe` method has a body, marked `default`, so classes get it for free and may replace it.

Two classes implement the interface with `implements`:

```java
class Circle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing a circle");
    }
}

class Square implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing a square");
    }

    @Override
    public void describe() {
        System.out.println("A square.");
    }
}
```

`Circle` uses the default `describe`, and `Square` replaces it. The `public` keyword is needed on each `draw` method, since an interface's methods are public, and a class implementing them can't make them less visible.

A method can take the interface as its parameter type:

```java
static void show(Drawable item) {
    item.draw();
    item.describe();
}
```

Press **Run**. The output is:

```text
Drawing a circle
A drawable thing.
Drawing a square
A square.
```

`show` works with any object that implements `Drawable`. It calls `draw` and `describe` on the object it receives, and the object decides which version runs, as the polymorphism step describes.

### One class, several interfaces

A class can implement any number of interfaces, listed after `implements` and separated by commas. Each interface adds the methods it describes, so a class can take on several roles:

```java
class Robot implements Drawable, Movable {
    // must provide draw and move
}
```

This is how Java gives a class behavior from several sources. The class extends only one base class, and it can implement as many interfaces as the design needs.

### Interface or abstract class

An abstract class can hold fields and concrete methods, and it's the right choice when subclasses share state or code. An interface holds only method signatures and default or static methods, and it's the right choice when unrelated classes share a capability, such as being drawable. A class can extend one abstract class and implement several interfaces, so the two work together.

---

### Quiz

*These questions are written for this course.*

````quiz
What must a class do to use an interface?
○ Extend the interface, the same way it extends a class.
→ Not quite. A class implements an interface, using the implements keyword.
◉ Implement it with implements, and provide every method that has no default body.
→ Yes. The class must supply each abstract method the interface declares.
○ Create an object of the interface.
→ Not quite. An interface can't be created with new.
````

````quiz
How many interfaces can one class implement?
○ One.
→ Not quite. A class can implement several interfaces.
◉ As many as it needs, listed after implements.
→ Yes. Java allows any number of interfaces, unlike its single base class.
○ None, since interfaces are only for other interfaces.
→ Not quite. A class is the normal thing that implements an interface.
````

---

### Try it in the runner

Remove the `draw` method from `Square`, and press **Run**. The build fails, because `Square` is not abstract and does not provide `draw`, which the interface requires. Restore the method before you continue.

Then add a `Movable` interface with a method `move()`, and make `Circle` implement it as well, with `implements Drawable, Movable`. Predict whether `Circle` still builds without a `move` method. It doesn't, since each implemented interface's required methods must be provided. Add a `move` method to `Circle`, and check that the build succeeds.

---

An interface describes what a class can do, and a class can implement several. The next step returns to the inheritance tree, and shows how a single call behaves differently for each object it's made on.
