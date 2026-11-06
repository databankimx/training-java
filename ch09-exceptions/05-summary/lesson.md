## Summary: Exceptions

This chapter covered how a program reports and handles errors that stop its normal flow.

* **`throw` ends the normal flow.** Nothing after the throw runs, and control passes to the nearest matching `catch`.
* **Java throws objects.** The thrown value is an object of a class that extends `Throwable`, and it can carry information for the handler.
* **A `catch` matches by type.** A `catch` handles its type and its subclasses, and any other type passes on to another catch, or ends the program.
* **Exceptions cross method boundaries.** The throw can be in a method called from the try block, and the catch can be further up the call chain.
* **Checked exceptions must be handled or declared.** `IOException` and other checked types are checked by the compiler, and a `throws` clause declares them.
* **Error codes say little.** A bare number needs a lookup, and two different causes can share a code. An `enum` gives each code a name.
* **Standard exceptions carry a message.** `IllegalArgumentException`, `IndexOutOfBoundsException`, `IllegalStateException`, and `ArithmeticException` each name a category, and `getMessage` returns the text.
* **Catch the base class when one handler covers several types.** `catch (RuntimeException error)` handles every unchecked exception of that family.
* **Avoid `Throwable` except at the top level.** It also catches errors that a program usually can't recover from.
* **`finally` and resources clean up.** A `finally` block runs on every path, and a resource declared in a `try` header is closed automatically.

### Check your understanding

*These questions are written for this course.*

````quiz
What happens to the statements in a try block after a throw?
○ They all run, and the throw is ignored.
→ Not quite. A throw ends the try block at that point.
◉ They are skipped, and control moves to the first matching catch.
→ Yes. Nothing after the throw runs, in the method or the block that threw.
○ They run after the catch block.
→ Not quite. The catch runs in place of the skipped statements, and they do not run later.
````

````quiz
Which catch is the best choice for a standard exception that should report its message?
○ catch (Throwable error)
→ Not quite. It catches errors as well, and it's meant only for the outermost level of a program.
◉ catch (RuntimeException error), followed by error.getMessage()
→ Yes. It handles the unchecked exception types, and getMessage gives the message.
○ catch (int error)
→ Not quite. Java can't catch an int, since only Throwable objects are thrown.
````

````quiz
Why is an error code such as -2 harder to maintain than a standard exception?
○ Because codes can't be caught at all.
→ Not quite. A catch can receive a code, but the code carries no description.
◉ Because the code gives no description, and the same number can mean different things in different places.
→ Yes. A reader has to look up the meaning, and nothing stops reuse.
○ Because codes are slower to throw.
→ Not quite. The cost of throwing a code is not the problem.
````
