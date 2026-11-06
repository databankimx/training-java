## Comparing references and values

The `==` operator compares references, not contents. For an object, `a == b` asks whether `a` and `b` refer to the same object, and not whether the objects look alike. That's one of the most common surprises in Java, and it matters most for text.

Here's the program for this step, in `src/Main.java`:

```java
String first = "Java";
String second = "Java";
String third = new String("Java");

System.out.println("first == second: " + (first == second));
System.out.println("first == third: " + (first == third));
System.out.println("first.equals(third): " + first.equals(third));
```

Press **Run**. The output is:

```text
first == second: true
first == third: false
first.equals(third): true
```

The first two variables both hold the text `"Java"`. Java stores each string literal once, so `first` and `second` refer to the same `String` object, and `==` gives `true`. The third variable is made with `new String("Java")`, which creates a separate object with the same text. `==` gives `false`, because the two references point to different objects.

`first.equals(third)` compares the text, and gives `true`. For text, and for most objects, `equals` is the comparison you want.

### The rule

Use `equals` to ask whether two objects hold the same value. Use `==` only to ask whether two references point to the same object, which is less often what you need.

The reason `==` looks right for `int` values is that an `int` variable holds the value itself, not a reference, so `==` compares values. Once a variable holds an object, such as a `String` or an array, `==` compares references.

### Arrays too

The last lines of the program compare two arrays with the same elements:

```java
int[] a = {1, 2};
int[] b = {1, 2};
System.out.println("a == b: " + (a == b));
```

The output is `a == b: false`. Both arrays hold 1 and 2, but they're two separate arrays, so their references differ. Arrays don't have an `equals` that compares contents. To compare the values, use `java.util.Arrays.equals`, which the homework lessons can introduce when they're needed.

### Strings in practice

`equals` is case sensitive, so `"Java".equals("java")` is `false`. Use `equalsIgnoreCase` when the case shouldn't matter.

When you compare a `String` that might be `null`, put the known text first: `"Java".equals(name)`. If `name` is `null`, that returns `false`. Calling `name.equals("Java")` would throw a `NullPointerException`, which is the problem the null lesson described.

---

### Quiz

*These questions are written for this course.*

````quiz
Which expression tests whether two `String` values hold the same text?
○ first == second
→ Not quite. == compares references, so two separate strings with the same text can still be unequal.
◉ first.equals(second)
→ Yes. equals compares the contents of the two strings.
○ first = second
→ Not quite. That's assignment, not a comparison.
````

````quiz
Why does `first == third` give `false` in the program?
○ Because "Java" and "Java" are different words.
→ Not quite. The text is the same, so it's the references that differ.
◉ Because `third` refers to a separate String object, created with `new`.
→ Yes. == compares references, and the two references point to different objects.
○ Because `==` never works on strings.
→ Not quite. == works on strings, but it compares references, not text.
````

---

### Try it in the runner

Change `third` to `String third = "Java";`, and predict the value of `first == third` before you press **Run**. It's `true`, because string literals with the same text are the same object. Then change `"Java"` in `third` to `"java"` and predict `first.equals(third)`. It's `false`, because equals is case sensitive. Restore the line before you continue.

Last, add `System.out.println("Null check: " + "Java".equals(null));`, and predict the output. It prints `false`, and it doesn't throw an exception, because `equals` with a `null` argument returns false. Remove the line before you continue.

---

Two references can hold the same object, or two objects can hold the same value. `equals` asks the second question, and `==` asks the first. The next step covers references in more depth, where one object can have several names.
