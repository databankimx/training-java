## Iterative factorial

For any recursive solution, there's at least one way to solve the same problem with a loop. A recursive solution is not automatically the better choice just because it matches the formula. This step writes the factorial as a loop, and compares the two.

### The loop

```java
static long iterativeFactorial(int n) {
    long f = 1;
    for (int i = 2; i <= n; i++) {
        f *= i;
    }
    return f;
}
```

The loop multiplies the running product by each number from 2 to n. It uses one method call, so the stack doesn't grow as n grows. The recursive version made n calls, and each call added a frame. The loop makes no extra calls, so its depth is always one.

The results match the recursive version for 0 to 9:

```text
0! = 1
1! = 1
2! = 2
3! = 6
4! = 24
5! = 120
6! = 720
7! = 5040
8! = 40320
9! = 362880
```

### Going beyond a long

A `long` holds values only up to 20!, so this loop can't compute 1000! The Python lesson makes the same point, and Python's integers simply grow to fit. Java's fixed-size types don't, so the program uses `BigInteger`, from the `java.math` package, for values of any size:

```java
static BigInteger bigFactorial(int n) {
    BigInteger f = BigInteger.ONE;
    for (int i = 2; i <= n; i++) {
        f = f.multiply(BigInteger.valueOf(i));
    }
    return f;
}
```

A `BigInteger` is an object, so each multiplication creates a new value and does not change the old one. `multiply` takes another `BigInteger`, so the loop converts each number with `BigInteger.valueOf`. `BigInteger.ONE` is the starting value of 1.

Press **Run** to see the result for 1000:

```text
1000! has 2568 digits
It starts with 4023872600770937
```

The number has 2,568 digits. `toString` turns it into text so the program can count the digits and show the first sixteen. Printing the whole number is possible, but it fills the screen, so the program shows only part of it.

The loop runs 999 multiplications for 1000!. Each multiplication works on a larger number, so the time grows faster than the count of steps. That cost is the main limit on this approach, and it's the same for the recursive version.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does the iterative version handle 1000 without a stack problem, while the recursive version does not?
○ Because loops are faster than recursion.
→ Not quite. The stack is the issue, not the speed.
◉ Because the loop doesn't add a stack frame for each step, so the depth stays at one.
→ Yes. The recursive version made one call for each step, which is what the stack limit counts.
○ Because the recursive version uses a `long`.
→ Not quite. Both versions can use the same type. The difference is the calls.
````

````quiz
Why does `bigFactorial` use `BigInteger` rather than `long`?
○ Because `BigInteger` is faster than `long`.
→ Not quite. The reason is the size of the number.
◉ Because a `long` can't hold 1000!, while a `BigInteger` grows to the size the value needs.
→ Yes. The number of digits in 1000! is far beyond what a fixed-size type holds.
○ Because `long` can't be multiplied.
→ Not quite. A long can be multiplied, but its size is limited.
````

---

### Try it in the runner

Change `bigFactorial(1000)` to `bigFactorial(20)`, and predict the number of digits before you press **Run**. 20! is 2432902008176640000, which has 19 digits. Then predict the first sixteen digits. They're 2432902008176640, which is the start of the number.

---

A loop does the same work as the recursive method without adding a frame for each step. The next step measures how long each version takes.
