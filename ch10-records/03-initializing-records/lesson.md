## Initializing records

A new variable with no initial values can hold anything, and that's a risk for a record as much as for a plain variable. Java gives you two tools. A record's components are always set when it's created, through its constructor, so no component can be left empty. And a record can have extra constructors, which supply default values for the components a caller leaves out.

Here's the record for this step, in `src/Main.java`. It has its main constructor, the one that takes all three components, and two more that supply defaults:

```java
record Settings(String name, int volume, boolean muted) {
    Settings() {
        this("default", 5, false);
    }

    Settings(String name) {
        this(name, 5, false);
    }
}
```

Each extra constructor calls the full one with `this(...)`, passing the default values for the components it doesn't receive. The defaults are written once, in those calls, so the record's starting state is clear to the reader.

Press **Run**, and look at the output:

```text
default 5 no
Scott 9 yes
Scott 1 yes
```

### Default values

`new Settings()` calls the constructor with no arguments. That constructor passes the default name, volume 5, and `muted` set to false. The output's first line shows that.

### Explicit values

`new Settings("Scott", 9, true)` gives all three values in order: the first goes to `name`, the second to `volume`, and the third to `muted`. The order must match the declaration. Swapping the volume and the name would fail to compile, since a number can't become a text. The compiler checks each value against its component's type.

### Copies are independent

The third variable is made from the second, with the volume changed:

```java
Settings c = new Settings(b.name(), 1, b.muted());
```

A Java record can't be changed after it's created, so the program doesn't change `b`'s copy. It builds a new record with the values it wants. The third line of output shows that change, and the second line still shows `b` with volume 9. Two records are independent objects, and creating one never changes another.

---

### Quiz

*These questions are written for this course.*

````quiz
What is the state of `new Settings()`, which calls the constructor with no arguments?
○ Every component is zero or null, whatever the defaults say.
→ Not quite. The no-argument constructor passes specific defaults to the full constructor.
◉ The name is "default", the volume is 5, and muted is false.
→ Yes. The defaults are written in the constructor that calls the full one.
○ The program won't compile, because a record needs all its components.
→ Not quite. The extra constructor supplies them, so the call compiles.
````

````quiz
In `new Settings("Scott", 9, true)`, which component receives 9?
○ name
→ Not quite. name receives the first value, "Scott".
◉ volume
→ Yes. The values are matched to the components in the order they are declared.
○ muted
→ Not quite. muted receives the third value, true.
````

---

### Try it in the runner

Add `Settings d = new Settings("Ada");` and `show(d);` to the program, and predict the output before you press **Run**. The name is `Ada`, and the volume and muted values come from the constructor that takes only a name, so volume is 5 and muted is no. The line should read `Ada 5 no`.

Then try `new Settings(5, "Ada", false)`, with the values in the wrong order, and press **Run**. The build fails, because 5 can't become a text.

---

Constructors give a record a clear starting state, and the record can't change after that. The homework applies records to a small task, and the summary brings the chapter together.
