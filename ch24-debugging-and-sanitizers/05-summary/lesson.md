## Summary: Debugging

This chapter covered the tools and habits for finding bugs in a Java program.

* **A stack trace shows how the program got to an error.** The first line is where the exception was created, and the lines below it are the calls that led there.
* **`getStackTrace` gives the frames in code.** Each frame has a class name, a method name, and a line number.
* **`assert` checks a promise the code makes to itself.** It's off by default, and runs only when the program is started with `-ea`.
* **Assertions are for bugs, and exceptions are for handled situations.** Bad input from outside the program is handled with an exception.
* **A wrong answer is found by comparison.** Work out the expected answer, compare it with the output, and find the step where the two first differ.
* **Off-by-one errors are common in loops.** A loop from index 0 to `count` must stop before `count`.
* **The JVM checks common mistakes as the program runs.** Indexes, references, divisions, and casts each report their own exception, at the point of the mistake.

### Check your understanding

*These questions are written for this course.*

````quiz
Which line of a stack trace shows where the exception was created?
○ The last line, which is main.
→ Not quite. The last line is where the chain of calls started.
◉ The first line, which names the method that was running when the exception was created.
→ Yes. The rest of the trace shows the calls that led to it.
○ The line that prints the message.
→ Not quite. The message and the trace are separate parts of the output.
````

````quiz
Why does a program started without `-ea` ignore its assertions?
○ Because assertions are removed from the source when it is compiled.
→ Not quite. The assert lines are still compiled, but they do nothing unless assertions are enabled.
◉ Because assertions are off by default, and only run when the JVM is started with -ea.
→ Yes. That keeps checks for developers out of programs that are run by users.
○ Because the JVM doesn't support assertions.
→ Not quite. The JVM supports them, and they are enabled with -ea.
````

````quiz
What does `i < count` in a loop that adds the first `count` values avoid?
○ Adding the value at index count, which is one past the values being summed.
→ Yes. The last index of the first count values is count - 1, so the loop must stop before count.
○ Dividing by zero.
→ Not quite. The condition has nothing to do with division.
○ Creating a new array.
→ Not quite. The loop does not create arrays.
````
