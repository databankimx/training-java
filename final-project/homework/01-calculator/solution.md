## Walkthrough: The calculator core

The calculator works in three stages, each in its own file.

### The tokenizer

`Tokenizer.tokenize` reads the input one character at a time. Whitespace is skipped. A digit or a dot starts a number, and the run of digits and dots is read as one token. More than one dot is an error, so `1.2.3` reports `invalid number '1.2.3' at column 1`. Each token records the column where it starts. The end of the input is a token too, one column past the last character, which is how the "end of input" errors get their column.

### The parser

The parser has one method per rule in the grammar. The loop in `expr` takes the operators from left to right, so `10 - 4 - 3` is `(10 - 4) - 3`:

```java
private double expr() {
    double value = term();
    while (peek().kind() == Kind.PLUS || peek().kind() == Kind.MINUS) {
        Token op = next();
        double right = term();
        value = op.kind() == Kind.PLUS ? value + right : value - right;
    }
    return value;
}
```

`term` works the same way for `*` and `/`. It calls `factor`, which handles unary minus, numbers, and brackets. Because `term` calls `factor`, and `expr` calls `term`, multiplication is worked out before addition.

Division checks for zero before it divides, and reports the column of the `/` operator:

```java
if (right == 0) {
    throw new CalcException("division by zero at column " + op.column());
}
```

### Checking for the closing bracket

After an opening bracket, `factor` reads an expression, and then requires a closing bracket. If the next token isn't one, the message names what was found, or `end of input`.

### Printing the result

`format` prints a whole number with no decimal point. Any other number is rounded to 15 significant digits, which hides the small errors of floating-point arithmetic:

```java
static String format(double value) {
    if (value == Math.rint(value) && Math.abs(value) < 1e15) {
        return String.valueOf((long) value);
    }
    return new BigDecimal(value).round(new MathContext(15)).stripTrailingZeros().toPlainString();
}
```

### The modes

`main` runs the REPL with no arguments, `demo` with the word `demo`, `selftest` with the word `selftest`, and anything else is read as one expression. `selftest` compares each example and each error message with its expected text, and exits with 1 when a check fails.
