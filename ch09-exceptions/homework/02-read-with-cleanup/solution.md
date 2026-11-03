## Read with cleanup: walkthrough

The `try` header declares the reader, so Java closes it when the block ends, whether the reads succeed or fail. The `finally` block belongs to the outer `try`, and it runs after that close, so its message always comes last.

The third `readLine()` returns `null` because the file has only two lines. The string concatenation turns the `null` into the text `null`, which is why the output shows it. A program that uses the value would have to check for `null` first.

If one of the reads threw an exception instead, the resource would still be closed, and the `finally` would still print, before the exception leaves `main`.
