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

Java lets you switch on an `int`, a `char`, or a `String`. You'll see the `String` form later in this lesson, in the arrow version.

### Several labels for one block

Labels with no code between them share the code that follows. The next part of the program uses a `char`:

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

Sometimes you do want one case to run into the next. When that's deliberate, say so with a comment, as the program does:

```java
int level = 1;
switch (level) {
    case 1:
        System.out.println("Level 1 tasks");
        // falls through
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

The comment tells the next reader that the missing `break` is intended. Java has no special marker for this, and javac doesn't warn about fall-through by default, so the comment is the only record of the choice. Use it rarely. Most switches should break at the end of every case.

### The arrow form

Java 14 added a shorter form, with an arrow in place of the colon. The arrow form has no fall-through, so there's no `break` to forget:

```java
String command = "start";
switch (command) {
    case "start" -> System.out.println("Starting");
    case "stop" -> System.out.println("Stopping");
    default -> System.out.println("Unknown command");
}
```

The output is:

```text
Starting
```

Each arrow runs only its own line, and then the switch ends. This version also works on a `String`, which the earlier forms can do as well. Text is the most common reason to choose a `switch` over an `if` chain, since it compares the whole string for equality.

When you write new code, the arrow form is usually the clearer choice. The classic form is worth reading, because most older Java code uses it.

### The switch expression

A `switch` can also produce a value, and the arrow form makes that easy. The program uses it to find a day's name:

```java
int dayNumber = 6;
String dayName = switch (dayNumber) {
    case 1 -> "Monday";
    case 2 -> "Tuesday";
    case 6, 7 -> "Weekend";
    default -> "Weekday";
};
System.out.println("Day " + dayNumber + " is " + dayName);
```

The output is:

```text
Day 6 is Weekend
```

The value after each arrow is the result of that branch. Labels can be listed together, as `6, 7` is here, and the `default` covers any value that isn't listed. The whole expression ends with a semicolon, because it's part of the assignment.

A switch expression must cover every possible value. An `int` has far too many values to list, so the `default` is required here. With an enum, which the later chapters cover, you can list every value and leave out the `default`, and the compiler checks that the list is complete.

---

### Quiz

*These questions are written for this course.*

````quiz
What happens if a case has no `break`, and the arrow form isn't used?
○ The switch ends, and the next case is skipped.
→ Not quite. That's what break does. Without it, the next case runs too.
◉ The code runs into the next case as well.
→ Yes. Execution continues into the next case unless you stop it.
○ The program won't compile.
→ Not quite. It compiles, and javac doesn't warn about it by default.
````

````quiz
Which of these can you use as the value of a classic `switch`?
○ double
→ Not quite. A switch doesn't accept floating-point values.
◉ int
→ Yes. Integers are a valid switch value.
○ boolean
→ Not quite. A switch can't use a boolean as its value.
````

````quiz
What does the arrow form of a switch avoid?
○ The default case.
→ Not quite. The arrow form still has a default, and it's often needed.
◉ Falling through from one case into the next.
→ Yes. Each arrow runs only its own line, so there's no break to forget.
○ Comparing with a String.
→ Not quite. The arrow form works on a String, just as the classic form does.
````

---

### Try it in the runner

Change `level` to `2`, and press **Run**. Predict how many lines the switch prints. Starting at case 2 skips the first label, so only `Level 2 tasks` prints.

Then remove the `break;` after `Good`, and predict whether `Keep going` also prints. It does, because without the `break`, the `'B'` case falls into the `default` label. Java won't warn you about it, which is the reason the arrow form is recommended for new code.

Last, change `dayNumber` to `4`, and predict the name. The switch expression has no matching case, so the `default` gives `Weekday`. Then remove the `default` line, and press **Run**. The build fails, because an `int` switch expression must cover every value.

---

A `switch` names the value once and lists each case beneath it. Reach for it when one value is compared with several constants, such as a menu choice or a set of codes.
