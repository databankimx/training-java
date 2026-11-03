## Defining methods

A method is a named block of code that does one job. You've already written one: `main` is a method, and the runner calls it first. Writing your own methods lets you name each job, and call it from as many places as you need.

Here's the program for this step, in `src/Main.java`:

```java
public class Main {
    public static void main(String[] args) {
        greet();
        printSquare(4);
        System.out.println("Sum: " + add(2, 3));
    }

    static void greet() {
        System.out.println("Hello from a method.");
    }

    static void printSquare(int n) {
        System.out.println(n + " squared is " + (n * n));
    }

    static int add(int a, int b) {
        return a + b;
    }
}
```

Press **Run**. The output is:

```text
Hello from a method.
4 squared is 16
Sum: 5
```

The `main` method calls three others. Each call names the method and gives it its inputs in parentheses, and the method runs its own block and then returns to the line that called it.

### The shape of a method

Each method declaration has four parts, in this order:

* `static`, which lets the method be called from `main`, as this step needs. You'll see what it means for instance methods in the classes chapter.
* A return type, such as `void` for a method that returns nothing, or `int` for one that returns an integer.
* A name, written in camel case, such as `printSquare`.
* A list of parameters in parentheses, with a type for each one. A method with no inputs has empty parentheses, as `greet()` does.

The body goes in curly braces, like the bodies of `if` and loops.

### Where methods go

Java doesn't need a method to be declared before it's used. The `main` method calls `add` before `add` appears in the file, and the compiler finds it anyway. C++ requires a declaration before the first call, which is why that course has a separate step for it, and Java doesn't.

The methods here are placed below `main` by convention, and the program reads top to bottom: the main steps come first, and the details follow.

### void and return

`greet` and `printSquare` have the return type `void`, which means they do their work and return nothing. `add` has the return type `int`, and its `return a + b;` sends the sum back to the caller. A method with a return type must return a value of that type on every path, and the compiler checks it.

---

### Quiz

*These questions are written for this course.*

````quiz
Which return type means a method returns nothing?
○ null
→ Not quite. null is a value a method can return for an object type, not a return type that means nothing.
◉ void
→ Yes. A void method does its work and returns no value.
○ empty
→ Not quite. Java has no empty return type. The word is void.
````

````quiz
Why can `main` call `add` even though `add` is written after `main`?
○ Because add is declared static.
→ Not quite. static lets it be called from main, but the reason it works is different.
◉ Because Java finds methods anywhere in the class, so no forward declaration is needed.
→ Yes. The compiler looks at the whole class before it checks the calls.
○ Because the program runs the methods in alphabetical order.
→ Not quite. Methods are called in the order the code asks for them.
````

---

### Try it in the runner

Change `add` to return `a * b`, and predict the `Sum` line. The method now multiplies, so `add(2, 3)` gives 6, and the line still reads `Sum: 6`. The name no longer matches what the method does, which is a reminder to name methods for their job. Restore the addition before you continue.

Then change `greet` to return `int`, leaving its body as it is, and press **Run**. The build fails, because a method with a return type must return a value. Restore `void` before you continue.

---

A method names a job, and a call runs it from anywhere in the class. The next step covers parameters, the inputs a method receives from its caller.
