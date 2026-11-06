## Layouts

A window often holds more than one component, and the layout manager decides where each one goes and how big it is. Java has several layout managers, and each one follows its own rule. Two more things make a window look right: the spacing between components, and the look that the components are drawn in.

This step's window has a title, a status line, and a grid of four buttons. The buttons are a sample of a layout, not a working calculator. Each one just reports which button was pressed, so you can see the layout and the listeners working together.

Here's the code for this step, in `src/Main.java`. The window uses `BorderLayout` for its parts, and the buttons sit in a panel that uses `GridLayout`:

```java
JPanel content = new JPanel(new BorderLayout(10, 10));
content.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

content.add(title, BorderLayout.NORTH);
content.add(status, BorderLayout.SOUTH);

JPanel grid = new JPanel(new GridLayout(2, 2, 10, 10));
grid.add(keyButton("7"));
grid.add(keyButton("8"));
grid.add(keyButton("9"));
grid.add(keyButton("+"));
content.add(grid, BorderLayout.CENTER);
```

The `keyButton` method creates a button and gives it a listener that changes the status line:

```java
static JButton keyButton(String text) {
    JButton button = new JButton(text);
    button.addActionListener(event -> status.setText("Pressed " + text));
    return button;
}
```

Press **Run**. The window shows a title at the top and a status line at the bottom, with the four buttons in the middle. Click the `8`, and the status line reads `Pressed 8`.

### Gaps and padding

Each layout takes gap sizes in its constructor. `new BorderLayout(10, 10)` leaves 10 pixels between its parts, and `new GridLayout(2, 2, 10, 10)` leaves 10 pixels between the grid's cells. Without the gaps, the components touch each other, and the window looks crowded.

`BorderFactory.createEmptyBorder(12, 12, 12, 12)` adds 12 pixels of empty space around a panel's edge. The values are top, left, bottom, and right. This keeps the components away from the window's frame.

### Look and feel

The first line of `main` sets the look and feel:

```java
UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
```

The look and feel decides how buttons, labels, and borders are drawn. Without this line, Swing uses its own default look, which can look different from the rest of the computer. The system look matches the other windows. This call must come before any window is created.

### BorderLayout

`BorderLayout` divides a panel into five places: `NORTH`, `SOUTH`, `EAST`, `WEST`, and `CENTER`. Each place can hold one component. The outer places take only the space their component needs, and `CENTER` takes whatever is left. Resizing the window stretches the centre, and the labels stay at the top and bottom.

### GridLayout

`GridLayout(2, 2, 10, 10)` places components in a grid of two rows and two columns, in the order they're added. Each cell is the same size, so the four buttons share the panel equally. A panel can hold its own layout, which is why the grid is placed in the centre of the content panel as a single component.

### Panels

`JPanel` is a container with no visible border. It groups components, so a whole group can be given one place in a larger layout. Panels are the usual way to combine layouts: one layout places the panels, and each panel uses its own layout for its contents.

---

### Quiz

*These questions are written for this course.*

````quiz
Which place in a `BorderLayout` takes up whatever space is left after the others?
○ NORTH
→ Not quite. NORTH takes only the height its component needs.
◉ CENTER
→ Yes. CENTER takes the remaining space, and it stretches when the window is resized.
○ WEST
→ Not quite. WEST takes only the width its component needs.
````

````quiz
What do the two numbers in `new GridLayout(2, 2, 10, 10)` after the rows and columns do?
○ They set the size of each button in pixels.
→ Not quite. The sizes of the buttons come from the layout, not from these numbers.
◉ They set the horizontal and vertical gap, in pixels, between the cells.
→ Yes. The gaps keep the cells from touching.
○ They set the number of rows and columns a second time.
→ Not quite. The first two numbers are the rows and columns.
````

---

### Try it in the runner

Click the `+` button, and predict the status line first. It reads `Pressed +`, since the button's text is passed to its listener. Then change the gap in `new GridLayout(2, 2, 10, 10)` to `0, 0`, and check that the buttons touch each other. Restore the gap before you continue.

---

Layouts decide where components go, gaps and padding keep them apart, and the look and feel decides how they're drawn. The next step is the chapter summary, followed by two homework exercises that build small windows.
