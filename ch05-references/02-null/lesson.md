## Null

A reference is another name for an object, and it's fixed to that object only while it refers to it. Java also has a value for a reference that refers to nothing, which is `null`. A reference can be `null`, can be given a different object at any time, and needs a check before it's used if it might be empty.

Here's the program for this step, in `src/Main.java`:

```java
int[] food = {1, 2, 3};
int[] ref = food;
int[] empty = null;

System.out.println("food[0]: " + food[0]);
System.out.println("ref[0]: " + ref[0]);

if (empty == null) {
    System.out.println("The reference is null.");
}
```

Press **Run**. The output is:

```text
food[0]: 1
ref[0]: 1
The reference is null.
```

`ref` refers to the same array as `food`, so its element reads the same value. `empty` is set to `null`, which means it refers to no object. The `if` checks for that case with `==`, the same comparison as in the decisions chapter.

### Using a reference that might be null

Using a `null` reference, such as reading `empty[0]`, throws a `NullPointerException`, and the program stops. The `if` check is what prevents that. Before you use a reference that might be empty, check it, as this `if` does.

The program then sets `empty` to a real array:

```java
empty = food;
System.out.println("empty[0] after it is set: " + empty[0]);
```

The output is `empty[0] after it is set: 1`. The reference was reassigned to `food`'s array, and it now refers to that object. Reassigning a reference changes which object it refers to, and it doesn't change the object.

### Null or reference

A reference that is `null` has no object, and a reference that is set always refers to one. Use a plain reference when the thing it refers to always exists. Use a reference that may be `null` when the object might be missing or might be chosen later, and check it before use.

---

### Quiz

*These questions are written for this course.*

````quiz
What does `empty` hold after `int[] empty = null;`?
○ An empty array with no elements.
→ Not quite. An empty array has a length of zero, but null is no array at all.
◉ A reference that refers to no object.
→ Yes. null means the reference has no object, and it must be checked before use.
○ The number 0.
→ Not quite. A reference can't hold a number, and null is not 0.
````

````quiz
Which check is correct before reading `empty[0]`?
○ if (empty[0] == null)
→ Not quite. That reads the element first, which is the failure the check is meant to prevent.
◉ if (empty != null)
→ Yes. The check happens before the element is read.
○ if (empty == 0)
→ Not quite. A reference is compared with null, not with 0.
````

---

### Try it in the runner

Remove the `if` check around `empty`, and add a line that reads `empty[0]` straight after `empty` is set to `null`. Predict what happens before you press **Run**. The program throws a `NullPointerException`, and the output stops at that line. Remove the line before you continue.

Then move the line `empty = food;` above the `if`, and predict whether the `if` still prints `The reference is null.`. It doesn't, because `empty` no longer refers to null before the check, so the check is false.

---

A reference can be null, and reassigning it changes what it refers to. The next step puts references to arrays together with the elements inside them, where the connection to the functions chapter becomes clear.
