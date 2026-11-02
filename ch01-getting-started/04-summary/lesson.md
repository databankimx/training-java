## Summary: Getting started

This chapter covered the tools, and the shape of a Java program.

* **The JDK has two parts.** `javac` compiles source into bytecode, and `java` runs it.
* **The runner works in two stages.** Compiling reports any errors, and running shows what the program prints.
* **A class's name must match its file.** A public class `Main` lives in `Main.java`.
* **Execution starts in `main`.** The runtime calls `public static void main(String[] args)` first.
* **`System.out.println` prints a line.** `System` comes from `java.lang`, which Java imports for every program.
* **Other packages need an import.** `import java.util.List;` tells the compiler where `List` lives.
* **Comments are for readers.** `//` for a line, `/* */` for a block, and `/** */` for documentation read by javadoc.
* **Comments are not checked.** Keep them in step with the code they describe.

### Check your understanding

*These questions are written for this course.*

````quiz
What happens when you press Run on a Java step?
○ The source is run directly, with no compile step.
→ Not quite. Java source is always compiled first.
◉ The source is compiled with javac, and the compiled class is run with java.
→ Yes. The two stages match the two lines the Output pane shows.
○ The program is copied to a folder and started from there.
→ Not quite. The runner compiles the program and runs the compiled class.
````

````quiz
Which name must match the file name for a public class?
○ The method name.
→ Not quite. The method name is main, and it doesn't need to match the file.
◉ The class name.
→ Yes. A public class must be declared in a file with the same name.
○ The name of the folder.
→ Not quite. The file name matters, not the folder.
````

````quiz
Why does `List` need an import, when `System` doesn't?
○ Because List is a newer class.
→ Not quite. Age has nothing to do with it.
◉ Because `System` is in java.lang, which Java imports automatically, and `List` is in java.util.
→ Yes. Only java.lang is imported for you.
○ Because `System` is a keyword.
→ Not quite. System is a class, and it isn't a keyword.
````

---

The next chapter covers the variables a program uses to hold values, and reading input from the user.
