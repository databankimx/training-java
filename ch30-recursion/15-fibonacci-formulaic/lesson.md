## A formula for Fibonacci

There is a mathematical formula that gives F(n) directly, without computing the earlier terms. It uses the golden ratio, phi, which is (1 + √5) / 2, about 1.618:

```text
phi = (1 + sqrt(5)) / 2
F(n) = (phi^n - (1 - phi)^n) / (phi - (1 - phi))
```

The formula does a fixed number of operations, whatever n is. It needs no recursion and no loop, so the call count is one and the work doesn't grow with n.

### The code

```java
static final double PHI = (1 + Math.sqrt(5)) / 2;

static long fibonacciFormula(int n) {
    if (n == 0 || n == 1) {
        return n;
    }
    double psi = 1 - PHI;
    return Math.round((Math.pow(PHI, n) - Math.pow(psi, n)) / (PHI - psi));
}
```

`Math.sqrt` takes the square root of 5, and `Math.pow` raises a number to a power. The two base cases are handled first, since the formula's division is not needed for them.

The formula's result should be very close to a whole number, but floating-point arithmetic isn't exact. `Math.round` gives the nearest whole number, which is the value we want. Truncating with a cast would give one less than the correct answer whenever the result falls just below the true value.

### Checking the formula

The formula can't be trusted without a check. The program compares it with the loop from the previous step, which is exact:

```java
long formula = fibonacciFormula(n);
long loop = fibonacciLoop(n);
System.out.println("Matches the loop: " + (formula == loop));
```

Press **Run**. For n = 40, the output is:

```text
Formula: F(40) = 102,334,155
Matches the loop: true
```

For n = 40, the rounding error is far smaller than half a unit, so the formula gives the exact answer.

### Where the formula fails

A `double` holds about 15 or 16 significant digits. As n grows, the numbers in the formula grow too, and the rounding error grows with them. The program's second check uses n = 80, where F(80) is larger than a `double` can represent exactly:

```text
Formula: F(80) = ...
Loop:    F(80) = 23,416,728,348,467,685
```

Run the program and read the formula's value for F(80). It will differ from the loop's value in the last digits, which shows the rounding error. The loop is exact and the formula isn't, so for large n the loop is the one to trust. The formula is useful for understanding the sequence, and for estimating a large value, but not for exact answers.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does the formula use `Math.round` rather than a cast to `long`?
○ Because casting is not allowed for doubles.
→ Not quite. A cast is allowed. The difference is in how the value is rounded.
◉ Because a cast truncates, which gives one less than the right answer when the result falls just below the whole number.
→ Yes. Math.round picks the nearest whole number, so small errors in either direction don't change the answer.
○ Because `Math.round` is faster.
→ Not quite. The reason is the answer's accuracy.
````

````quiz
What makes the formula's answer unreliable for large n?
○ The formula has a recursive call.
→ Not quite. The formula has no recursion.
◉ The floating-point rounding error grows with n, until it changes the whole-number answer.
→ Yes. A double can't hold the large values exactly.
○ The golden ratio is not a number.
→ Not quite. phi is a real number, and the formula uses it correctly.
````

---

### Try it in the runner

Change `n` to 20, and predict whether the formula matches the loop before you press **Run**. It matches, since the rounding error is small at n = 20. Then change `n` to 70, and predict the result of the check. The answer can differ, because F(70) is large enough for the error to appear.

---

The closed-form formula gives the answer with no recursion and no loop, but its floating-point arithmetic makes it approximate for large n. The next step looks at the conclusions across all the Fibonacci methods, and the final lessons of this chapter cover the file system.
