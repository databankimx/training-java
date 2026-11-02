## Unit converter

Write a program that converts a temperature from Celsius to Fahrenheit.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* ask for a temperature in Celsius, with the prompt `Enter a temperature in Celsius: `
* read it as a `double`, so decimals work
* calculate the Fahrenheit value with the formula `F = C * 9 / 5 + 32`
* print the result as `<celsius> C is <fahrenheit> F`, with one place after the point

Store the Fahrenheit value in a `final` variable, since it's calculated once and never changes. Use `System.out.flush()` after the prompt, so the question shows before the program waits for an answer.

### Where to change the code

The prompt, the reading, and the printing are the parts you'll write yourself. The formula is the one line that does the work:

```java
final double fahrenheit = celsius * 9 / 5 + 32;
```

Check that the formula is written as shown, and that `celsius` is a `double`, not an `int`.

### What to check

Try these inputs, and compare your output with the answers:

* `100` should give `100.0 C is 212.0 F`
* `0` should give `0.0 C is 32.0 F`
* `-40` should give `-40.0 C is -40.0 F`

Watch out for integer division. If both numbers in a division are whole numbers, Java drops the fraction. Because `celsius` is a `double`, the multiplication by 9 is a decimal, and the division by 5 is too. If you change `celsius` to an `int`, the answers will be wrong, which is a good way to see why the type matters.

### Extensions

* Add a second conversion, from Fahrenheit back to Celsius, and ask the user which one they want.
* Print the result to two decimal places, using the `%.2f` form from the arithmetic lesson.

---

*This exercise is not from the book. It practices the ideas in Chapter 2.*
