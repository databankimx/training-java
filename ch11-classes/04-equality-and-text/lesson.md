## Equality and text

A class defines what its objects are. Two of the questions a beginner asks are: when are two objects equal, and what does an object look like when it's printed. Java answers both by letting the class override three methods from `Object`: `equals`, `hashCode`, and `toString`.

Here's the class from `src/Main.java`:

```java
@Override
public boolean equals(Object other) {
    if (this == other) {
        return true;
    }
    if (!(other instanceof Car car)) {
        return false;
    }
    return brand.equals(car.brand) && year == car.year;
}
```

The default `equals` compares identity, which means the same object. A class overrides it to say what equality means for its own data. Here two cars are equal when their brand and year match, even when they are different objects. The `instanceof` pattern checks the type and names the object as `car` in one step, so the fields can be read directly.

Press **Run**. The output begins:

```text
first == second: false
first.equals(second): true
```

The `==` operator still compares identity, so the two objects are different. The `equals` method compares their values.

### hashCode

A hash-based collection, such as `HashSet`, finds an object by its hash code, and then checks `equals` among the objects with that code. For that to work, equal objects must give equal hash codes:

```java
@Override
public int hashCode() {
    return Objects.hash(brand, year);
}
```

If a class overrides `equals` and not `hashCode`, two equal cars can land in different places in a set, and the set can hold both. The program adds both cars to a `HashSet` and prints its size:

```text
Cars in the garage: 1
```

The set holds one car, because the two equal cars have the same hash code and `equals` reports them as the same. Rule to remember: when you override `equals`, override `hashCode` too, using the same fields.

### toString

`toString` gives the text shown when an object is printed or joined into a string:

```java
@Override
public String toString() {
    return brand + " (" + year + ")";
}
```

The last line of output uses it:

```text
Car: Ford (1969)
```

Without `toString`, Java prints the class name and an address-like code, which doesn't help a reader. Write it for any class you print in a report, a log, or a test failure message.

### Records already do this

A record generates `equals`, `hashCode`, and `toString` from its components, which is why the records chapter could compare points by value without extra code. A class that needs custom rules, such as comparing only some fields, writes the three methods itself.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does `first == second` print false when both cars have the same brand and year?
○ Because the two cars have different names.
→ Not quite. The cars have no names, and == doesn't look at their fields.
◉ Because == compares whether two references refer to the same object, and these are two objects.
→ Yes. Equal values in two objects are compared with equals.
○ Because toString is not overridden.
→ Not quite. toString has nothing to do with ==.
````

````quiz
What must be true of two objects that `equals` reports as equal?
○ They must be the same object.
→ Not quite. Equal objects are usually different objects with equal values.
◉ They must produce the same hash code, so a hash-based collection can find them.
→ Yes. Overriding equals without hashCode breaks that rule.
○ They must print the same text.
→ Not quite. Printing is toString's job, and it isn't part of equality.
````

---

### Try it in the runner

Remove the `hashCode` override, and predict the size of `garage` before you press **Run**. Two equal cars may land in different buckets, so the set can report 2. The exact result depends on how the hash codes are computed, but the rule is broken, and the set may hold both. Restore the method before you continue.

Then change `toString` to return only the brand, and predict the last line. It prints `Car: Ford`.

---

Overriding `equals`, `hashCode`, and `toString` together gives a class value semantics, which the collections and the printed output both depend on. The next steps cover enums and ordering, and then access control.
