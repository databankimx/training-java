## Homework: Area functions: walkthrough

`rectangleArea` multiplies the two parameters. Because the parameters are `double` values, the product is a `double`, and no integer division can drop a fraction.

`circleArea` uses the formula pi times the radius squared. Writing `radius * radius` is simpler than `Math.pow`, and it gives the same result for a square.

The output uses `printf` with `%.2f`, which rounds 12.56636 to 12.57. The rectangle prints 12.00, because the product is exact, and `%.2f` shows the two places.
