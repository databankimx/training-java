## Recursion

A method that calls itself is called recursive. It sounds like a trick, but many problems have this shape: a task that's made of smaller copies of the same task. Folders are a good example. A folder contains files and other folders, and each of those folders contains files and folders in turn. To list everything under a folder, you list its entries, and for each subfolder you do the same job again, on a smaller part of the tree.

Here's the program for this step, in `src/Main.java`. The `makeSample` method builds a small tree of folders and files inside the step's folder, and `listFolder` lists it:

```java
static void listFolder(File folder, int depth) {
    File[] entries = folder.listFiles();
    Arrays.sort(entries);
    for (File entry : entries) {
        System.out.println(" ".repeat(depth * 2) + entry.getName());
        if (entry.isDirectory()) {
            listFolder(entry, depth + 1);
        }
    }
}
```

Press **Run**. The output lists every entry, with each level indented under its folder. The output is:

```text
music
  song.txt
notes.txt
photos
  beach
    wave.txt
  holiday.txt
readme.txt
```

The program sorts the entries by name before it prints them, so the listing comes out in the same order every time. A folder's entries come from the file system in no particular order, and sorting makes the output repeatable.

### What the recursion does

The method does one job: it lists the entries of one folder. For each entry, it prints the name. If the entry is a folder, the method calls itself on that folder, with `depth` one larger, to list what's inside. The call handles a smaller tree, and it finishes before the loop moves on to the next entry.

The recursion stops because the tree is finite. An ordinary file doesn't call the method, so it's the end of that branch. A folder with no subfolders gives a loop that calls nothing more. Every branch eventually reaches files, and the calls unwind back to the top.

### The depth parameter

The `depth` argument doesn't affect what gets listed. It only controls the indentation, so the output shows which folder each entry belongs to. Each call passes `depth + 1`, so entries inside a folder are indented one more step than the folder. The value is a counter that travels down the recursion, and it's the same idea as the parameters from earlier in the chapter.

### The library behind it

`java.io.File` represents a file or a folder. `listFiles` returns the entries of a folder as an array, and `isDirectory` says whether an entry is a folder. The enhanced `for` loop works on that array, just as it does on any array, so the loop visits each entry in turn.

`makeSample` uses `java.nio.file`, the newer library for files. Its methods create the folders and write the files, so the program has a tree to list. The runner rebuilds the step's folder on every run, so the sample is created again each time.

Recursion and loops often work together like this. The loop handles the entries at one level, and the method call handles each level below.

### A warning about loops

A folder can contain a link that points back to an ancestor folder, and then the recursion never reaches a file or an empty folder. The program would keep going until something fails. Real tools track which folders they've visited, to avoid this. The sample tree has no such links, so this program is safe, but keep the risk in mind for any recursion over data you don't control.

---

### Quiz

*These questions are written for this course.*

````quiz
What stops the recursion in `listFolder`?
○ A counter that reaches a fixed limit.
→ Not quite. The depth grows as the tree goes deeper, but nothing stops at a fixed limit.
◉ The tree is finite, so every branch eventually reaches files, which don't call the method.
→ Yes. Each branch ends at a file or an empty folder.
○ The method returns a value to say it's finished.
→ Not quite. listFolder returns nothing, and it stops because the tree runs out.
````

````quiz
What does the `depth` parameter change in the output?
○ Which entries are listed.
→ Not quite. Every entry is listed whatever the depth.
◉ How far each entry is indented under its folder.
→ Yes. depth only controls the indentation.
○ The order in which the files appear.
→ Not quite. The sort decides the order, not depth.
````

---

### Try it in the runner

In `makeSample`, change the file name `notes.txt` to `trip.txt`, and press **Run**. The listing shows the new name in the same place, since the entries are sorted. Then change the line that writes `wave.txt` so it writes the file into `music` instead of `photos/beach`, and predict where it appears in the listing before you run. It appears inside `music`, after `song.txt`, indented one step.

Then consider what would happen if `sample` contained a link back to `sample` itself. Describe what the recursion would do, and why the program would never finish. You don't need to build that case, since the lesson's warning explains it.

---

Recursion is a method that handles one level of a problem, and calls itself for the smaller problems inside it. Some recursive solutions are correct but wasteful, since they repeat work they've already done. That's a question of cost, and the summary and homework look at it when they come back to the chapter's examples.
