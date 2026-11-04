## Summary: Decisions

This chapter covered how a program chooses what to do, based on the values it has.

* **`boolean` holds true or false.** It prints as the word `true` or `false`.
* **Comparisons ask questions.** `==`, `!=`, `<`, `>`, `<=`, and `>=` each give a boolean. Equality is `==`, and a single `=` assigns a value instead. An `if` condition must be a boolean, so `if (x = 10)` doesn't compile.
* **Logical operators combine booleans.** `&&` needs both sides to be true, `||` needs one, and `!` flips a value. XOR is true when exactly one side is true, and Java can express it with `^`, with `p != q`, or with `(p || q) && !(p && q)`.
* **Precedence sets the reading order.** `!` binds most tightly, then the comparisons, then `&&`, then `||`. Add parentheses when mixing `&&` and `||`, since the rules give the answer but the reader may not see it.
* **Short-circuit evaluation stops early.** `&&` and `||` skip the right side when the left side already decides the answer. Put the safe check first. `&` and `|` always evaluate both sides.
* **`if` runs a block on a condition.** `else` runs when the condition is false, and `else if` tests further conditions in order. The first true branch wins. The final `else` is optional.
* **Chain or separate `if` blocks.** A chain runs only one block, so use it when the conditions exclude each other. When several conditions can apply, and each should run, use separate `if` blocks.
* **The conditional operator chooses a value.** `condition ? a : b` gives `a` when the condition is true, and `b` otherwise.
* **`switch` picks a case by value.** Use it when one value is compared with several constants. Each case ends with `break`, unless a comment marks a deliberate fall-through. A classic `switch` works on integers, `char`, and `String`. The arrow form, with `->`, never falls through.
* **Always use braces.** They stop a later edit from putting a line outside the `if` by accident.

### Check your understanding

*These questions are written for this course.*

````quiz
What does a boolean print as when it's added to a string?
○ 1 or 0, as in older languages.
→ Not quite. Java prints a boolean as the word true or false.
◉ true or false.
→ Yes. The value is printed as the word, with no setting needed.
○ The variable's name.
→ Not quite. The value is printed, not the name.
````

````quiz
Which expression is true when `age` is 15 and `hasPermission` is true?
○ age >= 18 && hasPermission
→ Not quite. 15 is not 18 or over, so the left side is false.
◉ age < 18 || hasPermission
→ Yes. Either side being true is enough for ||, and hasPermission is true.
○ age == 15 && !hasPermission
→ Not quite. That needs hasPermission to be false, and it's true here.
````

````quiz
What is the value of `result` after `int result = (5 > 3) ? 10 : 20;`?
○ 20
→ Not quite. 20 is the value used when the condition is false, and 5 is greater than 3.
◉ 10
→ Yes. The condition is true, so the first value, 10, is used.
○ true
→ Not quite. The conditional operator gives one of its two values, not the condition itself.
````
