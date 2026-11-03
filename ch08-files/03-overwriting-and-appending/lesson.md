## Overwriting and appending

What happens when you write to a file that already exists depends on the options you give. The default replaces the old contents, and an option changes that to add to the end.

Here's the program for this step, in `src/Main.java`:

```java
Path log = Path.of("log.txt");
Files.writeString(log, "first entry\n");
Files.writeString(log, "second entry\n");
System.out.print("After overwrite:\n" + Files.readString(log));
```

Press **Run**. The output begins:

```text
After overwrite:
second entry
```

The first write created the file with `first entry`. The second write replaced the whole file with `second entry`. Nothing from the first write is left, because `writeString` by default truncates the file to empty before it writes.

### Appending

To add to the end of a file, name the append option:

```java
Files.writeString(log, "third entry\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
System.out.print("After append:\n" + Files.readString(log));
```

The output is:

```text
After append:
second entry
third entry
```

`StandardOpenOption.APPEND` keeps the existing contents and writes after them. `CREATE` makes the file if it's missing, so the same line works the first time a log is written, as well as after that. Without `CREATE`, appending to a missing file throws a `NoSuchFileException`.

### Why the options are named

The options are explicit because a write can do several things, and the choice matters. Overwriting loses the old contents, and appending keeps them. Naming the option in the code tells the reader which one happens, and it protects the file from being replaced by accident.

A log is the usual case for appending: each run adds a line to a record that must survive. A file that holds a configuration is usually overwritten.

---

### Quiz

*These questions are written for this course.*

````quiz
What happens when `Files.writeString` writes to a file that already has text, with no options given?
○ The new text is added after the old.
→ Not quite. That's what the APPEND option does. The default replaces the file.
◉ The old contents are replaced by the new text.
→ Yes. The default truncates the file, so the old text is removed.
○ The write fails, because the file already exists.
→ Not quite. Writing to an existing file replaces it, and doesn't fail.
````

````quiz
Which option keeps the existing text and adds to the end?
○ StandardOpenOption.CREATE
→ Not quite. CREATE only makes the file if it's missing.
◉ StandardOpenOption.APPEND
→ Yes. APPEND writes after the existing contents.
○ StandardOpenOption.READ
→ Not quite. READ is for reading, not for writing to the end.
````

---

### Try it in the runner

Remove `StandardOpenOption.CREATE,` from the third write, leaving only `StandardOpenOption.APPEND`, and press **Run**. Predict the result. The file already exists from the earlier writes, so the append still works, and the output is the same. Restore `CREATE` before you continue.

Then remove `StandardOpenOption.CREATE` from the third write, and change its file name to `Path.of("other.txt")`, which doesn't exist. Predict the result before you press Run. Without `CREATE`, appending to a missing file throws a `NoSuchFileException`, and the program stops before the `After append` line. Restore the original write before you continue.

---

Writing replaces a file by default, and `APPEND` keeps what was there. The summary brings the chapter together, and the homework writes and reads a file of your own.
