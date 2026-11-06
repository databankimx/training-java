## Access control and encapsulation

Access control decides which parts of the program can use a class's fields and methods. Encapsulation is the design that follows from it: an object keeps its data private, and changes the data only through methods that check each change. The result is that an object can't be left in a state the class doesn't allow.

Here's the class for this step, in `src/Main.java`:

```java
class Car {
    private String brand;
    private String model;
    private int year = 0;
    // constructor, getter, setter, and describe() go here
}
```

The `private` keyword means `brand`, `model`, and `year` can't be used from outside the class. `main` can't write `myCar.year` at all, and the compiler reports the error. The methods marked without `private` form the class's interface, the part the rest of the program can use.

### Getters and setters

Because the data is private, the methods provide access to it. A getter reads a field, and a setter changes one:

```java
int getYear() {
    return year;
}

void setYear(int yr) {
    if (yr < 1886) {
        throw new IllegalArgumentException("year is before cars existed");
    }
    year = yr;
}
```

The setter is where encapsulation earns its place. It checks the value before storing it. A year before 1886 is refused, since no car existed before then, and the exception stops the assignment. The object keeps its last valid year.

Press **Run**. The output is:

```text
1969 Ford Mustang
2014 Ford Mustang
year is before cars existed
2014 Ford Mustang
```

The setter accepts 2014. The attempt to set 1700 throws, the `catch` prints the message, and the last line shows that the year is still 2014. The bad value never reached the object.

The exception is an `IllegalArgumentException`, the standard class for a value that a method won't accept. The exceptions chapter covered the choice of class.

### The constructor uses the setter

The constructor calls `setYear(year)` rather than assigning `this.year` directly. That way, the same check applies to an object's first value as to every later change. A constructor that bypassed the setter could create a car with a year the setter would refuse, which would make the rule easy to break.

### Why keep data private

Private data can be changed in one place, the class itself, so a rule written there applies everywhere. If the program stored the year in a public field, every part of the program would have to remember the rule, and one forgotten check would let a bad value in. The private field and the setter keep the rule in a single place.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does the setter check the year before storing it?
○ Because the compiler requires a check in every setter.
→ Not quite. The compiler doesn't require it. The class does, to keep its rule.
◉ So an object can't be left with a year that the class's rules don't allow.
→ Yes. The check keeps every change inside the rules the class sets.
○ Because the year is stored as text.
→ Not quite. The year is an int, and the check is about its value.
````

````quiz
Which statement about `private` fields is true?
○ Any part of the program can read them.
→ Not quite. Private fields are hidden from outside the class.
◉ They can be used only from inside the class's own methods.
→ Yes. Outside code must go through the public methods.
○ They can't be used by any method, even inside the class.
→ Not quite. The class's own methods can use them freely.
````

---

### Try it in the runner

Add the line `myCar.year = 1700;` to `main`, and predict whether it compiles. It doesn't: `year` is private, so `main` can't reach it. Use `setYear` instead, as the program does, and the check runs. Remove the line before you continue.

Then add `System.out.println(myCar.getYear());` to the end of `main`, and predict the value it prints after the failed call to `setYear(1700)`. It prints 2014, since the setter refused the change.

---

Encapsulation keeps an object's rules in one place, and the setters enforce them. The summary and homework ask you to build a class of your own, using both ideas.
