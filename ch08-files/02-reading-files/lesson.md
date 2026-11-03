## Reading files

Writing a file is only useful if a program can read it back. `Files` offers two common ways to read text: all of it as one `String`, or as a list of lines. This step uses both, on the same file.

Here's the program for this step, in `src/Main.java`:

```java
Path file = Path.of("notes.txt");
Files.writeString(file, "apples\nbananas\ncherries\n");

String text = Files.readString(file);
System.out.print("Whole file:\n" + text);
```

Press **Run**. The output begins:

```text
Whole file:
apples
bananas
cherries
```

The program writes the file first, so it has something to read. `Files.readString` returns the whole file as one `String`, with the line breaks still in it. The `print` line adds the text after the label, and because the text ends with a newline, the output ends cleanly.

### Reading as lines

The second read splits the file into lines:

```java
List<String> lines = Files.readAllLines(file);
System.out.println("Line count: " + lines.size());
System.out.println("Second line: " + lines.get(1));
```

The output is:

```text
Line count: 3
Second line: bananas
```

`readAllLines` returns a `List<String>`. A `List` holds a sequence of items, and it's the Java way to hold a list whose length can change, unlike an array. The `size()` method gives the number of items, and `get(1)` returns the item at index 1. Indexes count from 0, as they do for arrays, so index 1 is the second line.

The collections chapter covers `List` in depth. For now, the important part is that `readAllLines` gives you one item for each line, with no line breaks in them.

### Reading a file that's missing

If the file doesn't exist, `readString` and `readAllLines` throw a `NoSuchFileException`. The program in this step writes the file first, which is why it runs. A real program should check with `Files.exists` before reading, as the last step showed, or handle the exception.

---

### Quiz

*These questions are written for this course.*

````quiz
Which method reads a file as one String, with its line breaks kept?
○ Files.readAllLines
→ Not quite. That returns a List with one item per line, and the breaks aren't in the items.
◉ Files.readString
→ Yes. It returns the whole file as one String, with its line breaks included.
○ Files.exists
→ Not quite. That checks whether the file is there, and doesn't read it.
````

````quiz
What is `lines.get(0)` in the program?
○ The last line of the file.
→ Not quite. Index 0 is the first item, not the last.
◉ The first line, "apples".
→ Yes. Indexes count from 0, so index 0 is the first line.
○ The whole file as one String.
→ Not quite. That's what readString returns, not the first line.
````

---

### Try it in the runner

Change the second line of the file's text to `"bananas\n\n"`, so the file has an empty line in the middle, and predict `lines.size()`. It's 4, because `readAllLines` keeps the empty line as an item. Restore the original text before you continue.

Then change `lines.get(1)` to `lines.get(3)`, and predict the result. Index 3 is past the end of a three-line list, so the program throws an `IndexOutOfBoundsException`. Restore the index before you continue.

---

Reading a file gives you text, either all at once or as a list of lines. The next step covers what happens when a file that already exists is written again, and how to add to the end of one.
