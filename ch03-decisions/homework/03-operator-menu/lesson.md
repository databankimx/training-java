## Homework: Operator menu

Write a calculator that asks for an operator and two numbers, and prints the result. This exercise uses a `switch`, and it also needs an `if` inside one of its cases.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* ask for an operator, with the prompt `Enter an operator (+, -, *, /): `, and read its first character as a `char`
* ask for two numbers, as `double` values, with the prompts `Enter the first number: ` and `Enter the second number: `
* use a `switch` on the operator to choose the calculation: `+`, `-`, `*`, or `/`
* print `Cannot divide by zero.` when the operator is `/` and the second number is 0, rather than dividing
* print `Unknown operator.` for any other character

Use `System.out.flush()` on each prompt, and a `break` at the end of every case.

### What to check

Try these inputs, and compare the output with the answers:

* `+`, `2`, `3` should print `5.0`
* `*`, `4`, `2.5` should print `10.0`
* `/`, `9`, `3` should print `3.0`
* `/`, `7`, `0` should print `Cannot divide by zero.`
* `%`, `1`, `2` should print `Unknown operator.`

The numbers are `double` values, so Java prints each result with a decimal point. The division by zero case matters most. Dividing a `double` by zero doesn't cause an error in Java, but it gives infinity, which isn't a useful answer for this program. That's why the check is written as an `if`.

### Extensions

* Add a `%` case that gives the remainder. Think about whether `double` values support `%`. Java allows `%` on `double` values, but the result may not mean what you expect for decimals.
* Print the result to two decimal places, using `printf` with `%.2f`. Check how whole-number answers, such as 5, now print.

---

*This exercise is not from the book. It practices the ideas in Chapter 3.*
