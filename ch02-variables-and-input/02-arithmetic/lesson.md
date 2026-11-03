## Arithmetic

Most programs spend a lot of their time doing sums, so Java gives you the usual arithmetic operators. Here they are, with the examples from this step's program. The table shows `a` as 7 and `b` as 2:

| Operator | Meaning | Example | Result |
|---|---|---|---|
| `+` | Addition | `a + b` | 9 |
| `-` | Subtraction | `a - b` | 5 |
| `*` | Multiplication | `a * b` | 14 |
| `/` | Division (whole numbers when both sides are `int`) | `a / b` | 3 |
| `%` | Remainder after integer division (integers only) | `a % b` | 1 |

They work the way you'd expect from school, with one important exception that catches almost everyone at least once. This lesson covers the operators, that exception, and how to print decimal numbers neatly.

Here's the start of this step's program, in `src/Main.java`:

```java
int a = 7;
int b = 2;

System.out.println("a + b = " + (a + b));
System.out.println("a - b = " + (a - b));
System.out.println("a * b = " + (a * b));
System.out.println("a / b = " + (a / b));
System.out.println("a % b = " + (a % b));
```

Press **Run**. The first lines of output are:

```text
a + b = 9
a - b = 5
a * b = 14
a / b = 3
a % b = 1
```

Five of the operators are here. `+`, `-`, and `*` do what you'd expect. `/` is division, and `%` is the remainder operator, which gives what's left over after dividing. Seven divided by two is three with one left over, so `a % b` is 1.

Notice the parentheses around each calculation. The `+` is also used to join text in Java, so `"a + b = " + a + b` would join the pieces in order, and print `a + b = 72`. The parentheses make the addition happen first.

### The division trap

Look at `a / b` again. Seven divided by two is three and a half, but the output says 3. That's not a mistake in the compiler. When both numbers are `int`, Java does whole-number division, and it throws away the fraction. The answer is always rounded down towards zero.

That matters more than it first appears. The unit converter in the homework divides by 5, and if you write it as `9 / 5` with whole numbers, every answer comes out wrong. The fix is to make at least one of the numbers a decimal.

The next lines of the program show that:

```java
double c = 7.0;
System.out.println("c / b = " + (c / b));
System.out.println("a / 2.0 = " + (a / 2.0));
```

The output is:

```text
c / b = 3.5
a / 2.0 = 3.5
```

When one side of an operator is a `double`, the whole calculation is done in decimals. `2.0` is a decimal literal, and `a` is converted to a decimal before the division. Either way, you get 3.5.

### Converting on purpose

Sometimes both values are `int`, and you still want a decimal answer. Then you convert one of them explicitly:

```java
double average = (double) a / b;
```

`(double) a` turns `a` into a decimal for this calculation, and the division happens in decimals. The variable `a` itself stays an `int`. The conversion lasts only for the expression. The parentheses around the type are called a cast. Java uses casts for every conversion that might lose information, and the cast tells the reader exactly what you intended.

### Order of operations

When a calculation has more than one operator, Java doesn't simply work left to right. It follows precedence rules, which decide which operation happens first. They match the rules you learned at school:

| Order | Operators | Rule |
|---|---|---|
| First | `( )` | Parentheses are worked out first, so you can use them to force the order you want. |
| Second | `*`, `/`, `%` | Multiplication, division, and remainder, from left to right. |
| Third | `+`, `-` | Addition and subtraction, from left to right. |

The program prints three lines that show all three rules:

```text
2 + 3 * 4 = 14
(2 + 3) * 4 = 20
10 - 4 - 3 = 3
```

`2 + 3 * 4` is 14, not 20. The multiplication happens first, so it's `2 + 12`. Parentheses change that: `(2 + 3) * 4` adds first, so it's `5 * 4`, which is 20.

The last line shows the left-to-right rule. `10 - 4 - 3` is worked out as `(10 - 4) - 3`, which is 3. Operators at the same level are done in the order they're written, from left to right.

When you're not sure, use parentheses. They cost nothing, and they make the order obvious to the next reader.

### Combined operators

Combined operators are worth knowing, because you'll use them constantly. Each one does an arithmetic operation and stores the result back in the same variable:

| Operator | Meaning | Example | Same as |
|---|---|---|---|
| `++` | Add one | `count++` | `count = count + 1` |
| `--` | Subtract one | `count--` | `count = count - 1` |
| `+=` | Add | `total += 5` | `total = total + 5` |
| `-=` | Subtract | `total -= 3` | `total = total - 3` |
| `*=` | Multiply | `total *= 2` | `total = total * 2` |
| `/=` | Divide | `total /= 4` | `total = total / 4` |
| `%=` | Remainder | `total %= 4` | `total = total % 4` |

> **Before or after?** `++` and `--` can go in front of a variable or after it, and the two positions behave differently when the value is used inside a larger expression.
>
> In `count++`, the variable is increased after its current value has been used. In `++count`, the variable is increased first, and the new value is used.
>
> ```java
> int x = 5;
> int a = x++;   // a is 5, then x becomes 6
> int b = ++x;   // x becomes 7, then b is 7
> ```
>
> When `++` or `--` is a statement on its own, as in every example in this lesson, the two forms do exactly the same thing. The difference only shows up when the result is used. Keep `++` and `--` out of longer expressions until you're comfortable with the difference.

The program uses several of these on the same variable, starting from 10. Each line changes `total` a little more:

```java
int total = 10;
total -= 3;   // 7
total *= 2;   // 14
total /= 4;   // 3, because int division drops the fraction
total %= 4;   // 3
```

The `total /= 4` step is the division trap from earlier in this lesson. Because `total` is an `int`, the result is 3, not 3.5, and the rest of the sequence works from that 3.

The right-hand side is always worked out first. So `total *= 2 + 3` means `total = total * (2 + 3)`, which multiplies by 5, not by 2 and then adds 3.

The program also uses `count++` and `count += 5` on a variable that starts at 0, and `count` ends at 6.

### Printing decimals neatly

A decimal can print with far more digits than you want. The program prints the average with two places after the point, using `printf`:

```java
double average = (double) a / b;
System.out.printf("average = %.2f%n", average);
```

The output is `average = 3.50`. The `%.2f` part means "a decimal with two places after the point", and `%n` ends the line. `printf` is a formatted print, and it takes the same kind of codes as the C family of languages, so you'll meet it in many programs.

Each value in the list of arguments fills one of the codes, in order. `%d` is for whole numbers, and `%.2f` is for decimals.

### Math methods

The standard library has ready-made methods for the calculations you'd otherwise work out by hand. They all belong to the `Math` class, which is in `java.lang`, so no import is needed. The last lines of the program use some of them:

| Method | Returns | Example | Result |
|---|---|---|---|
| `Math.max(a, b)` | The larger of two values | `Math.max(7, 2)` | 7 |
| `Math.min(a, b)` | The smaller of two values | `Math.min(7, 2)` | 2 |
| `Math.sqrt(x)` | The square root of `x` | `Math.sqrt(16)` | 4.0 |
| `Math.pow(x, y)` | `x` raised to the power `y` | `Math.pow(2, 10)` | 1024.0 |
| `Math.round(x)` | `x` rounded to the nearest whole number | `Math.round(2.6)` | 3 |
| `Math.floor(x)` | `x` rounded down | `Math.floor(2.6)` | 2.0 |
| `Math.ceil(x)` | `x` rounded up | `Math.ceil(2.1)` | 3.0 |

The output of the program shows these lines:

```text
max(a, b) = 7
min(a, b) = 2
sqrt(16) = 4.00
round(2.6) = 3
pow(2, 10) = 1024.00
```

Each method takes values and returns a result, which you can print or store, like any other expression. You'll write your own methods later, in the methods chapter, and they're called in exactly the same way.

`Math.round` returns a `long`, a whole number type with a wider range than `int`, which is why it prints as `3` rather than `3.0`. `Math.floor` and `Math.ceil` return `double` values, so they print with a decimal point. If you need an `int` from a `double`, use a cast: `(int) Math.floor(2.6)`.

### A note on decimals

A `double` can't store every decimal exactly. Some values, such as 0.1, are stored as the closest binary fraction, which is very close but not exact. That's why a calculation that should give 0.3 may print as something like 0.30000000000000004 if you ask for enough digits. For everyday output, `printf` with a fixed number of places hides the difference. For money or other exact values, you'd use a different approach, which a later chapter covers.

---

### Quiz

*These questions are written for this course.*

````quiz
What is the value of `7 / 2` when both numbers are `int`?
○ 3.5
→ Not quite. With two whole numbers, Java divides in whole numbers and drops the fraction.
◉ 3
→ Yes. The fraction is dropped, so the answer is rounded down to 3.
○ 4
→ Not quite. Integer division rounds towards zero, so it doesn't round up here.
````

````quiz
Which expression gives a decimal answer of 3.5 for `a` equal to 7?
○ a / 2
→ Not quite. Both values are whole numbers, so the fraction is lost.
◉ a / 2.0
→ Yes. The 2.0 is a decimal, so the division is done in decimals.
○ a % 2
→ Not quite. % gives the remainder, which is 1 here.
````

````quiz
What is the value of `2 + 3 * 4`?
○ 20
→ Not quite. Reading strictly left to right would give (2 + 3) * 4, which is 20. Multiplication comes first.
◉ 14
→ Yes. The multiplication happens first, giving 2 + 12.
○ 24
→ Not quite. That would mean multiplying the 2 as well, which the rules don't do.
````

````quiz
After `int x = 10;` and then `x /= 4;`, what is `x`?
○ 2.5
→ Not quite. x is an int, so it can't hold a fraction.
◉ 2
→ Yes. 10 divided by 4 is 2 with the fraction dropped.
○ 3
→ Not quite. Integer division drops the fraction rather than rounding, so 2.5 becomes 2, not 3.
````

---

### Try it in the runner

Change `b` to `4`, and press **Run**. Before you do, predict the values of `a / b` and `a % b`. Seven divided by four is one with three left over, so `a / b` should be 1 and `a % b` should be 3.

Then change `a / 2.0` to `a / 2`. The value drops from 3.5 to 3, because both sides are whole numbers again. Finally, change `10 - 4 - 3` to `10 - (4 - 3)`, and predict the answer. The parentheses change the order, so the answer is 9, not 3. Last, predict the value of `total` after `total *= 2 + 3`, starting from `total` at 1. The answer is 5, because the right-hand side is worked out first.

---

Arithmetic is where the numbers in your variables start to do something. The next step introduces strings, the other kind of value you'll meet in almost every program.
