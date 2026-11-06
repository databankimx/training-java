## Homework: Word count

Write a program that counts the lines and words in a text file.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own file.

The program writes its own sample file, `data/essay.txt`, since each run starts from a fresh copy of the step's folder. It should:

* open `data/essay.txt`, and count its lines with `readLine`
* open the file again, and count its words with a `Scanner`, which reads one whitespace-separated word at a time
* print the two counts, as `Lines: 3` and then `Words: 11`

The file has to be opened twice, since the first pass reads it to the end. Reopening it is simpler than moving back to the start.

### What to check

For the sample file, the output should be:

```text
Lines: 3
Words: 11
```

Count the words yourself before you run the program. A word is a run of characters between spaces, so `dog.` counts as one word, and `The` at the start of the last line counts too.

### Extensions

* Count the characters as well, by reading the file as a stream of characters. Decide whether the newlines should be counted, and say why.
* Print the number of words on each line, beside the line number. This needs the two counts to be made in the same pass, so think about how you'd split the line into words. You'll meet a better tool for that later in the course.

---

*This exercise is not from the book. It practices the ideas in Chapter 8.*
