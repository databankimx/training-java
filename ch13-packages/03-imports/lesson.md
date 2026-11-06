## Imports and repeated names

A file often uses the same class more than once, and it may name that class in several places. Java works like this: each class is compiled once, and a file refers to other classes through `import` statements, which only tell the compiler what a name means.

Here's the program for this step. The `Battery` class is in its own package, in `src/power/Battery.java`:

```java
package power;

public class Battery {
    private final int capacity;
    private int charge = 0;

    public Battery(int capacity) {
        this.capacity = capacity;
    }

    public void recharge(int amount) {
        if (amount == 0) {
            charge = capacity;
        } else {
            charge += amount;
        }
        if (charge > capacity) {
            charge = capacity;
        }
    }

    public void recharge() {
        recharge(0);
    }

    public void show() {
        System.out.println("Charge: " + charge);
        System.out.println("Capacity: " + capacity);
        System.out.println();
    }
}
```

`Main` imports it, and it imports it twice on purpose:

```java
import power.Battery;
import power.Battery;
```

Press **Run**. The output is:

```text
Charge: 20
Capacity: 50

Charge: 50
Capacity: 50

Charge: 50
Capacity: 50

```

The program works, and the duplicate import does nothing. Java accepts the same single-type import more than once, and it treats the repeat as redundant. The class is compiled once, and `Main` uses the one definition.

### The recharge rules

`recharge` has two cases. An amount of zero fills the battery, which is why `recharge()` with no argument gives a full charge. Any other amount adds to the charge, and the final check stops the charge from going above the capacity. The check runs in both cases, so a full recharge is never above the capacity either.

The second `recharge(100)` shows the cap at work. The charge would be 150, but the capacity of 50 limits it.

### How a class is defined once

Each class is defined once, in its own file, and the compiler looks up names through `import` and the package structure. A class can't be defined twice in the program, and nothing is inserted from one file into another.

---

### Quiz

*These questions are written for this course.*

````quiz
What does an `import` statement do?
○ It copies the imported class's code into the file.
→ Not quite. An import only tells the compiler what a name refers to. The code stays in its own class.
◉ It tells the compiler which class a name refers to, so the file can use it.
→ Yes. The import names the class, and the class is compiled once in its own file.
○ It runs the imported class's code.
→ Not quite. An import does nothing at run time.
````

````quiz
What happens if the same class is defined in two files of one package?
○ Both definitions are used, and the later one wins.
→ Not quite. Java doesn't merge or replace classes.
◉ The build fails, because the class is defined twice.
→ Yes. Each class name must be defined once in its package.
○ The compiler picks the file with the shorter name.
→ Not quite. The compiler doesn't choose between definitions.
````

---

### Try it in the runner

Remove the second `import power.Battery;` line from `Main`, and press **Run**. The program builds and runs as before, which confirms the repeated import changed nothing.

Then remove the `package power;` line from `Battery.java`, and press **Run**. The build fails, because `Main`'s import names `power.Battery`, and the class no longer belongs to that package. Restore the line before you continue.

---

An import names a class for the compiler, and the class is compiled once. The next step puts several classes in separate files, and shows one class built from another.
