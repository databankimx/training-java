## Summary: Exceptions

This chapter covered handling problems that stop the normal flow of a program.

* **`throw` starts an exception.** It stops the method at that point, and the exception travels up the calls.
* **`catch` handles a type of exception.** It matches that type and its subclasses, and `getMessage()` gives the message.
* **Catch the most specific type.** A general `catch` can hide problems you didn't expect.
* **Checked exceptions must be handled or declared.** `throws` names them, and the compiler enforces the rule.
* **Unchecked exceptions need no declaration.** They usually mean a bug, such as a bad number or index.
* **`finally` runs on every path.** Cleanup goes there, whether the try finishes, returns, or throws.
* **Try-with-resources closes what it opens.** A resource in the `try` header is closed when the block ends.
* **An uncaught exception stops the program.** The runner shows the stack trace.

### Check your understanding

*These questions are written for this course.*

````quiz
Which exception must a method declare, or catch, to compile?
○ NumberFormatException
→ Not quite. That's unchecked, so the compiler doesn't require a declaration.
◉ IOException
→ Yes. IOException is checked, so the method must handle it or declare it.
○ NullPointerException
→ Not quite. That's unchecked, like the other runtime exceptions.
````

````quiz
What happens to a `finally` block when the `try` throws an exception?
○ It's skipped, because the exception ends the try.
→ Not quite. finally runs even when the try throws.
◉ It runs before the exception leaves the method.
→ Yes. finally is the place for cleanup on every path.
○ It runs only if the exception is caught.
→ Not quite. finally runs whether or not the exception is caught.
````

````quiz
What does try-with-resources do with the resource it declares?
○ It leaves it open for the next block to use.
→ Not quite. The resource is closed when the block ends.
◉ It closes it automatically when the block ends, even after an exception.
→ Yes. That's the point of declaring resources in the try header.
○ It copies it, so the original stays open.
→ Not quite. It doesn't copy the resource.
````
