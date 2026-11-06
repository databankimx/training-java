## Homework: Temperature utilities: walkthrough

Each method is a single formula. `celsiusToFahrenheit` multiplies by 9, divides by 5, and adds 32. `fahrenheitToCelsius` subtracts 32, multiplies by 5, and divides by 9. The brackets in the second formula make sure the subtraction happens first.

Both divisions use `double` values, so they keep their fractions. `printf` with `%.2f` shows each result to two places.
