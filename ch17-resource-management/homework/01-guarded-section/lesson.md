## Homework: Guarded section

A guard is a small object whose cleanup runs when a block ends. In this exercise, you'll write a guard and check that its cleanup still runs when an exception leaves the method early.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* define a class `Guard` that implements `AutoCloseable`. Its constructor takes a name and prints `Enter <name>`, and its `close` method prints `Leave <name>`
* define `process(boolean fail)`, which declares a `Guard` named `section` in a `try` header, prints `Working`, throws a `RuntimeException` with the message `work failed` when `fail` is true, and otherwise prints `Finished working`
* in `main`, call `process(false)` and then `process(true)` inside a `try` block, and catch the exception, printing `Caught: ` followed by its message
* print `End` after the `try` block

### What to check

The output should be:

```text
Enter section
Working
Finished working
Leave section
Enter section
Working
Leave section
Caught: work failed
End
```

The line `Leave section` in the second call comes before `Caught`. That's the point of the exercise: the guard's `close` runs while the exception travels out of the method, before the `catch` that handles it.

### Extensions

* Add a second guard to `process`, and predict the order of the two `Leave` messages when the exception is thrown. Check your prediction.
* Remove the `try` block from `main`, and describe what the program does when the exception isn't caught. Note whether the guard's `close` still runs first.

---

*This exercise is not from the book. It practices the ideas in Chapter 17, with the exceptions from Chapter 9.*
