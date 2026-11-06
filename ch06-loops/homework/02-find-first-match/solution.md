## Find the first match: walkthrough

The loop checks each element by index, and when it finds the target, it saves the index in `foundAt` and uses `break`. The `break` matters: without it, the loop would keep going, and the index would be overwritten by the second match at index 4.

The `-1` start value is a marker, not a real position. An array index can't be negative, so `foundAt == -1` after the loop means the search never found the target.

The first extension, removing `break`, makes the loop keep going, so each match overwrites `foundAt`, and the last match wins. That prints index 4.
