## Homework: Read with cleanup

The program writes a file with two readings, then reads three lines from it. The file has only two, so the third read has nothing to return. This exercise shows what the program does with that, and how the cleanup runs.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own file.

The program should:

* write the file `readings.txt` with two lines, `21.5` and `22.0`
* open it with try-with-resources, and read three lines with `readLine`
* print `Readings: ` followed by the three values, separated by commas
* print `Reader closed.` from a `finally` block, after the resource block has ended

### Where to change the code

The three `readLine` calls are the lines to change. Reading fewer lines, or more, changes the output. Reading past the end of the file gives `null` rather than an exception, so the output shows `null` for the missing line.

### What to check

The output should be:

```text
Readings: 21.5, 22.0, null
Reader closed.
```

The third reading is `null`, because the file ends after the second line. `readLine` returns `null` at the end of a file, which is the usual signal, not an error. The reader is closed by the try-with-resources before the `finally` runs, so `Reader closed.` is printed last.

### Extensions

* Use a `while` loop with `readLine`, reading until it returns `null`, rather than three fixed calls. Check that the output lists both readings and no `null`.
* Change the file to hold a line that isn't a number, and convert the readings with `Double.parseDouble` inside a `try`, as the safe-sum exercise did.

---

*This exercise is not from the book. It practices the ideas in Chapter 9.*
