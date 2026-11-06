## Deleting a word

Removing a word from a trie is harder than inserting or searching. The nodes for its letters may be used by other words, so they can't simply be removed. If both "any" and "anyone" are in the trie, deleting "any" must leave the path for "any" in place, because "anyone" needs it. Only the word-end flag can change.

### What the delete must decide

Walking down the trie for the word, the delete needs to know two things:

1. Whether the word is in the trie at all. If the final node isn't marked as a word end, the word isn't there, and nothing is removed.
2. How much of the path can be removed. A node can be removed only if no other word uses it. The safe point is the last node on the path that branches, meaning a node with more than one child. Everything below that node belongs to this word alone.

The algorithm keeps track of the last branch node it passes, along with the index of the child it moves to from that node. Then it has three cases.

### The method

```java
boolean delete(String word) {
    TrieNode node = root;
    TrieNode lastBranch = null;
    int lastBranchIndex = -1;

    for (char c : word.toCharArray()) {
        int index = c - 'a';
        if (node.children[index] == null) {
            return false;
        }
        if (countChildren(node) > 1) {
            lastBranch = node;
            lastBranchIndex = index;
        }
        node = node.children[index];
    }

    if (!node.isWordEnd) {
        return false;
    }
    if (countChildren(node) > 0) {
        node.isWordEnd = false;
        return true;
    }
    if (lastBranch != null) {
        lastBranch.children[lastBranchIndex] = null;
        return true;
    }
    root.children[word.charAt(0) - 'a'] = null;
    return true;
}
```

The three cases at the end are:

* If the final node has children, other words continue past it. Only its word-end flag is cleared, so the longer words remain.
* If the final node has no children, the path from the last branch node down is removed by clearing one link. That deletes every node that only this word used.
* If there was no branch at all, the word is the only one that starts with its first letter, so that link from the root is cleared.

The check `if (!node.isWordEnd)` is what stops a prefix from being deleted by mistake. Without it, deleting "an" from a trie that only holds "anyone" would remove the whole branch.

### Running it

The trie holds "any" and "anyone". Press **Run**. The output is:

```text
delete("an"): false
delete("any"): true
contains("anyone"): true
contains("any"): false
```

Deleting "an" returns false, since "an" was never inserted. It's only a prefix. Deleting "any" clears its word-end flag, and the longer word stays. Both results are what the trie should give.

### Keeping the change in memory

The delete changes the trie only while the program runs. A trie loaded from a file isn't changed on disk, so the word returns the next time the program starts. A program that needs the change kept must write the trie out again.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does `delete` clear only the word-end flag when the final node has children?
○ Because the children can't be removed.
→ Not quite. Children can be removed, but not when other words use them.
◉ Because the children belong to longer words, and removing them would delete those words too.
→ Yes. The node is still needed as part of a longer word's path.
○ Because the final node can't be marked as not a word end.
→ Not quite. The flag can be cleared, and that's what the code does.
````

````quiz
What stops `delete("an")` from removing the "anyone" branch when only "anyone" is in the trie?
○ The word is shorter than the others.
→ Not quite. The length isn't the test.
◉ The check that the final node is a word end, which fails for a prefix.
→ Yes. A prefix that isn't a word returns false before anything is removed.
○ Nothing stops it. The branch is removed.
→ Not quite. The check on the word end prevents this.
````

---

### Try it in the runner

Insert `"ant"` after the two words, then delete `"any"`. Predict the result of `contains("ant")` before you press **Run**. It's true, since "ant" shares the path for "an" and was not removed. Then delete `"anyone"` as well, and predict the result of `contains("anyone")`. It's false, and the branch from the last node that has two children is cleared.

---

Delete removes only the nodes that the deleted word alone used. That completes the trie's basic operations, and the summary follows.
