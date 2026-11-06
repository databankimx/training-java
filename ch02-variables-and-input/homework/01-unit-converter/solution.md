## Unit converter: walkthrough

The program reads the temperature as a `double`, so the calculation is done in decimals from the start:

```java
double celsius = input.nextDouble();
final double fahrenheit = celsius * 9 / 5 + 32;
System.out.printf("%.1f C is %.1f F%n", celsius, fahrenheit);
```

The formula is worked out left to right for `*` and `/`, and then `+ 32` is added. For 100 degrees, `100.0 * 9` is 900.0, divided by 5 is 180.0, plus 32 is 212.0.

The `final` keyword is there because the converted value is calculated once and never changes. If a later line tried to assign `fahrenheit` again, the build would fail.

The `%.1f` codes print each value with one place after the point, so `212.0` rather than `212`. That's why the expected outputs in the lesson show `.0`.
