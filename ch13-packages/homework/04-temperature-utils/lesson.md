## Homework: Temperature utilities

Write a small utility class: two conversion methods, and a `Main` that uses them.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own conversions.

The program should:

* in `TemperatureConverter.java`, define two public static methods, `celsiusToFahrenheit` and `fahrenheitToCelsius`, each taking a `double` and returning a `double`, using `F = C * 9 / 5 + 32` and its reverse
* in `Main.java`, call them and print five conversions with two decimal places, in the form `100.00 C is 212.00 F`

The five lines are: 0 C, 100 C, and -40 C converted to Fahrenheit, and 212 F and 32 F converted to Celsius. Use `printf` with `%.2f` for each value.

### What to check

The output should be:

```text
0.00 C is 32.00 F
100.00 C is 212.00 F
-40.00 C is -40.00 F
212.00 F is 100.00 C
32.00 F is 0.00 C
```

The `-40` line is a useful check: the two scales meet there, so both values are the same. If a line is off, check which of the two formulas it used.

### Extensions

* Add a third method, `kelvinFromCelsius`, that adds 273.15. Check that the value for 0 C is 273.15.
* Remove the `TemperatureConverter.` prefix from one call in `Main`, and read the compiler's error. Explain why the class name is needed for a static method called from another class.

---

*This exercise is not from the book. It practices the ideas in Chapter 13.*
