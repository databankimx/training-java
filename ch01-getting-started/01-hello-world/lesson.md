## Hello, world

Every programming course starts with a program that prints a message. It's the smoke test of the whole setup: if this runs, your compiler, your build tools, and the runner are all on speaking terms. This one prints two lines, so you can also see how a Java program is put together.

Here's the program, in `src/Main.java`:

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("I am learning Java");
    }
}
```

Press **Run**, or press F5. The Output pane should look something like this:

```text
Compiling Main...
Running Main

Hello World!
I am learning Java
— Finished with exit code 0 —
```

The first lines are the important ones to understand. Java is a compiled language, which means the text in `Main.java` isn't run directly. The compiler turns it into bytecode, and the Java runtime runs that bytecode. Running the program happens in two stages, and the runner shows you each one:

* **Compiling** is the Java compiler, `javac`, reading `Main.java` and producing a class file. If the text has an error, the compiler stops here, and the runner shows the error.
* **Running** starts the Java runtime with the class file, which calls the program's `main` method.

The blank line before the program's output is there on purpose. It separates the compiler and runner messages from what your program prints, so the two don't run together.

At the end, the runner shows the exit code. Zero means the program finished without a problem.

### What the program is made of

Each part of the program has a job, and you'll meet every one of them again.

* `public class Main` declares a class called `Main`. The file's name must match the public class's name, which is why the file is `Main.java`.
* `public static void main(String[] args)` is where the program starts. Every Java program has a `main` method like this, and the runtime always calls it first.
* `System.out.println("...")` writes text to the output, and then moves to a new line. `System` is a class from the standard library, and `out` is the output stream that the runner shows.
* Every statement ends with a semicolon, and the braces group the class and the method bodies.

The name `main` and its parameter list must be written exactly as shown, since the runtime looks for them by name. The `args` parameter holds the words typed after the program's name, which the command-line arguments chapter covers.

---

### Quiz

*These questions are written for this course.*

````quiz
Which class holds the `out` stream used by `System.out.println`?
○ Console
→ Not quite. There is no Console class in this program, and the output stream belongs to System.
◉ System
→ Yes. System is part of the standard library, and it provides the out stream.
○ Output
→ Not quite. Output isn't a class in the standard library.
````

---

### Try it in the runner

Replace the second `System.out.println` with `System.out.print`, and press **Run**. The output changes: the second line no longer ends with a newline, so the runner's closing line follows it on the same line. Put the `println` back before you continue.

---

And that's your first Java program, compiled and run by the same tools that the professionals use. Nothing about it was magic, and every line had a reason to be there.
