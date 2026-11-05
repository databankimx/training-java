## Collisions and lives

So far the bullets pass through the aliens, and the aliens can reach the ship without any effect. This step makes the game respond to contact. A bullet that touches an alien removes both. When the fleet is cleared, a new one appears. An alien that touches the ship, or reaches the bottom of the screen, costs the ship a life. When no lives are left, the game is over.

Press **Run**. Fire at the fleet and the aliens disappear, one bullet for each. Clear the whole fleet, and a new one appears. Let an alien reach the ship, and the count at the top left drops from three to two, and a new fleet starts. Let the lives run out, and the words GAME OVER appear, with the fleet frozen.

### Finding a hit

Each sprite covers a rectangle, and two rectangles overlap when a hit has happened. Java's `Rectangle` class has an `intersects` method that answers the question, so each object reports its area:

```java
public Rectangle bounds() {
    return new Rectangle((int) x, (int) y, width, height);
}
```

The alien, the bullet, and the ship each have a `bounds` method. The rectangle is created fresh each time, so it always matches the current position.

### Removing a hit alien and its bullet

`Fleet.removeHitBy` checks each alien against each bullet. When one overlaps, both are removed:

```java
if (alien.bounds().intersects(bulletIterator.next().bounds())) {
    alienIterator.remove();
    bulletIterator.remove();
    break;
}
```

The loop uses iterators rather than a for-each loop, because a for-each loop can't remove items safely while it runs. `iterator.remove` takes out the current item, and the loop then continues from the next one. The `break` stops checking the current alien once it has been removed, since it no longer exists.

### Losing a life

The panel checks two things each frame. If the fleet touches the ship, or any alien has reached the bottom of the screen, a life is lost:

```java
if (fleet.touches(ship.bounds()) || fleet.reachedBottom(Settings.SCREEN_HEIGHT)) {
    loseShip();
}
```

`loseShip` takes away a life, starts a new fleet, and clears the bullets. When the number of lives reaches zero, `gameOver` is set. From then on, the frame still redraws, but the fleet, the ship, and the bullets stop moving.

### Why the game keeps drawing after it ends

The timer keeps running after the game is over, so `repaint` still happens every frame. That keeps the GAME OVER message on screen, and it means the window still responds to the close button. Stopping the timer would freeze the window, which the next step's play button would need to avoid.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does the fleet removal use an iterator rather than a for-each loop?
○ Because iterators are faster than for-each loops.
→ Not quite. The reason is safety when removing items during a loop, not speed.
◉ Because a for-each loop cannot remove items from a list safely while it runs, and an iterator can.
→ Yes. iterator.remove takes out the current item and keeps the loop going.
○ Because an alien cannot be stored in a list with a for-each loop.
→ Not quite. Aliens are stored in a list, and the for-each loop works on it.
````

````quiz
What does `break` do after an alien is removed in `removeHitBy`?
○ It stops the timer.
→ Not quite. The timer is in the panel, and this loop doesn't touch it.
◉ It stops checking bullets against that alien, since the alien no longer exists.
→ Yes. Once removed, the alien can't be hit again.
○ It ends the game.
→ Not quite. The game continues until the lives run out.
````

---

### Try it in the runner

Change `SHIPS_ALLOWED` from 3 to 1, and predict what happens the first time an alien reaches the ship. The lives drop to zero, and GAME OVER appears straight away. Restore the value before you continue.

Then change `BULLETS_ALLOWED` from 3 to 10, and predict how many bullets you can fire before the first one leaves the screen. Ten bullets can be in the air at once, so you can fire a wide spread before waiting. Restore the value before you continue.

---

Hits remove aliens and bullets, and contact with the ship costs a life. The next step adds the play button, so the game starts from a screen rather than at once.
