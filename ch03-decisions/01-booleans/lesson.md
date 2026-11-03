## Booleans and comparisons

Every program makes decisions. It checks whether a value is big enough, whether a name was entered, or whether two things match, and then it chooses what to do next. The type for those decisions is `boolean`, which holds one of two values: `true` or `false`.

Here's the start of this step's program, in `src/Main.java`:

```java
boolean isCodingFun = true;
boolean isFishTasty = false;

System.out.println("isCodingFun: " + isCodingFun);
System.out.println("isFishTasty: " + isFishTasty);
```

Press **Run**. The output is:

```text
isCodingFun: true
isFishTasty: false
```

Java prints a `boolean` as the word `true` or `false`. There's no setting to change that, and no need for one. In C++ the same values print as `1` and `0` by default, which is why C++ has a `boolalpha` setting. You won't meet that in Java.

Notice that the names are written in camel case, such as `isCodingFun`, the usual style in Java. The keyword `boolean` is spelled out in full, unlike the `bool` of C++.

### Comparisons

A comparison asks a question about two values, and the answer is a `boolean`. The operators are:

| Operator | Meaning | Example | Result when `x` is 10 and `y` is 9 |
|---|---|---|---|
| `==` | Equal to | `x == 10` | `true` |
| `!=` | Not equal to | `x != y` | `true` |
| `>` | Greater than | `x > y` | `true` |
| `<` | Less than | `x < y` | `false` |
| `>=` | Greater than or equal to | `x >= 10` | `true` |
| `<=` | Less than or equal to | `y <= 8` | `false` |

The rest of the program prints these results, in the same order as the table:

```text
x == 10: true
x != y: true
x > y: true
x < y: false
x >= 10: true
y <= 8: false
```

The parentheses around each comparison are needed in the program. Without them, `"x == 10: " + x == 10` would be read as a comparison between the text and 10, and that doesn't compile. The parentheses make the comparison happen first, and then join its `true` or `false` to the text.

Notice that equality is `==`, with two equals signs, and not `=`. A single `=` is assignment, which you've been using since the variables lesson.

### The assignment mistake

Writing `if (x = 10)` in place of `if (x == 10)` is one of the most common mistakes in C-family languages. Java catches it for you. The condition of an `if` must be a `boolean`, and `x = 10` is an `int`, so the compiler refuses the code and reports `incompatible types: int cannot be converted to boolean`.

That protection is one of the reasons Java is strict about types. It turns a silent bug into a compile error you see straight away.

### Comparing types

You can compare `int` and `double` values with each other. The `int` is converted to a `double` for the comparison, so `5 == 5.0` is `true`. That's the same promotion you saw in arithmetic.

Comparing text is a different matter. Two `String` values that look the same aren't always equal with `==`, because `==` compares whether they are the same object. The correct way to compare text is the `equals` method, which the strings lesson covers when we return to text later in the course.

---

### Quiz

*These questions are written for this course.*

````quiz
What does `System.out.println("isCodingFun: " + isCodingFun);` print when `isCodingFun` is true?
○ isCodingFun: 1
→ Not quite. Java prints boolean values as words, not numbers.
◉ isCodingFun: true
→ Yes. Java prints a boolean as true or false.
○ isCodingFun: bool
→ Not quite. The variable's value is printed, not its type.
````

````quiz
Which expression is true when `x` is 10?
○ x = 9
→ Not quite. That assigns 9 to x, and it doesn't ask a question at all.
◉ x == 10
→ Yes. == asks whether x is equal to 10.
○ x != 10
→ Not quite. That's true only when x is not 10.
````

---

### Try it in the runner

Change `y` to `10`, and press **Run**. Before you do, predict the values of `x == y` and `x < y`. Both `x` and `y` will be 10, so `x == y` becomes `true` and `x < y` becomes `false`.

Then add a line to the output that reads `System.out.println("x = 3: " + (x = 3));`, and press **Run**. The program still builds, because `x = 3` is a valid assignment that gives the value 3. It changes `x` to 3, so any line that uses `x` after it sees the new value. This is a valid Java expression, unlike `if (x = 10)`, which the compiler rejects. Remove the line before you continue.

---

Booleans are the raw material of every decision a program makes. The next step combines them, so one condition can depend on two others.
