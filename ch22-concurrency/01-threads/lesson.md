## Threads

So far every program has run one thread of work, one step after another. A thread is an independent sequence of work that runs alongside the others. Modern computers have several processors, and threads let a program use them at the same time, or let one part of a program wait while another keeps going.

Java's thread support is in the `java.lang.Thread` class, which is always available. Here's the core of the program from `src/Main.java`:

```java
static void greet(String name) {
    System.out.println("Hello from " + name);
}

Thread worker = new Thread(() -> greet("worker"));
worker.start();
worker.join();
```

The `Thread` object is created with the code it will run, given as a lambda. Creating it doesn't start anything. `start` begins the new thread, and the thread runs `greet` at the same time as the code that started it. `join` waits for the thread to finish.

Press **Run**. The output is:

```text
Start
Hello from worker
After join
```

The `join` call is what puts the output in this order. The thread prints its message while `main` waits, and `After join` prints only once the thread has finished.

Because `join` can be interrupted, it throws an `InterruptedException`. The `main` method declares that it may throw one, so the program compiles. A program that waits for threads in a larger design would catch the exception and decide what to do.

### Why join matters

Without `join`, `main` would carry on while the thread runs, and the two could print in either order. A thread can still be using data from the method that started it, which is a common source of bugs. `join` makes the rule explicit: the work is finished before the code that depends on it continues.

A Java program doesn't stop when a thread is left running. The program ends when all of its threads that aren't daemon threads have finished. That means a forgotten `join` doesn't crash the program, but it can make the output appear in an unexpected order, or cut work short, so `join` is still the way to wait.

### Output from several threads

When two threads print at once, their output can interleave in any order. A message can be split between threads in the middle of a line. The course won't show that here, because the output would differ from run to run. The next step shows how to share data between threads safely, and the homework shows how to split work so that no sharing is needed.

---

### Quiz

*These questions are written for this course.*

````quiz
What does `start` do on a `Thread` object?
○ It waits until the thread has finished.
→ Not quite. That is what join does.
◉ It begins running the thread's code at the same time as the code that started it.
→ Yes. Creating the Thread object only prepares the code to run.
○ It runs the thread's code and waits for it to finish.
→ Not quite. start does not wait. join is the call that waits.
````

````quiz
What does `join` do?
○ It starts a new thread.
→ Not quite. start starts a thread.
◉ It waits until the thread has finished before the calling code continues.
→ Yes. Without join, the two threads can run in either order.
○ It stops the thread.
→ Not quite. join waits for the thread to finish, and doesn't stop it.
````

---

### Try it in the runner

Add a second thread that calls `greet` with a different name, and start it right after the first. Join both before `After join`. Predict whether the two greetings always appear in the same order before you press **Run**. They might not. Both threads run at the same time, so the order can change from one run to the next.

---

A thread runs a method at the same time as the code that started it, and `join` is how the program waits for it. The next step is the harder part: two threads that change the same data, and the lock that keeps those changes from colliding.
