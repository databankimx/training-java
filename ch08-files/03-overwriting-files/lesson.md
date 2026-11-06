## Overwriting files

The last two steps wrote a file and read one back, and they seemed to work. This step shows a mistake that's easy to make, and that gives wrong results without any error message.

Here's the program for this step, in `src/Main.java`. It writes a long line, then writes a short line over it:

```java
try (PrintWriter out = new PrintWriter("data/test.txt")) {
    out.print("This first version of the text is quite long.");
}

try (RandomAccessFile file = new RandomAccessFile("data/test.txt", "rw")) {
    file.writeBytes("Short text.");
}

System.out.println(Files.readString(Path.of("data/test.txt")));
```

Before you press **Run**, predict the output. The program then reads the file back and prints it. Press **Run** and check your prediction. The output is:

```text
Short text.version of the text is quite long.
```

Only the first eleven characters were replaced, and the rest of the original line survived. Nothing reported an error, because nothing went wrong in the sense an exception reports. The file did exactly what it was asked to: it wrote the new text at the start of the file, and it didn't clear what came after.

### Why it happens

`RandomAccessFile` opens an existing file for reading and writing, and it leaves the existing contents in place. Writes start at the beginning of the file, and they overwrite whatever bytes are there. If the new text is shorter than the old, the leftover bytes remain at the end.

The fix is to say, when you open the file, that its old contents should be discarded. A `PrintWriter` does this by default, because it's made for writing a new file, and it empties the file when it opens. You can also clear a `RandomAccessFile` yourself with `setLength(0)`, which the next step's Try shows. Whichever you use, the rule is the same: open for writing only when you intend to replace the whole file.

### The block and closing

The first part of the program, in its own `try` block, ends with `out` being closed. The file closes at the closing brace, before the program reads it back. That's the same automatic closing the writing step described. Without the block, `out` would stay open until the writer's buffer was flushed, and the reading code could see the file before its last text was written.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does `RandomAccessFile` in this program leave the rest of the old text in place?
○ Because it always appends to the end of the file.
→ Not quite. It writes from the start of the file, not the end.
◉ Because it opens the existing file without clearing it, so only the bytes written are replaced.
→ Yes. Writes overwrite from the start, and anything beyond the new text stays.
○ Because the file is locked while it's open.
→ Not quite. Locking has nothing to do with the leftover text.
````

````quiz
Which way of writing a file replaces its whole contents?
○ RandomAccessFile, opened with "rw", and no other calls.
→ Not quite. It keeps the existing contents, so leftover text survives.
◉ PrintWriter, which empties the file when it opens.
→ Yes. A PrintWriter discards the old contents when it opens the file.
○ FileReader, which reads the file first.
→ Not quite. FileReader reads, and can't write at all.
````

---

### Try it in the runner

Change `RandomAccessFile` to `PrintWriter` in the second block, using `out.print("Short text.")` in place of `file.writeBytes`, and predict the output before you press **Run**. The new text replaces the whole file, so the output is `Short text.` and nothing more.

Then go back to `RandomAccessFile`, and add `file.setLength(0);` before `file.writeBytes`. Predict the output. It's `Short text.`, since `setLength(0)` empties the file before the new text is written.

---

Writing, reading, and overwriting cover the basics of file access. The summary and homework put them together, and the next chapter adds exceptions, which is the other standard way to report a file that won't open.
