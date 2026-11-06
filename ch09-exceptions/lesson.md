## Ch09 · Exceptions

Programs often reach a point where they can't carry on normally: a value is out of range, a file won't open, or an input is unusable. This chapter covers exceptions, which stop the normal flow at the point of the problem and hand control to code that deals with it. You'll throw exception objects, use error codes, and then use the standard exception classes, which carry a message.

The steps are:

* [Throwing values](#goto/02-throwing-values): `throw`, `try`, and `catch`, and how a throw crosses method boundaries.
* [Error codes](#goto/03-error-codes): throwing a code, and choosing a message with a `switch`.
* [Standard exceptions](#goto/04-standard-exceptions): standard exception classes, their messages, and catching the base class.
* [Summary](#goto/05-summary): a recap of the chapter and a short quiz. The homework is listed under the chapter.

---

*The explanations are new. The corrected boundary in the error-code example is also new.*
