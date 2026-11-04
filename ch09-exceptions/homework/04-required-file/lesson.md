## Homework: Required file

Write a program that reads a file the program needs. If the file can't be opened, the reading method throws an exception, and `main` reports the problem.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program writes its own settings file, `data/settings.txt`, since each run starts from a fresh copy of the step's folder. It should:

* define `printFile`, which takes a file name as a `String`, opens the file, and throws a `RuntimeException` with the message `could not open <name>` when it can't
* print each line of the file when it opens, using `readLine`
* in `main`, call `printFile` on `data/settings.txt` inside a `try` block
* catch the exception, and print its message with `getMessage()`

### What to check

Run the program as it is. The two lines of the settings file should appear, and no error should be reported.

Then change the file name to `data/missing.txt`, and run again. The output should be `could not open data/missing.txt`, and the program should end normally.

### Extensions

* Throw an `IllegalArgumentException` instead for an empty file name, and check the name before opening it. Decide whether an empty name is a mistake in the program, or a problem with the data. That decides the class.
* Call `printFile` for two files, one after the other, so that a failure in the first doesn't stop the second. Think about where the `try` block has to go.

---

*This exercise is not from the book. It practices the ideas in Chapter 9, and it uses the file reading from Chapter 8.*
