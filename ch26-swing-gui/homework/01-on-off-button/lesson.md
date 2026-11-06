## Homework: On and off button

Write a window with a label and a button. The label starts as `Off`, and each click of the button switches it between `On` and `Off`.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* open a window titled `Switch`, which closes the program when it's closed
* hold a `static boolean` field that records whether the switch is on, starting as false
* show a label, starting with the text `Off`, and a button labelled `Toggle`
* on each click, flip the boolean and set the label to `On` or `Off` to match

### What to check

Press **Run**, then click the button several times. The label should alternate between `On` and `Off`, starting with `On` after the first click. Close the window, and the run should finish with `Finished with exit code 0`.

### Extensions

* Change the button's text to `On` or `Off` according to the state, so the button describes what clicking it will do.
* Add a second button that sets the switch to off, whatever its state, and explain why the listener for the second button doesn't need to check the current state.

---

*This exercise is not from the book. It practices the ideas in Chapter 26, with the events from the buttons step.*
