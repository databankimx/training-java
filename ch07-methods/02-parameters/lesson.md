## Parameters

A method that always does the same thing is limited. Most methods need information from the code that calls them, and parameters are how that information gets in. A parameter is a variable named in the method's parentheses, and the value passed when the method is called is its argument.

Here's the program for this step, in `src/Main.java`:

```java
static void introduce(String fname, int age) {
    System.out.println(fname + " is " + age + " years old.");
}
```

The method has two parameters, `fname` and `age`, each with a type. The call supplies the arguments in the same order:

```java
introduce("Scott", 53);
```

Press **Run**. The output is:

```text
Scott is 53 years old.
```

Inside the method, `fname` holds "Scott" and `age` holds 53. The method doesn't know or care where those values came from. Calling it with other arguments gives different output, from the same code.

The arguments must match the parameters in number and type. Passing the arguments in the wrong order, such as `introduce(53, "Scott")`, fails to compile, because 53 can't be used as a `String`.

### Overloads instead of default values

The same result comes from writing two methods with the same name, one of which calls the other:

```java
static void welcome() {
    welcome("United States");
}

static void welcome(String country) {
    System.out.println("The country is: " + country);
}
```

The program calls both versions:

```java
welcome();
welcome("Mexico");
```

The output is:

```text
The country is: United States
The country is: Mexico
```

The first call has no argument, so the method with no parameters runs, and it passes "United States" to the other version. The second call passes "Mexico" directly. Methods with the same name and different parameters are overloads, and Java chooses between them by the arguments in the call. The methods chapter's overloading step covers that choice in more detail.

---

### Quiz

*These questions are written for this course.*

````quiz
In `static void introduce(String fname, int age)`, what is `fname`?
○ An argument.
→ Not quite. The argument is the value passed in, such as "Scott".
◉ A parameter.
→ Yes. The parameter is the name the method uses for the value it receives.
○ A return value.
→ Not quite. A return value is what a method sends back, and this one returns nothing.
````

````quiz
Which is the usual way to let a caller leave out an argument?
○ Give the parameter a default in its declaration, such as int a = 1.
→ Not quite. A parameter declaration can't include a default value.
◉ Write an overload with fewer parameters that calls the full method.
→ Yes. The overload supplies the default value and calls the method that does the work.
○ Pass null for the missing argument.
→ Not quite. null works only for object types, and it doesn't supply a value for an int.
````

---

### Try it in the runner

Call `introduce` with its arguments in the wrong order, as `introduce(53, "Scott")`, and press **Run**. Read the error message, and look for the argument the compiler complains about. Then put the arguments back in the right order.

Then remove the `welcome()` overload, and call `welcome()` from `main`. Predict the result. The build fails, because no method with no parameters exists. Restore the overload before you continue.

---

Parameters let one method handle many cases. The next step covers the other half of a method's interface: the value it sends back to the caller.
