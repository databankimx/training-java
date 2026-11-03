## Creating records

A record's constructor sets its components from the values it's given. Often that's all it needs to do. Sometimes a value must be checked first, for example a temperature can't be below absolute zero, and this step shows how to check a value when a record is created.

Here's the program for this step, in `src/Main.java`. The first record checks its value:

```java
record Temperature(double celsius) {
    Temperature {
        if (celsius < -273.15) {
            throw new IllegalArgumentException("below absolute zero: " + celsius);
        }
    }
}
```

The block without parentheses after the record name is a compact constructor. It runs before the components are set, so the check happens on every new `Temperature`, and the parts are assigned after the block finishes. You don't assign `celsius` yourself, because the record does that once the check passes.

Press **Run**. The output is:

```text
room: Temperature[celsius=21.5]
a == b: false
a.equals(b): true
Rejected: below absolute zero: -300.0
```

The first line is the room temperature, which passes the check. The last line shows the check at work: the constructor throws for `-300.0`, the `catch` prints the message, and the value is never created.

### Equality by value

The program creates two points with the same values, and compares them two ways. The `==` comparison gives `false`, because the two are separate objects. The `equals` method gives `true`, because the records hold the same values.

A record's generated `equals` compares its components, so two records with the same values are equal. This is the behavior you want for a value type, and it's different from a class, which compares references unless you write `equals` yourself.

### When to use the check

Put a check in the compact constructor when some values make no sense at all, and the record should never exist with them. A temperature below absolute zero, a negative count, or an empty name are all examples. Checks in the constructor mean every record in the program is valid, so the rest of the code doesn't have to check again.

---

### Quiz

*These questions are written for this course.*

````quiz
Why is the check written in a compact constructor, rather than in each place a Temperature is created?
○ Because compact constructors run faster.
→ Not quite. The reason is correctness, not speed.
◉ So that every Temperature in the program is valid, with no place to skip the check.
→ Yes. The check happens whenever a record is created, so no invalid value can exist.
○ Because records can't be created without it.
→ Not quite. A record without a check is perfectly valid.
````

````quiz
For `new Point(3, 4)` and `new Point(3, 4)`, what does `a.equals(b)` give?
○ false, because they are separate objects.
→ Not quite. A record's equals compares its values, not whether the objects are the same.
◉ true, because both records hold the same values.
→ Yes. The generated equals compares every component.
○ A compile error, because records can't be compared.
→ Not quite. Records can be compared with equals.
````

---

### Try it in the runner

Change `new Temperature(-300.0)` to `new Temperature(-10.0)`, and predict the output. The value passes the check, so the record is created, and the line `never printed Temperature[celsius=-10.0]` appears. Restore `-300.0` before you continue.

Then change `new Point(3, 4)` for `b` to `new Point(4, 3)`, and predict the value of `a.equals(b)`. It's `false`, because the components differ, even though both points are made from 3 and 4. Restore the original values before you continue.

---

A compact constructor checks a value once, when the record is created, and `equals` compares records by their values. The summary gathers the chapter together, and the homework uses records in a small program.
