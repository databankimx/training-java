## The alien fleet

The aliens are the targets, so a fleet of them makes the game a game. This step builds the fleet: four rows, one for each colour, with a grid of aliens in each row. The fleet moves together. When any alien reaches the side of the screen, the whole fleet turns around and drops one row.

Press **Run**. The window shows the fleet across the top, with one row of blue, green, pink, and yellow aliens. The fleet slides to one side, turns when it reaches the edge, and drops down. Bullets still pass through the aliens, since hitting them is the next step.

The aliens are drawn from the assets that were copied from the Python project. Each one uses the first frame of its colour's animation, so the pictures are the real ones. The animation itself comes later.

### Settings for the fleet

`Settings.java` gains the colours, the number of columns, and the fleet's speed and drop distance:

```java
static final String[] ALIEN_COLOURS = {"blue", "green", "pink", "yellow"};
static final int FLEET_COLUMNS = 6;
static final int ALIEN_SPEED = 1;
static final int FLEET_DROP = 20;
```

The number of rows is the number of colours, so the fleet has one row of each. Changing `FLEET_COLUMNS` makes the fleet wider or narrower.

### The alien

`Alien.java` loads its picture from the assets folder. The path is relative to the program's build folder, which is where the runner copies the assets:

```java
image = new ImageIcon("assets/images/" + colour + "-anim/frame_0_delay-0.08s.gif");
```

Each alien keeps its own position, and it can move sideways, drop down, and report when it touches an edge. The picture is drawn at that position with `paintIcon`, which takes the panel as the component to draw on.

### The ship's picture

`Ship.java` now loads its picture the same way, from the first frame of the ship's animation. The ship's size comes from the picture, so the bullets still start at its tip, whatever the size of the image:

```java
image = new ImageIcon("assets/images/ship-anim/frame_0_delay-0.08s.gif");
width = image.getIconWidth();
height = image.getIconHeight();
```

The ship keeps this picture until it fires. Its firing animation, which plays the other frames, is added in the step that builds the explosions and animations.

### The fleet

`Fleet.java` holds the list of aliens and the direction the fleet is moving. The constructor places them in a grid. The columns are a fixed distance apart, set by `COLUMN_SPACING` as a multiple of the alien's width. The fleet starts at the margin and is only as wide as its columns need, so it has room to travel. A fleet that filled the whole width would have almost no sideways distance to cover.

```java
double spacing = width * Settings.COLUMN_SPACING;
double x = Settings.FLEET_MARGIN + column * spacing;
double y = height + 2.0 * height * row;
```

A smaller `COLUMN_SPACING` brings the columns closer together, so the fleet is narrower and each sideways trip is longer.

The window is 800 pixels wide and 600 pixels tall, the same size as the earlier steps. The fleet crosses it at the same speed, so each sideways trip is shorter than in a wider window.

Each frame, `update` checks the aliens for the edge the fleet is moving towards. If one is there, the direction is reversed and every alien drops. Then every alien moves one step in the current direction. The fleet moves sideways until an alien reaches that edge, then drops once, and then moves the other way.

The check looks only at the edge in the direction of travel. Checking both edges every frame would cause a problem: an alien can pass the edge by a few pixels before the fleet turns, and after the turn it's still past the edge for a few frames. Each of those frames would turn and drop the fleet again, so it would fall many rows in a moment. Checking only the edge ahead means the turn happens once, and the fleet is moving away from that edge afterwards.

### Why the check uses a break

Once one alien is found at an edge, the fleet only needs to turn once, so the loop stops with `break`. Without it, every alien at the edge would reverse the direction again, and the fleet would stop dead.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does the fleet check every alien for an edge before moving any of them?
○ Because the aliens cannot move in two directions at once.
→ Not quite. The reason is about the whole fleet, not the direction of a single alien.
◉ So that the whole fleet turns and drops together, as one group.
→ Yes. If each alien turned alone, the fleet would fall apart.
○ Because the edge check is faster when done first.
→ Not quite. The reason is the behaviour of the fleet, not speed.
````

````quiz
What does `break` do in the edge check of `update`?
○ It stops the timer.
→ Not quite. The timer keeps running, and the loop is the only thing that stops.
◉ It leaves the loop once one alien has been found at an edge, so the fleet turns only once.
→ Yes. Without it, the direction would change for each alien at the edge.
○ It removes the alien from the fleet.
→ Not quite. No alien is removed by this check.
````

---

### Try it in the runner

Change `COLUMN_SPACING` from 1.5 to 1.0, and predict how far the fleet travels before it reaches the other side. The columns touch, the fleet is narrower, and it has further to travel, so each sideways trip is longer. Restore the value before you continue.

Then change `FLEET_DROP` from 20 to 60, and predict how the fleet moves when it reaches an edge. It drops three times as far, so the aliens get closer to the ship much faster. Restore the value before you continue.

---

The fleet moves as one group, turning and dropping when it reaches the edges. The next step has the bullets hit the aliens, which makes them explode and removes them from the fleet.
