## Homework: Shapes: walkthrough

`Shape` has an `area` method that returns 0.0. Each subclass replaces it with its own formula. `printArea` takes a `Shape`, and calls `area` on it, so the call runs the version that belongs to the object's real class.

The rectangle's area is width times height, which is 6 for the sample. The circle's area uses 3.14159 for pi, and rounds to 3.14 when printed with two places. A plain `Shape` has no replacement, so its base version returns 0.00.
