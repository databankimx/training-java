We are continuing the Java port of the training tutorial, in C:\Development\training\training-java.

Before doing anything else:

1. Read resume.md in C:\Development\training\training-java. It holds the decisions, the current state, the runner's design, the content conventions, the known issues, and the lessons learned. Treat it as the source of truth for this session.
2. Check the files it names against what it says. Things may have changed since it was written. In particular, confirm that resume.md's section 9 items are true: git status, that Probe.java is gone, and the chapter names in parts.json.
3. Tell me briefly what you found, and anything that doesn't match resume.md, before writing any new content.

Then, in this order:

A. Ask me to confirm the chapter names in parts.json, if they haven't been confirmed. Do not create chapter folders until I have.

B. Once confirmed, write Chapter 2 (variables, arithmetic, strings, and input), following the C++ course's ch02-variables-and-input structure, and the conventions in resume.md section 6. Java code uses public class Main, output blocks use ```text, commands use ```pwsh, and homework keeps the finished program under the spoiler, with the lesson saying where to change the code.

C. Do not change the runner, the Discovery or Commands code, or the web pages unless a chapter needs something they don't yet provide. If a chapter does need a runner change, tell me first, and explain why.

D. Keep every "Try it in the runner" section and every expected output accurate to what the runner actually prints, and say which ones you have not been able to verify.

E. Use hyphens rather than em dashes in documentation, avoid emojis, and use US English.

Known issues to leave parked unless I raise them: the main window's shrink after a cross-monitor move (click to recover), and the exception noise on the event thread (see resume.md section 8).

When you're done with Chapter 2, tell me what you wrote, what you verified and what you could not verify, and what I should test in the runner.
