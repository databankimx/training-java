## Defining methods

So far, each program has been one long `main`. That works while the program is short, but a larger program repeats the same steps in several places, and the same code in several places is hard to change. A method gives a block of code a name, so you write it once and call it wherever you need it.

Here's the program for this step, in `src/Main.java`:

```java
static void greet() {
    System.out.println("I just got executed!");
}
```

The definition has four parts. `static` lets the method be called from `main` without an object, which the classes chapter explains. `void` is the return type, which says the method gives back nothing. `greet` is the name, followed by parentheses, which are empty here. The braces hold the body, which is the code that runs when the method is called.

Calling the method is one line, the method's name followed by parentheses:

```java
System.out.println("Before the calls");
greet();
greet();
System.out.println("After the calls");
```

Press **Run**. The output is:

```text
Before the calls
I just got executed!
I just got executed!
After the calls
```

The method runs twice, because it's called twice. Each call jumps to the body, runs it, and comes back to the line after the call.

### No declarations are needed

The compiler reads the whole class before it checks the calls, so a method can be called before the line where it's defined. The program places `greet` above `main` for readability, but it would work below `main` too.

Many programmers put `main` at the top and the methods after it, and others define each method before it's used. Either is fine. The rule that matters in Java is that the method must exist in a class the call can reach, and that its parameters and return type match the call.

---

### Quiz

*These questions are written for this course.*

````quiz
What does the `void` before a method's name mean?
○ The method takes no parameters.
→ Not quite. The empty parentheses after the name mean that.
◉ The method returns no value.
→ Yes. void means there's no return value.
○ The method does nothing when called.
→ Not quite. A void method can do a great deal. It just doesn't return a value.
````

````quiz
Why can `greet();` appear in `main` even when `greet` is defined below `main`?
○ Because Java runs methods in the order they are written.
→ Not quite. The order of definitions doesn't decide what can be called.
◉ Because the compiler reads the whole class before it checks the calls.
→ Yes. The compiler reads the whole class, so a method can be called from anywhere in it.
○ Because greet is declared static.
→ Not quite. static lets the method be called from main, but it isn't why the call works before the definition.
````

---

### Try it in the runner

Move the definition of `greet` below `main`, and press **Run**. The program still builds and prints the same output, because the compiler reads the whole class before it checks the calls.

Then change the call `greet();` to `greet(5);`, and predict the result before you press **Run**. The build fails, because `greet` takes no parameters, and the call passes one. Restore the call before you continue.

---

A method is a named block of code. The next step gives methods inputs, so the same method can do different work each time it's called.
