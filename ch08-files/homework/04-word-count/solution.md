## Homework: Word count: walkthrough

The lines are counted with `readLine`, which returns one line at a time until it returns `null`. The words are counted with a `Scanner`, whose `hasNext` and `next` skip spaces and newlines, so the line breaks don't matter.

The file is opened twice: once for the lines and once for the words. The first reader is closed at the end of its `try` block before the second one opens, so the second pass starts from the top of the file.

For the sample, the first line has 4 words, the second has 5, and the third has 2, a total of 11, which matches the expected output.
