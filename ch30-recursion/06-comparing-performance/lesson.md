## Comparing performance

A method that gives the right answer may still be slow, and a method that's clever may still be slower than a plain loop. The only way to know which is faster is to measure. This step times three ways of computing 10!, and compares them.

The rule from the Python lesson applies here: assuming an approach is the best, without testing, is usually wrong.

### The three methods

The recursive method and the loop are the two from the previous steps. The third is a stream:

```java
static long factorialStream(int n) {
    return LongStream.rangeClosed(1, n).reduce(1, (x, y) -> x * y);
}
```

`LongStream.rangeClosed(1, n)` produces the numbers from 1 to n. `reduce` combines them, starting from 1, with the function `(x, y) -> x * y`, so each number is multiplied into the running product. This is the Java equivalent of the `reduce` approach in the Python lesson. Java has no built-in factorial in its standard library, so the stream is the closest thing to a ready-made method.

### Timing a method

The timing method takes the method to test, along with the value and the number of repeats. It records the time before and after the repeats, using `System.nanoTime`:

```java
static long checkPerformance(IntToLongFunction func, int n, int r) {
    long start = System.nanoTime();
    for (int i = 0; i < r; i++) {
        sink += func.applyAsLong(n);
    }
    long end = System.nanoTime();
    return end - start;
}
```

`IntToLongFunction` is a functional interface, so a method reference such as `Main::factorialRec` can be passed in, as the lambdas chapter showed. The result of each call is added to `sink`. Without that, the compiler could decide the calls had no effect and remove them, and the timing would measure nothing.

### Warming up

The JVM compiles a method to fast machine code after it has run for a while. The first runs of a method are slower than later ones, so the program runs each method briefly before it's timed. Without that warm-up, the first method tested would look slower than it really is.

### Running the test

Press **Run**. Your timings will differ from the example, and a second run on the same machine may give different numbers. The output has this form:

```text
Testing 10! 1,000,000 times for each algorithm...

Recursive:
0.021 sec

Iterative:
0.009 sec

Using a stream reduce:
0.031 sec
```

The numbers in this example are illustrative. Read your own output, and compare the three methods on your machine.

The checksum at the end shows that the results were used. It has no other purpose.

### What the results show

On a typical run, the loop is the fastest of the three. The recursive method is slower because every call adds a frame and does its own bookkeeping. The stream does the same multiplications, but it goes through the library's machinery, which costs time for a small job such as 10!.

These results come from one machine, and one value of n. A different size or a different computer can change the order. The conclusion should come from measurements of the case you care about, not from an assumption.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does `checkPerformance` add each result to `sink`?
○ Because a result must be stored before it can be printed.
→ Not quite. The results aren't printed here, and the sink's purpose is different.
◉ So the compiler can't remove the calls as having no effect, which would make the timing meaningless.
→ Yes. Using the result keeps the work in the timed code.
○ Because `sink` is faster than a local variable.
→ Not quite. The reason is that the work must not be removed.
````

````quiz
Why does the program run each method briefly before timing it?
○ Because methods must be called once before they can be used.
→ Not quite. A method can be called without any warm-up.
◉ Because the JVM compiles a method after it has run for a while, so the first runs are slower.
→ Yes. The warm-up lets each method reach its normal speed before the timed run.
○ Because the timing method needs a value to start from.
→ Not quite. The warm-up is about how the JVM runs the code.
````

---

### Try it in the runner

Change `n` to 20, and predict which method is slowest before you press **Run**. The recursive method still makes twenty calls, and the stream still does twenty multiplications, so the order is likely the same as for 10!. The loop remains the fastest of the three. The timings grow for all three, since the work grows with n.

---

Measuring is the only reliable way to compare methods. The loop was the fastest here, but the comparison is only as good as the case it tests. The next step looks at the nested lists the Python lesson uses for the next group of recursive problems.
