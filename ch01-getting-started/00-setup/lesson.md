## Set up your workstation

Before we write any Java, we need a Java compiler, which turns the text you write into a program the computer can run. This course uses **Eclipse Temurin 25**, the Java 25 runtime and development kit, and **Maven**, which builds the lesson runner itself. If you haven't set them up yet, do that first.

The full steps are in [QUICKSTART.md](../../QUICKSTART.md) at the root of the repository. The short version is:

1. Install Eclipse Temurin 25 to `C:\Program Files\Eclipse Adoptium`.
2. Install Maven, and check that `mvn` is on your PATH.
3. Start the lesson runner.

### Check the compiler

Open a PowerShell window, and run:

```pwsh
javac -version
```

You should see a version number of 25. If you see an older version, or the command isn't found, the runner won't build the later chapters, so check your PATH or the installation.

### What is this step for?

This step doesn't have any code to run, and that's on purpose. The Run button is grayed out, and the Source tab says the step has no code. Read the lesson, make sure the setup works, and then move on to the next step.

When everything is working, you'll have a compiler, Maven, and the runner itself. You don't need to know how those fit together yet. The next lessons will show you what happens when you press Run.

---

And that's the whole setup. From here on, the work happens in the runner.
