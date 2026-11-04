## Homework: Unit converter

Write a program that converts a temperature from Celsius to Fahrenheit.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* ask for a temperature in Celsius, with the prompt `Enter a temperature in Celsius: `
* read it as a `double`, so decimals work
* calculate the Fahrenheit value with the formula `F = C * 9 / 5 + 32`
* print the result as `<celsius> C is <fahrenheit> F`, with one decimal place for each value

Store the Fahrenheit value in a `final` variable, since it's calculated once and never changes. Use `System.out.flush()` on the prompt, so the question shows before the program waits for an answer.

### What to check

Try these inputs, and compare your output with the answers:

* `100` should give `100.0 C is 212.0 F`
* `0` should give `0.0 C is 32.0 F`
* `-40` should give `-40.0 C is -40.0 F`

Watch out for integer division. If you write `9 / 5` with two whole numbers, Java gives `1`, not `1.8`. The program avoids this because `celsius` is a `double`, so the calculation is done in decimals.

### Extensions

* Add a second conversion, from Fahrenheit back to Celsius, and ask the user which one they want.
* Print the result to two decimal places, using `printf` with `%.2f`, as the arithmetic lesson showed.

---

*This exercise is not from the book. It practices the ideas in Chapter 2.*
