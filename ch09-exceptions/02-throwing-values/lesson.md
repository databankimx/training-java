## Throwing values

A program often reaches a point where it can't carry on normally. A value is missing, a number is out of range, or a file won't open. The usual tool for that is an exception: the code throws an object, the normal flow stops at once, and control passes to a `catch` block that deals with the problem.

Java can't throw a plain `int`, so the thrown value is an object. The object's class extends `Exception`, and it can hold whatever information the handler needs. Here the class holds the age that was refused.

Here's the program for this step, in `src/Main.java`. The method refuses an age below 18 by throwing an object that carries it:

```java
static void checkAccess(int age) throws AccessDeniedException {
    if (age < 18) {
        throw new AccessDeniedException(age);
    }
    System.out.println("Access granted - you are old enough.");
}
```

The `throw` statement takes an object, here an `AccessDeniedException` built from the age, and sends it out of the method. Nothing after the `throw` runs, and the call in `main` doesn't return normally. The `throws` clause in the method's declaration says the method can send out this exception.

The code that calls the method sits inside a `try` block, and a `catch` block follows it:

```java
try {
    checkAccess(25);
    checkAccess(15);
    System.out.println("This line never runs.");
} catch (AccessDeniedException myNum) {
    System.out.println("Access denied - you must be at least 18 years old.");
    System.out.println("Age is: " + myNum.age);
}
```

Press **Run**. The output is:

```text
Access granted - you are old enough.
Access denied - you must be at least 18 years old.
Age is: 15
```

The first call passes, and prints its message. The second call throws an exception carrying 15. Control leaves `checkAccess` immediately, skips the line that says `This line never runs.`, and jumps to the `catch` block. The `catch` names the exception type, and the variable `myNum` receives the object that was thrown, so the program reads the age from it.

### Why use this instead of a return value

A method can report failure by returning a special value, such as -1. That works for simple cases, but the caller has to check every call, and a forgotten check lets the bad value carry on. An exception can't be ignored in the same way. If nothing catches it, the program stops with a message, rather than continuing with a wrong answer.

Exceptions also cross method boundaries. The `throw` happened inside `checkAccess`, but the `catch` sits in `main`. You don't need to pass the error back through every method in between, and the methods in between don't need to know about it.

### Only matching catches handle a throw

A `catch` names a type, and it handles the exception objects of that type and of its subclasses. A thrown `AccessDeniedException` is caught by a `catch` for `AccessDeniedException`, or for `Exception`, but not by a `catch` for an unrelated type. The next step covers the two families of exceptions, and the rules the compiler applies to them.

---

### Quiz

*These questions are written for this course.*

````quiz
When `throw` runs inside a method, what happens to the rest of that method?
○ It runs, and then the throw takes effect.
→ Not quite. The throw ends the method at once.
◉ It doesn't run, and control passes to the matching catch.
→ Yes. Nothing after the throw runs, and the search for a catch begins.
○ It runs only if the exception is caught.
→ Not quite. The rest of the method is skipped whether or not a catch exists.
````

````quiz
What kind of value can Java throw?
○ A plain int, such as 15.
→ Not quite. Java throws only objects of classes that extend Throwable.
◉ An object of a class that extends Throwable, such as an exception.
→ Yes. The object carries the information, and the catch receives it.
○ A String with the error message.
→ Not quite. A String isn't a Throwable, so it can't be thrown.
````

---

### Try it in the runner

Change `checkAccess(15)` to `checkAccess(30)`, and predict whether the catch block runs and which lines print. Both calls now pass, so there's no throw. The output is the granted message twice, followed by `This line never runs.`, since the try block finishes normally.

Then change the catch type to `java.io.IOException`, and press **Run**. The compiler reports an error, because nothing in the try block can throw an `IOException`, so the catch can never apply. Change the catch back to `AccessDeniedException` before you continue.

---

A `throw` sends an exception object out of the normal flow, to the nearest `catch` of a matching type. The next step uses one exception to report several kinds of error, each with its own code.
