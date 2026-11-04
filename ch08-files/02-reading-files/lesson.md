## Reading files

Reading a file mirrors writing one. You open the file with a `FileReader`, wrapped in a `BufferedReader`, and read from it with `readLine`. The main difference is that a file has an end, and the loop that reads it has to notice when it reaches that end.

Here's the program for this step, in `src/Main.java`:

```java
try {
    BufferedReader in = new BufferedReader(new FileReader("data/notes.txt"));
    String line;
    int count = 0;
    while ((line = in.readLine()) != null) {
        count++;
        System.out.println(count + ": " + line);
    }
    in.close();
    System.out.println("Lines read: " + count);
} catch (IOException error) {
    System.out.println("Could not open data/notes.txt");
}
```

The program writes `data/notes.txt` before it reads it, since each run starts from a fresh copy of the step's folder. The file has three lines.

Press **Run**. The output is:

```text
1: First note: buy bread.
2: Second note: call the dentist.
3: Third note: finish the report.
Lines read: 3
```

The loop reads each line into `line`. `readLine` reads up to the end of a line, and drops the newline at the end. When it has no line left to read, it returns `null`, and the loop stops. That's the same pattern as the `while` loops from the loops chapter: the loop runs while the condition is true, and the condition here is "a line was read".

### Why the catch matters

A missing file, or one you aren't allowed to read, makes the `FileReader` throw an exception before the loop starts. The `catch` block reports it. Without the catch, the program would stop with an error, so the message is the clearer result for a missing file. A file that exists but is empty is different: the loop runs zero times, and the program prints `Lines read: 0`.

### Reading words instead of lines

`readLine` reads whole lines. To read a file one whitespace-separated word at a time, use a `Scanner` on the file, as you did with the keyboard:

```java
Scanner words = new Scanner(new File("data/notes.txt"));
while (words.hasNext()) {
    String word = words.next();
    // word holds one word at a time
}
```

The loop stops at the end of the file, because `hasNext` returns `false` there. Each `next` skips spaces and newlines, so the loop sees every word whatever the line breaks look like. Use `readLine` when the line structure matters, and `Scanner` when it doesn't.

---

### Quiz

*These questions are written for this course.*

````quiz
What does `readLine` return when the file has no more lines?
○ An empty string.
→ Not quite. An empty string would be a blank line, which is not the end of the file.
◉ null, so the while loop stops.
→ Yes. A read with no line left returns null, which ends the loop.
○ A newline character.
→ Not quite. The newline is dropped, and there's nothing left to return.
````

````quiz
Why does the program catch an exception around the file open?
○ Because reading from a file always fails the first time.
→ Not quite. Reading works when the file opens.
◉ Because a missing file must be reported, rather than letting the program stop without a message.
→ Yes. The catch block tells the reader what went wrong.
○ Because the catch moves the reading position to the start.
→ Not quite. Catching an exception doesn't move anything in the file.
````

---

### Try it in the runner

Change the file name to `data/missing.txt` in the reader, and press **Run**. The program reports that it couldn't open the file. Change the name back before you continue.

Then replace the `readLine` loop with the `Scanner` word loop from the lesson, counting words instead of lines. Predict the count for the three notes before you run it. The notes contain 4, 5, and 5 words, so the total is 14. Each word is counted with its punctuation attached, so `note:` is one word.

---

Reading and writing use the same ideas in opposite directions. The next step puts them together with a problem that catches many programmers out: what happens when a file is written over, and the new text is shorter than the old.
