## Imports and packages

In the last lesson, `System.out.println` used the class name `System` directly, with no setup. Java's classes are grouped into packages, and a package is a named container for classes. The standard library puts `System` and `String` in `java.lang`, and that package is imported into every program automatically. Classes from other packages, such as `ArrayList` in `java.util`, need an import, or a full name that includes the package.

The program has two parts. The first uses a short name, after importing it:

```java
import java.util.ArrayList;
import java.util.List;

List<String> names = new ArrayList<>();
names.add("Ada");
names.add("Grace");
System.out.println(names);
```

Press **Run**. The output is:

```text
[Ada, Grace]
```

The two `import` lines tell the compiler what `List` and `ArrayList` refer to, and they must come before the class. After that, the short names work throughout the file. `List` is an interface and `ArrayList` is the class that implements it, so the program declares the variable with the interface and creates the object with the class.

### Full names

The second part doesn't use an import. It writes the full name, which includes the package:

```java
java.util.ArrayList<String> other = new java.util.ArrayList<>();
System.out.println(other.size());
```

The output is `0`. The full name always works, and it needs no import, but it's long. Most programs import the classes they use and write short names, and they use full names only when two packages contain a class with the same name.

### Wildcard imports

An import can name a whole package with `*`, as in `import java.util.*;`. That brings in every public class of the package. It's shorter, and it's common in examples. Many style guides prefer naming each class, since a reader can then see at a glance where a name comes from, and since a wildcard can bring in a name that clashes with one you define yourself. The compiler reports a clash only when the ambiguous name is used.

---

### Quiz

*These questions are written for this course.*

````quiz
Which package is imported into every Java program automatically?
○ java.util
→ Not quite. java.util holds collections and helpers, and it needs an import.
◉ java.lang
→ Yes. System and String come from java.lang, which needs no import.
○ java.io
→ Not quite. Input and output classes such as File need an import from java.io.
````

---

### Try it in the runner

Remove the `import java.util.ArrayList;` line, and press **Run**. The build fails, with an error saying the class `ArrayList` can't be found. The compiler doesn't know the name without the import. Restore the line, or change the declaration to `java.util.ArrayList<String>` in full, and the program builds again. Either fix is correct.

---

Packages keep names organized, and imports let a program use them by short names. The next step covers comments, which leave notes for the people who read the code.
