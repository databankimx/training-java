## Homework: Distinct words

Find how many different words the list holds, and check whether particular words are among them. A set keeps each word once, so adding every word gives the distinct ones.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own words.

The program should:

* start with the same array as the word count exercise, `{"the", "cat", "saw", "the", "dog", "the", "cat"}`
* add each word to a `Set<String>`
* print `Words: ` followed by the length of the array, and `Distinct: ` followed by the set's size
* print whether `dog` and `bird` are in the set, with the labels `Has dog: ` and `Has bird: `

### Where to change the code

The loop adds every word, and the set quietly ignores the repeats. To ask about other words, change the `contains` strings. To count distinct words in a different list, change the array.

### What to check

The output should be:

```text
Words: 7
Distinct: 4
Has dog: true
Has bird: false
```

There are seven words in total, and four distinct ones: the, cat, saw, and dog. `bird` never appears in the list.

### Extensions

* Print the distinct words themselves, by looping over the set. Predict the order before you run it, and note that a `HashSet` doesn't promise any order.
* Change the set to a `TreeSet`, and check that the printed words are then in alphabetical order.

---

*This exercise is not from the book. It practices the ideas in Chapter 14.*
