## Combining futures

Most programs need more than one slow result, and the results often depend on each other. A total needs every part. A report needs a fetch and then a calculation on what was fetched. A failure in one part should not stop the whole program. `CompletableFuture` has methods for each of these, and each one returns a new future, so the steps can be chained.

Here's the start of this step's program, in `src/Main.java`:

```java
CompletableFuture<Integer> six = CompletableFuture.supplyAsync(() -> 6);
CompletableFuture<Integer> seven = CompletableFuture.supplyAsync(() -> 7);

CompletableFuture<Integer> product = six.thenCombine(seven, (x, y) -> x * y);
System.out.println("Product: " + product.join());
```

`thenCombine` waits for both futures, and then calls its function with their two values. The result is a new future, which `join` reads. The output is:

```text
Product: 42
```

### Chaining

`thenApply` takes the value of one future, once it's ready, and returns a new future with a value computed from it:

```java
CompletableFuture<Integer> doubled = product.thenApply(value -> value * 2);
System.out.println("Doubled: " + doubled.join());
```

The output is `Doubled: 84`. Each method returns a future, so calls can be written one after another, and each step starts when the one before it has a value.

### A failure

A future whose work throws an exception is finished with that failure. `join` on it throws, unless the program handles the failure first. `exceptionally` supplies a fallback value:

```java
CompletableFuture<Integer> failing = CompletableFuture.supplyAsync(() -> {
    throw new IllegalStateException("sensor offline");
});
int reading = failing.exceptionally(error -> -1).join();
System.out.println("Reading after failure: " + reading);
```

The output is `Reading after failure: -1`. The fallback value keeps the rest of the program running, and the program decides what a failed reading means.

### Waiting for a group

`allOf` takes an array of futures and returns one future that finishes when all of them have finished. Its `join` waits for the whole group, and then each part can be read without waiting:

```java
CompletableFuture.allOf(parts.toArray(new CompletableFuture[0])).join();
```

The output is `Sum of three: 60`. Once `allOf` has returned, `join` on each part returns at once, since every part already has its value.

---

### Quiz

*These questions are written for this course.*

````quiz
What does `thenCombine` do with two futures?
○ It returns the first future's value, and ignores the second.
→ Not quite. Both futures contribute to the result.
◉ It waits for both, then calls a function with their two values to make a new future.
→ Yes. The new future is ready once both inputs are ready.
○ It runs both futures one after another, on the same thread.
→ Not quite. thenCombine waits for both to finish, and it doesn't say how they run.
````

````quiz
What does `exceptionally` provide when the work failed?
○ The exception, to be thrown again later.
→ Not quite. exceptionally gives a value, which the rest of the program uses.
◉ A fallback value, so the program can continue with a value instead of a failure.
→ Yes. The function passed to exceptionally returns the value to use.
○ Nothing, since a failed future has no value and cannot be used.
→ Not quite. exceptionally is the way to give a failed future a value.
````

---

### Try it in the runner

Change the function in `exceptionally` from `error -> -1` to `error -> 0`, and predict the output before you press **Run**. It prints `Reading after failure: 0`. Then change `thenApply(value -> value * 2)` to `thenApply(value -> value + 1)`, and predict the doubled line. It prints `Doubled: 43`.

---

Futures combine into chains, groups, and fallbacks, so a program can express a whole series of slow steps as one value. The summary follows, and then two homework exercises that use these methods.
