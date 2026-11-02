## Set up your workstation

This step has no code. It checks that the tools are in place before you write any.

You need two things: the Java Development Kit (JDK), which compiles and runs Java programs, and this runner, which shows your work. This course uses Eclipse Temurin 25 LTS, a third-party build of the JDK.

### Check the JDK

Open a terminal and run these two commands:

```pwsh
java -version
javac -version
```

The first prints the version of the Java runtime, and the second prints the version of the compiler. Both should report version 25. Temurin prints its name in the first line, such as `Temurin-25`.

If either command isn't found, the JDK isn't on your PATH. Install Temurin 25 and open a new terminal, so the change takes effect.

### Check the runner

If you can read this page, the runner is working. Its menu lists the chapters, and the Output pane is empty until you run a step.

**Check:** `javac -version` prints 25, and this page is showing in the runner.

---

*This step has no code, so there is nothing to run.*
