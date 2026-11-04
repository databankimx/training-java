## Error codes

The last step threw an object that carried the age itself. Often the value thrown is a code that says what went wrong, and the catch block chooses a message from it. This step uses that approach, with a `switch` from the decisions chapter to pick the message.

Java throws objects, not plain numbers, so the code here is an `enum`. An enum is a fixed set of named values, and the exception carries one of them. The exception object is the thing that's thrown, and the enum value tells the handler what went wrong.

Here's the program for this step, in `src/Main.java`. The method throws a different code for each kind of failure:

```java
static void checkAccess(int age) throws AccessException {
    if (age < 18) {
        throw new AccessException(AccessError.UNDERAGE);
    }
    if (age >= 65) {
        throw new AccessException(AccessError.TOO_OLD);
    }
    System.out.println("Access granted.");
}
```

Press **Run**, and enter `15` at the prompt. The output is:

```text
Enter your age: 15
Access denied! Underage!
```

The age is under 18, so the method throws an exception carrying `UNDERAGE`. The `catch` receives it as `err`, and the `switch` on `err.code` picks the message for that code. Enter `40`, and the method returns normally, printing `Access granted.` Enter `70`, and it throws an exception carrying `TOO_OLD`, which the switch turns into `Access denied! Too old!`.

The `switch` uses the arrow form from the decisions chapter. Each case runs only its own line, so no `break` is needed.

### The boundary

The range the method accepts is 18 up to, but not including, 65. An age of exactly 65 is refused, so the check is `age >= 65`, not `age > 65`. Testing the boundary values is the best way to catch an off-by-one error in a condition like this, and the Try asks you to do exactly that.

### The limit of codes

Error codes work, but a bare number says little. A reader sees `-2` and has to look up what it means, and nothing stops a different method from using `-2` for an unrelated reason. The enum fixes that. Each code has a name that says what it is, and two different causes can't share one value. A `switch` over the enum also tells the reader which cases exist. If you add a new code, a `switch` that doesn't list it won't be flagged by the compiler, so check every `switch` on the enum when you add one.

The next step uses exceptions from the standard library, which carry a message and a type that describe what failed.

### Input errors

If the user types a word instead of a number, `Scanner.nextInt` throws an `InputMismatchException`. Nothing in this program catches it, so the program stops and the runner shows the stack trace. That stops the program at the bad input, instead of reporting a wrong result. The homework shows how to check the input before reading it.

---

### Quiz

*These questions are written for this course.*

````quiz
An age of exactly 65 is refused. Which condition in the method produces that?
○ age > 65
→ Not quite. That refuses only ages above 65, so 65 would pass.
◉ age >= 65
→ Yes. The range accepted is 18 up to, but not including, 65.
○ age == 65
→ Not quite. That would refuse only the one age, and ages above 65 would pass.
````

````quiz
What does the enum add over a plain number such as -2?
○ It makes the program run more slowly.
→ Not quite. The cost of a code is negligible either way.
◉ Each value has a name, and two different causes can't share one value.
→ Yes. The name says what the code means, and the set of values is fixed.
○ It lets a catch handle any type of exception.
→ Not quite. The enum is a value the exception carries, and it doesn't change which catches apply.
````

---

### Try it in the runner

Enter `65` at the prompt, and predict the message. It's `Access denied! Too old!`. Then enter `64`, and predict again. It's `Access granted.`

Then enter `abc`, and predict the result. The input can't be read as a number, so `nextInt` throws an `InputMismatchException`. Nothing catches it, so the program stops with the stack trace. That's the input problem from the lesson, which the homework addresses.

---

A thrown code tells the catch block what went wrong, and a `switch` turns it into a message. The next step uses the standard exception classes, which carry a message and a type that describe what failed.
