## Imports

The last program used `System.out`, and it didn't need an import. The reason is that `System` belongs to `java.lang`, a package that Java imports into every program automatically. Most of the standard library is not like that. Its classes live in other packages, and a program must name them before it uses them.

This program uses `List`, which is in `java.util`, so it starts with an import:

```java
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> greetings = List.of("Hello World!", "I am learning Java");
        for (String greeting : greetings) {
            System.out.println(greeting);
        }
    }
}
```

Press **Run**. The output is the same two lines as the last step:

```text
Hello World!
I am learning Java
```

### What an import does

`import java.util.List;` tells the compiler that the name `List` means `java.util.List`. The name in full is the package, followed by the class. Without the import, the compiler reports that it cannot find the symbol `List`, and the error names the line where the class is used.

Every class has a full name made of its package and its own name. `java.util.List` is the list class in the `java.util` package. An import saves writing that full name every time.

You can also import every class in a package with a star, such as `import java.util.*;`. Star imports are convenient in small programs, but a named import shows a reader exactly which classes the program uses, so the course names each one.

### What Java imports for you

Java imports the `java.lang` package into every program. That's why `System`, `String`, and `Math` need no import. Everything else in the standard library needs one, with the exception of classes written in the same package as your program.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does this program need `import java.util.List;`?
○ Because every Java program needs at least one import.
→ Not quite. A program needs an import only for the classes it uses from other packages.
◉ Because `List` is in `java.util`, which Java does not import automatically.
→ Yes. Only java.lang is imported for you, and List is not in it.
○ Because the import makes the program run faster.
→ Not quite. An import tells the compiler where to find a class. It has no effect on speed.
````

---

### Try it in the runner

Remove the import line, and press **Run**. The compile stage fails, because `List` can't be found. The error names the line where `List` is used, and nothing runs. Put the import back before you continue.

---

The import tells the compiler where a class lives, and `java.lang` is the one package Java provides without asking.
