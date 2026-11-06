## Inserting and searching

A `Trie` class wraps the root node and offers the operations a program needs: insert a word, check whether a word is in the trie, and check whether any word starts with a given prefix. Each operation follows the path of the letters down from the root, one node at a time.

### Inserting

`insert` walks down the trie for each letter of the word. If the child for a letter doesn't exist, it's created. When the last letter has been handled, the node for it is marked as a word end:

```java
void insert(String word) {
    TrieNode node = root;
    for (char c : word.toCharArray()) {
        int index = c - 'a';
        if (node.children[index] == null) {
            node.children[index] = new TrieNode();
        }
        node = node.children[index];
    }
    node.isWordEnd = true;
}
```

The variable `index` converts each letter to its position in the array, so `'d'` becomes 3. Inserting "and" after "ant" reuses the nodes for `a` and `n`, since they already exist, and only the `d` node is new. That sharing is what makes the trie compact for words with common beginnings.

### Finding a path

The private method `find` follows the letters of a text from the root. If a child is missing at any point, the text isn't in the trie, and `find` returns `null`. Otherwise it returns the node for the last letter:

```java
private TrieNode find(String text) {
    TrieNode node = root;
    for (char c : text.toCharArray()) {
        node = node.children[c - 'a'];
        if (node == null) {
            return null;
        }
    }
    return node;
}
```

### Searching and prefixes

Two public methods use `find`. They differ in what they require:

```java
boolean contains(String word) {
    TrieNode node = find(word);
    return node != null && node.isWordEnd;
}

boolean startsWith(String prefix) {
    return find(prefix) != null;
}
```

`contains` needs the path to exist and the final node to be a word end. `startsWith` needs only that the path exists, since a prefix such as "an" is valid even when it isn't a word on its own.

### Running it

The trie holds "and", "ant", "dad", and "do". Press **Run**. The output is:

```text
'an' is a prefix: true
'anbx' is a prefix: false
'do' is a word: true
'an' is a word: false
```

"an" is a prefix of "and" and "ant", but it's not a word in the trie, which is why the last line is false. The lookup for "anbx" stops at the `b` step, because no child for `b` exists under "an".

### The cost of a search

Each lookup takes one step for each letter of the text, and it stops early when a child is missing. The time depends on the length of the word, not on the number of words stored. That's the advantage over a list, where a search may have to look at every word.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does `contains("an")` return false after "and" and "ant" are inserted?
○ Because "an" is too short to be stored.
→ Not quite. Short words can be stored, and "an" is simply not one of the words inserted.
◉ Because "an" is only a prefix, and its node is not marked as a word end.
→ Yes. Only the words that were inserted have their end flag set.
○ Because the search always fails for two-letter words.
→ Not quite. Two-letter words are found when they were inserted, as "do" shows.
````

````quiz
How many steps does `find` take for the text "anbx" in this trie?
○ 4, one for each letter.
→ Not quite. The search stops early when a child is missing.
◉ 3, since the search fails at the "b" step under "an".
→ Yes. The steps go to "a", then "n", then stop when "b" is missing.
○ 26, one for each letter of the alphabet.
→ Not quite. The alphabet's size doesn't set the number of steps.
````

---

### Try it in the runner

Insert `"anbx"`, and predict the output of its prefix line before you press **Run**. It's true, since the new word creates the path for "anb" and "anbx". Then predict the output of `contains("anb")`. It's false, since "anb" is only a prefix of the new word.

---

A trie searches by following letters, and the search time depends on the word's length. The next step removes words, and removes any nodes that no longer lead to a word.
