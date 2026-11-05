## Scoring and the high score

A game needs a reason to keep playing, and a score gives the player one. This step adds points for each alien hit, shows the score while the game runs, and keeps the best score ever reached. The best score is saved to a file, so it's still there the next time the game starts.

Press **Run**, click PLAY, and hit some aliens. Each hit adds 10 points to the score at the top right. When the game ends, the score is compared with the high score in the middle at the top. If it's higher, it becomes the new high score, and it's written to the high score file in the user's home folder. Close the window and start the game again, and the high score is still shown.

### The scoreboard

The score is kept in a separate class, `Scoreboard`, rather than in the panel. The panel only asks the scoreboard for values and tells it what happened, which keeps the drawing code free of the saving rules:

```java
public void addPoints(int points) {
    score += points;
    if (score > highScore) {
        highScore = score;
    }
}

public void finishGame() {
    if (highScore > savedHighScore) {
        saveHighScore();
        savedHighScore = highScore;
    }
}
```

The panel calls `addPoints` after each frame, so the high score rises on the screen as soon as the score passes it. `finishGame` runs once, when the last life is lost, and saves the record only if this game set a new one. A game that ends below the record leaves the saved record as it was.

### Counting hits

`Fleet.removeHitBy` now returns the number of aliens it removed in that frame, so the panel knows how many points to add:

```java
int hits = fleet.removeHitBy(bullets);
scoreboard.addPoints(hits * Settings.ALIEN_POINTS);
```

Two hits in the same frame add twenty points, so a fast double hit scores as well as a single one.

### Saving when the window closes

A game can be closed in the middle of play, and the record shouldn't depend on the game reaching its end. The window's close button is handled by a listener, which calls `quit` before the program exits:

```java
frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
frame.addWindowListener(new WindowAdapter() {
    @Override
    public void windowClosing(WindowEvent event) {
        panel.quit();
        System.exit(0);
    }
});
```

The default close behaviour is switched off, so the window doesn't disappear before the save. `quit` calls `finishGame`, which saves the record only if it has risen since the last save. The listener then ends the program explicitly.

### Saving to a file

The high score is kept in the user's home folder, in a file named `.alien-invasion-high-score.txt`. The runner empties the build folder before each run, so a file kept there would be deleted at the start of the next game. A file in the home folder survives between runs, which is what a high score needs.

The high score is stored as plain text, with the number written out as characters:

```java
Files.writeString(file, Integer.toString(highScore));
```

Reading it back is the reverse, with `Integer.parseInt`. The file operations can fail. If the file is missing, the high score starts at zero, which is expected the first time the game runs. Any other problem, such as a read-only folder, is caught and reported, and the game goes on. A failed save only costs the record, not the game.

The file is plain text rather than a structured format, since a single number needs nothing more. The chapter on files covered reading and writing text in the same way.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does `finishGame` save only when a new record was set during the game?
○ Because saving a file takes too long to do every frame.
→ Not quite. The reason is the rule for saving, not the time it takes.
◉ So that the file is written once, at the end of a game, and only when the record has changed.
→ Yes. The comparison means an unchanged record is never written again.
○ Because the file can hold only one number.
→ Not quite. The file holds one number, but that's not why the score is compared.
````

````quiz
What happens when the high score file is missing?
○ The game stops with an error.
→ Not quite. A missing file is expected the first time, and the game carries on.
◉ The high score starts at zero, and the file is created when a higher score is saved.
→ Yes. The missing file is treated as no saved score yet.
○ The score is reset to zero each frame.
→ Not quite. The score only changes when aliens are hit.
````

---

### Try it in the runner

Change `ALIEN_POINTS` from 10 to 50, and predict the score after two hits. It shows 100. Then open `.alien-invasion-high-score.txt` in your home folder after a game, and check that it holds the high score as a plain number.

Finally, change `HIGH_SCORE_FILE` to a path in a folder that doesn't exist, such as a folder called `missing` inside your home folder, and predict what happens at game over. The game still shows GAME OVER, and the error message appears in the output. The score is not saved, but the game doesn't stop. Restore the file name before you continue.

---

The score and the high score give each game a result to beat. The next step adds an animated explosion, which plays when an alien is hit.
