## Homework: Word count

Count how many times each word appears in a list. The map's key is the word, and its value is the count so far. Each word is handled in turn, and the count for it goes up by one.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own words.

The program should:

* start with the array `{"the", "cat", "saw", "the", "dog", "the", "cat"}`
* create an empty `Map<String, Integer>`
* for each word, store the count one higher than the current count, where a missing word counts as zero
* print the counts for `the`, `cat`, and `dog`, using the labels shown in the output below

### Where to change the code

The loop body is the one line that does the work:

```java
counts.put(word, counts.getOrDefault(word, 0) + 1);
```

`getOrDefault` gives 0 for a word seen for the first time, and `put` stores the new count. To count different words, change the array, and to print other words, change the three print lines.

### What to check

The output should be:

```text
the: 3
cat: 2
dog: 1
```

`the` appears three times, `cat` twice, and `dog` once.

### Extensions

* Print every word with its count, by looping over the map's `entrySet()`. You'll need a `for` loop over the entries, using `Map.Entry`.
* Count the words in a sentence, by splitting a `String` on spaces with `split(" ")`, and then counting the resulting words.

---

*This exercise is not from the book. It practices the ideas in Chapter 14.*
