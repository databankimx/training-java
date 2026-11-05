## Sound

The game looks finished, but it's silent. This step adds the sounds from the Python game: background music during play, a shot when the ship fires, an explosion when an alien or the ship is hit, and a womp when the game ends.

Press **Run** and click PLAY. The music starts, and each shot makes a sound as its bullet leaves the ship. Hits and the ship's explosion play the explosion sound, and the last life plays the womp sound and stops the music.

### Reading a WAV file

Java's `javax.sound.sampled` package is part of the JDK, so no library is needed. A sound is loaded from a file and attached to a `Clip`:

```java
Clip clip = AudioSystem.getClip();
clip.open(AudioSystem.getAudioInputStream(Path.of("assets", "sounds", file).toFile()));
```

`getAudioInputStream` reads the WAV file, and `open` gives the clip its data. A clip is kept in a field, so it's loaded once and reused. Loading a sound takes time, and a game that loaded its sounds during play would stutter at every shot.

The loading can fail. A missing file or an unsupported format raises an exception, which the method catches and reports, returning `null`. The play methods check for `null`, so a missing sound leaves the game running in silence:

```java
} catch (IOException | UnsupportedAudioFileException | LineUnavailableException error) {
    System.err.println("Could not load the sound " + file + ": " + error.getMessage());
    return null;
}
```

Three exceptions are listed in one `catch`, separated by the vertical bar. This is a multi-catch: the same handling applies to each.

### Playing once, and playing on a loop

Most sounds are effects, and an effect can start again while the last one is still playing. Each effect is read from its file once, when the game starts, and kept as bytes. Each play then opens a new clip from those bytes:

```java
Clip clip = AudioSystem.getClip();
clip.addLineListener(event -> {
    if (event.getType() == LineEvent.Type.STOP) {
        clip.close();
    }
});
clip.open(openStream(data));
clip.start();
```

Each clip is closed when it stops, so the clips don't build up. Because every hit gets its own clip, a second explosion starts at once, even while the first is still playing.

The background music is different. It is a single clip that repeats until it's stopped:

```java
background.setFramePosition(0);
background.loop(Clip.LOOP_CONTINUOUSLY);
```

`LOOP_CONTINUOUSLY` makes the clip start again each time it reaches its end, and `stop` ends it.

### Where each sound plays

The panel calls the `Sounds` class at the moments the game changes:

* `startGame` starts the music.
* When a bullet is added on the firing frame, the shot sound plays. The shot sound is tied to the bullet, so the sound and the picture of the bullet leaving the gun happen together.
* When aliens are hit, the explosion sound plays once for the frame, however many aliens were hit.
* When the ship is lost, the explosion sound plays, and the ship's explosion starts.
* `endGame` stops the music and plays the womp sound.

The sounds are separate from the game's logic, so the game still runs correctly if a sound fails to load.

---

### Quiz

*These questions are written for this course.*

````quiz
Why is a sound loaded once and kept in a field, rather than loaded each time it's played?
○ Because a sound can only be played once.
→ Not quite. A sound can be played many times from one loaded clip.
◉ Because loading takes time, and loading during play would make the game stutter at each shot.
→ Yes. Loading once when the game starts keeps play smooth.
○ Because the Clip class cannot be created while the game runs.
→ Not quite. Clips can be created at any time, and the reason here is speed.
````

````quiz
What does `LOOP_CONTINUOUSLY` do when a clip is started with `loop`?
○ It plays the clip once and then stops it.
→ Not quite. That is what start does without loop.
◉ It starts the clip again each time it reaches its end, until stop is called.
→ Yes. Background music uses this so it never runs out.
○ It plays the clip at double speed.
→ Not quite. The speed of the sound is not changed by the loop.
````

---

### Faster fleets

Each fleet carries its own speed, which is passed to its constructor. When a fleet is cleared, the level goes up, and the next fleet is made at a higher speed:

```java
if (fleet.isEmpty()) {
    level++;
    fleet = new Fleet(speedFor(level));
    bullets.clear();
}
```

`speedFor` adds `SPEED_INCREASE` to the starting speed for each level after the first. Losing a life doesn't change the level, so the replacement fleet keeps the speed it had. The level is shown under the lives count.

### Try it in the runner

Rename `shoot.wav` in the assets sounds folder, run the game, and fire. The game still runs, and the output shows `Could not read the sound shoot.wav`. Restore the name before you continue.

Then hit two aliens close together, and listen to the explosion. Each hit starts its own clip, so the second sound begins at once, while the first is still playing. Change `playEffect` to reuse one clip for every explosion, and predict what a second hit during the first sound does. The second sound cannot start until the first has finished, since one clip can play only one copy at a time. Restore the original method before you continue.

---

The game now has its music and sound effects. The next step is the expanded game, which takes the ideas in the try-it-yourself exercise from the Python course.
