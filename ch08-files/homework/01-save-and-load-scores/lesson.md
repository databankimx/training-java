## Homework: Save and load scores

Write four scores to a file, one per line, then read the file back and add the scores up. The file is the only link between the two halves of the program, so this exercise checks that the read matches what was written.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own scores.

The program should:

* write the scores 72, 95, 88, and 61 to a file called `scores.txt`, one per line
* read the file back as a list of lines
* convert each line to an `int`, and add it to a total
* print `Scores read: ` followed by the number of lines, and `Total: ` followed by the total

### Where to change the code

The scores are written as one `String`, with each score followed by a line break, so the file has one score per line. To use different scores, change that text. The reading part doesn't change, because it reads whatever lines the file contains.

### What to check

The output should be:

```text
Scores read: 4
Total: 316
```

The total is 72 plus 95 plus 88 plus 61, which is 316.

### Extensions

* Add a fifth score to the text, and check that the count and the total both change.
* Print the average, using a `double` total divided by the count, and `printf` with two places after the point.

---

*This exercise is not from the book. It practices the ideas in Chapter 8.*
