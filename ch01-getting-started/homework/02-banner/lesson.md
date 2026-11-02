## Banner

Write a program that prints the word HELLO inside a box made of stars. The box has a border line above and below the word, and the border is as wide as the word, plus two stars at each side.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for a different word.

### Where to change the code

The example prints three lines with `System.out.println`:

* the first and last lines are the border, nine stars each
* the middle line is the word, with a star and a space on each side

To print a different word, change the middle line only. Keep the stars and spaces around it. The border must be as wide as the middle line, so if the word gets longer, add stars to both border lines to match.

### What to check

When you press **Run**, the Output pane should show:

```text
*********
* HELLO *
*********
```

Count the stars carefully: the border has nine, and the middle line has the five letters of HELLO, a space on each side, and a star at each end.

### Extensions

* Change the word to your own name, and make the box fit it. The border has to change width as the word changes.
* Store the word in a variable, and print the border from the length of that variable, so the box fits any word.
