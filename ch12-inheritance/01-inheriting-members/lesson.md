## Inheriting members

Many classes share most of their behavior. A car is a kind of vehicle, and a sports car is a kind of car. Rather than writing the shared members again for each class, inheritance lets a class take on the members of another class, and add its own.

Here's the program for this step, in `src/Main.java`. The base class comes first:

```java
class Vehicle {
    String brand = "Ford";

    void honk() {
        System.out.println("Toot, toot!");
    }
}
```

The derived class names its base with `extends`:

```java
class Car extends Vehicle {
    String model = "Mustang";
}
```

The words `extends Vehicle` mean that `Car` inherits the members of `Vehicle` that are visible to it. A `Car` has `brand` and `honk()` without writing them again, and it adds `model` of its own.

Press **Run**. The output is:

```text
Toot, toot!
Ford Mustang
Toot, toot!
Ford Mustang 180
```

`myCar.honk()` and `myCar.brand` come from `Vehicle`, and `myCar.model` comes from `Car`. The `SportsCar` class extends `Car`, which extends `Vehicle`, so `fast` has all three sets of members. Inheritance can continue down a chain of any length.

### The base and the derived class

The class that is inherited from is the base class, also called the superclass, and the class that inherits is the derived class, or subclass. A derived class is a more specific kind of its base. Every `Car` is a `Vehicle`, which is the test for whether inheritance fits: the sentence "a derived is a base" should be true.

### Inherited members are not copied

The derived class doesn't copy the base's members into its own code. It refers to them through the base, so a change to the base's behavior appears in every class that inherits it. That's the reason inheritance is useful for shared behavior, and the reason it's worth using carefully.

### Extending one class

A Java class extends exactly one class. Each class in the chain above has one base, and the `extends` clause has no access keyword. Which of the base's members a derived class can use depends on their own access keywords, and the next step covers those.

---

### Quiz

*These questions are written for this course.*

````quiz
Which statement describes inheritance correctly?
○ The derived class copies the base class's code into itself.
→ Not quite. The derived class refers to the base's members, rather than copying them.
◉ The derived class gets the base's visible members, and can add its own.
→ Yes. A derived class is a more specific kind of its base.
○ The base class gets the derived class's members.
→ Not quite. Inheritance works the other way: the derived class takes on the base's members.
````

````quiz
In `class SportsCar extends Car`, which members can a SportsCar use?
○ Only its own members.
→ Not quite. It also gets the members of Car, and of Vehicle through Car.
◉ Its own, Car's, and Vehicle's members that are visible to it.
→ Yes. Inheritance passes along the chain, so each class adds to the one above.
○ Only Vehicle's members.
→ Not quite. SportsCar also has the members of Car and its own.
````

---

### Try it in the runner

Add a method called `describe()` to `Vehicle` that prints `A vehicle.` Call it on `fast`, and predict whether `SportsCar` can use it. It can: `describe` is inherited through `Car`, and no change to `Car` is needed.

Then change `String brand = "Ford";` in `Vehicle` to `private String brand = "Ford";`, and press **Run**. The build fails. The field is private to `Vehicle`, so even `Car` can't use it, and `fast.brand` in `main` is an error. Remove the keyword before you continue.

---

A derived class takes on the visible members of its base, and can add its own. The next step covers the access keywords, which decide which members a derived class can use.
