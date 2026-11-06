## Writing files

Every program so far has written to the screen and read from the keyboard. A file is a third place for data, and it's the one that outlives the program. The standard library handles files with writer classes from the `java.io` package, and writing to one looks a lot like writing to `System.out`.

Here's the program for this step, in `src/Main.java`:

```java
Files.createDirectories(Path.of("data"));
try {
    PrintWriter out = new PrintWriter("data/greetings.txt");
    out.print("Hello, file!\n");
    out.print("Writing to a file works like System.out.\n");
    out.close();
    System.out.println("Wrote data/greetings.txt");
} catch (IOException error) {
    System.out.println("Could not create the file.");
}
```

Press **Run**. The program prints one line, `Wrote data/greetings.txt`. The program doesn't print the file's contents. A **View** button appears over the bottom right of the Output pane, named for the file. Press it to open the file in the default application for text files.

### The output stream

`PrintWriter` is the class for writing text to a file. You create one with the file's path, and that opens the file for writing. Its `print` method works just as `System.out.print` does, so you can send text and numbers to the file in the same way.

The path is relative to the folder the program runs from. The runner runs each step in a copy of its folder, so `data/greetings.txt` refers to the `data` folder inside that copy. The first line of `main` creates that folder, since the copy starts without it.

### Checking that the file opened

Opening a file can fail. The folder might not exist, the file might be locked, or you might not have permission to write it. In Java, a failed open throws an exception, rather than leaving the stream in a bad state. The `catch` block handles that case, and prints a message instead of letting the program carry on as if the writes had worked.

The `catch` names `IOException`, which is the family of exceptions for input and output. The exceptions chapter explains why the compiler makes the program deal with it.

### Closing the file

The program calls `out.close()` when it's finished writing. Closing flushes any text still waiting in the writer's buffer, and releases the file so other programs can use it. A file that isn't closed may not contain all of its text, and that's a bug that's hard to see.

There's a safer habit. A writer declared in the header of a `try` with resources is closed automatically when the block ends, even if an exception is thrown. You'll see this in the third step of this chapter, and the exceptions chapter introduced the same pattern.

---

### Quiz

*These questions are written for this course.*

````quiz
Which package provides the `PrintWriter` class for writing to a file?
○ java.lang
→ Not quite. java.lang has String and System, but not the file writers.
◉ java.io
→ Yes. java.io provides the classes for reading and writing streams and files.
○ java.util
→ Not quite. java.util holds collections and helpers, not file writers.
````

````quiz
Why does the program put the file open inside a `try` block?
○ Because the file must be written in a background thread.
→ Not quite. The try block is about failure, not threads.
◉ Because the file might not open, and the failure must be caught and reported.
→ Yes. The open can throw IOException, and the catch block reports it.
○ Because PrintWriter objects are always empty.
→ Not quite. The try block handles a failed open, not the contents of the writer.
````

---

### Try it in the runner

Change the path to `data/missing/greetings.txt`, where `missing` is a folder that the program doesn't create, and press **Run**. The program reports that it couldn't create the file, and it doesn't write anything. Change the path back before you continue.

Then remove the `out.close();` line, and run again. The text may be missing from the file, because it stays in the writer's buffer until the writer is closed or flushed. Put the line back, since closing explicitly makes the point where the file is finished easy to see.

---

Writing a file is an ordinary output operation with a path attached. The next step reads one back, and shows how the same idea works in the other direction.
