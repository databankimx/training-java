## The pom.xml file

`pom.xml` is the project's description. Maven reads it to find out what the project is, which Java version to compile with, what libraries it needs, and how to package it. This step explains the parts of a typical file, and the program prints the names that identify the project.

### A minimal pom.xml

This is a complete file for a small project. It's the `pom.xml` in this step's source folder, so you can open it in the source drop-down. The course runner doesn't read it, and it's shown here for reference:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.example.training</groupId>
  <artifactId>maven-demo</artifactId>
  <version>1.0.0</version>
  <packaging>jar</packaging>

  <properties>
    <maven.compiler.release>25</maven.compiler.release>
  </properties>

  <dependencies>
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <version>5.11.0</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
</project>
```

### The identifying parts

Maven names every project with three values, written as `groupId:artifactId:version`:

* `groupId` names the organization or person, in reverse domain order, such as `com.example.training`.
* `artifactId` names the project itself, such as `maven-demo`.
* `version` names the release, such as `1.0.0`.

Together they identify the project uniquely. Other projects use the same three values to depend on it, which is why the program prints them in one line. The values in the program match the ones in `pom.xml`, so the two can be checked against each other.

### Properties

The `properties` section holds values that the rest of the file can use. `maven.compiler.release` tells Maven which Java version to compile for. Setting it once keeps every compile step on the same version.

### Dependencies

A dependency is a library the project uses. Maven downloads it, and puts it on the classpath for the build. The example uses JUnit, the testing framework from the testing chapter, in the `test` scope. A `test` dependency is available to the tests but not included in the program's jar.

Each dependency is named by the same three values. Before adding one, check its licence. The Development Rules of Engagement ask for the same check on any library the course uses, as the JSON and testing chapters did.

### Running it

The program prints the project's coordinates:

```text
Project: com.example.training:maven-demo:1.0.0
```

The text is the same as the three values in `pom.xml`, which Maven uses to name the jar it builds.

---

### Quiz

*These questions are written for this course.*

````quiz
Which three values together identify a Maven project?
○ The name of the main class, the folder, and the Java version.
→ Not quite. These describe the project, but they don't identify it to other projects.
◉ `groupId`, `artifactId`, and `version`.
→ Yes. Together they name the project uniquely, and other projects use them to depend on it.
○ The name of the `target` folder.
→ Not quite. target is where the build puts its output.
````

````quiz
Why does the `test` dependency have `<scope>test</scope>`?
○ Because JUnit can only be used in tests.
→ Not quite. The scope is about where the library is used in the build.
◉ Because the library is needed only for tests, so it is left out of the program's jar.
→ Yes. The scope keeps test libraries out of the packaged program.
○ Because the scope makes the tests run faster.
→ Not quite. The reason is which build step needs the library.
````

---

### Try it in the runner

Change `VERSION` to `"1.0.1"`, and predict the output before you press **Run**. It prints `Project: com.example.training:maven-demo:1.0.1`. The program doesn't read pom.xml, so the version in the file must be changed by hand as well, to keep the two in step.

---

The pom file names the project, sets the Java version, and lists the dependencies. The next step covers the commands that use it.
