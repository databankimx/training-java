## The play button and game states

Until now the game starts running as soon as the window opens, and it has no way to begin again after it ends. A real game shows a starting screen, waits for the player to press a button, and offers another game when the first one is over. This step adds those screens.

Press **Run**. The window shows the fleet and a PLAY button, but the fleet doesn't move and the ship doesn't appear. Click PLAY, and the game starts. When the last life is lost, GAME OVER appears with a PLAY AGAIN button, and clicking it starts a new game with three lives.

### Three states

The game is always in one of three states. An enum lists them, as the enums chapter showed:

```java
private enum State {
    WAITING, PLAYING, GAME_OVER
}
```

The panel holds a `State` field. The timer updates the game only while the state is `PLAYING`, and the drawing code chooses the screen from the state. This keeps the screens separate: the rules for moving and hitting belong to the playing state, and the buttons belong to the other two.

### The button

`Button.java` draws a rounded rectangle with a label, and answers whether a point is inside it. The text is centred using the font's measurements, so it stays in the middle whatever the label's length:

```java
int textX = area.x + (area.width - metrics.stringWidth(label)) / 2;
```

The button doesn't decide what a click does. That's left to the panel, which asks the button whether the click landed inside it.

### Responding to the mouse

The panel adds a mouse listener. A `MouseAdapter` is a listener with empty methods, so only the method needed is written:

```java
addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent event) {
        if (state != State.PLAYING && playButton.contains(event.getPoint())) {
            startGame();
        }
    }
});
```

The click starts a game only when the game isn't already being played, and only when it lands on the button. The anonymous class can read the panel's fields, since it's written inside the panel.

### The Enter key

The same start can be made from the keyboard. The key handler starts a game when Enter is pressed and the game isn't being played, so the button and the keyboard do the same job:

```java
} else if (event.getKeyCode() == KeyEvent.VK_ENTER && state != State.PLAYING) {
    startGame();
}
```

The check on the state is the same as for the mouse, so pressing Enter during a game does nothing.

### Starting and ending a game

`startGame` resets everything the game uses: the lives, the ship, the fleet, and the bullets. It then changes the state to `PLAYING`. When the last life is lost, `loseShip` changes the state to `GAME_OVER` and replaces the button with one labelled PLAY AGAIN.

The button is a field rather than a constant, so the label can change when the state changes. Replacing the button with a new object is simpler than changing its label in place, since the button is only created when the game is over.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does the timer update the game only while the state is PLAYING?
○ Because the timer can only run once.
→ Not quite. The timer keeps running for every state. The state decides what it updates.
◉ So that the fleet and the ship stay still on the starting and game over screens.
→ Yes. The other screens show the same panel without any movement.
○ Because the state makes the timer faster.
→ Not quite. The timer's speed doesn't depend on the state.
````

````quiz
Why does the mouse listener check that the state is not PLAYING before it starts a game?
○ Because clicks are not allowed during a game.
→ Not quite. Clicks are allowed, but only the button starts a game.
◉ So that clicking the button during a game does not restart it and reset the lives.
→ Yes. The check stops a click in the middle of a game from starting a new one.
○ Because the button can only be clicked once.
→ Not quite. The button can be clicked again on the game over screen.
````

---

### Try it in the runner

Change the click check in `mousePressed` to drop the `state != State.PLAYING` part, and predict what happens when you click the play area during a game. The button is hidden during play, so the click only starts a new game if it lands where the button would be. Clicking that point during play restarts the game and resets the lives. Restore the check before you continue.

Then change `SHIPS_ALLOWED` from 3 to 1, and predict the screen after the first lost life. It shows GAME OVER and PLAY AGAIN straight away, since no lives are left. Restore the value before you continue.

---

The game now has a starting screen and a game over screen, and it can be played again without restarting the program. The next step keeps a score, and shows it as aliens are hit.
