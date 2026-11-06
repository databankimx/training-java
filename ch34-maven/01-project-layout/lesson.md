## Project layout

Maven is the standard build tool for Java projects. It compiles the code, runs the tests, checks the dependencies, and packages the result into a jar file. A project that uses it follows a fixed layout, so any Maven user can find the parts without a guide.

### The standard layout

```text
my-project/
  pom.xml
  src/
    main/
      java/
        Main.java
    test/
      java/
        MainTest.java
  target/
```

* `pom.xml` is the project file. It names the project, lists its dependencies, and says how to build it.
* `src/main/java` holds the program's own source code.
* `src/test/java` holds the tests, which Maven keeps apart from the program.
* `target` holds everything Maven builds: compiled classes, test reports, and the jar. Maven creates it, and it's safe to delete, because a build recreates it.

This course's steps use a simpler layout, with `src` for the program and `tests` for the test classes. The runner compiles them itself, so Maven isn't needed to run a step. The Maven layout is the one a real project uses, and it's the one this chapter describes.

### The program

The program in this step is the same as the others, placed in `src/main/java` in a Maven project. It prints one line:

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("This program was built with the layout Maven expects.");
    }
}
```

Press **Run**. The runner compiles and runs the file, and the output is that line. The runner doesn't call Maven, so this run shows the program, not the build.

### Building with Maven

The build is run from a terminal, in the folder that holds `pom.xml`:

```pwsh
mvn compile
```

Maven reads `pom.xml`, compiles the classes in `src/main/java` into `target/classes`, and stops if anything fails. `mvn test` also compiles and runs the tests. `mvn package` goes one step further and builds the jar in `target`. Each command runs the earlier steps it depends on, so `mvn package` compiles and tests first.

These commands need Maven installed on your computer. They run outside the course runner, which has its own build.

---

### Quiz

*These questions are written for this course.*

````quiz
Which folder does Maven create to hold everything it builds?
○ `src`
→ Not quite. src holds the source code, which is written by hand.
◉ `target`
→ Yes. Maven puts its compiled classes, reports, and jar there, and a build recreates it.
○ `tests`
→ Not quite. In a Maven project, tests go in src/test/java, and target holds the build output.
````

````quiz
What does `mvn package` do that `mvn compile` does not?
○ It runs the program.
→ Not quite. Maven doesn't run the program during a build.
◉ It builds the jar file in target, after compiling and testing.
→ Yes. The jar is the packaged result of the build.
○ It deletes the target folder.
→ Not quite. A build recreates target, and doesn't delete it.
````

---

### Try it in the runner

Move `Main.java` into a folder called `src/main/java` inside this step, and predict whether the runner still runs it. It does, because the runner compiles every file it finds under `src`. The runner's layout is looser than Maven's, so a Maven project needs the folders the layout names.

---

A Maven project keeps its code, its tests, and its build output in fixed places, and `pom.xml` describes the rest. The next step explains the parts of `pom.xml`.
