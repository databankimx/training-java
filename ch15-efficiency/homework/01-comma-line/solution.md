## Comma line: walkthrough

The `if (i > 0)` check adds the separator before every number except the first, so the line starts with the first digit and ends with the last one.

`line.append(values[i])` adds the number's text to the buffer. The buffer is changed in place, so the loop never copies the growing text, which is the reason to use `StringBuilder` here.

`line.toString()` makes the `String` once, at the end, when it's printed. The length comes from the builder directly, so no copy is needed for that either.
