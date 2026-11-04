## Summary: Files

This chapter covered reading and writing files with the classes in `java.io` and `java.nio.file`.

* **`java.io` provides the file reader and writer classes.** `PrintWriter` writes text, `BufferedReader` reads lines, and `Scanner` reads words.
* **File classes work like `System.out` and `Scanner` on the keyboard.** The `print`, `println`, `readLine`, and `next` methods apply to a file the same way they apply to the console.
* **Handle a failed open.** A missing file or a permission problem throws an `IOException`, which the program must catch or declare.
* **A read loop stops at the end of the file.** `while ((line = in.readLine()) != null)` runs while lines remain.
* **Closing flushes and releases the file.** A file closes at `close()`, or at the end of a `try` block that declares it as a resource.
* **`RandomAccessFile` keeps existing contents.** Writing a shorter text over a longer file leaves the leftover text behind. Use `PrintWriter`, or call `setLength(0)`, to replace the whole file.

### Check your understanding

*These questions are written for this course.*

````quiz
Which class should you use to replace the entire contents of a file?
○ BufferedReader
→ Not quite. BufferedReader reads a file, and can't write to it.
◉ PrintWriter
→ Yes. A PrintWriter empties the file when it opens, so the new text replaces everything.
○ RandomAccessFile, opened with "rw", and no other calls
→ Not quite. It keeps the old contents, so leftover text can remain.
````

````quiz
A file read loop `while ((line = in.readLine()) != null)` stops when what happens?
○ The first empty line is read.
→ Not quite. An empty line is a line, and the loop continues past it.
◉ There are no more lines to read, so readLine returns null.
→ Yes. The loop condition is false once the end of the file is reached.
○ The file is closed by the loop.
→ Not quite. The loop reads, and the file stays open until it's closed.
````

````quiz
Why does the program catch an exception when opening a file for reading?
○ Because reading always fails the first time.
→ Not quite. Reading works when the file opens.
◉ So that a missing file is reported, rather than the program stopping without a message.
→ Yes. The catch block tells the reader what went wrong.
○ To move the reading position to the start.
→ Not quite. Catching an exception doesn't change where the file is read from.
````
