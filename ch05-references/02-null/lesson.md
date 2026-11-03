## Null

Sometimes a reference refers to nothing. Java has a special value for that, `null`, which means "no object here". A variable of an object type starts as `null` if you don't give it a value, and an unset element of an object array is `null` too, as the arrays chapter showed.

Here's the program for this step, in `src/Main.java`:

```java
String name = null;
System.out.println("name is null: " + (name == null));

if (name != null) {
    System.out.println("name length: " + name.length());
} else {
    System.out.println("No name yet");
}
```

Press **Run**. The output is:

```text
name is null: true
No name yet
```

The check `name == null` is a comparison, like the ones in the decisions chapter. It asks whether the reference refers to nothing, and the answer is `true` here.

### Checking before you use

Calling a method on a `null` reference throws a `NullPointerException`. The program avoids that by checking first: `name != null` is true only when there's an object to use, so `name.length()` runs only then. The `else` branch handles the case where there's nothing yet.

The rest of the program shows what happens after the name is set:

```java
name = "Ada";
System.out.println("name is null: " + (name == null));
System.out.println("name length: " + name.length());
```

The output is:

```text
name is null: false
name length: 3
```

Once `name` refers to a `String`, it's no longer null, so the length can be read.

### Why check

A `null` reference is one of the most common causes of errors in Java programs. The compiler can't always tell whether a reference will be `null` when the program runs, so the program has to check. The check costs one comparison, and it turns a crash into a clear branch that the reader can see.

Use `== null` and `!= null` for this check. Don't compare a reference with `equals`, because `null` has no methods to call.

---

### Quiz

*These questions are written for this course.*

````quiz
What is the value of `String name;` when it's declared but never given a value, and used as a field of a class?
○ An empty string, "".
→ Not quite. A reference that's never assigned is null, not an empty string.
◉ null
→ Yes. Object references start as null until they're given an object.
○ 0
→ Not quite. A String reference can't hold a number.
````

````quiz
Which check is safe before calling `name.length()`?
○ name.length() != null
→ Not quite. That calls length() before the check, and it crashes first.
◉ name != null
→ Yes. The check happens before the method is called, so the call only runs when there is an object.
○ name == ""
→ Not quite. A null name is not equal to "", and that comparison doesn't guard against null.
````

---

### Try it in the runner

Remove the `if` and `else` check, leaving only `System.out.println("name length: " + name.length());` after `name` is set to `null`. Predict the result. The program throws a `NullPointerException` at that line. Restore the check before you continue.

Then change `name = "Ada";` to `name = "";`, and predict the length. The empty string is not null, so `name != null` is true, and the length is 0. The check guards against null, not against empty text.

---

`null` is the value of a reference that refers to nothing. Check for it before you use the object, and the program can't crash on a missing value. The last step covers comparing references, where `==` and `equals` give different answers.
