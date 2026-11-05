## Summary: Swing windows

This chapter covered building graphical windows with Swing, from the standard library.

* **Swing builds windows on the event dispatch thread.** `SwingUtilities.invokeLater` schedules the code that creates the window.
* **A `JFrame` is the window, and `setVisible(true)` shows it.**
* **`setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)` ends the program when the window closes.** Without it, the program keeps running with no window in view.
* **A `JLabel` shows text, and a `JButton` sends an event when clicked.**
* **`addActionListener` runs a lambda on each click.** A lambda can change a field, but not a local variable.
* **`BorderLayout` places components at the edges and in the centre.** `CENTER` takes the space that is left.
* **`GridLayout` arranges components in equal cells.** A `JPanel` can hold its own layout inside another.

### Check your understanding

*These questions are written for this course.*

````quiz
Which thread should create and show a Swing window?
○ Any thread, since it makes no difference.
→ Not quite. Swing components are used safely only on the event dispatch thread.
◉ The event dispatch thread, reached through SwingUtilities.invokeLater.
→ Yes. That thread handles all drawing and input for Swing.
○ The thread that runs the garbage collector.
→ Not quite. The collector has no part in drawing windows.
````

````quiz
What does a `JButton` need in order to do something when it's clicked?
○ A field with the same name as the button.
→ Not quite. A name doesn't make a button act.
◉ An action listener, usually a lambda passed to addActionListener.
→ Yes. Each click calls the listener.
○ A call to setVisible on the button.
→ Not quite. setVisible shows a component. It doesn't make the button respond to clicks.
````

````quiz
In a `BorderLayout`, which place stretches to fill the space left over when the window is resized?
○ NORTH
→ Not quite. NORTH takes only the height its component needs.
◉ CENTER
→ Yes. CENTER takes the remaining space.
○ SOUTH
→ Not quite. SOUTH takes only the height its component needs.
````
