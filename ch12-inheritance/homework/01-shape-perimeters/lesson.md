## Homework: Shape perimeters

Add a perimeter to the shape hierarchy. `Shape` declares `perimeter` as an abstract method, and each concrete shape supplies its own formula. Print the perimeter of a rectangle and a circle through the parent type.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own shapes.

The program should:

* declare `abstract class Shape` with an abstract method `double perimeter()`
* declare `Rectangle` and `Circle` as subclasses, each with private fields and a constructor
* implement `perimeter` in each: twice the sum of the sides for a rectangle, and twice pi times the radius for a circle
* store a 3 by 4 rectangle and a circle of radius 1 in an array of `Shape`, and print each shape's class name and perimeter with two places after the point

### Where to change the code

The formulas are the two lines in the `perimeter` methods. To add a shape, subclass `Shape` and implement `perimeter`, and the loop in `main` will print it without any change.

### What to check

The output should be:

```text
Rectangle perimeter: 14.00
Circle perimeter: 6.28
```

The rectangle's perimeter is 2 times 7, which is 14. The circle's is 2 times pi, which is about 6.283, shown as 6.28.

### Extensions

* Add a `Square` subclass that takes one side, and check its perimeter for a side of 2, which should be 8.
* Try to create a `new Shape()` in `main`, and predict the compiler's message before you check it.

---

*This exercise is not from the book. It practices the ideas in Chapter 12.*
