## Comments

Programs are read far more often than they're written, and most of the reading is done by people, including future you. Comments are how you leave notes for those readers. The compiler ignores every one of them, so a comment can say as much as you like without changing what the program does.

Java has two kinds of comment:

* `//` starts a comment that runs to the end of the line. It's the one you'll use most.
* `/* ... */` makes a comment that can cover several lines, or part of one.

Here's the program from the first lesson, with comments added:

```java
// Hello World, with comments added. The compiler ignores this line.
public class Main {                       // The class that holds the program
    public static void main(String[] args) {   // The start of the program
        System.out.println("Hello World!");    // println sends the text to the output
        System.out.println("I am learning Java");

        /* The program has nothing more to do, so it finishes here.
           A block comment can run over several lines. */
    }
}
```

Press **Run**. The output is the same as before:

```text
Hello World!
I am learning Java
```

Good comments explain something the code can't say for itself. A comment such as `// print hello` repeats the code in English, and it adds nothing. A comment such as `// Show the greeting before the prompt, so the user knows what to type` tells you why, and that's the part you'll need later.

The `public static void main` line is a fair example of a line that's worth a note. A comment that says what the method is for is more useful than one that says what the keywords mean, since the keywords are the same in every program.

Comments can also be useful while you test. `/* ... */` lets you switch off part of a program without deleting it, so you can bring it back when you need it.

---

### Try it in the runner

Put `/*` in front of the second `println` line, and `*/` after it, so that line is inside a comment. Press **Run**. The second line of output disappears, because the compiler doesn't see that line at all. Remove the markers, and the line comes back.

---

Comments cost you a few seconds when you write them, and they save a lot of time when someone, including you a month from now, has to read the code. Write them as you go, while the reason is still fresh.
