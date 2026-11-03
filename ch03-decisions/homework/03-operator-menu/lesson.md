## Operator menu

Write a calculator that asks for an operator and two numbers, and prints the result. This exercise uses a `switch`, and it also needs an `if` inside one of its cases.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* ask for an operator, with the prompt `Enter an operator (+, -, *, /): `, and read its first character as a `char`
* ask for two numbers, as `double` values, with the prompts `Enter the first number: ` and `Enter the second number: `
* use a `switch` on the operator to choose the calculation: `+`, `-`, `*`, or `/`
* print `Cannot divide by zero.` when the operator is `/` and the second number is 0, rather than dividing
* print `Unknown operator.` for any other character

Use `System.out.flush()` on each prompt, and use the arrow form of `switch`, so no `break` is needed.

### Where to change the code

The `switch` has one case with a block in it, and that block holds the `if`. The division case is the one that checks for zero:

```java
case '/' -> {
    if (second == 0) {
        System.out.println("Cannot divide by zero.");
    } else {
        System.out.println(first / second);
    }
}
```

Each of the other cases is a single line, with the calculation in it. To add a new operator, add a new case line before the `default`.

### What to check

Try these inputs, and compare the output with the answers:

* `+`, `2`, `3` should print `5.0`
* `*`, `4`, `2.5` should print `10.0`
* `/`, `9`, `3` should print `3.0`
* `/`, `7`, `0` should print `Cannot divide by zero.`
* `%`, `1`, `2` should print `Unknown operator.`

Java prints every `double` with a decimal point, so the answers show `5.0` rather than `5`. That's the same behaviour you saw in the arithmetic lesson, and it isn't a mistake in the program.

The division by zero case matters most. A `double` divided by zero doesn't cause an error, but it gives infinity, which isn't a useful answer for this program. The `if` stops that before the division happens.

### Extensions

* Add a `%` case that gives the remainder. Java's `%` works on `double` values as well as integers, so check what a remainder of two decimals gives.
* Print the result to two decimal places, using `printf` with `%.2f`, as the arithmetic lesson showed. Check whether a whole-number answer now prints as `5.00`.

---

*This exercise is not from the book. It practices the ideas in Chapter 3.*
