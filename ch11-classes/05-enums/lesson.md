## Enums

Some values come from a short, fixed list: the days of the week, the compass points, the states of an order. An `int` or a `String` can hold any value, so a bad one such as 9 for a weekday, or "Funday", gets through until something breaks. An enum names the allowed values, and the compiler accepts only those.

Here's the enum from `src/Main.java`:

```java
enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    boolean isWeekend() {
        return this == SATURDAY || this == SUNDAY;
    }
}
```

The constants are listed in the braces. Each is an object of type `Day`, created once by Java. A variable of type `Day` can hold only one of the seven, and a method can be added to the enum like any class's method. Here `isWeekend` compares the value with two constants.

Press **Run**. The first lines of output are:

```text
Today: WEDNESDAY
Weekend: false
Position: 2
```

Printing an enum shows its name. `ordinal` gives the position of the constant in the list, counting from 0, so WEDNESDAY is at position 2.

### switch on an enum

The arrow form of `switch` from the decisions chapter works on enums, and the case labels are just the constant names:

```java
switch (today) {
    case SATURDAY, SUNDAY -> System.out.println("Rest");
    default -> System.out.println("Work");
}
```

The output is `Work`. Each label is a constant of the enum, so there's no need to write `Day.` in front of it.

### Fields and constructors

An enum can carry data. Each constant is created with its own values, through a constructor:

```java
enum Planet {
    MERCURY(0.38), EARTH(1.0);

    private final double gravity;

    Planet(double gravity) {
        this.gravity = gravity;
    }

    double gravity() {
        return gravity;
    }
}
```

The loop in `main` uses `Planet.values()`, which returns every constant in order:

```java
for (Planet planet : Planet.values()) {
    System.out.println(planet + " gravity " + planet.gravity());
}
```

The output is:

```text
MERCURY gravity 0.38
EARTH gravity 1.0
```

### valueOf

`valueOf` turns a name into the constant with that name:

```java
Day parsed = Day.valueOf("FRIDAY");
System.out.println("Parsed: " + parsed);
```

The output is `Parsed: FRIDAY`. A name that matches no constant throws an `IllegalArgumentException`, so text read from a file or a user must be checked, or the exception handled.

---

### Quiz

*These questions are written for this course.*

````quiz
What does `Day.WEDNESDAY.ordinal()` return?
○ WEDNESDAY
→ Not quite. ordinal gives a number, not the name.
◉ 2
→ Yes. Positions start at 0, so WEDNESDAY is the third constant.
○ 3
→ Not quite. The first constant is at position 0, so the third is at position 2.
````

````quiz
What does `Day.valueOf("Funday")` do?
○ It returns null.
→ Not quite. A name that matches no constant is an error, not a null value.
◉ It throws an IllegalArgumentException, since no constant has that name.
→ Yes. Check text against the constants before you convert it.
○ It returns the first constant.
→ Not quite. valueOf returns only a constant whose name matches exactly.
````

---

### Try it in the runner

Change `Day.valueOf("FRIDAY")` to `Day.valueOf("Friday")`, and predict the result before you press **Run**. The name is written in a different case, and valueOf matches names exactly, so it throws an `IllegalArgumentException`. Change the name back before you continue.

Then change `Day today = Day.WEDNESDAY;` to `Day today = Day.SATURDAY;`, and predict the first four lines of output before you press **Run**. The weekend is true, so the first lines print `Today: SATURDAY`, `Weekend: true`, and `Position: 5`, and the switch prints `Rest`.

---

An enum fixes the set of values a variable can hold, and it can carry data and methods. The next step covers `Comparable`, which lets a class define its own order.
