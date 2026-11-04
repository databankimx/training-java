## Homework: Area functions

Write two methods that calculate areas, and a `main` that uses them.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* define `rectangleArea`, which takes a width and a height as `double` parameters, and returns their product
* define `circleArea`, which takes a radius as a `double` parameter, and returns the area, using 3.14159 for pi
* in `main`, ask for a width, a height, and a radius, each with its own prompt, and read them as `double` values
* print the rectangle's area and the circle's area, each with a label, using two decimal places

Use `System.out.flush()` on each prompt. Java doesn't need a declaration before a call, so the methods can be placed below `main` as well as above it.

### What to check

Try these inputs, and compare the output with the answers:

* width `3`, height `4`, radius `2` should print a rectangle area of `12.00` and a circle area of `12.57`
* width `2.5`, height `2`, radius `1` should print `5.00` and `3.14`
* width `0`, height `10`, radius `0` should print `0.00` for both

The last case checks that your methods work with zero, which is a valid input for area.

### Extensions

* Add a third method, `triangleArea`, that takes a base and a height, and returns half their product. Call it from `main` as well.
* Check which parts of the program would change if the circle used `Math.PI` from the `Math` class. You don't need to make the change, but think about why a named constant is easier to read than a number.

---

*This exercise is not from the book. It practices the ideas in Chapter 7.*
