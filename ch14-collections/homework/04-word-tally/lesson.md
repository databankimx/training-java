## Homework: Word tally

Write a program that counts how often each word appears in a list, and reports the most common word.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own words.

The program should:

* create a `Map<String, Integer>` to hold the counts, using a `TreeMap`
* count these words, in this order: `red`, `blue`, `red`, `green`, `blue`, `red`
* print each word and its count, in the order the map keeps them, as `blue: 2`
* find the word with the highest count, and print `Most common: red`

Store the words in a `String` array, and use the enhanced `for` loop to count them. To find the most common word, keep the best word and best count found so far, and update them in a loop over the map's entries.

### What to check

The output should be:

```text
blue: 2
green: 1
red: 3
Most common: red
```

The map prints in key order, so `blue` comes first. The most common word is found by comparing counts, not by position.

### Extensions

* Ignore case, so `Red` and `red` count as the same word. Decide where the conversion belongs, and say why.
* Print every word that ties for the highest count. Think about whether you need one pass or two.

---

*This exercise is not from the book. It practices the ideas in Chapter 14.*
