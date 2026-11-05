## Buttons and events

A window that only shows text can't respond to anything. A button sends an event when it's clicked, and a program responds to the event with a listener. In Java, the listener is often a lambda, written where the button is created.

Here's the core of this step's program, in `src/Main.java`:

```java
button.addActionListener(event -> {
    clicks++;
    countLabel.setText("Clicks: " + clicks);
});
```

`addActionListener` registers the lambda with the button. Each click calls it once, and the lambda updates the label. The window shows `Clicks: 0` at first. Press **Run**, then click the button. Each click adds one to the count on the label.

### Why the count is a field

A lambda can only capture local variables that never change, as the lambdas chapter explained. The count changes on every click, so it can't be a local variable inside `main`. It's a `static` field instead, and the lambda changes the field directly. The label is also a field, so the lambda can reach it.

### Layout

The window uses `BorderLayout` to place two components. `BorderLayout.CENTER` takes up the middle of the window, and `BorderLayout.SOUTH` takes the strip along the bottom. The other places are `NORTH`, `EAST`, and `WEST`. Each component stretches to fill its place, so the window can be resized without the parts overlapping.

### Events happen on the event dispatch thread

The listener runs on the same thread that draws the window, so it can update the label directly. That's the reason the window is built inside `invokeLater`, as the previous step explained. A listener that takes a long time would freeze the window, so long work belongs on another thread, which the concurrency chapter covered.

---

### Quiz

*These questions are written for this course.*

````quiz
What does `addActionListener` do?
○ It runs the lambda once, when the window opens.
→ Not quite. The lambda runs when the button is clicked.
◉ It registers the lambda so that it runs each time the button is clicked.
→ Yes. Each click sends an event, and the listener responds to it.
○ It changes the text on the button.
→ Not quite. That's the job of setText, not of a listener.
````

````quiz
Why is `clicks` a static field rather than a local variable in `main`?
○ Because local variables are slower.
→ Not quite. The reason is that the lambda must be able to change the count.
◉ Because a lambda can't change a local variable, but it can change a field.
→ Yes. The field is shared between the window code and the listener.
○ Because buttons can only read static fields.
→ Not quite. Buttons don't read fields. The listener does, and it changes them.
````

---

### Try it in the runner

Change `clicks++;` to `clicks += 5;`, and predict the label after the first click. It reads `Clicks: 5`. Then remove the `countLabel.setText` line, and predict what happens when you click. The count still goes up, but the label doesn't change, since nothing updates it.

---

A button sends an event, and a listener runs in response. The next step arranges several components in a window with layouts.
