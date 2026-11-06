## Homework: Shared list

Two threads add values to one shared `List`. The list is protected by a lock, so the two threads never change it at the same moment. The order in which the values arrive depends on the threads, so the program prints only results that don't depend on that order.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* write `static void addValues(List<Integer> list, Object lock, int first, int last)`, which adds each number from `first` up to, but not including, `last` to the list. Each `add` happens inside a `synchronized` block on the lock
* in `main`, create an empty `ArrayList` and a lock object, start one thread that adds 1 through 5, and a second that adds 6 through 10
* join both threads, then print `Size: ` followed by the list's size, and `Sum: ` followed by the sum of its values

### What to check

The output should be:

```text
Size: 10
Sum: 55
```

The order of the values in the list can differ from one run to the next, and the program doesn't print them. The size and the sum are the same every time, because the lock keeps each `add` whole.

### Why the sum is safe to read

The sum loop runs in `main` after both `join` calls return. Both threads have finished by then, so no thread can change the list while it's being read. The lock is needed while the threads are running, and the `join` calls take over once they've finished.

### Extensions

* Sort the list in place with `Collections.sort` after both threads have finished, and print it. Explain why the sort is safe to run without the lock at that point.
* Remove the `synchronized` block, and run the program several times. Explain why the size might come out wrong, and why the program could still appear to work most of the time.

---

*This exercise is not from the book. It practices the ideas in Chapter 22, with the lists from Chapter 14.*
