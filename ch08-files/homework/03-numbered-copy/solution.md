## Homework: Numbered copy: walkthrough

The reading loop runs while `readLine` returns a line. The counter `count` goes up by one for each line, before the line is written, so the first line gets number 1.

Both files are declared in the same `try` header. Java closes them in reverse order when the block ends, so the numbered file is complete before the program prints its message.

The message uses `count` after the loop. It's the number of lines written, so it matches the output file.
