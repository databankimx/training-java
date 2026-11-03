## Summary: Files

This chapter covered keeping data in a file, and reading it back.

* **`Path` names a location.** A relative path is resolved against the folder the program starts in.
* **`Files.writeString` writes text.** It creates the file when it's missing, and replaces its contents by default.
* **`APPEND` adds to the end.** Name it with `CREATE` when the file might not exist yet.
* **`Files.readString` reads the whole file.** The line breaks stay in the text.
* **`Files.readAllLines` reads one item per line.** It returns a `List<String>`, and `get` and `size` work on it.
* **`Files.exists` checks before you use a file.** It returns a `boolean` for an `if`.
* **File operations are checked.** `IOException` must be handled or passed on with `throws`.
* **The runner rebuilds the build folder.** A file the program writes is removed on the next run of that step.

### Check your understanding

*These questions are written for this course.*

````quiz
Which call writes text to the end of an existing file, creating it if it's missing?
○ Files.writeString(path, text)
→ Not quite. With no options, the default replaces the file.
◉ Files.writeString(path, text, StandardOpenOption.CREATE, StandardOpenOption.APPEND)
→ Yes. CREATE makes the file if needed, and APPEND adds to its end.
○ Files.readString(path)
→ Not quite. That reads the file, and doesn't write to it.
````

````quiz
What does `Files.readAllLines` return?
○ One String with the whole file in it.
→ Not quite. That's what readString returns.
◉ A List of Strings, one for each line.
→ Yes. Each item is one line, without its line break.
○ The number of lines in the file.
→ Not quite. That's the size of the list, which you get by calling size().
````

````quiz
Why does `main` declare `throws IOException` in a file program?
○ Because file operations can fail, and Java requires the failure to be handled or passed on.
→ Yes. IOException is a checked exception, so the method must deal with it or declare it.
○ Because the file is written in a separate thread.
→ Not quite. Threads have no part in this.
○ Because it's optional, and makes the program run faster.
→ Not quite. The declaration is required, and it doesn't affect speed.
````
