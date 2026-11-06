## The trie node

A trie is a tree that stores strings. Each node stands for one step in spelling a word, so words that share a beginning share the nodes for it. The word "and" and the word "ant" both start at the "a" node, then the "n" node, and then branch apart. Searching a trie takes one step for each letter, which makes the search time depend on the length of the word, not on the number of words stored.

Every trie is built from nodes, and each node has two parts:

* A set of children, one for each possible next letter.
* A flag that says whether a word ends at this node.

### The node

Here's the node from `src/Main.java`:

```java
class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isWordEnd = false;
}
```

The array has 26 slots, one for each letter of the English alphabet. Index 0 is `a`, index 1 is `b`, and so on. A slot holds `null` until a child for that letter is created. The `isWordEnd` flag is needed because a node can be both the end of a word and the start of longer ones: "an" is a word, and it's also the start of "and" and "ant".

The class is package-private, which means only this file can use it. The `Main` class is the only public class in the file, and the program is the only user of the node.

### Walking the children

The program creates the path for "an" by hand, one node at a time:

```java
root.children['a' - 'a'] = new TrieNode();
TrieNode a = root.children['a' - 'a'];
a.children['n' - 'a'] = new TrieNode();
TrieNode an = a.children['n' - 'a'];
an.isWordEnd = true;
```

The expression `'a' - 'a'` is 0, and `'n' - 'a'` is 13, which is the index of `n` in the array. Subtracting `'a'` converts a letter to its index, and the next step shows this done automatically. Each assignment creates one node, and each step goes down one level in the tree.

Press **Run**. The output is:

```text
Child 'a' exists: true
Child 'b' exists: false
'an' is a word: true
```

The root has a child for `a`, but none for `b`, since no word in the trie starts with `b`. The node for "an" is marked as the end of a word.

---

### Quiz

*These questions are written for this course.*

````quiz
What does `'n' - 'a'` give?
○ 'n'
→ Not quite. Subtracting characters gives a number, not a character.
◉ 13
→ Yes. n is the 14th letter, so its index is 13.
○ 26
→ Not quite. 26 is the number of slots in the array, not the index of n.
````

````quiz
Why does a node need an `isWordEnd` flag as well as its children?
○ Because the children can't be stored in an array.
→ Not quite. The children are stored in an array, and the flag is a separate matter.
◉ Because a node can end a word and also be the start of a longer word, such as "an" and "and".
→ Yes. The children show what can come next, and the flag shows where a word stops.
○ Because the flag makes the search faster.
→ Not quite. The flag records whether a word ends here, and it doesn't change speed.
````

---

### Try it in the runner

Add `root.children['b' - 'a'] = new TrieNode();` and predict the output of the second line before you press **Run**. It now says `true`, since a child for `b` exists, even though no word has been inserted with it.

---

A trie node holds one child for each letter, and a flag for word ends. The next step builds a trie class that inserts and searches words.
