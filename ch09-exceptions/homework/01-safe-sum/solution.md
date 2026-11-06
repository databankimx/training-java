## Safe sum: walkthrough

The `try` covers the two lines that can fail. `Integer.parseInt` throws `NumberFormatException` for `"abc"` and for the empty string, so the `catch` counts those as skipped, and the loop goes on to the next value.

The `catch` names `NumberFormatException` rather than `Exception`. Only a bad number is expected here, so any other exception, such as a `NullPointerException` from a bug, still stops the program and shows its stack trace.

The total isn't changed when a value is skipped, because the addition is inside the `try` after the conversion, so the exception happens before anything is added.
