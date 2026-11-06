## Homework: The calculator core

Build a calculator that reads expressions such as `2 + 3 * (4 - 1)` and prints the result. This is the core. The bonus items come in the next step, and each one builds on this.

Write your own program in your project first. The reference program is in this step's source folder, and it stays hidden until you reveal it. The walkthrough is in the solution.

The calculator works in three stages, and it's worth keeping them in separate files:

1. **Tokenizer.** Turns the text into tokens: numbers, operators, and brackets. Each token records the column where it starts.
2. **Parser.** Reads the tokens and works out their grouping, following the rules of precedence. It also works out the value.
3. **Main.** Runs the read-eval-print loop (REPL), which keeps asking for expressions until the user types `quit`, and the `demo` and `selftest` modes.

### What the core must do

- **Numbers** with an optional decimal part, such as `3`, `2.5`, and `0.75`. Use `double` for values.
- **Operators** `+`, `-`, `*`, and `/`, with the usual precedence. `*` and `/` bind tighter than `+` and `-`, so `2 + 3 * 4` is 14.
- **Left-to-right** order for operators of equal precedence, so `10 - 4 - 3` is 3, and `8 / 4 / 2` is 1.
- **Parentheses** `( )` for grouping, which can be nested.
- **Unary minus**, so `-3 + 5` is 2, and `2 * -3` is -6.
- **Clear errors** for bad input, which name the column where the problem is.
- **A REPL.** Blank lines are ignored, `quit` exits, and each result is printed on its own line.
- **A `demo` mode** that runs a fixed list of expressions and prints each one with its result or error.
- **A `selftest` mode** that checks the examples and the error messages, prints `PASS` or `FAIL` for each, and exits with a non-zero code if any check fails.

### The grammar

Your parser should follow this grammar. Each line is one rule, and `*` means "zero or more":

```text
expr    := term (('+' | '-') term)*
term    := factor (('*' | '/') factor)*
factor  := '-' factor | number | '(' expr ')'
```

Each rule calls the rules below it, so precedence falls out of the structure.

### Error messages

Columns are counted from 1, starting at the first character of the input. The calculator prints an error as `error: ` followed by the message, and your messages must match these forms:

| Input | Message |
|---|---|
| `2 + $` | `unexpected character '$' at column 5` |
| `1 / 0` | `division by zero at column 3` |
| `(2 + 3` | `expected ')', found end of input at column 7` |
| `2 + 3)` | `expected end of input, found ')' at column 6` |
| `2 +` | `expected a number or '(', found end of input at column 4` |
| `1.2.3` | `invalid number '1.2.3' at column 1` |

The column for "end of input" is one past the last character. The column for a division by zero is the column of the `/` operator.

### Output

A result is printed with up to 15 significant digits. Whole numbers print without a decimal point, so `2 * 7` prints as `14`. That limit also hides small floating-point errors, such as `2.0000000000000004`, which prints as `2`.

### Examples to check against

| Input | Output |
|---|---|
| `2 + 3 * 4` | `14` |
| `(2 + 3) * 4` | `20` |
| `10 - 4 - 3` | `3` |
| `8 / 4 / 2` | `1` |
| `-3 + 5` | `2` |
| `2 * -3` | `-6` |
| `10 / 4` | `2.5` |
| `1.5 * 4` | `6` |

### What to submit

* A project that builds, and runs the REPL when it's started with no arguments.
* A `demo` mode and a `selftest` mode, as described above.

---

*This project is not from the book. It uses ideas from Chapters 1 to 34.*
