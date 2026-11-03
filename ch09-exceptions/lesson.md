## Ch09 · Exceptions

Programs often reach a point where they can't carry on normally: a value is out of range, a file won't open, or an input is unusable. This chapter covers exceptions, which stop the normal flow at the point of the problem and hand control to code that deals with it. You'll throw exceptions, catch them, decide which kinds must be handled, and make sure cleanup always happens.

The steps are:

* [Throwing and catching](#goto/01-throw-and-catch): `throw`, `try`, and `catch`, and how an exception crosses method boundaries.
* [Checked and unchecked](#goto/02-checked-and-unchecked): the two families of exceptions, and why the compiler treats them differently.
* [Finally and resources](#goto/03-finally-and-resources): code that always runs, and closing resources automatically.
* [Summary](#goto/04-summary): a recap of the chapter and a short quiz. The homework is listed under the chapter.

---

*The examples and explanations are new.*
