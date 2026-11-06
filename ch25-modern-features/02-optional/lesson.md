## Optional

Some results may be missing. A search can find nothing, a lookup can have no entry, and a value can be absent for a reason the program must handle. A method could return `null` for that, but then every caller must remember to check, and a forgotten check is a `NullPointerException` waiting to happen. `Optional` makes the possibility part of the method's return type, so the caller sees it in the signature.

Here's the method from `src/Main.java`:

```java
static Optional<Integer> firstEven(List<Integer> values) {
    for (int value : values) {
        if (value % 2 == 0) {
            return Optional.of(value);
        }
    }
    return Optional.empty();
}
```

The return type says that the method gives back an `Optional<Integer>`, which holds an `Integer` or nothing. `Optional.of` wraps a value, and `Optional.empty` says there is no value.

### Reading an Optional

The `orElse` method gives the value when there is one, and the fallback when there isn't:

```java
Optional<Integer> found = firstEven(List.of(3, 5, 8, 9));
System.out.println("First even: " + found.orElse(-1));

Optional<Integer> missing = firstEven(List.of(1, 3, 7));
System.out.println("First even: " + missing.orElse(-1));
```

Press **Run**. The output is:

```text
First even: 8
First even: -1
```

The first list has an even value, 8, so `orElse` gives that. The second has none, so the fallback -1 is printed.

### Acting on a value when it's present

`ifPresentOrElse` runs one action when there is a value, and another when there isn't:

```java
missing.ifPresentOrElse(
        value -> System.out.println("Found " + value),
        () -> System.out.println("No even value"));
```

The last line of output is `No even value`, since `missing` is empty. The two parts of the call are lambdas, from the lambdas chapter, one for each case.

### Choosing a fallback

A fallback should be a value the program can use, and it should not be confused with a real result. A value of -1 works when the caller knows that no real value is negative. When no such value exists, the program should check with `isPresent` or use `ifPresentOrElse`, rather than treat a placeholder as a real answer.

---

### Quiz

*These questions are written for this course.*

````quiz
What does `Optional.empty()` represent?
○ The value zero.
→ Not quite. Zero is a value, and empty means no value at all.
◉ A result that has no value, so the caller must handle its absence.
→ Yes. The return type tells the caller that a value may be missing.
○ An exception that the method throws.
→ Not quite. The method returns an Optional, and nothing is thrown.
````

````quiz
What does `missing.orElse(-1)` return when `missing` is empty?
○ null
→ Not quite. orElse gives the fallback value when the Optional is empty.
◉ -1
→ Yes. The value passed to orElse is used when there is nothing inside.
○ An exception
→ Not quite. orElse never throws when it is given a fallback.
````

---

### Try it in the runner

Change `firstEven(List.of(1, 3, 7))` to `firstEven(List.of(1, 4, 7))`, and predict the output of the second line before you press **Run**. It prints `First even: 4`, since 4 is the first even value in that list. Then change the fallback in `orElse` to `0`, and predict the output for a list with no even values. It prints `First even: 0`, which shows why a fallback should be a value the program can tell apart from a real result.

---

`Optional` puts the possibility of a missing value in the method's return type, where the caller sees it. The summary follows, and then two homework exercises that use `var`, patterns, and `Optional` together.
