## Summary: Asynchronous work

This chapter covered running work in the background, and waiting for its results when they're needed.

* **A `CompletableFuture` is a value that will exist later.** It's created with `supplyAsync`, which starts the work and returns at once.
* **`join` waits for the value.** It returns the result, or throws an unchecked exception if the work failed.
* **`get` also waits, with checked exceptions.** The method calling `get` must declare or catch `InterruptedException` and `ExecutionException`.
* **`thenApply` builds on one future.** It starts once the value is ready, and returns a new future.
* **`thenCombine` waits for two futures.** It calls its function with both values.
* **`exceptionally` gives a fallback value.** A failed future then produces a value the program can use.
* **`allOf` waits for a group.** Its result is ready when every future in the group is ready.

### Check your understanding

*These questions are written for this course.*

````quiz
What does `join` return?
○ The future itself.
→ Not quite. join returns the value the future holds.
◉ The value the future holds, after waiting for it if the work is not finished.
→ Yes. join waits for the value, then returns it.
○ True or false, depending on whether the work succeeded.
→ Not quite. join returns the value, and it throws if the work failed.
````

````quiz
Which method continues from one future's value, once it's ready, and returns a new future?
○ join
→ Not quite. join waits and returns the value, rather than making a new future.
◉ thenApply
→ Yes. thenApply runs its function when the value is ready, and returns a future for the result.
○ allOf
→ Not quite. allOf waits for a group of futures, and returns one future for the group.
````

````quiz
What does `allOf` return?
○ A list of the values of every future.
→ Not quite. allOf returns a single future, which has no value of its own.
◉ One future that finishes when all of the futures in the group have finished.
→ Yes. Its join waits for the whole group.
○ The first future to finish.
→ Not quite. allOf waits for every future, not just the first.
````
