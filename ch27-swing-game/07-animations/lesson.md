## Animations

So far the pictures in the game have been still, showing the first frame of each animation. This step makes them move. The aliens' animations loop for as long as each alien is alive. When an alien is hit, it bursts into an explosion. When the ship fires, its picture plays through its firing frames.

Press **Run**, click PLAY, and hit an alien. An explosion plays where it was, in the colour of that alien's row. Fire, and the ship's picture plays through its frames before returning to the first one. The aliens keep animating the whole time, so their wings and legs move while the fleet slides across the screen.

The game also has a darker look now. The background is a deep blue, and the text and the buttons use light colours so they stand out against it. The colours are set in `Settings.java` as `BACKGROUND` and `TEXT`.

### What an animation is

An animation is a list of pictures shown one after another. The pictures in the assets folder are named `frame_0`, `frame_1`, and so on. `Animation.java` loads them in order, and keeps track of which one is showing:

```java
for (int i = 0; ; i++) {
    Path file = Path.of("assets", "images", folder, "frame_" + i + "_delay-0.08s.gif");
    if (!Files.exists(file)) {
        break;
    }
    frames.add(new ImageIcon(file.toString()));
}
```

The loop counts the frames by looking for each file in turn, and it stops at the first one that's missing. The number of frames doesn't have to be written down, so an animation with more frames needs no change to the code.

### Looping and one-time animations

The constructor takes a second argument that decides whether the animation repeats:

```java
animation = new Animation(colour + "-anim", true);   // an alien: loops for as long as it lives
firing = new Animation("ship-anim", false);          // the ship: plays once per shot
animation = new Animation(colour + "-boom", false);  // an explosion: plays once, then goes away
```

When a looping animation reaches its last picture, it returns to the first one and keeps playing. A one-time animation stops at its first picture instead. The code that moves the index on is the same in both cases, and the `loop` flag decides only what happens at the end.

### Timing

The game runs at 60 frames a second, and each picture should stay on screen for about 80 milliseconds. That's five game frames, so `Settings.java` holds `ANIMATION_TICKS_PER_FRAME` as 5. Each call to `update` counts a frame, and when the count reaches five, the next picture is shown:

```java
ticks++;
if (ticks >= Settings.ANIMATION_TICKS_PER_FRAME) {
    ticks = 0;
    index++;
    if (index >= frames.size()) {
        index = 0;
        playing = false;
    }
}
```

When the last picture has been shown, the animation stops, and its index goes back to the first picture. So `currentFrame` always returns a picture to draw, and when the animation isn't playing, that picture is the first one.

### Explosions

An `Explosion` holds an animation and a position. Its constructor takes the name of the folder with its pictures, and it's created when an alien is hit:

```java
explosions.add(new Explosion(alien.colour() + "-boom", alien.x(), alien.y()));
```

Each frame, the panel updates every explosion, and removes the ones that have finished. The explosion stays where the alien was, since its position is fixed when it's created. The alien itself has already been removed from the fleet.

### The ship's explosion

When the ship is hit, the game enters a new state, `SHIP_DOWN`. The ship's explosion starts from the `kaboom` pictures, and nothing else in the game changes until it has finished:

```java
private void loseShip() {
    shipsLeft--;
    bullets.clear();
    Rectangle area = ship.bounds();
    shipExplosion = new Explosion("kaboom", area.x, area.y);
    state = State.SHIP_DOWN;
}
```

While the state is `SHIP_DOWN`, the frame loop updates only the ship's explosion. When it has finished, the fleet is replaced. If lives remain, the game returns to `PLAYING`. If not, `endGame` runs.

The play button and the Enter key start a game only from the starting screen or the game over screen. Both use the same check, `canStartGame`, so pressing them during the ship's explosion does nothing.

### The ship's firing animation

The ship keeps one animation, for its firing frames. When the ship fires, it starts that animation from its first picture. While the animation plays, the ship draws the current frame. Once it has finished, the ship draws its first picture again, which is the resting picture from earlier steps.

The bullet doesn't appear at the moment of the key press. It appears when the firing animation reaches frame 2, which is set in `Settings.java` as `BULLET_FRAME`. The key press starts the animation and sets `shotPending`, and each frame the panel checks whether the animation is showing that frame. Only then is the bullet added, and the flag is cleared:

```java
if (shotPending && ship.isShowingFrame(Settings.BULLET_FRAME)) {
    bullets.add(new Bullet(ship.centerX(), ship.topY()));
    shotPending = false;
}
```

The bullet now leaves the gun as the ship's picture shows it firing, rather than before the picture has changed. A second press during the wait does nothing, so the animation and the bullet always stay in step.

### Starting an animation again

`start` resets the index and the timer, even when the animation is already playing. A quick second shot therefore restarts the firing picture from the beginning, rather than waiting for the first animation to finish.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does `Animation` count the frames by looking for files, rather than storing a fixed number?
○ Because the number of frames changes every time the game runs.
→ Not quite. The frames are fixed, and the count comes from the files that exist.
◉ So that an animation with more or fewer frames needs no change to the code.
→ Yes. The loop stops at the first missing file, so the count follows the folder.
○ Because ImageIcon cannot load more than one picture.
→ Not quite. ImageIcon loads one picture at a time, and the loop loads them one by one.
````

````quiz
Why does `start` reset the index and the timer rather than continuing from where the animation is?
○ Because the index cannot be read while the animation is playing.
→ Not quite. The index can be read, and the reset is a choice.
◉ So that each shot shows the firing picture from the beginning, even if the last shot's animation is still playing.
→ Yes. A quick second shot starts the animation again, rather than waiting.
○ Because the game would crash if the animation did not start from frame zero.
→ Not quite. Starting from frame zero is a choice for how the animation looks, not a requirement.
````

---

### Try it in the runner

Change `ANIMATION_TICKS_PER_FRAME` from 5 to 2, and predict how the explosion looks before you press **Run**. Each picture stays for about 33 milliseconds instead of 80, so the explosion plays much faster and looks like a flicker. Restore the value before you continue.

Then change `ANIMATION_TICKS_PER_FRAME` to 20, and predict the effect on the ship when you fire. Each picture stays on screen for over a third of a second, so the firing animation is slow enough to watch frame by frame. Restore the value before you continue.

---

The aliens and the ship now move and react to hits with pictures instead of still shapes. The next step adds sound, which plays when the ship fires and when an alien explodes.
