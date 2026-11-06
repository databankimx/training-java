## Homework: Ownership handoff

In this exercise, you hand a resource to a method, and watch the method own it until it returns.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* define a `Resource` class that implements `AutoCloseable`. Its constructor prints `Acquire <name>`, and its `close` method prints `Release <name>`
* define a `Box` class that holds at most one resource, with a `take` method that returns the resource and empties the box
* define `consume(Box box)`, which takes the resource out of the box in a `try` header, prints `Received <name>`, and lets the `try` close the resource when the method returns
* in `main`, create a box holding a resource named `handoff`, pass the box to `consume`, and then print `Back in main`
* print `End` before `main` returns

### What to check

The output should be:

```text
Acquire handoff
Received handoff
Release handoff
Back in main
End
```

The `Release` line comes before `Back in main`. The method owned the resource while it ran, and the `try` header released it when the method returned.

Predict the output before you run it, and explain why `Release handoff` appears where it does.

### Why the box is needed

`consume` takes the box, not the resource, so the box can be emptied by `take`. After `consume` returns, the caller's box holds nothing, and it can't hand out the resource a second time. The `if` check in `consume` handles a box that is already empty, where `take` returns `null`.

### Extensions

* Change `consume` to read the name without taking the resource, so the box keeps its contents. Predict whether the release still happens inside `consume`, and check your prediction.
* Call `consume` a second time with the same box, and predict the output. Explain why the second call prints nothing.

---

*This exercise is not from the book. It practices the ideas in Chapter 18, with the resource management from Chapter 17.*
