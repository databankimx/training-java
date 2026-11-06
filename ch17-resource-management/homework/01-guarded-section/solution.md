## Homework: Guarded section: walkthrough

`process` declares the guard in the `try` header, so `close` runs when the block ends. With `fail` set, the exception leaves the block after `close` has already run, so `Leave section` prints before the `catch` in `main` sees the exception.

With `fail` clear, the block ends normally after `Finished working`, and `close` runs at the closing brace, so `Leave section` follows it.
