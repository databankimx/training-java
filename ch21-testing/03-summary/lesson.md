## Summary: Testing

This chapter covered checks with known answers, and running them so that the result is read by a machine as well as a person.

* **A test checks a known answer.** The expected result is worked out from the specification before the code is run.
* **Edges are where mistakes show.** Zero, negative values, and the ends of a range catch errors that ordinary values miss.
* **A check prints a name and counts failures.** The count is kept in a field, so every check adds to the same total.
* **A test class ends with an exit code.** `System.exit(0)` means every check passed, and any other value means one failed.
* **The runner reads the exit code.** The Test button compiles the step with its `tests` folder, runs the `Tests` class, and reports the code.
* **A wrong test is as common as wrong code.** A failing check can mean the code is wrong or the expected answer is wrong, and the expected answer is checked first.

### Check your understanding

*These questions are written for this course.*

````quiz
Why should the expected answer in a test be worked out before the code runs?
○ Because the test is faster to write that way.
→ Not quite. The reason is correctness, not speed.
◉ So that the test checks the code against a known answer, rather than confirming whatever the code produces.
→ Yes. A test copied from the program's output proves nothing.
○ Because the compiler needs the answer to build the program.
→ Not quite. The compiler doesn't use test answers.
````

````quiz
What value should a test class pass to `System.exit` when one of its checks fails?
○ 0
→ Not quite. Zero means every check passed.
◉ A value other than 0, such as 1.
→ Yes. Any non-zero exit code tells the runner that the test failed.
○ -1 only, since that is the only failure code.
→ Not quite. Any non-zero value marks a failure.
````

````quiz
Which edge cases are most useful for testing a clamp method?
○ Only ordinary values in the middle of the range.
→ Not quite. Ordinary values rarely expose the mistakes that edges show.
◉ The two ends of the range, and values just outside them.
→ Yes. Those are the places where a comparison is most likely to be wrong.
○ Only the largest int the program can store.
→ Not quite. The ends of the range are more useful than the largest possible value.
````
