## Locks

Two threads that change the same variable at the same time can get the wrong result. An increment, `total++`, is really three steps: read the value, add one, and write it back. If two threads read the same value before either writes, one of the increments is lost. That's a data race. Java defines what the threads see only when they use a lock or another synchronizing operation, so a race leaves the result unpredictable.

A lock is a way to make one thread at a time change shared data. In Java, every object can serve as a lock. A `synchronized` block takes the object's lock on entry and releases it when the block ends. Any other thread that wants the same lock waits until it's free.

The program in this step uses a `synchronized` block around each increment:

```java
static void addMany(int[] total, Object lock, int times) {
    for (int i = 0; i < times; i++) {
        synchronized (lock) {
            total[0]++;
        }
    }
}
```

Each pass of the loop takes the lock, adds one, and releases the lock at the closing brace of the block. The lock is released on every way out of the block, including an exception, so it can't be left held by mistake.

### Sharing the data

The shared total is an `int` array with one element, and the lock is a plain `Object`. The lambdas that start the threads can't assign to a local `int`, as the lambdas chapter explained, but they can change an element of an array that never changes. Both threads share the same array and the same lock.

```java
int[] total = {0};
Object lock = new Object();

Thread first = new Thread(() -> addMany(total, lock, 100000));
Thread second = new Thread(() -> addMany(total, lock, 100000));
```

Press **Run**. The output is:

```text
Total: 200000
```

Each thread adds 100,000, and the total is 200,000 only because no increment was lost. The lock forces the threads to take turns on the shared counter.

### The cost of the lock

A lock makes the threads wait for each other. In this program, almost all the time goes into taking and releasing the lock, so the threads gain little from running at the same time. Real programs lock as little as they can, for as short a time as possible, and try to avoid sharing data at all. The homework shows one way to do that.

The `java.util.concurrent.locks` package has a `ReentrantLock` for cases where a lock must be taken in one method and released in another. Its `unlock` call belongs in a `finally` block, so the lock is released even when an exception is thrown. The `synchronized` form does that automatically, which is why it's the choice for most code.

### Deadlock

Two threads that each hold a lock and wait for the other's lock will wait forever. That's a deadlock. The usual cause is taking locks in different orders in different places. A program that takes its locks in one fixed order avoids it.

---

### Quiz

*These questions are written for this course.*

````quiz
Why can two threads that increment a shared counter without a lock get the wrong total?
○ Because the compiler refuses to build the program.
→ Not quite. The program builds, and the problem appears at run time.
◉ Because each increment reads, adds, and writes, and two threads can interleave those steps so that an update is lost.
→ Yes. That's a data race, and the result isn't guaranteed.
○ Because increments are always lost when threads run together.
→ Not quite. Lost updates can happen, but the result isn't always wrong, and the outcome isn't guaranteed either way.
````

````quiz
When does a `synchronized` block release its lock?
○ Only when the program ends.
→ Not quite. The lock is released when the block ends.
◉ When the block ends, on every way out of it, including an exception.
→ Yes. The block does the release for you, so the lock can't be left held by mistake.
○ As soon as the lock is taken, so other threads can enter at once.
→ Not quite. The lock is held for the whole block, which is what keeps other threads out.
````

---

### Try it in the runner

Remove the `synchronized` block around `total[0]++`, so the increment is no longer protected, and run the program several times. The total might come out below 200000, and it might not. Predict what you'd see, and explain why a result that's correct sometimes is still a bug. A program that's correct only some of the time is not correct. Put the block back before you continue.

---

A lock lets one thread at a time change shared data, and a `synchronized` block releases it on every way out. The chapter's last steps cover the summary and two homework exercises that split work so that little or nothing is shared.
