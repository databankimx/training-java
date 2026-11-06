## Homework: Split sum

Add the whole numbers from 1 to 1,000 by splitting the work between two threads. Each thread writes only its own result, so the threads share no data, and no lock is needed.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* write `static void sumRange(int first, int last, long[] total)`, which adds the numbers from `first` up to, but not including, `last`, and stores the result in `total[0]`
* in `main`, create two arrays of one `long` each, one for each half, both starting at 0
* start one thread that sums 1 through 500, and a second that sums 501 through 1,000, giving each thread its own array
* join both threads, and print `Sum: ` followed by the two results added together

### What to check

The output should be:

```text
Sum: 500500
```

The two halves are 125,250 and 375,250. Each thread writes only its own array element, so there's no race, and the `join` calls ensure both results are complete before `main` reads them.

### Why arrays

A lambda can't assign to a local variable, and a `long` passed to a method is copied. Each thread therefore gets an array to store its answer in, and the array is shared only with the code that reads it after `join`.

### Extensions

* Use three threads instead of two. Decide where the ranges should split, so that every number is counted once.
* Change both threads to write to the same array element. Explain why the result is no longer safe, and what a lock would add.

---

*This exercise is not from the book. It practices the ideas in Chapter 22.*
