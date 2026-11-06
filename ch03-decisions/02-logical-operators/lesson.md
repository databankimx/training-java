## Logical operators and short-circuiting

Comparison operators answer one question. Logical operators combine questions, or reverse one. They also short-circuit: Java stops evaluating as soon as the result is already known.

Here's the start of this step's program, in `src/Main.java`:

```java
boolean x = true;
boolean y = false;

System.out.println("x && y: " + (x && y));
System.out.println("x || y: " + (x || y));
System.out.println("!x: " + !x);
```

Press **Run**. The output is:

```text
x && y: false
x || y: true
!x: false
```

`&&` means "and". The result needs both sides to be true.

`||` means "or". The result is true when at least one side is true.

`!` means "not". It reverses its operand, so true becomes false and false becomes true.

### Truth tables

The two-input operators are easier to predict when every combination is laid out. In each table, `t` means true and `f` means false for the inputs, and `T` and `F` show the result.

For `&&`, the result is true only when both inputs are true:

| p | && | q |
| - | - | - |
| t | T | t |
| t | F | f |
| f | F | t |
| f | F | f |

For `||`, the result is true when at least one input is true. A single true input is enough:

| p | \|\| | q |
| - | - | - |
| t | T | t |
| t | T | f |
| f | T | t |
| f | F | f |

Negation has one input. `!` reverses it:

| p | !p |
| - | - |
| t | F |
| f | T |

Read the tables as a checklist. When you're unsure what an expression gives, find the row for its inputs and read off the result.

### Combining comparisons

The operators become useful when they join comparisons, as the next lines of the program do:

```java
int age = 25;
boolean hasTicket = false;
System.out.println("Adult and has ticket: " + (age >= 18 && hasTicket));
System.out.println("Adult or has ticket: " + (age >= 18 || hasTicket));
```

The output is:

```text
Adult and has ticket: false
Adult or has ticket: true
```

`age >= 18` is true, and `hasTicket` is false. So `&&` is false, because the person hasn't got a ticket, and `||` is true, because being an adult is enough for an OR. Each side of an operator is a full expression, so the comparison doesn't need extra parentheses around it.

### Short-circuit evaluation

The next lines of the program show where the operators stop early:

```java
int count = 0;
boolean ok = (count != 0) && (10 / count > 1);
System.out.println("ok: " + ok);
```

Dividing an `int` by zero is an error in Java, and the program would stop with an `ArithmeticException` if the division ran. This line is safe because `&&` stops as soon as the answer is known. The left side, `count != 0`, is false, so `&&` can't be true, and the right side is never evaluated. The division never happens.

The same applies to `||` in the other direction. If the left side is true, the right side is skipped, because an OR is already true.

This is called short-circuit evaluation. It's the reason to put the cheap or safe check first.

The operators `&` and `|` also work on `boolean` values, but they don't short-circuit: both sides are always evaluated. Use `&&` and `||` unless you specifically need both sides to run.

### XOR

XOR means "exclusive or". The result is true when exactly one input is true, and false when both are true or both are false. In everyday terms, it's "one or the other, but not both". A light switch controlled from two places works this way.

Here's the table for XOR:

| p | XOR | q |
| - | - | - |
| t | F | t |
| t | T | f |
| f | T | t |
| f | F | f |

Java has a logical XOR operator, `^`, which works on `boolean` values. The program shows it next to two other ways of writing the same test:

```java
boolean p = true;
boolean q = false;

boolean myXor = (p || q) && !(p && q);
System.out.println("(p || q) && !(p && q): " + myXor);

myXor = p != q;
System.out.println("p != q: " + myXor);
System.out.println("p ^ q: " + (p ^ q));
```

The output is:

```text
(p || q) && !(p && q): true
p != q: true
p ^ q: true
```

The first form spells the logic out. The first half, `p || q`, needs at least one true input. The second half, `!(p && q)`, rules out the case where both are true. Together, they're true only when exactly one input is true. It's longer, but each part maps directly onto the definition.

The second form, `p != q`, is shorter. Two `boolean` values are different exactly when one is true and the other is false, which is XOR by definition.

The third form, `p ^ q`, says the same thing with the operator built for the purpose. It's the clearest of the three when the reader knows the operator, and every form gives the same result for every combination in the table.

### Precedence

When an expression has several operators, Java decides the order in which they run. That order is called precedence:

```java
true || false && false
```

Without any rules, you might read it left to right, giving `(true || false) && false`, which is false. Java reads it differently. `&&` binds more tightly than `||`, so the `&&` is worked out first, and the expression is `true || (false && false)`, which is true. The program prints that result:

```text
true || false && false: true
```

These are the precedence rules for the operators in this lesson, from the tightest binding to the loosest:

| Level | Operators | Notes |
| - | - | - |
| 1 | `!` | Applies to the value directly after it. |
| 2 | `<`, `<=`, `>`, `>=` | Comparisons. |
| 3 | `==`, `!=` | Equality comparisons. |
| 4 | `&` | Logical AND, without short-circuiting. |
| 5 | `^` | Logical XOR. |
| 6 | `\|` | Logical OR, without short-circuiting. |
| 7 | `&&` | Logical AND, with short-circuiting. |
| 8 | `\|\|` | Logical OR, with short-circuiting. |

Some results follow directly from the table. `!p && q` is `(!p) && q`, because `!` binds more tightly. `age >= 18 && hasTicket` is `(age >= 18) && hasTicket`, because the comparison binds more tightly than `&&`.

Java doesn't warn you when you mix `&&` and `||` without parentheses, so the rule is yours to follow. When you combine them, add parentheses. The parentheses cost nothing, and they tell the next reader what you meant, even though the rules would give the same answer.

---

### Quiz

*These questions are written for this course.*

````quiz
Using the truth table, when is `p || q` false?
○ When p is true.
→ Not quite. When p is true, the result of || is always true.
◉ When both p and q are false.
→ Yes. || needs at least one true input, so both being false gives false.
○ When both p and q are true.
→ Not quite. That gives true for ||.
````

````quiz
Which expression is true when `p` and `q` are both true?
○ p != q
→ Not quite. Two equal booleans are not different, so p != q is false here.
◉ !(p != q)
→ Yes. p != q is false when both are true, so its negation is true.
○ p && !q
→ Not quite. q is true, so !q is false, and the whole expression is false.
````

````quiz
What does `true || false && false` give in Java?
○ false, because it's read left to right.
→ Not quite. && binds more tightly than ||, so it isn't read left to right.
◉ true, because the && is worked out first.
→ Yes. The expression is true || (false && false), which is true.
○ A compile error, because mixing || and && is not allowed.
→ Not quite. Java allows it, and the precedence rules decide the result.
````

---

### Try it in the runner

Change `p` and `q` to both be `true`, and press **Run**. Predict all three XOR results before you do. They should all be false, because two equal values are not different, and exactly one input is not true.

Then change `(p || q) && !(p && q)` to `(p || q) && (p && q)`, and predict the result for the original values, `p` true and `q` false. The answer is false, because the second half is now `p && q`, which is false when `q` is false. It's a different rule, and the table shows why.

Last, change `x && y` to `x & y`, and make `y` equal to a call with a side effect, such as `y = (count++ > 5)`. With `&&`, the right side is skipped when `x` is false. With `&`, it always runs. Use this to see that short-circuiting changes what runs, not only the answer.

---

## More background

See the [Java Language Specification: Conditional-And and Conditional-Or operators](https://docs.oracle.com/javase/specs/jls/se25/html/jls-15.html#jls-15.23) for the precise rules, and the [Java tutorial on operators](https://docs.oracle.com/javase/tutorial/java/nutsandbits/operators.html).

---
