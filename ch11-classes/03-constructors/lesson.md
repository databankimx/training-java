## Constructors

A constructor is a special method that runs when an object is created. Its job is to set the object's fields, so that every object starts in a valid state. It's written with the class's name and no return type, and it takes whatever arguments the object needs to be built.

Here's the class for this step, in `src/Main.java`:

```java
class Car {
    String brand;
    String model;
    int year;

    Car(String mk, String mod, int yr) {
        brand = mk;
        model = mod;
        year = yr;
    }
}
```

The constructor's body copies its arguments into the fields. Nothing else needs to happen, since the fields are already declared above it.

Press **Run**. The output is:

```text
1969 Ford Mustang
2014 Dodge Challenger
```

### Creating objects

The program creates two cars:

```java
Car myCar1 = new Car("Ford", "Mustang", 1969);
Car myCar2 = new Car("Dodge", "Challenger", 2014);
```

Java has one way to create an object, and it always uses `new`. The `new` allocates the object and runs the constructor that matches the arguments.

### Why the constructor matters

A class with a constructor that takes arguments can't be created without them. The compiler refuses `new Car()` or `new Car("Fiat")`, since no constructor matches, so an incomplete car can't exist. If you write no constructor at all, Java supplies one that takes no arguments, and the fields start with their default values.

### Braces are not for objects

Braces create arrays and records in Java, as earlier chapters showed. They don't create objects with constructors. An object is always created with `new`, so the constructor's rules always apply.

### Passing arguments to a base class

When a class extends another, its constructor has to build the base part first. The `super` call names the base class's constructor, and passes it the arguments it needs:

```java
class Car {
    Car(String mk, String mod, int yr) {
        // sets brand, model, and year
    }
}

class ElectricCar extends Car {
    ElectricCar(String mk, String mod, int yr) {
        super(mk, mod, yr);
        // the setup for ElectricCar itself goes here
    }
}
```

The `super` call must be the first statement in the constructor. The base constructor runs first, with the values passed to `super`, and then the rest of the derived constructor runs. If a derived class has no constructor of its own, Java supplies one that calls the base class's no-argument constructor, so the base must have one.

The inheritance chapter covers `extends` in full.

### A constructor isn't a method

A constructor is called by `new`, never by writing `myCar.Car(...)`. It runs once per object, and it doesn't return a value. Methods are called on existing objects, and they can be called many times. Both are written inside the class, but their jobs are different.

---

### Quiz

*These questions are written for this course.*

````quiz
What does a constructor have as its return type?
○ int
→ Not quite. A constructor returns nothing at all, and has no return type.
◉ None. It has no return type, and its name is the class name.
→ Yes. A constructor's name is the class name, and it returns nothing.
○ void
→ Not quite. A constructor doesn't have a return type, even void.
````

````quiz
When does a constructor run?
○ Each time a method is called.
→ Not quite. Methods don't run the constructor.
◉ When an object of the class is created with new.
→ Yes. Creating the object calls its constructor, once.
○ When the program ends.
→ Not quite. Constructors run at creation, and an object is freed later by the collector.
````

---

### Try it in the runner

Add `Car myCar3 = new Car("Fiat", "500", 2010);` to `main`, with a line to print it, and predict whether it compiles. It does, because the arguments match the three-argument constructor. Then try `Car myCar4 = new Car("Fiat");`, with only one argument, and press **Run**. The build fails, since no constructor takes one argument. Remove the line before you continue.

---

A constructor sets an object's fields when the object is created, so each object starts in a known state. The next steps cover equality and text, enums, and natural order, before access control.
