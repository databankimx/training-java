## Word count: walkthrough

`getOrDefault(word, 0)` returns the count so far, or 0 if the word hasn't been seen. Adding 1 and storing the result with `put` updates the count. The first time a word appears, the map gains an entry, and later times it replaces the value.

`put` with an existing key replaces the old value, which is what makes the running count work. Without `getOrDefault`, a first lookup would return `null`, and adding 1 to `null` would fail.

The output reads the counts back with `get`, which is safe here because each word in the list is in the map.
