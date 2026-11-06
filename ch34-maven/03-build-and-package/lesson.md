## Building and packaging

Maven's commands run the stages of a build in order. Each command also runs the stages before it, so the most useful one is often the one at the end.

| Command | What it does |
|---|---|
| `mvn compile` | Compiles the program's source into `target/classes`. |
| `mvn test` | Compiles, then runs the tests and reports the results. |
| `mvn package` | Compiles, tests, and builds the jar file in `target`. |
| `mvn clean` | Deletes `target`, so the next build starts from nothing. |

These are run from a terminal, in the folder that holds `pom.xml`. They need Maven installed, and they run outside the course runner.

### Running the jar

A jar is a single file that holds the compiled program. Once `mvn package` has built it, the program can be run with `java -jar`, and anything after the jar's name is passed to `main` as arguments:

```pwsh
java -jar target/maven-demo-1.0.0.jar first second
```

The output of the program is:

```text
Arguments received: 2
  first
  second
```

The jar must know which class holds `main`. Maven records it in the jar's manifest, which the `maven-jar-plugin` section of `pom.xml` sets:

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-jar-plugin</artifactId>
      <configuration>
        <archive>
          <manifest>
            <mainClass>Main</mainClass>
          </manifest>
        </archive>
      </configuration>
    </plugin>
  </plugins>
</build>
```

Without the `mainClass` entry, `java -jar` has no class to start, and it reports an error.

### Running the program here

The runner runs this program directly, and it passes arguments in the same way as the command line. The runner's argument box is the course's equivalent of the arguments after the jar's name. Press **Run**. The runner prints the arguments you give it, or `Arguments received: 0` if you give none.

---

### Quiz

*These questions are written for this course.*

````quiz
What does `mvn test` do that `mvn compile` does not?
○ It deletes the target folder.
→ Not quite. That is `mvn clean`.
◉ It also runs the tests and reports the results.
→ Yes. Tests come after compiling, so mvn test includes the compile step.
○ It builds the jar file.
→ Not quite. The jar is built by `mvn package`.
````

````quiz
Why does `java -jar` need the `mainClass` entry in the manifest?
○ Because the jar can't contain more than one class.
→ Not quite. A jar can hold many classes.
◉ Because `java -jar` has no other way to find the class that holds `main`.
→ Yes. The manifest names the starting point for the program.
○ Because the entry makes the jar smaller.
→ Not quite. The entry is for finding the starting class.
````

---

### Try it in the runner

Add an argument, such as `third`, in the runner's argument box, and predict the output before you press **Run**. The program prints `Arguments received: 1` followed by `  third`. Then run it with no arguments, and predict the output. It prints `Arguments received: 0` alone.

---

Maven builds, tests, and packages a project with a few commands, and the jar runs with `java -jar`. The final step of this chapter summarizes the Maven material.
