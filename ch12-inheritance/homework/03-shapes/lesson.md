## Homework: Shapes

Write a small set of shape classes, and a method that prints any shape's area without knowing which shape it is.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* define a class `Shape` with a method `area`, which takes no parameters and returns 0.0
* define `Rectangle`, extending `Shape`, with `double` fields `width` and `height`, a constructor that takes both, and an `area` that overrides the base method
* define `Circle`, extending `Shape`, with a `double` field `radius`, a constructor that takes it, and an `area` that overrides the base method, using 3.14159 for pi
* define `printArea`, which takes a `Shape` and prints `Area: ` followed by the area with two decimal places
* in `main`, call `printArea` with a `Rectangle` of width 3 and height 2, a `Circle` of radius 1, and a plain `Shape`

Use `printf` with `%.2f` for the area.

### What to check

The output should be:

```text
Area: 6.00
Area: 3.14
Area: 0.00
```

The important part is that `printArea` is written once, for `Shape`, and works for all three. If the first two lines are both `0.00`, the `area` method in the derived classes isn't replacing the base method, so check the names and the parameter lists.

### Extensions

* Add a `Triangle` class with a base and a height, and check that `printArea` works for it without any change to `printArea`.
* Put `@Override` on each replacement, then misspell `area` in one of the derived classes, and read the compiler's message. Explain what the error prevents.

---

*This exercise is not from the book. It practices the ideas in Chapter 12.*
