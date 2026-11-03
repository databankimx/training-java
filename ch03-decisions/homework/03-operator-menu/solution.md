## Operator menu: walkthrough

The program reads the operator as text, and takes its first character:

```java
char operator = input.next().charAt(0);
```

`input.next()` reads one word, and `charAt(0)` gives its first character. That lets a user type `+` and press Enter. If they type a whole word, only its first character is used.

The two numbers are read with `nextDouble`, so decimals work. The `switch` then chooses the calculation. Each arrow branch runs only its own line, so no `break` is needed, and nothing falls through.

The division case holds an `if`, inside a block with braces. The check `second == 0` happens before the division, so a zero divisor prints the message and never reaches `first / second`. Without that check, the result would be infinity, which the program doesn't want to print.

The output is always a `double`, because the numbers are `double` values. That's why `2 + 3` prints `5.0`.
