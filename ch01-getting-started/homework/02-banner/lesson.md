## Homework: A banner

Write a program that prints a box around a greeting, like this:

```text
**********
* Hello! *
**********
```

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own greeting.

The box has three lines. The top and bottom lines are the same length as the middle line, and the middle line has the greeting with a space on each side, inside the stars.

Use one `System.out.println` statement for each line. Don't print the box with a single long string, because the point of the exercise is to see how the lines fit together.

### What to check

Count the characters on each line. All three should be the same length. If the top line is longer or shorter than the middle line, the box won't look like a box.

### Extensions

* Change the greeting to something longer, and work out how many stars the top and bottom lines need. Can you write the program so the stars come from a count, rather than being typed out? A `for` loop that prints stars would do it.
* Print the same box with `System.out.print` for each piece of a line, and a final `System.out.println()` to end each line. The output shouldn't change.

---

*This exercise is not from the book. It practices the ideas in Chapter 1.*
