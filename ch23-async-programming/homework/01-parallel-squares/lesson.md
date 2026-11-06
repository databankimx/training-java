## Homework: Parallel squares

Compute the squares of the numbers from 1 to 5 in the background, then add them up once all of them are ready.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* start one future for each number from 1 to 5 with `supplyAsync`, each returning the square of its number
* keep the futures in a list
* wait for all of them with `allOf`, then add their values together with `join`
* print `Sum of squares: ` followed by the total

### What to check

The output should be:

```text
Sum of squares: 55
```

The squares are 1, 4, 9, 16, and 25, and they add up to 55.

### Why the variable is copied

Each lambda needs its own number. The loop variable `n` changes on every pass, so the example copies it into `number` first, and each lambda captures the copy. Try using `n` directly in the lambda, and read the compiler's message.

### Extensions

* Change the program to compute the squares of 1 through 100, and check the total against the formula n(n + 1)(2n + 1) / 6 for n = 100.
* Make one of the futures fail, by throwing an exception for one number, and add `exceptionally` so the sum uses 0 for that number.

---

*This exercise is not from the book. It practices the ideas in Chapter 23.*
