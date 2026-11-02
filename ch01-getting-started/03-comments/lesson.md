## Comments

A comment is text the compiler ignores. It's there for the people who read the code, including you in six months. Java has three kinds, and this program uses each one.

```java
/**
 * A program that prints a greeting.
 *
 * This is a documentation comment. Tools such as javadoc read it to build the program's reference pages.
 */
public class Main {
    public static void main(String[] args) {
        // A line comment runs from the two slashes to the end of the line.
        System.out.println("Hello World!");

        /* A block comment can cover several lines,
           and the compiler ignores all of it. */
        System.out.println("I am learning Java");
    }
}
```

Press **Run**. The output is the same as the first program:

```text
Hello World!
I am learning Java
```

### The three kinds

* **Line comments** start with `//`, and run to the end of the line. Use them for a short note on the line they sit beside, or above.
* **Block comments** start with `/*` and end with `*/`, and can run over several lines. Use them for a longer note, or to switch off a few lines while you test.
* **Documentation comments** start with `/**` and end with `*/`, and sit directly above a class, a method, or a field. The tool `javadoc` reads them to build a reference page. They're the comments to write for anyone who will use your code, rather than for yourself.

### What to write

A comment should say why the code does something, or what a reader needs to know that the code doesn't show. It shouldn't repeat what the code already says. `// print hello` above `System.out.println("Hello")` adds nothing, while `// the greeting is printed before the course name, as the course asks` says something useful.

### Comments are not checked

The compiler doesn't check what a comment says. A comment that is out of date is worse than no comment, because a reader trusts it. When you change the code, change the comment above it at the same time.

---

### Quiz

*These questions are written for this course.*

````quiz
Which comment is the right kind for describing a class to someone who will use it?
○ A line comment, starting with //.
→ Not quite. A line comment is for a short note on one line, and it isn't read by documentation tools.
◉ A documentation comment, starting with /**, above the class.
→ Yes. javadoc reads these, so they describe the class for the people who use it.
○ A block comment that is later removed.
→ Not quite. Block comments are for longer notes or for switching off code, not for the reference description.
````

---

### Try it in the runner

Put `//` in front of the `System.out.println("Hello World!");` line, and press **Run**. Only the second line prints, because the compiler ignores the commented-out line. Remove the `//` before you continue.

---

Comments change nothing the program does. They change how easily someone else can understand it.
