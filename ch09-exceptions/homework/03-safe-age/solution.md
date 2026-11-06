## Homework: Safe age: walkthrough

The loop asks for an age until one is accepted. `hasNextInt` checks the next word before it's read. If the word isn't a number, the code reads it with `next` to remove it from the input, and then throws the `not a number` exception. Without that read, the same word would stay in the input, and every pass would fail on it.

The range check throws when the number is outside 0 to 120. Both exceptions are caught by one multi-catch, which prints the message and lets the loop ask again.

A valid age prints the accepted line and reaches `break`, which leaves the loop.
