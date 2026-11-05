## Your first window

Swing is Java's standard library for windows, buttons, and text fields. It comes with the JDK, so a graphical program needs no extra library. This step opens a window with a label in it.

Here's the program for this step, in `src/Main.java`:

```java
SwingUtilities.invokeLater(() -> {
    JFrame frame = new JFrame("First window");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new JLabel("Hello from Swing", JLabel.CENTER));
    frame.setSize(300, 150);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
});
```

Press **Run**. The Output pane shows the first line, `Opening the window`, and a window appears on the screen. The run stays in progress while the window is open. The Run button changes to Stop until you close the window.

### The event dispatch thread

Swing does all of its drawing and input handling on one thread, called the event dispatch thread. Creating and showing a window from `main` directly can cause problems, so the window is built inside a lambda passed to `SwingUtilities.invokeLater`. That schedules the lambda on the right thread, and `main` can return at once.

### The parts of a window

* `JFrame` is the window itself, with a title and a border.
* `JLabel` shows text. `JLabel.CENTER` centres the text inside its space.
* `frame.add` puts a component in the window. The window's default layout places it to fill the space.
* `setSize` sets the window's width and height in pixels, and `setLocationRelativeTo(null)` centres it on the screen.
* `setVisible(true)` shows the window. Until this call the window exists but is hidden.

### Closing the window

`setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)` makes the window's close button end the program. Without it, closing the window hides it, and the program keeps running with no window in view. When the program ends, the runner shows `Finished with exit code 0`.

---

### Quiz

*These questions are written for this course.*

````quiz
Why is the window built inside `SwingUtilities.invokeLater`?
○ To make the window load faster.
→ Not quite. The reason is threading, not speed.
◉ So that the window is created and shown on the event dispatch thread, which handles Swing's drawing and input.
→ Yes. Swing components are used safely only on that thread.
○ To make the window stay open after the program ends.
→ Not quite. A window stays open because the program keeps running, not because of invokeLater.
````

````quiz
What does `setVisible(true)` do?
○ It creates the window object.
→ Not quite. The window is created by new JFrame, before this call.
◉ It shows the window on the screen.
→ Yes. Until then the window exists but is hidden.
○ It makes the label visible inside the window.
→ Not quite. The label is shown along with the window.
````

---

### Try it in the runner

Remove the line `frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);`, and press **Run**. Close the window with its close button. The window disappears, but the run keeps going, and the Run button still shows Stop, since the program is still alive. Press Stop to end it, then put the line back.

---

A Swing program builds its windows on the event dispatch thread, and it ends when the window is closed. The next step adds buttons, which respond to clicks.
