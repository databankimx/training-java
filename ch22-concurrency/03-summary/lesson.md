## Summary: Concurrency

This chapter covered running work on more than one thread, and keeping shared data correct when threads use it.

* **A thread runs alongside the code that started it.** Create a `Thread` with the code it will run, and call `start` to begin it.
* **`join` waits for a thread to finish.** It throws `InterruptedException`, which the method must declare or catch.
* **Threads that share no data need no lock.** Each thread writes its own result, and `join` makes the results visible once the threads are done.
* **A data race loses updates.** An increment is a read, an add, and a write, and two threads can interleave those steps.
* **A `synchronized` block holds a lock.** Only one thread at a time can enter a block that uses the same lock object.
* **The lock is released on every exit.** Leaving the block, by normal completion or by an exception, releases the lock.
* **A deadlock is a wait that never ends.** Two threads that each wait for the other's lock stop forever, which is avoided by taking locks in one fixed order.

### Check your understanding

*These questions are written for this course.*

````quiz
What does `start` do on a `Thread` object?
○ It waits until the thread has finished.
→ Not quite. That is what join does.
◉ It begins running the thread's code at the same time as the code that started it.
→ Yes. Creating the Thread object only prepares the code to run.
○ It stops the thread.
→ Not quite. start begins the thread, and nothing in this chapter stops one.
````

````quiz
Why does a `synchronized` block protect a shared counter from lost updates?
○ Because it makes the counter a constant.
→ Not quite. The counter still changes, but only under the lock.
◉ Because only one thread at a time can hold the lock, so the read and write of each increment can't interleave with another thread's.
→ Yes. The lock makes each increment happen whole.
○ Because it makes each thread work on its own copy of the counter.
→ Not quite. The threads share the same counter, and the lock controls who changes it.
````

````quiz
What is a deadlock?
○ A thread that finishes too quickly.
→ Not quite. Finishing quickly is not a deadlock.
◉ Two threads each waiting for a lock the other holds, so neither can continue.
→ Yes. Taking locks in one fixed order is the usual way to avoid it.
○ A program that prints the same output every time.
→ Not quite. Repeatable output is the opposite of a race, not a deadlock.
````
