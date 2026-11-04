## Homework: Unit conversion

Put the conversion methods in a package called `units`, and call them from `Main`. The class `Converter` holds two public static methods, and it has a private constructor, since it only groups helper methods.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own conversions.

The program should:

* put `Converter` in the package `units`, in the folder `src/units`
* provide `celsiusToFahrenheit(double)`, which returns the Fahrenheit value, and `kilometersToMiles(double)`, which multiplies by 0.621371
* import `units.Converter` in `Main`
* print 100 degrees Celsius in Fahrenheit with one place after the point, and 10 kilometers in miles with two places

### Where to change the code

The formulas are in `Converter`, and `Main` only calls them. To add a conversion, add a public static method to `Converter`, and call it from `Main`.

### What to check

The output should be:

```text
100 C is 212.0 F
10 km is 6.21 miles
```

The Celsius conversion is 100 times 9, divided by 5, plus 32, which is 212. The kilometer conversion is 10 times 0.621371, which is 6.21371, shown as 6.21.

### Extensions

* Add a `milesToKilometers` method, and check that 6.21 miles converts back to about 10 kilometers.
* Try calling `new Converter()` from `Main`, and predict the compiler's message from the private constructor.

---

*This exercise is not from the book. It practices the ideas in Chapter 13.*
