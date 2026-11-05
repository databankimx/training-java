## The window and the ship

This is the first step of the Alien Invasion game. The finished game is a single window where a ship at the bottom of the screen shoots at a fleet of aliens. This step builds the foundation: a window, a ship that moves with the arrow keys, and the timer that keeps the game moving. The game is split across several files, one per job, and this step uses four.

Press **Run**. A window opens with a grey ship at the bottom. Press the left and right arrow keys, and the ship moves along the bottom edge. It stops at each side of the window.

### Settings

`Settings.java` holds the numbers that shape the game: the window size, the background colour, the ship's speed, and the time between frames.

```java
static final int SCREEN_WIDTH = 800;
static final int SCREEN_HEIGHT = 600;
static final int SHIP_SPEED = 5;
static final int FRAME_MILLISECONDS = 1000 / 60;
```

Keeping these values in one class means a change such as a faster ship is made in one place. `FRAME_MILLISECONDS` is 16 milliseconds, which gives about 60 frames a second.

### The ship

`Ship.java` keeps the ship's position and two flags for the direction it's moving. Its `update` method moves the ship one step each frame, but only when it hasn't reached the edge:

```java
public void update() {
    if (movingRight && x + WIDTH < Settings.SCREEN_WIDTH) {
        x += Settings.SHIP_SPEED;
    }
    if (movingLeft && x > 0) {
        x -= Settings.SHIP_SPEED;
    }
}
```

The ship's position is a `double`, which keeps the movement smooth when the speed doesn't divide the window width evenly. It's drawn as a triangle with a `Polygon`.

### The game loop

`GamePanel.java` connects the ship to the window. A `Timer` calls `actionPerformed` every 16 milliseconds. That method updates the ship and calls `repaint`, which runs `paintComponent` and draws the frame:

```java
@Override
public void actionPerformed(ActionEvent event) {
    ship.update();
    repaint();
}
```

This is the frame loop that every game uses: update the state, then draw it. The timer runs on the event dispatch thread, so the drawing and the updates never happen at the same time.

### Keyboard input

The panel listens for key presses and releases. Pressing an arrow key sets a flag in the ship, and releasing it clears the flag. The ship moves only while the key is held, because the movement happens in `update`, not in the key handler. The panel also needs focus to receive keys, which is why `setFocusable(true)` and `requestFocusInWindow` are used.

### Starting the window

`Main.java` builds the window on the event dispatch thread, as the Swing chapter described, and it shows the window centred on the screen.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does the ship move in `update` rather than in the key handler?
○ Because key handlers are not allowed to change fields.
→ Not quite. Key handlers can change fields. The reason is about how movement is applied.
◉ So that the ship moves smoothly once per frame for as long as the key is held.
→ Yes. The key only sets a flag, and the frame loop moves the ship each time.
○ Because the timer cannot run the update.
→ Not quite. The timer runs the update, as the next step builds on.
````

````quiz
What does `repaint` do?
○ It moves the ship one step.
→ Not quite. Movement happens in update.
◉ It asks Swing to call paintComponent, which redraws the panel.
→ Yes. The drawing happens in paintComponent, not in repaint itself.
○ It closes the window and restarts the game.
→ Not quite. repaint only redraws the panel.
````

---

### Try it in the runner

Change `SHIP_SPEED` from 5 to 15, and predict how the ship moves before you press **Run**. It crosses the screen much faster, and it still stops at the edges, since the edge check uses the same speed. Restore the speed before you continue.

Then change `FRAME_MILLISECONDS` to 100, and predict the effect. The game still moves, but about ten times a second, so the ship looks jerky. Restore the value before you continue.

---

The window, the ship, and the frame loop are the foundation for everything else in the game. The next step adds bullets, which the ship fires when the space bar is pressed.
