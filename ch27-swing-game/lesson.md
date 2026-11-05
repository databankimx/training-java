## Ch27 · Alien Invasion

This chapter builds a complete game in Swing: a ship that fires at an alien fleet, with animation, sound, and a saved high score. It's written as one project, in steps that add one part of the game at a time, so each step runs on its own and leaves a working program behind.

The steps are:

* [The window and the ship](#goto/01-window-and-ship): a window, a ship that moves with the arrow keys, and the timer that drives the frame loop.
* [Bullets](#goto/02-bullets): firing with the space bar, moving bullets up the screen, and removing the ones that leave it.
* [The alien fleet](#goto/03-aliens): a grid of aliens with real pictures, moving as one group and turning at the edges.
* [Collisions and lives](#goto/04-collisions): bullets that hit aliens, losing a life on contact, and the game over screen.
* [The play button and game states](#goto/05-play-button): a starting screen, a game over screen, and a button that starts a new game.
* [Scoring and the high score](#goto/06-scoring): points for each hit, and a best score saved to a file.
* [Animations](#goto/07-animations): looping alien pictures, explosions when aliens are hit, and the ship's firing frames.
* [Sound](#goto/08-sound): background music, and sound effects for shots, explosions, and game over.
* [Summary](#goto/09-summary): a recap of the game and the ideas it used, and a short quiz.

---

*The examples and explanations are new. The game and its design come from the Alien Invasion project in *Python Crash Course*, 3rd Edition, by Eric Matthes (No Starch Press). The alien and ship images and animations, and the sound effects, are by Scott McLean, and may be used however users wish.*
