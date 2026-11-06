## Homework: Reset counter

Write a window with a counter, a button that adds one, and a button that resets the count to zero.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* open a window titled `Counter`, which closes the program when it's closed
* keep the count in a `static int` field, starting at 0
* show the count on a label, starting with the text `Count: 0`
* have a button labelled `+1` that adds one to the count, and a button labelled `Reset` that sets the count to zero
* update the label after each click, so it always shows the current count

### What to check

Click `+1` three times. The label should read `Count: 3`. Click `Reset`, and the label should read `Count: 0`. Click `+1` again, and the count should start from zero again.

### Extensions

* Add a `-1` button that lowers the count, and decide whether it should stop at zero. Write the rule in the listener, not in the label.
* Move the label update into a separate `static void show()` method, and call it from both listeners. Explain what the change saves.

---

*This exercise is not from the book. It practices the ideas in Chapter 26, with the layouts and events from this chapter.*
