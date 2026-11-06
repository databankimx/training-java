## Homework: The calculator bonus

This step adds three bonus items to the calculator core from the previous step. Each one builds on the core, so finish the core first. Try them in order, since each uses ideas from the one before.

Write your own program in your project first, with the bonus items added to your core. The reference program is in this step's source folder, and it stays hidden until you reveal it.

### Bonus 1: exponents with `^`

`2^3` is 8. Exponents bind tighter than `*` and `/`, so `2 * 3^2` is 18. They're right-associative, so `2^3^2` is `2^(3^2)`, which is 512, not `(2^3)^2`, which is 64. A minus sign applies after the power, so `-2^2` is -4. The right side of `^` can be a negative number, so `2^-1` is 0.5.

### Bonus 2: square roots with `sqrt(...)`

`sqrt(16)` is 4, and `sqrt(2) * sqrt(2)` prints as `2`. A square root of a negative number is an error, `sqrt of a negative number at column N`. Any other name followed by brackets is an error, `unknown function 'name' at column N`. A cube root can be written as `8^(1/3)`, which prints as `2`. Decide how you'll handle the rounding, and explain your choice in a comment.

### Bonus 3: square and curly brackets

`[ ]` and `{ }` group in the same way as `( )`. A closing bracket must match its opening one, so `(2 + 3]` is an error: `expected ')', found ']' at column 7`.

### Examples to check against

| Input | Output |
|---|---|
| `2^3` | `8` |
| `2 * 3^2` | `18` |
| `2^3^2` | `512` |
| `-2^2` | `-4` |
| `2^-1` | `0.5` |
| `sqrt(16)` | `4` |
| `sqrt(2) * sqrt(2)` | `2` |
| `8^(1/3)` | `2` |
| `[2 + 3] * 2` | `10` |
| `{2 + 3} * 2` | `10` |
| `(2 + 3]` | `error: expected ')', found ']' at column 7` |
| `sqrt(-4)` | `error: sqrt of a negative number at column 1` |
| `cube(2)` | `error: unknown function 'cube' at column 1` |

### What to submit

* Everything from the core step, with `demo` and `selftest` covering the bonus examples as well.
* A comment at the top of `Main.java` that lists which bonus items you completed.

---

*This project is not from the book. It uses ideas from Chapters 1 to 34.*
