## Writing files

A file keeps its contents after the program ends. Java writes one with the `Files` class, which lives in `java.nio.file`, so this step's program starts with two imports.

Here's the program for this step, in `src/Main.java`:

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        Path file = Path.of("greeting.txt");
        Files.writeString(file, "Hello, file.\nThis line was written by Java.\n");
        System.out.println("Wrote " + file.toAbsolutePath().getFileName());
        System.out.println("Exists: " + Files.exists(file));
    }
}
```

Press **Run**. The output is:

```text
Wrote greeting.txt
Exists: true
```

`Path.of("greeting.txt")` names the file. A `Path` is a location, and it doesn't create anything by itself. `Files.writeString` creates the file if it's missing and writes the text. The `\n` characters start new lines inside the text.

### Where the file goes

A relative path such as `greeting.txt` is resolved against the folder the program is started from. The runner starts each step's program in the step's `build` folder, so this file is created there. You can find it under the step's `build` folder after the run. The runner rebuilds that folder on every run, so the file is removed the next time the step runs. A file that must last longer than one run is written when the program is started from a folder you choose, which is how a real program would be used.

### Checked exceptions

The `main` method has `throws IOException` after its parameters. Writing a file can fail for reasons outside the program's control: the disk is full, the folder is read-only, or the file is locked. Java makes you deal with such failures in the code, and the `throws` clause says this method passes the failure to its caller. If it's thrown and nothing catches it, the runner shows it with its stack trace.

The next part of the course, on exceptions, shows how to handle these failures where they happen, rather than passing them on. For now, `throws` is the honest choice, and it keeps the file operations short.

### Checking the result

The last line checks that the file exists with `Files.exists`. It returns a `boolean`, so the decisions chapter's `if` can use it. A program that needs a file should check first, rather than assume it's there.

---

### Quiz

*These questions are written for this course.*

````quiz
Where does `Path.of("greeting.txt")` point, when the program is run by the runner?
○ The folder that contains the source file.
→ Not quite. The runner starts the program in the step's build folder, not in src.
◉ The step's build folder, where the runner starts the program.
→ Yes. A relative path is resolved against the folder the program starts in.
○ The root of the C drive.
→ Not quite. A relative path doesn't start from the root.
````

````quiz
Why does `main` declare `throws IOException`?
○ Because every Java program must declare it.
→ Not quite. Only methods that can fail with I/O errors need it.
◉ Because writing a file can fail, and Java requires the failure to be handled or passed on.
→ Yes. IOException is a checked exception, so the method must say what it does with it.
○ Because the file is written in a background thread.
→ Not quite. Threads have nothing to do with this declaration.
````

---

### Try it in the runner

Change the file name to `"notes/greeting.txt"`, and press **Run**. Predict the result. The folder `notes` doesn't exist in the build folder, so `Files.writeString` throws a `NoSuchFileException`, and the program stops. Restore the original name before you continue.

Then change the output line to print `file.toAbsolutePath()`, without `getFileName()`, and predict the output. It prints the full path, which ends in the step's `build` folder.

---

Writing a file needs a path and some text, and the operation can fail, which is why `main` declares the exception. The next step reads the file back.
