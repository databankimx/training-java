# Java Training: Port Plan

This plan records the decisions for porting the C++ tutorial to Java, and the order of work. It's a working document, and it should be updated as decisions change.

## Decisions

| Topic | Decision |
|---|---|
| Structure | Mirror the C++ course chapter for chapter: chapters 1 to 25, then the final project. Each lesson is translated into Java idioms. Concepts with no Java counterpart are replaced with the nearest Java technique, as the C course did. |
| JDK | Eclipse Temurin 25.0.5 (LTS), a third-party build. Oracle's JDK is not used, because of its licensing terms. Installed at `C:\Program Files\Eclipse Adoptium\jdk-25.0.5.8-hotspot`. |
| Runner | A Java runner, identical in functionality and appearance to the C runner. It's written in Java and uses JCEF (Java Chromium Embedded Framework) for the window, so the existing HTML, CSS, and JavaScript render as they do under WebView2. JavaFX WebView was rejected, because WebKit renders differently from Edge. Calling WebView2 directly from Java was rejected as too fragile. |
| Build per step | No CMake. The runner compiles each step's `src` and `tests` folders with `javac` into a `build` folder and runs its `Main` class with `java`. |
| Tests | JUnit 5. The jars are vendored under `resources\vendor\junit`. JUnit is approved under section 12. |
| Platform | Windows, as the C course. |

## Repository layout

```
training-java/
|- chNN-name/                    - one folder per chapter, named as in the C++ course
|  |- NN-step/                   - one folder per step
|  |  |- lesson.md
|  |  |- commands.txt            - quick picks, as in the C course
|  |  |- src/Main.java           - the step's main class; other .java files may sit beside it
|  |  |- tests/                  - JUnit test classes, where the step has tests
|  |- homework/
|  |  |- NN-name/...
|- final-project/
|- lesson-runner/                - the Java runner
|- resources/
|  |- vendor/junit/              - JUnit jars
|  |- vendor/jcef/               - JCEF artifacts, once approved
|  |- visualizer/                - the shared visualizer page
|- docs/PLAN.md                  - this document
|- setup.ps1
|- QUICKSTART.md
|- AUTHORING.md
|- README.md
```

## Runner behaviour

The runner is identical to the C runner in functionality and appearance, so the web page (`web/`) is carried over unchanged where possible. The window is a JCEF browser inside a Java desktop window, and the back end is Java. Messages between the page and the back end keep the same command and event shape the C runner uses.

- **Run** compiles the step's `src` and `tests` folders with `javac` into `build`, then runs `Main` with `java -cp build`. Compile errors show in the Output pane with their line numbers.
- **Test** compiles the tests against JUnit, and runs the JUnit console launcher. The result shows as pass or fail per test.
- **Visualize** works as in the C course: a step whose source writes `trace.js` shows the button, and the shared page is copied beside the trace.
- **Quick picks** come from `commands.txt`, using `run` and `test`, as in the C course.
- **Options** are a checkbox per flag the step declares, as in the C course. The Java equivalent of the debug switch adds `-g` to `javac`.

## Order of work

1. **Plan and repository setup.** This document, the README, QUICKSTART, AUTHORING, and `setup.ps1` for Temurin and JUnit.
2. **JCEF approval.** The artifact and its components are chosen and approved under section 12.
3. **Runner.** A minimal Java runner with the C runner's window and bridge, that builds and runs one step. Tested on Chapter 1.
4. **Runner features.** Tests, quick picks, Visualize, the debug switch, and the homework spoiler, each checked against the C runner.
5. **Chapters 1 to 25.** Written in order, with each chapter tested before the next begins. Chapters are paused for testing in pairs, as in the C course.
6. **Final project.** The calculator, in Java, with the same core and bonus requirements.
7. **Release.** README, QUICKSTART, and AUTHORING brought up to date, as for the C course.

## Open items

- **JCEF approved (section 12).** Approved for use, bundled, on Windows x64. Artifacts:
  - `me.friwi:jcefmaven:146.0.10` (Apache-2.0), the wrapper.
  - `me.friwi:jcef-api:jcef-d3de827+cef-146.0.10+g8219561+chromium-146.0.7680.179`, the JCEF API, pulled in by the wrapper.
  - Transitive, from the wrapper's POM: `org.apache.commons:commons-compress:1.28.0` (Apache-2.0) and `com.google.code.gson:gson:2.11.0` (Apache-2.0).
  - The Chromium and CEF components inside the natives are approved, as you confirmed.
- **Natives source and bundling (approved).** The natives are fetched once by the jcefmaven wrapper, from the jcefmaven project's GitHub releases with Maven Central as the fallback. That source is approved. The fetch runs in a fetch-only mode of the runner, which downloads the Windows 64-bit natives into an explicit folder, `resources\vendor\jcef\natives`. The runner records a SHA-256 hash for each file, and checks every later build against it, so a changed file fails the build. The Maven build then bundles the verified files into `lesson-runner\dist`. Neither the natives nor `dist` are committed; `resources\vendor\jcef` and `lesson-runner\dist` are listed in `.gitignore`. Because the wrapper selects the build that matches its own version, no separate natives version needs pinning.
- **Java 25.** The JCEF sample app for 146.0.10 says module-opens flags are needed on JDK 16 and later. The runner's launcher adds them if the first run needs them.
- **Runner UI parity.** The C runner's window, toolbar, panes, and keyboard shortcuts are the reference, and each one is checked against it before a Java chapter is tested.
- **Runner bridge.** The page's JavaScript bridge moves from the WebView2 message channel to JCEF's query channel. That's the only planned change to the page.
