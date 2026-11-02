## Hello, world

Every programming course starts with a program that prints a message. It's the smoke test of the whole setup: if this runs, your JDK and the runner are on speaking terms. This one prints two lines, so you can also see how a Java program is put together.

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
Compiling 01-hello-world...
Running Main

Hello World!
I am learning Java
— Finished with exit code 0 —
```

Java is a compiled language, which means the text in `Main.java` isn't the program that runs. The compiler, `javac`, turns it into bytecode, and the Java runtime, `java`, runs that bytecode. The runner does both in two stages, and shows you each one:

* **Compiling** is `javac` reading `Main.java` and writing the compiled class into the step's `build` folder. Compile errors are reported here, with their line numbers.
* **Running** starts `java` on the class that was compiled, and shows what the program prints.

The blank line before the program's output is there on purpose. It separates the stages from what your program prints, so the two don't run together.

At the end, the runner shows the exit code. Zero means the program finished without a problem.

### What the program is made of

Each part of the program has a job, and you'll meet every one of them again.

* `public class Main` declares a class called `Main`. The file must be called `Main.java`, because its public class has that name.
* `public static void main(String[] args)` is where the program starts. Every Java program has a `main` method, and the runtime always calls it first. The words `public static void` are the form it must have.
* `System.out.println("...")` writes the text to the output, followed by a newline. `System.out` is the standard output, and `println` means print line.
* `String[] args` holds any arguments given on the command line. This program doesn't use them.

Java is strict about the names in a program. `Main` and `main` are different names, and a class called `main` would not start the program.

---

### Quiz

*These questions are written for this course.*

````quiz
What does `javac` produce from `Main.java`?
○ A program file that runs on its own, like a .exe.
→ Not quite. Java programs run on the Java runtime, not as standalone files.
◉ Compiled bytecode, which the `java` command runs.
→ Yes. javac writes a .class file, and java runs it.
○ A text file with the output already printed.
→ Not quite. The output appears only when the program runs.
````

---

### Try it in the runner

Change the second message to `"I am learning Java!"`, and press **Run** again. The output changes, and the compile stage runs again first, because the source has changed.

---

Your first Java program has been compiled and run by the same tools that Java developers use. Each line has a reason to be there.
