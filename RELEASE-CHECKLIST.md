# Release checklist: remaining tests and checks

Work through these in order. Items marked **blocker** must be done before release.

## 1. Runner build (blocker)

- [x] In `lesson-runner`, run `mvn -q compile`. It builds with no errors or warnings, since the build uses `-Xlint:all -Werror`.
- [x] Restart the runner with `run.bat`. The window opens and the chapters load.
- [x] Choose `ch34-maven/03-build-and-package`. Its quick picks show.
- [x] Choose `run first second`. The output is `Arguments received: 2`, then `  first` and `  second`.
- [x] Choose `final-project/homework/01-calculator`, and run `run 2 + 3 * (4 - 1)`. The output is `11`, which confirms the argument file works with `*`.
- [x] Type `2^3` (no quotes) in the argument box of a step with a `^` quick pick. The output is `8`, since the argument file works for `^` too.

## 2. Setup script on a clean machine (blocker)

Use a fresh clone under `C:\Development`, with no `dist`, `target`, or `build` folders.

- [ ] Run `powershell -ExecutionPolicy Bypass -File .\setup.ps1`. It finishes with `Setup complete`.
- [ ] The natives download succeeds, and the hash check passes.
- [ ] `lesson-runner\dist\lib` contains the runtime jars.
- [ ] `lesson-runner\dist\natives\jcef.dll` exists.
- [ ] Run the setup script a second time. It completes without re-downloading the natives.
- [ ] Test the failure paths: remove JDK 25 or Maven from the expected paths, and check that the script names the missing tool and stops.
- [ ] Run the script with `-InstallMissing` from an elevated PowerShell on a machine with none of the tools. It installs the JDK, Maven, and Git, and finishes with `Setup complete`.
- [ ] Run `-InstallMissing` without elevation. The script refuses before installing anything.
- [ ] Check that `lesson-runner\jdk.path` holds the JDK folder the script found, and that `run.bat` starts the runner from it.
- [ ] Test the Maven checksum check: change the expected hash, or serve a corrupted zip, and confirm the script stops and removes the download.
- [ ] Test a changed native file (edit a file in `resources\vendor\jcef\natives`). The script reports the hash mismatch and stops.
- [ ] Test with no internet access. The script reports the download failure.
- [ ] Confirm `dependency:copy-dependencies` works on the clean machine, and that the copied jars match `dist\lib`.

## 3. Licence review (blocker)

- [ ] Run `mvn dependency:tree` in `lesson-runner`, and list every runtime dependency.
- [ ] Review the licence of each jar in `dist\lib`: Gson, Apache Commons (codec, compress, io, lang3), GlueGen, JOGL, error_prone_annotations, and the JCEF API. Check each against the Development Rules of Engagement, section 12.
- [ ] Update the README's libraries table and Third Party Licenses section with every approved library.
- [ ] Remove the README sentence that says more libraries still need review, once they're approved.

## 4. Expected outputs (blocker)

Run each step and compare its output with its lesson. Where a lesson gives an expected value, the value must match. Where a value was worked out by hand, check it.

- [ ] Chapter 29 (bitwise operators): every step, including the homework.
- [ ] Chapter 30 (recursion): steps 04 to 16. Confirm the call counts (step 12 gives 331,160,281, step 13 gives 79 and then 1) and the timings are labelled as illustrative. Step 12 takes a few seconds.
- [ ] Chapter 30 homework: `Sum: 21` and `2^10 = 1024`, `3^4 = 81`, `5^0 = 1`.
- [ ] Chapter 31 (dates): `3/15/2024`, `March 15, 2024`, `4/14/2024`, `3/15/2024 14:30`, `Friday, March 15, 2024 at 14:30`, `285`, `PT5H30M`, `330 minutes`.
- [ ] Chapter 31 homework: `7/4/2026`, `Saturday`, `10/12/2026 (Monday)`, and `57`, `481`, `308`.
- [ ] Chapter 32 (trie): every step. The outputs match the lessons.
- [ ] Chapter 33 (JSON): `04-json-library` prints the expected lines. The homework prints `Updated: ...` and `Reads back: true`.
- [ ] Chapter 34 (Maven): the three steps print the expected output. The Maven commands are checked outside the runner, with Maven installed.
- [ ] Chapter 27 (Swing game): every step runs, and the sound files load.
- [ ] The final project: `demo`, `selftest`, the REPL, and each homework step's outputs.

## 5. Final project (blocker)

- [ ] `homework/01-calculator`: `demo` prints every example and error case. `selftest` ends with `All checks passed.` and exits 0.
- [ ] `homework/01-calculator`: the REPL accepts lines in the input box, skips blank lines, and ends on `quit`.
- [ ] `homework/01-calculator`: a deliberately broken expression, such as `2 +`, prints the matching error message.
- [ ] `homework/02-calculator-bonus`: `run 2^3^2` prints `512`, `run sqrt(16)` prints `4`, and `run [2 + 3] * 2` prints `10`.
- [ ] `homework/02-calculator-bonus`: `selftest` passes, including the bonus examples.
- [ ] Spoiler gating: the source and the walkthrough stay hidden until **Show example (spoiler)** is clicked, and Run is disabled until then.

## 6. Quick picks and spoilers across the course

- [ ] Quick picks appear for every step that has a `commands.txt`.
- [ ] Each quick pick runs and produces the output its lesson describes.
- [ ] Homework steps hide their source and solution until revealed.

## 7. Documentation

- [ ] Read `README.md` and `QUICKSTART.md` on a clean machine, and follow them exactly. Note any step that doesn't match what happens.
- [ ] Confirm every link in the chapter overviews opens its step.
- [ ] Check the chapter order in `parts.json` against the overviews.
- [ ] Change "licence" to "license" in the lessons, to match the course's US spelling. (Only my own prose needs changing. Keep the quotations from the Python course as they are.)

## 8. Cleanup

- [ ] Delete `review-matches.txt` from the repository root.
- [ ] Remove the duplicate `lesson-runner/dist/` line from `.gitignore`.
- [ ] Delete these folders, which are outside the repository: `C:\Development\training\tmp-difficulty`, `C:\Development\training\tmp-bitwise-old`, `C:\Development\training\tmp-reorder`, and `C:\Development\training\tmp-exceptionsink`.
- [ ] Remove `build` folders from the repository before committing. They're ignored by git, but keep them out of any package.
- [ ] Delete `lesson-runner\exceptions-run.txt` and the other diagnostic output files, if they're still there.

## 9. Known issues

- [ ] Main window shrinks after a cross-monitor move with a pop-out open. Fix, or document as a known issue.
- [x] Exception noise on `AWT-EventQueue-1` at startup. Documented in QUICKSTART's troubleshooting table. The cause is not traced: the runner's error log doesn't record it, and the JVM's exception log shows no unhandled exception. Fix later if it becomes a problem.
- [ ] Confirm the window still shows its icons and text despite the console message `Cannot create directory 'browser/images/...'`. Documented in QUICKSTART.
- [ ] PATH Java is Temurin 22, while `run.bat` pins 25. Document in QUICKSTART, or fix the PATH.

## 10. Process (blocker)

- [ ] Commit the work to the DataBank GitHub Enterprise repository.
- [ ] CI scans pass: the standards policy scan, SonarQube, and Snyk.
- [ ] The code is reviewed, and the reviewer confirms it meets DataBank standards.
- [ ] The work is tied to an approved quote or engagement, as the Development Rules of Engagement require.

---

## 11. training-c

### Clean VM setup (blocker)

- [x] Run from a reset VM with `setup.ps1 -InstallMissing` completes. The run took about seven minutes, and the summary shows `ok` for every line.
- [x] Git, MSYS2, the toolchain (gcc, cmake, ninja, gdb), and ImageMagick install, with retries after the core upgrade.
- [x] A DNS failure to `mirror.msys2.org` is retried before the script stops.
- [x] The MSYS2 folder is added to the user PATH, and VS Code finds gcc and gdb after a restart.
- [x] The repository clones `main`, and the clone's copy of the script takes over.
- [ ] Commit and push the updated README and QUICKSTART to `main`.

### Lesson runner (blocker)

- [x] The runner builds and starts.
- [x] Run works on a single-file lesson.
- [x] Test works on Ch21.
- [x] Quick picks fill the input box and run.
- [x] The visualizer opens for Ch16.
- [x] Typed input reaches a Ch02 program.
- [x] A file-writing example (Ch08) writes its file where the lesson says.
- [x] A multi-file lesson (Ch13, 02-include-guards) builds and runs in the runner.
- [x] The final project's `demo` and `selftest` run, and the bonus step's selftest passes.

### VS Code (blocker)

- [x] Choosing the GCC 16.2.0 ucrt64 kit works, and the CMake Tools notice is dismissed.
- [x] Ctrl+Shift+B builds a single file.
- [x] `C/C++: run active file` builds and runs a single file.
- [x] F5 with `Debug active C file (MSYS2 gdb)` starts the debugger, and stepping and the Variables panel work.
- [x] A multi-file lesson in VS Code reports `undefined reference`, as the README says, and is built through the runner instead.
- [x] The CMake command in the README and QUICKSTART works, with the step's folder in the `cd` line.

### Memory checking (Chapter 5 and Chapter 24)

- [x] `-fsanitize=address` fails to link on this toolchain (`cannot find -lasan`), so the lesson's claim holds.
- [x] Dr. Memory crashed on the test machine, with a log that shows no report, so it's removed from the course.
- [x] `gcc -fanalyzer` reports a double free, with the events in order.
- [x] `gcc -fanalyzer` reports an unchecked `malloc` result, and the checked version compiles cleanly.
- [x] `gcc -fanalyzer` does not report the missing `free` in either leak example, even with `-Wanalyzer-malloc-leak`. The lessons say leaks are checked by review.

### Open items

- [ ] Delete `resources\vendor\drmemory\fetch-drmemory.ps1` and its install folder, unless the course keeps it.
- [ ] Test the VS Code multi-file tasks on the VM: `C/C++: build lesson (CMake)` and `C/C++: run lesson (CMake)` on Ch13, 02-include-guards. Then commit the tasks and docs to `main`.

---

## 12. training-c-plus-plus

### Clean VM setup (blocker)

- [x] The setup script's first clean run on the VM completes. The summary shows `ok` for every line.
- [x] The repository clones `main` into `C:\Development\training\training-c-plus-plus`, and the clone's copy of the script takes over.
- [x] The toolchain (g++, cmake, ninja, gdb) installs, and the MSYS2 folder is on the user PATH.
- [x] Second clean run, with the summary and the time recorded. The summary matched the first run, and the run took just over seven minutes.
- [ ] Commit the runner fix (`web/main.js`, line endings) and the setup, docs, and tasks changes to `dev`, then merge to `main`.

### Lesson runner (blocker)

- [x] The runner builds and starts.
- [x] Run works on a single-file lesson.
- [x] Test works on Ch21.
- [x] Quick picks fill the input box and run.
- [x] The modified flag is off for an unedited file, after the line-ending fix.
- [x] The final project's `demo` and `selftest` run, and the bonus step's `selftest` passes.
- [x] The standard-library and C++23 lessons (Ch14, Ch20, Ch22, Ch25) build and run.
- [x] The runner's debugger stepping works on a Ch05 example.
- [ ] Visualizer on Ch16, if the course has one.
- [ ] Backtrace checkbox on Ch24, 01-stack-traces.

### VS Code (blocker)

- [x] No CMake Tools prompt appears, since the `.vscode` files are already in place.
- [ ] Ctrl+Shift+B builds a single file.
- [x] `C/C++: run active file` builds and runs a single file.
- [x] F5 with `Debug active C++ file (MSYS2 gdb)` builds the file, starts gdb, and stops at a breakpoint.
- [x] `C/C++: run lesson (CMake)` and `C/C++: build lesson (CMake)` build and run the Ch13 multi-file lesson, with the output matching the runner.

### Memory checking (Chapter 24)

- [x] `-fsanitize=address` fails to link on this toolchain (`cannot find -lasan`), so the lesson's claim holds.
- [x] `g++ -fanalyzer` reports a double free for `malloc` and `free`, with the events in order.
- [x] `g++ -fanalyzer` reports a use after `delete` for a double `delete`. The lesson describes it that way.
- [x] Dr. Memory isn't referenced in the C++ course.

### Open items

- [ ] Check the C++ course's Ch24 homework and summary wording against the analyzer output.
- [ ] Decide whether the C++ course needs the Dr. Memory fetch script deleted, if it has one. The search found no references to it.
