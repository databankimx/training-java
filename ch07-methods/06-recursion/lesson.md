## Recursion

A method can call itself. That's recursion, and it suits problems that are made of smaller copies of themselves, such as a countdown, a factorial, or a folder that contains folders. Every recursive method needs two parts, and this step builds both.

Here's the program for this step, in `src/Main.java`:

```java
static void countdown(int n) {
    if (n == 0) {
        System.out.println("Liftoff");
        return;
    }
    System.out.println(n);
    countdown(n - 1);
}
```

Press **Run**, and the output begins:

```text
countdown from 3:
3
2
1
Liftoff
```

Each call prints its number, and then calls itself with one less. The call with `n` equal to 0 is the base case: it prints `Liftoff` and returns, without calling itself again.

### The two parts

* **The base case** is the condition where the answer is known without any more calls. Here it's `n == 0`. The `return;` ends a `void` method early.
* **The recursive case** reduces the problem and calls the method on the smaller part. Here it's `countdown(n - 1)`, which moves one step closer to 0.

Every call waits for the call it made to finish. The calls pile up, one on top of the other, and they unwind as each one returns. The base case stops the pile from growing without limit.

### Factorial

The factorial of a number is the product of all the whole numbers from 1 up to it. The program computes `factorial(5)` as 120:

```java
static int factorial(int n) {
    if (n <= 1) {
        return 1;
    }
    return n * factorial(n - 1);
}
```

The output line is `factorial(5) = 120`. The base case is `n <= 1`, which returns 1. The recursive case multiplies `n` by the factorial of one less. So `factorial(5)` is 5 times `factorial(4)`, which is 5 times 4 times `factorial(3)`, and so on, until `factorial(1)` returns 1.

### Without a base case

A recursive method with no base case calls itself forever. Java stops it when the calls use up the space for them, and the program throws a `StackOverflowError`. That's the error to expect when a recursive method is missing its stopping condition, and it's the first thing to check when it appears.

Recursion can be elegant, but a loop is often clearer for a simple repeat. Use recursion when the problem really is made of smaller copies of itself, as a folder tree is.

---

### Quiz

*These questions are written for this course.*

````quiz
What is the base case in `countdown`?
○ The call with n equal to 1.
→ Not quite. The method still prints 1, and then calls countdown(0).
◉ The call with n equal to 0, which prints Liftoff and returns.
→ Yes. The base case is the one that doesn't call itself again.
○ The line that calls countdown(n - 1).
→ Not quite. That's the recursive case, which moves towards the base case.
````

````quiz
What does `factorial(4)` return?
○ 10
→ Not quite. That's the sum of 1 to 4, not the product.
◉ 24
→ Yes. 4 times 3 times 2 times 1 is 24.
○ 4
→ Not quite. A factorial multiplies all the numbers below it, not just the one given.
````

````quiz
What error does a recursive method with no base case produce?
○ A NullPointerException.
→ Not quite. That's for using a null reference.
◉ A StackOverflowError.
→ Yes. The calls keep piling up until there's no room left for them.
○ An ArrayIndexOutOfBoundsException.
→ Not quite. That's for an index outside an array.
````

---

### Try it in the runner

Remove the `if` block from `countdown`, leaving only `System.out.println(n);` and `countdown(n - 1);`, and press **Run**. The method never stops, so the program throws a `StackOverflowError`. The output shows some numbers, counting down into negative values, before the error. Restore the `if` block before you continue.

Then change `factorial(5)` to `factorial(0)` in the output line, and predict the result. The base case is `n <= 1`, which returns 1 for 0 too, so the output is `factorial(0) = 1`.

---

Recursion solves a problem by solving a smaller copy of it, as long as there's a base case to stop at. The summary gathers the chapter together, and the homework uses methods on their own.
