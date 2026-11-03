## Records as types

A record is a type that groups related values under one name. You declare its parts once, and then every value of that type carries all of them together.

Here's the program for this step, in `src/Main.java`:

```java
record Car(String brand, String model, int year) {
}

public class Main {
    public static void main(String[] args) {
        Car myCar = new Car("Toyota", "Corolla", 2020);

        System.out.println("Brand: " + myCar.brand());
        System.out.println("Model: " + myCar.model());
        System.out.println("Year: " + myCar.year());
        System.out.println("Whole car: " + myCar);
    }
}
```

Press **Run**. The output is:

```text
Brand: Toyota
Model: Corolla
Year: 2020
Whole car: Car[brand=Toyota, model=Corolla, year=2020]
```

The line `record Car(String brand, String model, int year)` declares the type. The parts in parentheses are its components, each with a type and a name. Java creates a constructor, accessor methods, and the text form for you, so you write only the declaration.

The record is declared above `Main` in the same file. A file can hold several top-level types, but only one of them can be `public`, and that one has the file's name.

### Creating and reading

`new Car("Toyota", "Corolla", 2020)` creates a car with its three parts set. The values are given in the order the components were declared.

To read a part, call the method with the component's name: `myCar.brand()`. Records don't use the `.length` field or the `getBrand` style of older code. The method has the same name as the component, and it has parentheses because it's a method.

### The text form

Printing the whole record gives `Car[brand=Toyota, model=Corolla, year=2020]`. The compiler wrote that method for you, and it lists each component by name and value. You can change it, but the default is usually all you need when you're checking a value.

### Records are data, not behavior

A record is for holding values. It doesn't need a constructor body, and it doesn't need the setters and getters that a class would. The next steps add methods to a record, and show that its values can't be changed once it's created.

---

### Quiz

*These questions are written for this course.*

````quiz
How do you read the `year` part of `myCar`?
○ myCar.year
→ Not quite. A record component is read with a method call, so it needs parentheses.
◉ myCar.year()
→ Yes. The accessor method has the component's name, and it's called with parentheses.
○ myCar.getYear()
→ Not quite. Records don't use the get prefix. The method is named after the component.
````

````quiz
What does `System.out.println(myCar)` print?
○ The address of the record in memory.
→ Not quite. Java doesn't print addresses, and the record has a text form.
◉ The record's name, and each component with its value.
→ Yes. The generated text form lists every component.
○ Nothing, because records can't be printed.
→ Not quite. Printing a record works, and it shows its components.
````

---

### Try it in the runner

Add a fourth component, `double price`, to the record declaration, and predict what the existing `new Car(...)` call does. It fails to compile, because the call gives three values, and the record now needs four. Add the price, such as `19999.5`, to the call, and check the printed text form. The output includes `price=19999.5`. Restore the original declaration and call before you continue.

Then change `myCar.model()` to `myCar.model`, and predict the result. The build fails, because a record component is read with a method call, not a field access. Restore the parentheses before you continue.

---

A record groups related values under one type, and its components are read with method calls. The next step puts records to work in methods, and shows the one thing a record never does: change its values after creation.
