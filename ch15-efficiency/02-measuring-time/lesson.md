## Measuring time

Guessing which code is slow is unreliable. Measure it. `System.nanoTime` returns the current value of a high-resolution clock, in nanoseconds. Take a reading before the work and another after, and the difference is the time the work took.

Here's the program for this step, in `src/Main.java`:

```java
long start = System.nanoTime();

long sum = 0;
for (int i = 1; i <= 1_000_000; i++) {
    sum += i;
}

long elapsed = System.nanoTime() - start;
System.out.println("sum: " + sum);
System.out.println("elapsed is not negative: " + (elapsed >= 0));
```

Press **Run**. The output is:

```text
sum: 500000500000
elapsed is not negative: true
```

The elapsed time itself isn't printed, because it changes from run to run and from machine to machine. A timing that changes every run can't be checked against an expected value. The check that the difference is not negative confirms the clock is behaving, and in a real measurement you'd print the elapsed time to read it.

The underscores in `1_000_000` are a readability aid for long literals. The compiler ignores them, so the number is one million.

### Why one measurement is not enough

A single timing is noisy. The operating system, the garbage collector, and the Java compiler's optimization all change the result. The first runs of a piece of code are often slower, while the compiler is still optimizing it. For a fair comparison, run the code several times, discard the first few runs, and compare the averages.

### Measure what matters

Measure the part of the program you suspect is slow, not the whole program. A timing around the wrong code tells you nothing useful. Keep the measured code the same between the two versions you compare, so the difference is caused by the change alone.

---

### Quiz

*These questions are written for this course.*

````quiz
What does `System.nanoTime` return?
○ The current date and time.
→ Not quite. nanoTime doesn't return a date. Its values are only useful as differences.
◉ A high-resolution clock value, in nanoseconds, used to measure a difference.
→ Yes. Subtract an earlier reading from a later one to get the elapsed time.
○ The number of lines the program has run.
→ Not quite. It measures time, not lines.
````

````quiz
Why does one timing of a piece of code give an unreliable answer?
○ Because nanoTime is always wrong.
→ Not quite. The clock is accurate, but the measurement around the code can be noisy.
◉ Because the operating system, garbage collector, and compiler all change the result.
→ Yes. Several things affect a single run, so repeated runs give a fairer picture.
○ Because the program measures itself.
→ Not quite. The measurement is a normal call, and self-measurement isn't the problem.
````

---

### Try it in the runner

Change the loop bound to `1_000`, and predict the sum. It's 500500, the sum of 1 to 1000. Restore one million before you continue.

Then change `elapsed >= 0` to `elapsed < 0`, and predict the output. It prints `elapsed is not negative: false`, which should never happen with a correct clock. The check is there to confirm the clock's behavior, not to pass a test. Restore the original check before you continue.

---

`System.nanoTime` measures a difference between two points in a program, and a single measurement is only a first guess. The next step introduces Big-O, the notation that describes how a program's cost grows as its input grows.
