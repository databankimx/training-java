## Summary: Getting started

This chapter covered the smallest complete Java program, and the tools that compile and run it.

* **A program starts in `main`.** Every program has a `public static void main(String[] args)` method, and the runtime calls it first.
* **`System.out.println` writes output.** It prints its text and moves to a new line. `System.out.print` prints without the new line.
* **Imports name classes from other packages.** `java.lang` is imported automatically. Other packages need an `import`, or a full name that includes the package.
* **Comments are notes for readers.** `//` runs to the end of the line, and `/* ... */` can cover several lines. The compiler ignores both.
* **A class is in a file with its name.** `public class Main` is in `Main.java`.
* **A run has two stages.** The runner compiles the source with `javac`, and then runs the program with `java`. Knowing which stage failed tells you where to look.

### Check your understanding

*These questions are written for this course.*

````quiz
Which method is the program's starting point?
○ start
→ Not quite. Java doesn't look for a method with that name.
◉ main
→ Yes. The runtime calls main first, and it must have the signature shown in the lesson.
○ run
→ Not quite. The runtime doesn't look for a method called run.
````

````quiz
Which line is a comment?
◉ // Print the greeting before the prompt
→ Yes. Everything after // on that line is ignored by the compiler.
○ System.out.println("Hello");
→ Not quite. That line is code, and the compiler runs it.
○ import java.util.ArrayList;
→ Not quite. An import is a statement, and it tells the compiler what a name refers to.
````

````quiz
When the Output pane shows "Compiling", which stage is it?
○ Running the program.
→ Not quite. That's the stage that shows the program's own output.
◉ Compiling the source into a class file.
→ Yes. The compiler turns the source into bytecode in the compiling stage.
○ Saving the file.
→ Not quite. The runner doesn't save anything when you press Run.
````
