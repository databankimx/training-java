## Unit conversion: walkthrough

`Converter` is in the `units` package, so `Main` imports it, and the calls use the short name `Converter`. The methods are `public static`, so they're callable from the default package. The private constructor prevents `new Converter()`, which would make no sense for a class of methods with no state.

The Fahrenheit formula multiplies before it divides, because `celsius * 9 / 5` is worked out left to right. Writing `9 / 5` first would divide the integers 9 and 5 and lose the fraction, which is the division trap from the arithmetic chapter.
