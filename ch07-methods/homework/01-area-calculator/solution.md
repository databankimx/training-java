## Area calculator: walkthrough

The two `area` methods have the same name, and their parameter lists differ, so they're overloads. `area(2.0)` has one `double` argument, so it matches the circle version. `area(3.0, 4.0)` has two, so it matches the rectangle version.

The circle formula uses `Math.PI`, which is a constant in the `Math` class. Writing `radius * radius` is clearer than `Math.pow(radius, 2)` for a simple square, and it avoids the decimal result `pow` always gives.

The output is printed with `printf` and `%.2f`, which rounds the circle's 12.566... to 12.57.
