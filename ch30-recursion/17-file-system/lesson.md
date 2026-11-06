## Recursion on the file system

A folder on your computer is a tree. Folders are branches, and files are leaves. To list everything inside a folder, or to add up the size of a folder, you have to visit every branch and every leaf. This is a natural job for recursion.

### The pattern

The directory tree has the same shape as the nested lists from the traversal step:

* If an item is a file, it is a base case: there is nothing more to visit.
* If an item is a folder, the same method is called on that folder.

### Listing a folder

`listDirectory` prints every item in a folder. When an item is a folder, the method calls itself on it, one level deeper, so the output is indented to show the tree:

```java
static void listDirectory(File folder, int depth) {
    File[] items = folder.listFiles();
    // ...
    for (File item : items) {
        System.out.println("  ".repeat(depth) + name + (isFolder ? "/" : ""));
        if (SKIP.contains(name)) {
            continue;
        }
        if (isFolder && !Files.isSymbolicLink(item.toPath())) {
            listDirectory(item, depth + 1);
        }
    }
}
```

Two checks make this safe:

* `isDirectory` separates the branches from the leaves.
* The `SKIP` set leaves out folders that aren't part of the project, such as the compiled `classes` folder. Skipping a folder limits the recursion, the same way the Python version skips `.venv`.

A symbolic link is a file that points somewhere else. A link can point back up the tree, which would make the recursion run forever. The program lists a link but never follows it, using `Files.isSymbolicLink`. The Python lesson makes the same point about links.

If `listFiles` returns `null`, the folder couldn't be read, for example because the user doesn't have permission. The program reports it and carries on, so one locked folder doesn't stop the whole listing.

### Measuring disk usage

`diskUsage` adds up the size of a file or folder. For a file, the size is its own length. For a folder, the size is the total of everything inside it, found by calling the method again on each item:

```java
static long diskUsage(File item) {
    long total = item.length();
    if (item.isDirectory() && !Files.isSymbolicLink(item.toPath())) {
        File[] children = item.listFiles();
        if (children != null) {
            for (File child : children) {
                if (!SKIP.contains(child.getName())) {
                    total += diskUsage(child);
                }
            }
        }
    }
    return total;
}
```

The skip list applies here too. Without it, the total would include the compiled classes, which are not part of the project's own size.

### Running it

The program starts from the folder it runs in, so the listing depends on where it runs. The output has this form. The exact names and sizes depend on your computer:

```text
build/
  src/
    Main.java
  commands.txt

Disk usage: 48,213 bytes
```

Each name is shown under its folder, indented one level more than the folder above it. The total is the last line, and it includes everything inside the folder, apart from the skipped ones.

### Try it yourself

Change `user.dir` to a different folder, such as your Documents folder, and run the program. The listing may take a while for a large folder. For a folder you don't want to list in full, add its name to `SKIP`. The skip set is where the recursion is limited.

---

### Quiz

*These questions are written for this course.*

````quiz
What is the base case when the program adds up disk usage?
○ A folder with no files in it.
→ Not quite. A folder with no files still has a size, and its children are handled by the same method.
◉ A file, since a file has no contents to visit.
→ Yes. A file's own length is the answer, with nothing to recurse into.
○ A symbolic link.
→ Not quite. A link is not followed, but that's a separate rule from the base case.
````

````quiz
Why does the program not follow a symbolic link?
○ Because links cannot be read.
→ Not quite. A link can be read, and the problem is where it leads.
◉ Because a link can point back up the tree, and following it would make the recursion run forever.
→ Yes. A cycle in the tree would never reach a base case.
○ Because links are always large.
→ Not quite. The size of a link is not the reason it isn't followed.
````

---

### Try it in the runner

Add `"build"` to the skip set, and predict the disk usage total before you press **Run**. It's smaller, since the build folder and everything in it are no longer counted. Then predict whether the listing still shows the `build/` folder. It does, since it is listed, but its contents are not.

---

Recursion matches the shape of a folder tree: files stop the recursion, and folders continue it. This is the last lesson of the chapter, and the summary follows.
