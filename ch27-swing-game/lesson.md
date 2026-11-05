## Ch27 · Alien Invasion

This chapter builds a complete game in Swing: a ship that fires at an alien fleet, with animation, sound, and a saved high score. It's written as one project, in steps that add one part of the game at a time, so each step runs on its own and leaves a working program behind.

The steps are:

* [The window and the ship](#goto/01-window-and-ship): a window, a ship that moves with the arrow keys, and the timer that drives the frame loop.
* [Bullets](#goto/02-bullets): firing with the space bar, moving bullets up the screen, and removing the ones that leave it.
* [The alien fleet](#goto/03-aliens): a grid of aliens with real pictures, moving as one group and turning at the edges.
* [Collisions and lives](#goto/04-collisions): bullets that hit aliens, losing a life on contact, and the game over screen.

---

*The examples and explanations are new. The game follows the design of the Python course's Alien Invasion project.*
