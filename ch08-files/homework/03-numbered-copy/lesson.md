## Homework: Numbered copy

Write a program that reads a text file, and writes a copy of it with each line numbered.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own file.

The program writes its own input file, `data/input.txt`, since each run starts from a fresh copy of the step's folder. It should:

* open `data/input.txt` for reading, and report a failure if it can't
* open `data/numbered.txt` for writing, and report a failure if it can't
* read each line, and write it to the new file with its number and a full stop before it, such as `1. apples`
* print `Wrote 3 lines to data/numbered.txt` when it's done, with the correct count

Close both files before the program finishes. Use `readLine` for the reading loop, as the reading-files lesson showed. A `try` block that declares both files closes them automatically.

### What to check

Press **Run**. The View button should appear over the Output pane, and opening it should show:

```text
1. apples
2. bananas
3. cherries
```

The count in the last line should match the number of lines in the input. If the numbers are off by one, check where the counter increases in the loop.

### Extensions

* Skip empty lines in the input, but still count only the lines that are written. Think about where the check goes, so that an empty line isn't numbered.
* Print a message and stop when either file fails to open. Test it by changing the input file's name to one that doesn't exist.

---

*This exercise is not from the book. It practices the ideas in Chapter 8.*
