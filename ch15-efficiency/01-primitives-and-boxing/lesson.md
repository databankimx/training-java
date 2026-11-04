## Primitives and boxing

Java has two kinds of number. A primitive such as `int` or `long` holds the value directly, and it's stored compactly. An object such as `Integer` wraps a number in a box, and the box is a separate object with its own memory and its own reference. Collections hold objects, so a list of numbers holds boxes.

Here's the program for this step, in `src/Main.java`:

```java
long total = 0;
for (int i = 1; i <= 5; i++) {
    total += i;
}
System.out.println("primitive total: " + total);

List<Integer> numbers = new ArrayList<>();
for (int i = 1; i <= 5; i++) {
    numbers.add(i);
}
int boxedTotal = 0;
for (int value : numbers) {
    boxedTotal += value;
}
System.out.println("boxed total: " + boxedTotal);
```

Press **Run**. The output is:

```text
primitive total: 15
boxed total: 15
```

Both totals are 15, so the answer is the same either way. The difference is in the work the program does. The first loop adds primitives and never creates an object. The second creates an `Integer` for each number when it's added to the list, and converts each one back to an `int` when it's read.

### Boxing and unboxing

Java converts between the two automatically. Putting an `int` into a list is boxing, and reading an `Integer` into an `int` variable is unboxing. The compiler inserts those conversions for you, so the code reads the same as if the list held plain numbers.

That automatic conversion is convenient, and it has a cost. Each box takes memory, and a large list of numbers creates many objects. For a loop that adds up a million values, a primitive total is much cheaper than a list of boxes.

### When to choose which

Use primitives for arithmetic in a loop, and for values you hold in a single variable or an array of primitives. Use boxed collections when you need a list or a map, because collections only hold objects. Don't worry about boxing in a short program; worry about it in a loop that runs very many times.

One trap: comparing two `Integer` objects with `==` compares references, not values, and that's the references lesson again. Use `equals`, or unbox first, when you compare boxed numbers.

---

### Quiz

*These questions are written for this course.*

````quiz
What does storing an `int` in a `List<Integer>` involve?
○ Nothing; the list holds the int directly.
→ Not quite. A list holds objects, so the int is wrapped first.
◉ Boxing, which wraps the number in an Integer object.
→ Yes. The compiler adds the conversion, and the list holds the box.
○ Copying the list into a primitive array.
→ Not quite. Storing a value boxes it, and doesn't change the list's type.
````

````quiz
Why is a loop that adds primitives usually cheaper than one that adds boxed values?
○ Because primitives are always stored in the same place as the code.
→ Not quite. The reason is the object creation, not where the code lives.
◉ Because boxed values create and read back objects on every pass.
→ Yes. A primitive total never creates an object, so there is less work per pass.
○ Because boxed values are never added correctly.
→ Not quite. Both loops add correctly, and both print 15.
````

---

### Try it in the runner

Change `numbers.add(i);` in the second loop to `numbers.add(1000);`, so the list holds 1000 five times. Predict the boxed total. It's 5000. Restore the original line before you continue.

Then change `List<Integer>` to `List<int>`, and predict the result. The build fails, because a type argument must be an object type, and `int` is a primitive. Restore `Integer` before you continue.

---

A primitive holds a value directly, and a box holds it as an object. Primitives are cheaper in tight loops, and boxed collections are the price of holding numbers in a list. The next step measures how long a piece of code takes, since guessing is not enough.
