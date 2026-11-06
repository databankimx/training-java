## Homework: Reversed words

For each word in a dictionary, decide whether its reverse is also in the dictionary. "stop" reversed is "pots", which is also a word.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* insert the words "stop", "pots", "dog", "god", and "cat" into a trie
* for each word, reverse it and check the trie for the reversed text
* print each word, followed by whether its reverse is a word

### What to check

The output should be:

```text
stop: its reverse is a word: true
pots: its reverse is a word: true
dog: its reverse is a word: true
god: its reverse is a word: true
cat: its reverse is a word: false
```

The reverse of "cat" is "tac", which is not in the dictionary.

### Extensions

* Add "tops" and "spot" to the dictionary, and predict the answers for them before you run the program.
* Count how many lookups the check makes in total, and compare it with the number of letters in the dictionary.

---

*This exercise is not from the book. It practices the ideas in Chapter 32.*
