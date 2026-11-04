## Homework: Visit log

Write a program that adds a line to a log file each time it runs, and then prints the whole log. Over several runs, the log should keep growing.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own log.

The program should:

* append a line that reads `visit: first` to `visits.log`, creating the file if it's missing
* append a second line that reads `visit: second`
* print the whole log

### Run it outside the runner

The runner rebuilds each step's `build` folder on every run, so inside the runner the log holds only the two lines from that run. To see the log grow, run the program from a folder that isn't rebuilt. Compile it once, with `javac -d . src\Main.java`, and then run `java Main` from the same folder each time. Each run adds two lines, and the log keeps every one.

### What to check

The first run prints:

```text
visit: first
visit: second
```

The second run prints four lines: the two from the first run, and two more. The log keeps growing because both writes use `APPEND`.

### Extensions

* Add a line with the current time, using `java.time.LocalDateTime.now()`, so each visit has a time stamp.
* Print only the last three lines of the log, using the `readAllLines` method from the reading lesson, and a `List` index.

---

*This exercise is not from the book. It practices the ideas in Chapter 8.*
