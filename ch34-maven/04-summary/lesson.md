## Summary: Maven

This chapter covered Maven, the build tool most Java projects use.

* **A Maven project has a fixed layout.** Program code goes in `src/main/java`, tests in `src/test/java`, and build output in `target`.
* **`pom.xml` describes the project.** It names the project with `groupId`, `artifactId`, and `version`, and it sets the Java release and the dependencies.
* **A dependency is a library the project uses.** Its license should be checked before it's added, and a `test` scope keeps it out of the program's jar.
* **Each Maven command runs the stages before it.** `mvn test` compiles first, and `mvn package` compiles and tests first.
* **`mvn package` builds a jar.** The jar holds the compiled program, and `java -jar` runs it.
* **The manifest names the starting class.** Without a `mainClass` entry, `java -jar` has no class to start.

### Check your understanding

*These questions are written for this course.*

````quiz
Where do the program's own source files go in a Maven project?
○ In the `target` folder.
→ Not quite. target holds the build's output, not the source.
◉ In `src/main/java`.
→ Yes. Maven looks for the program's code there.
○ In the root folder, next to `pom.xml`.
→ Not quite. Source files are kept in src, which keeps them apart from the build files.
````

````quiz
Which command builds the jar file?
○ `mvn compile`
→ Not quite. compile stops before the jar is built.
◉ `mvn package`
→ Yes. It compiles, tests, and then builds the jar in target.
○ `mvn clean`
→ Not quite. clean deletes the build output.
````

````quiz
What does a `test` scope dependency do?
○ It runs the tests automatically when the program starts.
→ Not quite. The scope doesn't run anything.
◉ It is available to the tests, and it is left out of the packaged program.
→ Yes. Test libraries are needed for building and testing, not for running the program.
○ It makes the dependency faster to download.
→ Not quite. The scope is about where the library is used.
````
