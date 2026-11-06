## What is recursion?

Recursion occurs when a method calls itself. That may sound strange, but many problems are most naturally solved that way. Sorting algorithms, walking through nested structures such as folders and trees, and the Fibonacci sequence all have recursive solutions.

The method in this step calls itself and has nothing to stop it:

```java
static void recursionOverflow() {
    calls++;
    recursionOverflow();
}
```

Each call waits for the next one to finish, and each waiting call takes up space on the call stack. The stack is a fixed amount of memory, so a recursion that never ends runs out of it. Java then throws a `StackOverflowError`.

Press **Run**. The output is:

```text
Recursion stopped with a StackOverflowError
More than 1000 calls were made: true
```

The program catches the error with an ordinary `try` and `catch`. The count shows that the method was called many times before the stack ran out. The exact number depends on the machine and on the size of the method's frames, so the program only checks that it's more than 1000.

### Two warnings

Recursion costs memory and time, because every call adds a frame to the stack and takes time to set up. A recursion that goes too deep fails, so a recursive method must be designed so that its depth is limited. If a problem needs a very deep recursion, the design should change, rather than the amount of stack available. The stack size can be changed when Java starts with the `-Xss` option, but that's a workaround, not a design.

### The base case

Every recursive method needs a condition that ends the recursion. That condition is the base case: the situation in which the method returns without calling itself. The next step adds one, so that the method finishes normally.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does `recursionOverflow` fail?
○ Because methods cannot call themselves.
→ Not quite. Methods can call themselves, and this one does.
◉ Because it has no base case, so the calls never stop and the stack runs out.
→ Yes. A recursion needs a condition that ends it.
○ Because the variable `calls` is never set.
→ Not quite. The variable is set on each call, and the error comes from the stack.
````

````quiz
What kind of error does Java throw when a recursion runs too deep?
○ NullPointerException
→ Not quite. That error is for a null reference.
◉ StackOverflowError
→ Yes. The call stack has run out of room.
○ ArithmeticException
→ Not quite. That error is for arithmetic such as division by zero.
````

---

### Try it in the runner

Add a `System.out.println` of `calls` after the catch, and predict whether it's a small number or a large one before you press **Run**. It's large, since the method runs until the stack is full. The exact count varies from one machine to the next.

---

A recursive method calls itself, and it needs a base case to finish. The next step adds the base case to a countdown.
