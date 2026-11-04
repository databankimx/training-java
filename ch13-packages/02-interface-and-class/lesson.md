## Interfaces and classes in separate files

So far every program has lived in one file, or in a few files that each hold one class. A larger program is easier to work with when each class has its own file, and when the part other code depends on is kept apart from the code that does the work. Java does this with an interface and a class. The interface declares what a class provides, and the class file says how it works.

Here's the program for this step. It has three files in the step's `src` folder. The interface comes first. It declares the method that a car can display, and nothing else:

```java
public interface CarDescription {
    void display();
}
```

The interface holds declarations only. It says what a `CarDescription` can do, and any code that uses one needs no more than that. Java interfaces can also hold default methods, which the inheritance chapter covered, but this one has only the declaration.

The class file implements the interface, and holds the details:

```java
public class Car implements CarDescription {
    private final String make;
    private final String model;
    private final int year;

    public Car(String makeName, String modelName, int yearMade) {
        make = makeName;
        model = modelName;
        year = yearMade;
    }

    @Override
    public void display() {
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
    }
}
```

The `implements CarDescription` clause ties the class to the interface, and the compiler checks that `display` is provided with a matching signature. The `@Override` annotation makes that check explicit.

`Main` uses the interface as the type of its variables, and it never names `Car` in its declarations:

```java
CarDescription first = new Car("Ford", "Escort", 1986);
first.display();
```

Press **Run**. The output is:

```text
Make: Ford
Model: Escort
Year: 1986

Make: Nissan
Model: Leaf
Year: 2015

```

The two calls create cars, and each is held by a `CarDescription` variable. `main` calls `display` without knowing how it's written, and the car does the work.

### Where the build finds the files

Java has no separate build script for this. The runner compiles every `.java` file in `src` together, so `Main` can see both `CarDescription` and `Car` without any setup. Each public type must be in a file with its name, which is why the three files are named as they are.

### Why separate the two

The interface is what other code depends on. Once `Main` depends only on `CarDescription`, changing how a car prints means changing `Car.java`, and nothing that uses the interface needs to change. Keeping the declaration in one file and the implementation in another is the main reason for the split.

---

### Quiz

*These questions are written for this course.*

````quiz
What goes in an interface such as `CarDescription`?
○ The complete code of every method.
→ Not quite. A method's code goes in the class that implements the interface.
◉ Method declarations, which the implementing class must provide.
→ Yes. The interface says what a class can do, and the class provides the details.
○ The main method.
→ Not quite. main belongs in a class that the program starts from, and it isn't part of an interface.
````

````quiz
What does `implements CarDescription` in the `Car` class require?
○ Nothing, since the names only need to match.
→ Not quite. The compiler checks that each required method is provided.
◉ That Car provides every method the interface declares, with matching signatures.
→ Yes. If Car doesn't provide display, the build fails.
○ That Car is copied into CarDescription.
→ Not quite. The class keeps its own code, and the interface stays a declaration.
````

---

### Try it in the runner

Remove the `display` method from `Car`, and press **Run**. The build fails, because `Car` claims to implement `CarDescription` but doesn't provide `display`. Restore the method before you continue.

Then delete `Car.java` from the step's `src` folder, and press **Run**. The build fails, because `Main` can't find a class named `Car`. Restore the file before you continue.

---

An interface says what a class can do, and the class says how. The next step covers imports, which name the classes a file uses from elsewhere.
