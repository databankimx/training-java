## Rectangle: walkthrough

The compact constructor runs before the components are set, so the check covers every rectangle the program creates. The `||` means either size being invalid is enough to reject the record, which is why a height of -2 is refused, just as a width of 0 is.

The `area` method is a normal method on the record. It can use `width` and `height` directly, because inside the record its components are its fields.

The rejected rectangle is never assigned to a variable, because the constructor throws before the record exists. The `catch` prints the message from the exception, and the program continues.
