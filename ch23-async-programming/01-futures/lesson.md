## Futures

Some work takes time: a call to a web service, a read from a slow disk, or a calculation that can run beside other work. A program shouldn't sit still while it waits. Java's answer is the `CompletableFuture` class, which represents a value that will exist later. The work starts in the background, and the program can do other things until it needs the value.

`supplyAsync` starts the work, and `join` waits for the result when the program needs it. Each step in this chapter shows one part of that pattern.

Here's the program for this step, in `src/Main.java`. The `slowSquare` method sleeps for a fifth of a second to stand in for slow work:

```java
CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> slowSquare(7));
System.out.println("Started the work");

int result = future.join();
System.out.println("Result: " + result);
```

`supplyAsync` takes a lambda, runs it on another thread, and returns at once with a future. The lambda's result becomes the future's value. `join` waits until that value is ready, and returns it.

Press **Run**. The output is:

```text
Start
Started the work
Result: 49
End
```

`Started the work` prints before the result exists, since the square takes time. The `join` call is the point where the program waits, and `Result: 49` prints only after the work has finished.

### join and get

`join` and `get` both wait for a future. `join` throws an unchecked exception when the work failed, which keeps code short. `get` throws checked exceptions, `InterruptedException` and `ExecutionException`, so the method must declare or catch them. This course uses `join` in its examples, because it doesn't need the extra `throws` clauses.

### Work that starts and is not waited for

A future whose result is never used still runs to completion, on its own thread. A program that ends before the work is done loses the result, so a program that needs the value must `join` it. The next step combines several futures, so the program waits for all of them.

---

### Quiz

*These questions are written for this course.*

````quiz
What does `supplyAsync` return?
○ The result of the work, as soon as it's done.
→ Not quite. It returns at once, before the work has finished.
◉ A CompletableFuture, which holds the result once the background work is finished.
→ Yes. The future is the handle that the program uses to get the value later.
○ Nothing, since the work runs without a result.
→ Not quite. supplyAsync returns a future that holds the value.
````

````quiz
Why does the program print `Started the work` before `Result: 49`?
○ Because the lines are printed in a random order.
→ Not quite. The order here is fixed by the program.
◉ Because supplyAsync returns at once, and the result is printed only after join has waited for the work.
→ Yes. The program continues while the work runs in the background.
○ Because printing the result takes longer.
→ Not quite. The order comes from the waiting, not from the time printing takes.
````

---

### Try it in the runner

Change the value passed to `slowSquare` from 7 to 12, and predict the result before you press **Run**. It prints `Result: 144`. Then move the line that prints `Started the work` after the `join` call. Predict the order of the lines. The result prints first, and then `Started the work`, since the program no longer prints anything before it waits.

---

A future is a value that will exist later, and `join` is how the program waits for it. The next step combines futures, so one value can depend on others.
