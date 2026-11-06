## Walkthrough: The calculator bonus

The bonus adds three things to the core. Each one is a change to the parser, so the tokenizer also needs the new symbols.

### Exponents: the power rule

Exponents need their own rule, between `unary` and `primary`. The exponent is parsed as a `unary`, not as a `primary`. That's what makes powers group to the right, and allows a negative exponent:

```java
private double power() {
    double base = primary();
    if (peek().kind() == Kind.CARET) {
        next();
        double exponent = unary();
        return Math.pow(base, exponent);
    }
    return base;
}
```

`2^3^2` is parsed as `2^(3^2)`, because the exponent `unary` contains another power. A minus sign is handled by `unary` before `power` is called, so `-2^2` is `-(2^2)`.

### Square roots: a named function

When `primary` finds a name, it calls `function`. Only `sqrt` is known. Its argument is a bracketed expression, and a negative argument is an error reported at the column of the name:

```java
private double function(Token name) {
    if (!name.text().equals("sqrt")) {
        throw new CalcException("unknown function '" + name.text() + "' at column " + name.column());
    }
    // ...
    if (argument < 0) {
        throw new CalcException("sqrt of a negative number at column " + name.column());
    }
    return Math.sqrt(argument);
}
```

### Matching brackets

The tokenizer accepts `(`, `[`, and `{` as opening brackets, and `)`, `]`, and `}` as closing ones. The parser checks that the closing bracket matches the opening one. The check chooses the expected closing bracket from the opening one:

```java
String wanted = switch (open.text()) {
    case "[" -> "]";
    case "{" -> "}";
    default -> ")";
};
```

The same check serves the bracket groups and the argument of `sqrt`.

### Rounding

The printing rule from the core is unchanged. The value of `sqrt(2) * sqrt(2)` is 2.0000000000000004, which prints as `2` once it's rounded to 15 significant digits. The value isn't changed, only its printed form. Your comment at the top of `Main.java` should say that this is your choice.
