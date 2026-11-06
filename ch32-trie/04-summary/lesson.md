## Summary: The trie

This chapter covered the trie, a tree that stores strings by their letters.

* **A trie shares the beginnings of words.** Words that start the same way share the nodes for that beginning.
* **A node has a child for each letter and a word-end flag.** The flag separates a word from a longer word that starts with it.
* **Search takes one step for each letter.** The time depends on the word's length, not on how many words the trie holds.
* **`contains` and `startsWith` differ.** `contains` needs the path and a word end. `startsWith` needs only the path.
* **Insert creates only the nodes that are missing.** Shared beginnings are reused.
* **Delete removes only what the word alone used.** It stops at the last branch node, and it never removes a prefix that isn't a word.
* **A trie keeps words in order.** A walk through the children visits the words alphabetically, which a hash table can't do.

### Check your understanding

*These questions are written for this course.*

````quiz
Why is the time for a trie search independent of how many words the trie holds?
○ Because the trie holds only one word.
→ Not quite. The trie can hold many words, and the search cost is still the same.
◉ Because a search follows one node per letter of the text, so only the text's length matters.
→ Yes. The number of words in the trie does not change the number of steps.
○ Because the search skips the children.
→ Not quite. The search follows the children, one letter at a time.
````

````quiz
What does `startsWith("an")` return for a trie that holds only "and"?
○ False, since "an" isn't a word.
→ Not quite. startsWith doesn't require a word, only a path.
◉ True, since the path for "an" exists.
→ Yes. "an" is a prefix of "and", so the path is there.
○ True, since "an" is a complete word.
→ Not quite. The word-end flag is not checked by startsWith.
````

````quiz
Why does `delete` keep track of the last branch node?
○ Because the branch node holds the word's count.
→ Not quite. The count is not stored there.
◉ Because nodes below the last branch belong only to the deleted word, so they can be removed as a group.
→ Yes. The last branch marks where the word's own path begins.
○ Because the branch node is the root.
→ Not quite. The last branch node can be anywhere on the path, and it's often not the root.
````
