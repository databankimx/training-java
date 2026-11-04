## Homework: Safe fallback: walkthrough

`readSensor(3)` returns 30, so the good future completes normally. `readSensor(-1)` throws, so its future is finished with that failure, and `exceptionally` replaces it with 0.

Both futures are read with `join` after they are created, so the total is 30 whichever future finishes first.
