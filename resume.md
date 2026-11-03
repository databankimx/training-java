# Resume: Java Training Port

This file is the handover for continuing the Java tutorial. It holds the context built across the three tutorials, the decisions we've made, the current state, and the next steps. Read it first, then check the files named here against what it says, since they may have changed.

## 1. The three tutorials

| Course | Location | State |
|---|---|---|
| C++ | `C:\Development\training\training-cpp` | Reference course. Chapters 1 to 25 and the final project. Its chapter structure, lessons, and homework are the model for the Java course. |
| C | `C:\Development\training\training-c` | Complete. Released as v1.0 on 11/01/2026. Its runner (`lesson-runner`, WebView2 with C host) is the reference for the Java runner's behaviour and look. Its `web` folder was copied into the Java runner. |
| Java | `C:\Development\training\training-java` | In progress. This is the course we are building now. |
| JavaScript | `C:\Development\training\training-javascript` | Exists, with its own runner. Its highlighting change was the source of the highlight.js choice. Not reviewed in this session. |

The C course's lessons, homework, and runner are the source of truth for conventions. The C++ course is the source of truth for chapter content, which the Java course mirrors chapter for chapter.

## 2. Decisions made for the Java course

- **Structure.** Mirror the C++ course chapter for chapter (1 to 25, then the final project). Each lesson is translated into Java idioms. Concepts with no Java counterpart get the nearest Java technique.
- **JDK.** Eclipse Temurin 25.0.5 (LTS), third-party, because of Oracle's licensing. Installed at `C:\Program Files\Eclipse Adoptium\jdk-25.0.5.8-hotspot`.
- **Runner.** A Java runner that matches the C runner's functionality and appearance. It uses JCEF (Java Chromium Embedded Framework) for the window, in off-screen rendering mode. JavaFX WebView was rejected because WebKit renders differently from Edge. Calling WebView2 directly from Java was rejected as too fragile.
- **Build.** Maven 3.10.0 (installed at `C:\Tools\apache-maven-3.10.0`). No CMake. The runner compiles each step's `src` with `javac` in-process, and runs it with `java`.
- **Tests.** JUnit 5. Approved under section 12. Jars are not yet vendored, and the Test button is not yet wired up.
- **Syntax highlighting.** highlight.js 11.11.1 (BSD-3-Clause), approved. Vendored under `lesson-runner\web\vendor\highlight`, with a hash list. Labelled code blocks only: ```` ```java ````, ```` ```pwsh ````, ```` ```text ````. Unlabelled blocks are plain.
- **Platform.** Windows only, as the C course.

## 3. Approvals and licences

Each of these was approved under section 12 of the development rules.

- **JUnit 5** (EPL-2.0). Approved. Not yet vendored.
- **JCEF** (`me.friwi:jcefmaven:146.0.10`, Apache-2.0), with its Chromium and CEF components (BSD-style). Approved for bundled use on Windows x64. The natives were fetched once from the jcefmaven GitHub releases (approved source). The file hashes are in `resources\vendor\jcef\natives\natives.sha256`, checked on every package build by `lesson-runner\scripts\verify-natives.ps1`.
- **highlight.js 11.11.1** (BSD-3-Clause). Approved. Fetched by `lesson-runner\scripts\highlight\fetch-highlight.ps1`. Hashes in `lesson-runner\web\vendor\highlight\highlight.sha256`.

## 4. Repository layout (Java)

```
training-java/
  parts.json                       - course structure: parts and their chapter folders
  README.md, QUICKSTART.md, AUTHORING.md
  docs/PLAN.md                     - the port plan (decisions and order of work)
  resume.md, prompt.md             - this handover, and the prompt for the next session
  ch01-getting-started/            - written and tested
    lesson.md
    00-setup/lesson.md
    01-hello-world/{lesson.md, commands.txt, src/Main.java}
    02-imports/{lesson.md, commands.txt, src/Main.java}
    03-comments/{lesson.md, commands.txt, src/Main.java}
    04-summary/lesson.md
    homework/{title.txt, lesson.md}
    homework/01-introduce-yourself/{lesson.md, solution.md, commands.txt, src/Main.java}
    homework/02-banner/{lesson.md, solution.md, commands.txt, src/Main.java}
  lesson-runner/
    pom.xml                        - Maven build (Java 25, jcefmaven, dependency copy, natives verify and bundle)
    run.bat                        - starts the runner using the Temurin 25 java.exe (pinned path)
    scripts/verify-natives.ps1     - checks the natives against natives.sha256
    scripts/highlight/fetch-highlight.ps1
    src/main/java/com/databankimx/training/runner/
      Main.java                    - entry point: opens the window; --fetch-natives mode; installs ErrorLog
      FetchNatives.java            - one-time natives download and hash recording
      Window.java                  - main JCEF window (off-screen rendering), close handling, icon, WebServer
      LessonWindow.java            - pop-out lesson window (second browser on lesson.html)
      BrowserSizing.java           - resizes an off-screen browser after its window moves or resizes
      Router.java                  - receives page commands through JCEF's query channel, sends replies
      Commands.java                - the page's commands: catalogue, reads, run, input, lesson state, pop-out
      Discovery.java               - reads parts.json and the chapter, section, step, and homework folders
      Runner.java                  - compiles (javac, in-process) and runs (java) a step; streams output
      WebServer.java               - serves web/ on 127.0.0.1:47800 (fixed port, so browser storage persists)
      ErrorLog.java                - event-queue wrapper that logs errors to runner.log
    web/                           - the page, carried over from the C runner, with Java changes:
      index.html, lesson.html, main.js, lesson.js, lesson-render.js, bridge.js (JCEF query channel),
      highlight.js (wrapper around the library), wheel.js (mouse and trackpad scrolling), zoom.js, ...
      vendor/marked.js, vendor/viz.js, vendor/highlight/ (library, styles, licence, hashes)
    dist/                          - packaged runner (git-ignored): lib/ and natives/
  resources/
    icon_databank_blue.png         - stored in lesson-runner\resources (see note below)
    vendor/jcef/natives/           - git-ignored except natives.sha256
    vendor/junit/                  - not yet present
```

Note: the icon is at `lesson-runner\resources\icon_databank_blue.png`. `Window.java` loads it from there.

## 5. How the runner works

- **Starting it.** `mvn -q compile`, then `.\run.bat`. `run.bat` uses the Temurin 25 `java.exe` path directly, with the `--add-exports` flags needed for off-screen rendering.
- **Window.** A JFrame with a JCEF browser in off-screen rendering mode (`createBrowser(url, true, false)`, `windowless_rendering_enabled = true`). Windowed rendering did not respond to input on this machine, and this was confirmed against the official sample app.
- **Page.** Served by `WebServer` on `http://127.0.0.1:47800/`. A fixed port keeps the browser's saved data (quiz answers, theme) between runs.
- **Bridge.** The page calls `window.cefQuery` with a JSON command. `Router` replies by running `window.__hostMessage(...)` in the page. Events (`run-output`, `run-finished`, `lesson-updated`, `theme-changed`, `lesson-popped`, `lesson-docked`) use the same message shape.
- **Commands implemented.** `list_parts`, `read_lesson`, `list_source_files`, `read_source`, `read_step_file`, `read_commands`, `run_step`, `stop_run`, `send_input`, `open_file`, `set_lesson`, `get_lesson`, `set_theme`, `get_theme`, `open_lesson_window`, `dock_lesson`.
- **Not yet implemented.** `open_visualizer` (raises "Not built yet"). The Test button raises "Tests need JUnit" until JUnit is wired up.
- **Run.** Copies the step's `src` into `build/src`, replaces `Main.java` with the edited text if the user changed it, compiles with `ToolProvider.getSystemJavaCompiler()` into `build/classes`, then runs `java -cp build/classes Main` with stdout and stderr streamed to the Output pane as `run-output` events.
- **Paths.** Every path from the page must resolve inside the repository. Commands refuse anything outside it.
- **Quick picks.** Each step's `commands.txt` lists `run`, `test`, or `run <args>` lines.
- **Trackpad and mouse.** The Output bar has a Trackpad checkbox. Unticked (the default): the wheel is reversed and scaled by `MOUSE_SCALE` (25) in `wheel.js`. Ticked: the wheel keeps its direction, scaled by `TRACKPAD_SCALE` (25). The choice is remembered in the browser.
- **Backtrace.** Removed. Java stack traces already show class, method, and line. It was not meaningful for Java. A per-step debug option (for local variable names) belongs in the debugging chapter, if needed.
- **Pop-out lesson.** Working. Opens a second browser on `lesson.html`. Lesson and theme updates are pushed to it. Docking, or closing its window, returns the lesson to the main window. The pop-out's Lesson pane has the mouse-wheel fix and the highlighting.

## 6. Content conventions (Java lessons)

Follow the C course's conventions, translated to Java.

- **Chapter folder names.** `chNN-name`, matching `parts.json`. The first `## ` heading in `lesson.md` is the chapter title shown in the menu (for example `## Ch01 · Getting Started`).
- **Step folder names.** `NN-name`. The first `## ` heading in the step's `lesson.md` is its title. Step folders with no `src` (setup, summary) have no code.
- **Sections.** Folders named `sNN-name` hold steps (not used yet).
- **Homework.** A `homework` folder with `title.txt`, `lesson.md` (the list of exercises), and step folders `NN-name`.
- **Files per code step.** `lesson.md`, `commands.txt` (for example `run`), and `src/Main.java`. Java code uses `public class Main`.
- **Homework pattern.** `src/Main.java` holds the complete finished program. The spoiler hides it until the learner reveals it. The lesson says where to change the code, and the learner writes their own program first. `solution.md` is a walkthrough, shown after the reveal.
- **Quiz blocks.** Use the four-backtick `quiz` fence, with `○` and `◉` choices and `→` explanations, as in the C course.
- **Try sections.** Each lesson ends with an accurate "Try it in the runner" section. Predictions must be checked against the real output before writing them.
- **Code fences.** Java code uses ```` ```java ````. Output uses ```` ```text ````. Commands use ```` ```pwsh ````.
- **Style.** US English. Hyphens rather than em dashes in documentation. No emojis. Output text copied from the runner keeps the runner's own dash, because that's the exact text shown.
- **Accuracy.** Every "Try" and expected output must match what the runner actually prints. Check these against the runner before a chapter is marked complete.

## 7. Current state

- **Chapter 1.** Complete, and tested by the user: all lessons and both homework exercises work correctly.
- **Chapter 2.** Complete, and tested by the user: all lessons and both homework exercises run correctly, and it is committed to `dev`. Chapter 2 covers variables, arithmetic, strings, user input (`Scanner`), and command-line arguments.
- **Chapter 3.** Complete, and tested by the user: all lessons and homework work properly. Covers booleans, logical operators, `if`, and `switch`.
- **Chapter 4.** Written, awaiting the user's testing: arrays (declaring, initializing, multidimensional), with a temperature log and a tic-tac-toe board as homework. Not yet committed.
- **Chapter 5 onward.** Not started. Check `parts.json` for the chapter names before creating folders.
- **Runner fixes since the first version of this file.** Output is sent as soon as any text arrives, so prompts show before the program waits for input. Enter sends the input line. Quick picks use an in-page menu, because the browser's drop-down opens in the wrong place. Ctrl + wheel zoom follows the Trackpad setting. The pop-out window has the same icon as the main window. Numpad digits type with Num Lock on: the window catches the navigation keys that the numpad sends, and types the digit into the page (see `Window.java`, the key dispatcher).
- **Testing rhythm.** The plan is to test chapters in pairs, as in the C course. The user now prefers the pop-out window for testing, which is working.
- **Runner work done.** Run, stop, input, pop-out, theme, lesson state, trackpad and mouse scrolling, highlighting, the window icon, and error logging.

## 8. Known issues

1. **Main window shrinks after a cross-monitor move (edge case).** Reproduce: pop-out open on the second monitor, then drag the main window to the second monitor and back. The main window's page is then shrunken until it is clicked. The pop-out does not have this problem. Workaround: click the window. `BrowserSizing` resizes the browser after a 250 ms pause, and that hasn't fixed it. Parked.
2. **Exception noise on the event thread.** The terminal shows `Exception in thread "AWT-EventQueue-1"` at startup, with no stack traces. The exception log (`exceptions_1.txt`, made with `-Xlog:exceptions=info`) shows:
   - Harmless: `ClassNotFoundException` probes, missing accessibility bundles, `NoSuchMethodException` checks, JOGL's `NumberFormatException` in `VersionNumber`, and the TempJarCache messages.
   - Worth tracking: ten `GLException`s from JOGL ("Error making context current ... drawableWrite 0x0") at startup, on `AWT-EventQueue-1-SharedResourceRunner`.
   - Worth tracking: eleven `StackOverflowError`s on the event thread from about 10.4 seconds, inside the error logger's `printStackTrace`. The original exception is never logged.
   - `runner.log` has not been created in any run so far. The event-queue wrapper in `ErrorLog` does not see these errors. Parked until a tool that records Java stacks at the point of the throw is confirmed for JDK 25.
3. **The wrong `java` on PATH.** The machine's PATH `java` is Temurin 22. Running the runner with it fails (class file version 69 is Java 25). Always use the pinned path in `run.bat`, or the full path shown in section 2.
4. **Test button.** Raises "Tests need JUnit", until JUnit is vendored and the runner runs the JUnit console launcher.
5. **Visualizer.** Not built. `open_visualizer` raises "Not built yet". The C course's visualizer is the reference (`resources\visualizer\visualize.html`, copied into the step's build folder). Needed for the sorting chapter and any step that writes `trace.js`.
6. **Pop-out and the main window's error noise.** The same exception noise appears in both windows.
7. **Closing the main window can leave the pop-out open.** Occasionally, with the pop-out open, closing the main window doesn't close the pop-out, and it has to be ended in Task Manager. Not reproduced consistently yet. The close path is in `Window.exit` and `LessonWindow.dock`, and the main window's exit uses `System.exit` on a background thread with a three-second watchdog. Check whether the pop-out's close (which docks the lesson) is still running when the main window exits.

## 9. Things to check before continuing

- **Git status.** Check that the work is committed. Recent additions include `lesson-runner/web/vendor/highlight/` (with its hash list), `lesson-runner/scripts/`, `ch01-getting-started/`, `parts.json`, `docs/PLAN.md`, `resume.md`, and `prompt.md`. `.gitignore` excludes `lesson-runner/dist`, `resources/vendor/jcef/natives`, `**/build/`, `lesson-runner/runner.log`, and `lesson-runner/target`.
- **Probe.java.** The user said it has been deleted. Confirm it's gone from `lesson-runner\src\main\java\com\databankimx\training\runner`. It shouldn't be there.
- **Chapter names in `parts.json`.** Confirmed by the user. Chapter names follow the C++ course's names, with Java terms only where the Java idiom genuinely differs. A different name needs a genuinely different ideological path, not a translation preference.
- **The `dist` folder.** Rebuilt by `mvn -q package`. Only needed for distribution. `run.bat` runs from `target\classes` and `dist\lib`, so `dist\lib` must exist (run `mvn -q package` once if it doesn't).

## 10. Lessons learned in this session (do not repeat these)

- **Don't guess API names.** The `wasResized` call on `CefBrowser` didn't exist, and a compile error caught it. Check method names against the library before using them, or say they are unverified.
- **Don't guess causes.** Several attempted fixes for the window's input problem were guesses. The working fix came from a single targeted test (the official sample app), so run the smallest decisive test first.
- **Windowed rendering doesn't work for input here.** Off-screen rendering (`createBrowser(url, true, false)` with `windowless_rendering_enabled = true`) does.
- **Mouse and trackpad values overlap.** Both arrive as small whole numbers. A page can't tell them apart, so the user chooses with the Trackpad checkbox.
- **PowerShell and `-D` options.** Quote them: `java "-Drunner.test=1" ...`. Unquoted, PowerShell splits the argument at the dot.
- **Maven and PATH.** `MAVEN_OPTS` with `sun.lwawt` causes a warning on JDK 25. Remove it. The `java` on PATH may not be 25.
- **Name clashes in Java lambdas.** A lambda parameter cannot reuse a local variable's name in the same method. This broke the first compile of the router.
- **Homework structure.** `src` holds the finished program, under the spoiler. The lesson holds the instructions. The reverse was wrong.
- **Output blocks.** Label them ```` ```text ````, so the highlighter leaves them alone. Unlabelled blocks are highlighted automatically, which coloured program output.
- **Swing threads.** Create and resize Swing and CEF objects on the event dispatch thread. Commands that create or dispose windows use `SwingUtilities.invokeLater`.

## 11. Next steps

1. **Resolve the chapter names** in `parts.json` with the user, if they haven't already been confirmed.
2. **Write Chapter 2** in `ch02-variables-and-input`, following the C++ course's `ch02-variables-and-input` structure and the conventions in section 6. Test it in the runner, with the user, before writing Chapter 3.
3. **Decide what to do with** the exception noise and the edge-case shrink. The user agreed to park these. The recommended next step is a Java stack-recording tool, once one is confirmed for JDK 25.
4. **Wire up JUnit** before the first chapter with tests (the Test button needs it). Vendor the approved jars under `resources\vendor\junit`, and have the runner run the JUnit console launcher.
5. **Build the visualizer** before the sorting chapter (Chapter 16 in the C++ order).
6. **Pop-out and docking** are done. Keep the mouse-wheel fix and highlighting in any future changes to the pop-out.
7. **Release checks** at the end: README, QUICKSTART, AUTHORING updated, as for the C course. Remove the `sun.lwawt` MAVEN_OPTS advice from any documentation.
