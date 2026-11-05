## Methods

A method is a function that belongs to a class. It's called on an object, and it can use that object's fields directly, without the caller naming them. That's why a method reads more naturally than a free method that takes the object as a parameter.

Here's the class for this step, in `src/Main.java`:

```java
class Car {
    String brand;
    String model;
    int year;

    String name() {
        return brand + " " + model;
    }

    String fullName() {
        return year + " " + brand + " " + model;
    }
}
```

Inside `name`, the fields `brand` and `model` appear by name, with no object in front of them. The method works on whichever object it was called on, so `myCar.name()` uses `myCar.brand`, and another object's `name()` uses its own.

All of a Java method is written inside the class body, between the braces of the class, so the method's declaration and its body always appear together. Larger classes are easier to read when their methods are grouped together, which is the same reason the class's fields come first.

Press **Run**. The output is:

```text
Ford Mustang
1969 Ford Mustang
```

The two methods read the same fields, and each formats them differently. The `+` joins the year to the text, since the year is combined with a `String` on its left.

### Read-only methods

A method that only reads its object is a read-only method. The compiler doesn't check that a method leaves the fields alone. The usual protection is to make the fields `final` when they must not change, since the compiler does check that. The Try section shows that check.

### Calling a method

The caller writes the object, a dot, the method name, and the parentheses:

```java
myCar.name()
```

Those parentheses are required, even when the method takes nothing. Without them, `myCar.name` is a field access, and the compiler reports an error, because there is no field called `name`.

---

### Quiz

*These questions are written for this course.*

````quiz
Inside a method, how does the code refer to the object's brand?
○ Car.brand
→ Not quite. Car is the class, and brand belongs to an object.
◉ brand
→ Yes. Inside a method, the object's fields can be named directly.
○ new Car().brand
→ Not quite. That creates a new object, and doesn't use the one the method was called on.
````

````quiz
Why does `myCar.name` without parentheses fail to compile?
○ Because methods can't be called on objects.
→ Not quite. Methods are called on objects all the time.
◉ Because without parentheses, the compiler reads it as a field access, and there's no field called name.
→ Yes. The parentheses make the call, and without them the name refers to nothing.
○ Because name is a reserved word.
→ Not quite. name is an ordinary identifier here.
````

---

### Try it in the runner

Make the `brand` field `final`, by changing its line to `final String brand;`, and press **Run**. The build fails, because `final` fields must be given a value when the object is created, and this class sets `brand` in `main` instead. Restore the field to `String brand;` before you continue.

Then leave `brand` non-final, and add a method `void rename(String newBrand) { brand = newBrand; }`. Call it from `main`, and check that `name()` shows the new brand afterwards. A method like this is the kind a read-only method must not be, so keep it out of any method that is meant only to read.

---

A method is a function that belongs to a class, and it works on the object it's called on. The next step covers constructors, which set an object's fields when it's created.
