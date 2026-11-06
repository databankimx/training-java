## Homework: Sort by length

Sort a list of words from shortest to longest, using a lambda as the comparison. Words of the same length should keep the order they had in the list.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own words.

The program should:

* create a `List<String>` holding "pear", "fig", "banana", and "kiwi", in that order
* sort it with `sort`, passing a lambda that compares the lengths of two words using `Integer.compare`
* print `By length:` followed by each word, with a space before each one

### What to check

The output should be:

```text
By length: fig pear kiwi banana
```

"pear" and "kiwi" both have four letters, and "pear" came first in the list, so it stays first. That's the stability this exercise is testing. Java's `List.sort` keeps the order of values that compare as equal, so the output is the same on every run.

### Extensions

* Change the lambda so that equal lengths are ordered alphabetically, and check the output. Think about how to combine two comparisons into one lambda.
* Write the same comparison as a named method, and explain why the lambda is the better choice for a rule used only once.

---

*This exercise is not from the book. It practices the ideas in Chapter 20, with the sorting from Chapter 16.*
