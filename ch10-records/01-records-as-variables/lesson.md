## Records as variables

A variable holds one value. Often a program needs several values that belong together: a car has a brand, a model, and a year, and those three values travel as one thing. A record groups them under one name, and each value inside it is a component.

Here's the program for this step, in `src/Main.java`. The record is declared once, outside `main`:

```java
record Car(String brand, String model, int year) {
}
```

The declaration creates a new type, `Car`, in the same way `int` and `String` are types. It doesn't create any cars. It describes what a car contains: a brand, a model, and a year. Declare it outside `main`, so every method can use it.

Create a variable of the type with `new`, and give it the values for its components in the order they were declared:

```java
Car myCar1 = new Car("BMW", "X5", 1999);
Car myCar2 = new Car("Ford", "Mustang", 1969);
```

Press **Run**. The output is:

```text
BMW X5 1999
Ford Mustang 1969
```

Each car is one variable, and its components are read with a method named after the component. `myCar1.brand()` is the brand of the first car, and `myCar2.year()` is the year of the second. The two cars have the same components, but each car has its own values.

### Components are set when the record is created

A record's components can't be set one at a time after it's created. Its components are fixed by the constructor call, and every component must be given a value there. That's why each car is created in one line with all three values.

The same rule explains why a record has no setter. Once `myCar1` exists, its brand can't change, so a program that needs a different car creates a different record.

### Declaring the record and the variables

Java also lets you declare a record inside another class, and some programs do that to keep the type close to the code that uses it. Declare the record with a name, as this step does, so you can create as many variables of it as you need, and pass it to methods.

### Components have their own types

Each component has its own type, and the compiler checks every value against it. `new Car("BMW", "X5", "1999")` won't compile, since `year` is an `int`. The record's declaration lists those types, so you can read it to see what each component expects.

---

### Quiz

*These questions are written for this course.*

````quiz
What does the declaration `record Car(...) { }` create?
○ A car, ready to use.
→ Not quite. It describes a car, but it doesn't create one.
◉ A new type, which variables of type Car can be made from.
→ Yes. The declaration names the type and lists its components.
○ A method that prints a car.
→ Not quite. A record is data, not a method.
````

````quiz
How do you read the year of `myCar1`?
○ myCar1.year
→ Not quite. A record component is read with a method call, so it needs parentheses.
◉ myCar1.year()
→ Yes. The accessor method has the component's name, and it's called with parentheses.
○ Car.year()
→ Not quite. The method is called on the variable, not on the type's name.
````

---

### Try it in the runner

Add a component `String color` to the record declaration, and press **Run**. Predict the result before you do. The build fails, because the two `new Car(...)` calls still give three values, and the record now needs four. Add a color to each call, such as `"white"` and `"black"`, and check that the program builds again. Then print `myCar1.color()`, and predict the output. It prints `white`, since the component now has a value. Restore the original declaration and calls before you continue.

Then change `new Car("BMW", "X5", 1999)` to `new Car("BMW", "X5", "1999")`, and predict the result. The build fails, because `year` is an `int`, and a `String` can't be stored in it.

---

A record groups related values under one type, and its components are fixed when the variable is created. The next step passes records to methods, and returns them from methods, which lets a program move a whole group of values at once.
