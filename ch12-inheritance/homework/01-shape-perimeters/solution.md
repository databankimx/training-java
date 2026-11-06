## Shape perimeters: walkthrough

`Shape` has no body for `perimeter`, so it can't be called on a plain shape. The array holds a `Rectangle` and a `Circle`, and each one's own `perimeter` runs when the loop calls it through the `Shape` type. That's the polymorphism from the chapter.

The `printf` uses `shape.getClass().getSimpleName()` for the name, so each line names the real class. The formatting is the same for every shape, and only the formula is different.

The rectangle formula is `2 * (width + height)`, which adds the two sides, and doubles them, because a rectangle has two of each.
