## Summary: Building a game

This chapter built Alien Invasion in Swing, one part at a time. Each step left a working game behind, and the last step has everything together.

* **A timer drives the frame loop.** Every frame the game updates its state, then repaints. Only the state decides what changes.
* **Settings live in one place.** Sizes, speeds, colours, and file locations are constants in `Settings`, so a change is made once.
* **Objects keep their own rules.** The ship, bullets, aliens, fleet, explosions, and scoreboard each handle one job, and the panel connects them.
* **Hits use rectangles.** Each object reports its area with `bounds`, and `intersects` decides when two overlap.
* **An enum holds the game's screens.** Only the playing states update the fleet, and the others show a screen.
* **Animations are folders of pictures.** The frame count comes from the files, and a flag decides whether the animation loops.
* **Sounds are read once.** Effects are kept as bytes and played on fresh clips, and the music is one looping clip.
* **Saved data goes where it survives.** The high score is in the user's home folder, since the build folder is cleared before each run.
* **Changes are made between frames.** Removing items from a list and starting a new fleet happen in the loop, not in the middle of a pass over the same list.

### Thank you

This game and the way its lessons are built come from the Alien Invasion project in *Python Crash Course*, 3rd Edition, by Eric Matthes, published by No Starch Press. Thank you to Eric Matthes for creating it. The Java version follows the design of the Python version of this training, which is based on that book, and thank you to everyone who worked on that version. The alien and ship images and animations, and the sound effects, are by Scott McLean, and may be used however users wish.

### Check your understanding

*These questions are written for this course.*

````quiz
Why does the game keep a state, such as PLAYING or GAME_OVER, instead of checking several flags?
○ Because flags cannot be used in Swing.
→ Not quite. Flags work in Swing. The reason for a state is clarity.
◉ So that each screen has one clear value, and the frame loop and drawing code agree on what is happening.
→ Yes. One value that names the screen is easier to keep right than several flags that might disagree.
○ Because the timer can only read one value at a time.
→ Not quite. The timer reads any value it needs. The state gives the screens one shared meaning.
````

````quiz
Why is the high score written to the user's home folder rather than the build folder?
○ Because the home folder is faster to write.
→ Not quite. Speed is not the reason.
◉ Because the runner clears the build folder before each run, so a file kept there would be deleted.
→ Yes. A saved record must outlive the run that made it.
○ Because the build folder cannot hold text files.
→ Not quite. The build folder holds text files just as well. The problem is that it's cleared.
````

````quiz
Why does an alien's animation loop, while the ship's firing animation plays once?
○ Because loops are faster to draw.
→ Not quite. Speed is not the reason.
◉ Because an alien keeps moving for as long as it lives, while the ship's firing is a single action on each shot.
→ Yes. The animation's behaviour follows what the object does in the game.
○ Because the ship's pictures are larger.
→ Not quite. The size of the pictures doesn't decide whether they loop.
````
