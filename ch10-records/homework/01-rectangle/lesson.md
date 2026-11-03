## Rectangle

Write a `Rectangle` record that holds a width and a height. A rectangle with a zero or negative size makes no sense, so the record must refuse to be created with one. Add an `area` method, and check both the valid and the invalid cases.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own sizes.

The program should:

* declare the record `Rectangle(int width, int height)`
* use a compact constructor that throws an `IllegalArgumentException` with the message `size must be positive` when either size is zero or below
* add an `area` method that returns the width times the height
* create a 3 by 4 rectangle, and print `Area of 3 by 4: ` followed by its area
* try to create a rectangle with a width of 0, and print `Rejected: ` followed by the message from the exception

### Where to change the code

The check is in the compact constructor, where the condition `width <= 0 || height <= 0` decides whether the record is accepted. To change which sizes are allowed, change that condition. The `area` method doesn't need to check anything, because the constructor has already made sure the sizes are valid.

### What to check

The output should be:

```text
Area of 3 by 4: 12
Rejected: size must be positive
```

Try a rectangle with a height of -2 as well. It should be rejected with the same message, because the `||` in the condition catches either side.

### Extensions

* Add a `perimeter` method, and check it for a 3 by 4 rectangle, which should be 14.
* Make the record hold `double` sizes, and decide whether the check should still reject zero. Explain your choice in a comment.

---

*This exercise is not from the book. It practices the ideas in Chapter 10.*
