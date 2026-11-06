## Ch24 · Debugging

Writing a program is only part of the work. Bugs appear when the program runs, and this chapter covers the tools for finding them: stack traces that show how the program reached an error, assertions that check promises the code makes to itself, a method for finding a wrong answer, and the checks the JVM makes on its own as the program runs.

The steps are:

* [Stack traces](#goto/01-stack-traces): reading the call stack in an exception, and walking it in code.
* [Assertions](#goto/02-assertions): checking conditions that must always hold, and why they're off unless enabled.
* [Finding bugs](#goto/03-finding-bugs): comparing an expected answer with the output, and finding an off-by-one error.
* [Built-in checks](#goto/04-built-in-checks): the exceptions the JVM throws for indexes, references, divisions, and casts.
* [Summary](#goto/05-summary): a recap of the chapter and a short quiz. The homework is listed under the chapter.

---

*The examples and explanations are new.*
