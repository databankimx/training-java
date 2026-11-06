## Homework: Rectangle scale

Write a program that uses a record to describe a rectangle, and scales it with a method.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* define a record called `Rectangle`, with two `double` components, `width` and `height`, and extra constructors that default each one to 1.0 when it's left out
* define `area`, which takes a `Rectangle` and returns `width * height`
* define `scale`, which takes a `Rectangle` and a `double` factor, and returns a new rectangle with both components multiplied by the factor
* in `main`, create a rectangle with `new Rectangle(3.0, 2.0)`, print its area, scale it by 2.0, and print its area again
* print each area with a label, such as `Area: 6.00`, using two decimal places

### Why scale returns a new rectangle

A record can't be changed, so `scale` builds a new rectangle, and `main` stores it in the same variable. The rule is the one from the records chapter: a changed value is a new record.

### What to check

For the rectangle in the sample, the output should be:

```text
Area: 6.00
Area: 24.00
```

The second area is four times the first, since scaling both sides by 2 multiplies the area by 4. Check that `area` doesn't change the rectangle, and that `scale` returns a rectangle with both sides doubled.

### Extensions

* Create a second rectangle with only its width, `new Rectangle(4.0)`, and print its area. What is the height, and why?
* Make `scale` refuse a factor of zero or less, by throwing an `IllegalArgumentException` with a message. Decide whether a zero scale is an error, or a valid result that the caller should know about.

---

*This exercise is not from the book. It practices the ideas in Chapter 10, with the methods and their parameters from Chapter 7.*
