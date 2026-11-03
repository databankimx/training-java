## Extending classes

A class can extend another, which means it inherits the fields and methods of the class it extends. The new class is a subclass, and the one it extends is its superclass. The subclass gets everything the superclass has, and it can add its own.

Here's the program for this step, in `src/Main.java`:

```java
class Animal {
    private final String name;

    Animal(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    String sound() {
        return "...";
    }
}

class Dog extends Animal {
    Dog(String name) {
        super(name);
    }
}
```

Press **Run**. The output is:

```text
Rex says ...
```

`class Dog extends Animal` says that a `Dog` is an `Animal`. The `Dog` class doesn't declare `getName` or `sound`, but it has both, because it inherits them. `rex.getName()` runs the method defined in `Animal`.

### Calling the superclass constructor

The `Dog` constructor begins with `super(name);`. The `super` call runs the constructor of `Animal`, which sets the `name` field. A subclass constructor must call the superclass constructor first, either explicitly or implicitly, so that the inherited part of the object is set up before the subclass adds its own.

The `name` field in `Animal` is private, so `Dog` can't read it directly. It can only use `getName`. That's encapsulation, from the previous chapter, carried into a subclass.

### The is-a rule

Use `extends` when the new class really is a kind of the other: a dog is an animal, a square is a shape. If the relationship is only that one class uses another, a field holding an object is usually the better choice, which is called composition.

---

### Quiz

*These questions are written for this course.*

````quiz
What does `class Dog extends Animal` give `Dog`?
○ Nothing, because a subclass must redeclare everything.
→ Not quite. A subclass inherits the fields and methods of its superclass.
◉ The fields and methods of Animal, which it can use, add to, or change.
→ Yes. The subclass inherits everything the superclass declares that is accessible.
○ A copy of Animal, which can't be changed.
→ Not quite. A subclass shares the superclass's members, and it can add its own.
````

````quiz
Why can't `Dog` read the `name` field of `Animal` directly?
○ Because subclasses never read fields.
→ Not quite. Subclasses can read accessible fields, and this one is private.
◉ Because the field is private to Animal, so Dog must use the getter.
→ Yes. Private members are visible only inside their own class.
○ Because the field is final.
→ Not quite. The field is private, which is the reason. Final doesn't stop reading.
````

---

### Try it in the runner

Change `getName` in `Animal` to `private`, and predict the result. The build fails, because `Dog` calls `getName` through `rex.getName()` in `Main`, which is outside `Animal`, so the private method isn't visible. Restore `getName` to package access before you continue.

Then remove the `super(name);` line from `Dog`, and predict the result. The build fails, because `Animal` has no constructor with no parameters, so the implicit call can't be made. Restore the line before you continue.

---

A subclass inherits its superclass's members and sets up the inherited part through `super`. The next step replaces an inherited method, and shows how a parent type can call the right version.
