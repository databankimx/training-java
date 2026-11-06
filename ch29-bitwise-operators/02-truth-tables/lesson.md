## Truth tables

The logical operators from the conditions chapter work on true and false. The bitwise operators work on 0 and 1, and they apply the same rules to each bit position. A truth table lists the result for every combination of inputs, which makes each operator's behaviour easy to check.

### Complement

The complement flips a bit. `~` does it to every bit of a value, and on a single bit the table is:

| b | ~b |
|---|---|
| 0 | 1 |
| 1 | 0 |

The program's first table uses `~b & 1`. The `& 1` keeps only the lowest bit, so the result is 1 or 0 for a single bit. Without it, `~0` is a whole int of ones, which is -1.

### AND, OR, and XOR

With two bits, each operator has its own table:

| b1 | b2 | AND | OR | XOR |
|---|---|---|---|---|
| 0 | 0 | 0 | 0 | 0 |
| 0 | 1 | 0 | 1 | 1 |
| 1 | 0 | 0 | 1 | 1 |
| 1 | 1 | 1 | 1 | 0 |

AND gives 1 only when both bits are 1. OR gives 1 when either bit is 1. XOR gives 1 when the bits differ. Press **Run** to see these tables from the program:

```text
NOT
 b  ~b
 0  1
 1  0

b1 b2  AND  OR  XOR
 0  0    0   0   0
 0  1    0   1   1
 1  0    0   1   1
 1  1    1   1   0
```

### Applying them to whole numbers

A table describes a single bit position. When the operators are applied to an `int`, each of its 32 bit positions follows the same table at the same time. So `12 & 10` doesn't need a separate rule for each bit: each pair of bits in the two numbers is combined by the AND table. The next steps apply these tables to real values.

---

### Quiz

*These questions are written for this course.*

````quiz
In the AND table, when is the result 1?
○ When either bit is 1.
→ Not quite. That's the OR table.
◉ Only when both bits are 1.
→ Yes. AND keeps a bit only where both inputs have it.
○ When the bits are different.
→ Not quite. That's the XOR table.
````

````quiz
What is the result of `1 ^ 1`?
○ 1
→ Not quite. The two bits are the same, and XOR gives 1 only when they differ.
◉ 0
→ Yes. Equal bits give 0 under XOR.
○ 2
→ Not quite. A single bit's operations give 0 or 1.
````

---

### Try it in the runner

Change the program to print the OR table for three bits, which has eight rows, and predict the number of 1s in the last column before you press **Run**. Seven of the eight rows give 1 under OR, since only the row where all three bits are 0 gives 0.

---

Each operator is a rule for one bit position, and the rule is applied to every position of a number at once. The next step applies AND to whole numbers, and uses it to check whether a number is even.
