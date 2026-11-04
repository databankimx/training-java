## Homework: Required file: walkthrough

`printFile` catches the `IOException` that the reader throws when it can't open the file, and throws a `RuntimeException` in its place, with the file name in the message. The caller in `main` doesn't need to handle a checked exception, because the method now throws an unchecked one.

The `try` in `main` catches that exception, and prints the message with `getMessage()`. For the missing file, the message reads `could not open data/missing.txt`, and the program ends normally.

Passing the original exception as the cause keeps the detail that the file wasn't found, so the stack trace shows both the message and the reason.
