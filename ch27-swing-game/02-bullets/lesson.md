## Bullets

A ship that only moves is not yet a game. This step lets the ship fire. Pressing the space bar starts a bullet at the top of the ship, and the bullet travels up the screen until it leaves the window. The ship can have only a few bullets in the air at once, which is the limit that makes firing a choice.

Press **Run**. Move the ship with the arrow keys, and press space. Each press sends a red bullet up from the ship's tip. Bullets disappear at the top of the window. Hold space down, and bullets keep firing, but only up to three at a time.

### Settings for bullets

The bullet's size, speed, and limit are added to `Settings.java`:

```java
static final int BULLET_WIDTH = 3;
static final int BULLET_HEIGHT = 15;
static final int BULLET_SPEED = 7;
static final int BULLETS_ALLOWED = 3;
```

Changing `BULLETS_ALLOWED` is the simplest way to make the game easier or harder. The bullet's speed is a number of pixels per frame, the same idea as the ship's speed.

### The bullet

`Bullet.java` has a position and two methods that the frame loop uses. `update` moves the bullet up by its speed, and `isOffScreen` says whether the bullet has passed the top of the window:

```java
public void update() {
    y -= Settings.BULLET_SPEED;
}

public boolean isOffScreen() {
    return y + Settings.BULLET_HEIGHT < 0;
}
```

The bullet starts at the ship's top point, and it moves up, so its `y` value gets smaller each frame. Once its bottom edge is above the top of the window, it has left the screen.

### Firing

The panel keeps the bullets in a `List`. Pressing space calls `fire`, which adds a new bullet only when the list is below the limit:

```java
private void fire() {
    if (bullets.size() < Settings.BULLETS_ALLOWED) {
        bullets.add(new Bullet(ship.centerX(), ship.topY()));
    }
}
```

The check happens before the bullet is added, so a fourth press while three bullets are in the air does nothing. The ship doesn't need to know about the bullets, and the bullets don't need to know about the ship after they start.

### Updating and removing

Each frame, the timer moves every bullet, and then removes the ones that have left the screen:

```java
for (Bullet bullet : bullets) {
    bullet.update();
}
bullets.removeIf(Bullet::isOffScreen);
```

`removeIf` takes a method that says which items to remove. Here the method is `Bullet::isOffScreen`, which the lambdas chapter explained. Removing items from a list while looping over it would cause an error, so the list is cleaned up in one call instead.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does `fire` check the number of bullets before adding one?
○ Because bullets cannot be stored in a list.
→ Not quite. Bullets can be stored in a list. The check is about the limit.
◉ So that the ship can have no more bullets in the air than the limit allows.
→ Yes. A fourth press while three bullets are in the air does nothing.
○ Because the timer stops after three bullets.
→ Not quite. The timer keeps running. The check only limits firing.
````

````quiz
What does `removeIf(Bullet::isOffScreen)` do?
○ It removes every bullet from the list.
→ Not quite. Only the bullets that have left the screen are removed.
◉ It removes each bullet for which isOffScreen returns true.
→ Yes. The remaining bullets stay in the list and keep moving.
○ It removes the first bullet in the list.
→ Not quite. removeIf checks every bullet, not only the first.
````

---

### Try it in the runner

Change `BULLETS_ALLOWED` from 3 to 1, and predict what happens when you press space twice quickly. Only one bullet appears, since the second press is ignored until the first bullet leaves the screen. Restore the value before you continue.

Then change `BULLET_SPEED` from 7 to 20, and predict how the bullets move. They travel up the screen much faster, so each bullet leaves the window in fewer frames. Restore the speed before you continue.

---

The ship can now fire, and the bullets are removed when they leave the screen. The next step builds the alien fleet that the bullets are aimed at.
