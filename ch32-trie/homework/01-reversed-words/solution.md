## Homework: Reversed words: walkthrough

Each word is reversed with `StringBuilder.reverse`, and the reversed text is looked up with `contains`. "stop" and "pots" are each the reverse of the other, so both are true. "dog" and "god" are also reverses of each other. "cat" reverses to "tac", which the trie doesn't hold.
