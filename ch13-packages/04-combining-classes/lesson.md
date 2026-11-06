## Combining classes across files

This step puts the chapter together. Three classes live in separate files, and one of them is built from the other two. Each class is small, and each file does one job. The program is spread across files, but the design is the one from the classes chapter: an `ElectricCar` is a `Car`, and it has a `Battery`.

The relationships are visible in the files:

* `ElectricCar.java` extends `Car`, because the class inherits from `Car`.
* `ElectricCar.java` holds a `Battery` in a field, because the class has one.

Here's the start of `ElectricCar.java`, which does both:

```java
class ElectricCar extends Car {
    private final Battery battery = new Battery();

    ElectricCar(String makeName, String modelName, int yearMade, int charge) {
        super(makeName, modelName, yearMade);
        if (charge > 0) {
            battery.recharge(charge);
        }
    }
    // charge and showCharge methods follow
}
```

Inheritance is written with `extends Car`. The `battery` field holds a `Battery` object, which is built when the `ElectricCar` is built, because its field initializer runs as part of the construction.

### The base constructor

The constructor of `ElectricCar` passes its arguments to the base class with `super(...)`:

```java
super(makeName, modelName, yearMade);
```

The `super` call must be the first statement in the constructor. `Car` has to be built before its derived part can be set up, so the arguments for `Car` are passed here. The rest of the constructor then runs for the parts that belong to `ElectricCar`: it gives the battery an initial charge when one is given.

The `battery` field doesn't appear in the `super` call, because it has no arguments to pass. Its own constructor runs with the default capacity of 100, through the `Battery()` constructor, which calls the one that takes a capacity.

### What the program prints

Press **Run**. The output is:

```text
Make: Ford
Model: Escort
Year: 1986

Make: Nissan
Model: Leaf
Year: 2015

Charge: 25
Capacity: 100

Charge: 50
Capacity: 100

Charge: 100
Capacity: 100

```

The first two blocks come from the `Car` objects, through the `display` method that `ElectricCar` inherits from `Car`. The last three come from the electric car's battery. Each `charge` call changes the battery and nothing else. The final `charge()` with no argument fills the battery, which is the rule from the imports step.

---

### Quiz

*These questions are written for this course.*

````quiz
In `ElectricCar(...)`, what does `super(makeName, modelName, yearMade);` do?
○ It calls the display method of Car.
→ Not quite. display is not called by the constructor.
◉ It passes the arguments to the Car constructor, which runs before the rest of the constructor.
→ Yes. The base part is built first, so its arguments are given in the super call.
○ It declares a new field called Car.
→ Not quite. Car is the base class, and nothing new is declared.
````

````quiz
Why does `ElectricCar` need both `Car` and `Battery`?
○ Because every class must use every other class.
→ Not quite. A class uses only the classes its design needs.
◉ Because ElectricCar extends Car and holds a Battery, so both must exist to build it.
→ Yes. The compiler needs both classes to build an ElectricCar.
○ Because one of them is a library class.
→ Not quite. Both are the course's own classes.
````

---

### Try it in the runner

Call `eCar.charge(200)` and predict the charge shown afterwards. The battery is capped at its capacity of 100, so the output is `Charge: 100`.

Then call `eCar.charge(-5)` and predict the result. The battery has no check for a negative amount, so the charge drops to 95. That's a real weakness of the class, and a good prompt for thinking about where validation belongs.

---

Three classes, three files, and a clear picture of which class depends on which. The summary covers the chapter's rules for keeping code in files, and the homework builds a small multi-file program of your own.
