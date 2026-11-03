## Return values and overloading

A method sends its result back with `return`. You've seen that in the last two steps. This step covers the rules for return values, and then overloading, where the same name serves several methods.

Here's the program for this step, in `src/Main.java`:

```java
System.out.println("add(2, 3): " + add(2, 3));
System.out.println("add(2.5, 1.5): " + add(2.5, 1.5));
System.out.println("add(1, 2, 3): " + add(1, 2, 3));
```

The program has three methods called `add`. Press **Run**. The output is:

```text
add(2, 3): 5
add(2.5, 1.5): 4.0
add(1, 2, 3): 6
```

Each call goes to the matching method. Java looks at the number and types of the arguments, and chooses the version whose parameters match.

### Overloading

Overloading means two or more methods share a name, and their parameter lists differ. The three `add` methods are:

* `add(int a, int b)`, for two whole numbers.
* `add(double a, double b)`, for two decimals.
* `add(int a, int b, int c)`, for three whole numbers.

The return type is not part of the choice. Two methods with the same parameters but different return types are not allowed, because the call would be ambiguous. Choose a different parameter list instead.

The choice is made when the program is compiled, from the types of the arguments. `add(2, 3)` uses the `int` version, because both arguments are `int`. `add(2.5, 1.5)` uses the `double` version, because both arguments are decimals.

### Replacing default values

Overloading is Java's replacement for default parameter values. The greeting example has two methods:

```java
static String greeting() {
    return "Hello, world.";
}

static String greeting(String name) {
    return "Hello, " + name + ".";
}
```

The output is:

```text
greeting: Hello, world.
greeting(Ada): Hello, Ada.
```

The version with no parameters acts as the default, and the caller chooses the version that matches the information it has. The same pattern works for any number of options, and each version is simple to read on its own.

### Returning several values

A method returns one value. When you need several, return an array or an object that holds them, rather than trying to return more than one value. A later chapter covers classes, which are the usual way to hold a group of related values.

---

### Quiz

*These questions are written for this course.*

````quiz
Which two methods can be declared in the same class?
○ int add(int a, int b) and double add(int a, int b)
→ Not quite. These have the same parameter list, so the call would be ambiguous. Changing only the return type isn't enough.
◉ int add(int a, int b) and double add(double a, double b)
→ Yes. The parameter types differ, so the compiler can tell which one to call.
○ int add(int a, int b) and int add(int x, int y)
→ Not quite. Renaming the parameters doesn't change the parameter types, so these clash.
````

````quiz
Which version does `add(2, 3)` call in the program?
○ The double version, because it's the most precise.
→ Not quite. Java doesn't choose by precision. It matches the types of the arguments.
◉ The int version, because both arguments are int.
→ Yes. The arguments match the int parameters exactly.
○ The three-parameter version.
→ Not quite. That needs three arguments, and this call has two.
````

---

### Try it in the runner

Change `add(2, 3)` to `add(2.0, 3)`, and predict the version it calls. The first argument is a `double`, so the call doesn't match the `int` version. The `double` version matches, because the `int` argument can be promoted to `double`. The output is 5.0. Restore the original call before you continue.

Then add a method `static int add(int a, int b) { return a - b; }`, and predict the result. The compiler reports a duplicate method, because the same name and parameter types are declared twice. Remove the method before you continue.

---

Overloading lets one name describe a family of related methods, with the caller choosing the right one. The next step covers arrays as parameters, which work differently from the values you've passed so far.
