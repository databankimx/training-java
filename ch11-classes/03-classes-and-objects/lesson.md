## Classes and objects

A record groups values, and it can't do anything else. A class does the same grouping, and it can also hold the methods that work on those values. A class is the general form, and a record is a class whose only job is to hold values.

Here's the program for this step, in `src/Main.java`. The class is declared above `main`:

```java
class Car {
    String brand;
    String model;
    int year;
}
```

The keyword is `class`, and the names in its body are fields. A field is a variable that belongs to each object of the class. The fields here have no access keyword, so code in the same package, which includes `main` here, can read and write them. The next steps make some fields private.

A variable of the class is called an object. `new Car()` creates one object, and each object has its own `brand`, `model`, and `year`:

```java
Car carObj1 = new Car();
carObj1.brand = "BMW";
carObj1.model = "X5";
carObj1.year = 1999;
```

Press **Run**. The output is:

```text
BMW X5 1999
Ford Mustang 1969
```

### Class and record

A record is the form to reach for when a type holds values and nothing else. A class is the form to reach for when a type needs methods that work on its data, or when its data must change over time. A record's fields are fixed when it's created. A class's fields can be changed by any code that can reach them, unless you control that access, as the encapsulation step shows.

### Objects are independent

Each object holds its own values. Changing `carObj1.year` leaves `carObj2.year` alone. Two objects of the same class have the same fields, but their values are separate, and one class can describe many cars.

---

### Quiz

*These questions are written for this course.*

````quiz
What access does a field with no access keyword have in Java?
○ public, so any code can use it.
→ Not quite. Public needs the keyword.
◉ Package access, so code in the same package can use it.
→ Yes. Without a keyword, a member is visible inside its own package.
○ private, so only the class can use it.
→ Not quite. Private needs the keyword, and there is none here.
````

````quiz
What is `carObj1` in `Car carObj1 = new Car();`?
○ A class.
→ Not quite. The class is Car, and carObj1 is one object of it.
◉ An object of the class Car.
→ Yes. Each variable of a class is an object of that class.
○ A method.
→ Not quite. Methods are functions that belong to a class, and carObj1 holds data.
````

---

### Try it in the runner

Add the keyword `private` in front of `String brand;` in the `Car` class, and press **Run**. Predict whether `main` still compiles. It doesn't: the field is private to `Car`, so `main` can't read or write it. The build fails with an error that names the field. Remove the keyword before you continue.

---

A class groups data, and each variable of it is an object with its own copy of that data. Its access keywords decide which parts of the program can reach that data. The next step adds methods, which are the functions that belong to the class.
