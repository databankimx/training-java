## Summary: Decisions

This chapter covered how a program chooses what to do, based on the values it has.

* **A `boolean` holds true or false.** Java prints it as the word, with no setting needed.
* **Comparisons ask questions.** `==`, `!=`, `<`, `<=`, `>`, and `>=` each give a `boolean`.
* **`==` is a comparison, and `=` is assignment.** Java refuses `if (x = 10)`, because an assignment is an `int`, not a `boolean`.
* **Logical operators combine conditions.** `&&` needs both sides true, `||` needs one, and `!` reverses its value. `^` is XOR, true when exactly one side is true.
* **Short-circuiting skips work.** `&&` and `||` stop as soon as the answer is known, so a check placed first can protect the code after it.
* **Precedence sets the order.** `!` is first, then comparisons, then `&&`, then `||`. Add parentheses when you combine `&&` and `||`, to make the intent clear.
* **`if` runs a block when a condition is true.** The condition goes in parentheses, the code goes in braces, and the braces go on every block.
* **`else` and `else if` choose one path.** A chain runs only its first true block, so order the tests from most specific to least.
* **Separate `if` blocks run independently.** Use them when more than one of the conditions should apply.
* **The conditional operator chooses a value.** `condition ? first : second` gives one of two values.
* **`switch` compares one value with constants.** It works on `int`, `char`, and `String`.
* **Classic `switch` falls through unless each case ends with `break`.** Comment any deliberate fall-through, because javac doesn't warn about it.
* **The arrow form has no fall-through.** `case 1 ->` runs only its own line, and a switch expression produces a value.

### Check your understanding

*These questions are written for this course.*

````quiz
What is the result of `false && (10 / count > 1)` when `count` is 0?
○ An ArithmeticException, because the division is attempted.
→ Not quite. && stops at the false left side, so the division is never attempted.
◉ false, with the right side skipped.
→ Yes. The left side is already false, so && can't be true, and the right side isn't evaluated.
○ true, because the division by zero is ignored.
→ Not quite. The result of && can't be true when its left side is false.
````

````quiz
Which statement about `else if` chains is correct?
○ Every block whose condition is true runs.
→ Not quite. That's the behaviour of separate if blocks, not of a chain.
◉ Only the first block whose condition is true runs.
→ Yes. Once one block runs, the rest of the chain is skipped.
○ The blocks run in the order they are written, whatever their conditions.
→ Not quite. The conditions are tested in order, and only the true one's block runs.
````

````quiz
What does a switch expression such as `String name = switch (day) { case 1 -> "Mon"; default -> "Other"; };` do?
○ It prints the name, and gives no value.
→ Not quite. The expression produces a value, which the assignment stores.
◉ It produces a value, from the branch that matches, and stores it in name.
→ Yes. The arrow branch's value is the result of the switch.
○ It only works with int values.
→ Not quite. A switch expression also works with String values, as the arrow form shows.
````
