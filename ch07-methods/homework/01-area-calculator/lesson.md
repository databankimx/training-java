## Homework: Area calculator

Write two overloaded `area` methods: one for a circle, given its radius, and one for a rectangle, given its width and height. Then print the areas of a circle and a rectangle.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own shapes.

The program should:

* provide `area(double radius)`, which returns `Math.PI * radius * radius`
* provide `area(double width, double height)`, which returns `width * height`
* print the circle area for radius 2, and the rectangle area for 3 by 4, each with two places after the point

### Where to change the code

Each method is one line of arithmetic. The call decides which method runs: one argument picks the circle, and two arguments pick the rectangle. To add a shape with a different number of inputs, add a method with that many parameters, and the calls will choose it automatically.

### What to check

The output should be:

```text
Circle with radius 2: 12.57
Rectangle 3 by 4: 12.00
```

The circle's area is pi times 4, which is about 12.566, shown as 12.57. Both shapes happen to have an area near 12, but for different reasons.

### Extensions

* Add a `perimeter` method for a rectangle, and check it against a 3 by 4 rectangle, which has a perimeter of 14.
* Add an `area` method for a triangle, given its base and height, and check that it doesn't clash with the other two.

---

*This exercise is not from the book. It practices the ideas in Chapter 7.*
