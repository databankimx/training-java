## Switch statements

The `else if` chain from the last step is the right tool when each condition is different: a range of scores, or a test that combines two values. When every condition compares the same value with a fixed constant, a `switch` is clearer. It names the value once, and lists the cases beneath it.

Here's the start of this step's program, in `src/Main.java`. It begins with a day number:

```java
int day = 3;
switch (day) {
    case 1:
        System.out.println("Monday");
        break;
    case 2:
        System.out.println("Tuesday");
        break;
    case 3:
        System.out.println("Wednesday");
        break;
    default:
        System.out.println("Not a workday");
        break;
}
```

Press **Run**. The output is:

```text
Wednesday
```

The value in parentheses after `switch` is compared with each `case` label in turn. The code under the matching label runs, and the `default` label runs when nothing else matches. `default` plays the role of a final `else`, and it's optional, just as `else` is.

### break

Each case ends with `break`. Without it, the program carries on into the next case and runs that code too. This is called falling through, and it's the most common `switch` mistake. Leave out one `break`, and you'll see output from two cases.

The value in a classic `switch` can be an `int`, a `char`, or a `String`. Java accepts a `String` here, which makes a text menu much clearer than a chain of `if` tests. The arrow form, shown at the end of this step, is the modern way to write one.

### Several labels for one block

Labels with no code between them share the code that follows:

```java
char grade = 'B';
switch (grade) {
    case 'A':
        System.out.println("Excellent");
        break;
    case 'B':
    case 'C':
        System.out.println("Good");
        break;
    default:
        System.out.println("Keep going");
        break;
}
```

The output is `Good`. Both `'B'` and `'C'` reach the same line, so the two labels stack. This is the case where a `switch` is clearer than an `if` chain, since the shared code is written once.

### Falling through on purpose

Sometimes you do want one case to run into the next. The program shows that with a comment, so the reader can see the missing `break` is intended:

```java
int level = 1;
switch (level) {
    case 1:
        System.out.println("Level 1 tasks");
        // no break here: the code falls through on purpose
    case 2:
        System.out.println("Level 2 tasks");
        break;
}
```

The output is:

```text
Level 1 tasks
Level 2 tasks
```

Java has no keyword for a deliberate fall-through, and javac doesn't warn about one by default. A comment is the usual way to mark it, and it's the only way a reader can tell it from a forgotten `break`. Use it rarely. Most switches should break at the end of every case.

Braces around each `case` aren't needed, but any case that declares a variable needs its own braces, so the variable is confined to that case. Add them when you declare something.

### The arrow form

Java 14 added a form of `switch` that avoids fall-through entirely. Each case is written with `->`, and only that case's code runs. There's no `break` to forget:

```java
String season = "winter";
switch (season) {
    case "winter" -> System.out.println("Cold");
    case "summer" -> System.out.println("Warm");
    default -> System.out.println("In between");
}
```

The output is `Cold`. The arrow form is the one to reach for in new code, since its behavior is hard to get wrong. The `case` labels can be strings, as here, or enum values, which the error-codes lesson used.

---

### Quiz

*These questions are written for this course.*

````quiz
What happens if a case has no `break` and no comment saying it falls through?
○ The switch ends, and the next case is skipped.
→ Not quite. That's what break does. Without it, the next case runs too.
◉ The code runs into the next case as well, and nothing in the build flags it.
→ Yes. Execution continues into the next case unless you stop it.
○ The program won't compile.
→ Not quite. It compiles, and javac doesn't warn about a fall-through by default.
````

````quiz
Which of these can you use as the value of a classic `switch`?
○ A double, such as 2.5.
→ Not quite. A switch doesn't accept floating-point values.
◉ A String, such as "winter".
→ Yes. Java allows a String as the value of a switch.
○ A boolean.
→ Not quite. A switch can't test a boolean value.
````

---

### Try it in the runner

Change `level` to `2`, and press **Run**. Predict how many lines the last switch prints. Starting at case 2 skips the first label, so only `Level 2 tasks` prints.

Then remove the `break;` after `Good`, and predict whether `Keep going` also prints. It does, because without the `break`, the `'B'` case falls into the `default` label. javac doesn't warn about that, so the output is the only sign of the mistake.

Last, change `"winter"` in the arrow switch to `"autumn"`, and predict the output. It's `In between`, since no case matches and the default runs.

---

A `switch` names the value once and lists each case beneath it. Reach for it when one value is compared with several constants, such as a menu choice or a set of codes.
