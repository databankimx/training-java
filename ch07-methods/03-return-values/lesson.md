## Return values and overloading

A method that only prints is useful, but many methods are better at calculating something and handing the result back. A method that returns a value gives its caller a result to store, print, or use in another calculation.

Here's the first part of this step's program, in `src/Main.java`:

```java
static int addFive(int x) {
    return 5 + x;
}
```

The `int` before the name is the return type. It says the method gives back an `int`. The `return` statement sends the value back to the caller, and it also ends the method at that point. Nothing after a `return` runs.

The program calls the method and uses its result:

```java
int res = addFive(3);
System.out.println(res);
```

The output is `8`. The call `addFive(3)` is replaced by its result, 8, which is then stored in `res`. A method call can appear anywhere a value of its return type can appear, so you can print it, store it, or put it inside another expression.

A method that gives nothing back uses `void` as its return type, as the first step did. `void` means there's no result to use, so a `void` method can't be the right-hand side of an assignment.

### Overloading

The program has two methods called `add`:

```java
static int add(int x, int y) {
    return x + y;
}

static double add(double x, double y) {
    return x + y;
}
```

Java allows this, because the parameters are different. When you call `add`, the compiler looks at the arguments and picks the version that matches them:

```java
int whole = add(8, 5);
double decimal = add(4.3, 6.26);
```

The first call passes `int` values, so the `int` version runs and returns 13. The second passes `double` values, so the `double` version runs and returns 10.56. The program prints the results:

```text
Int: 13
Double: 10.56
```

The `double` line uses `printf` with `%.2f`, which prints two places after the point. Without it, the sum of two decimal values can show extra digits from how decimals are stored.

Overloads must differ in their parameters. Two methods that differ only in their return type can't be overloaded, because the call `add(8, 5);` would give the compiler no way to choose, and it would reject the code.

---

### Quiz

*These questions are written for this course.*

````quiz
What happens to the statements after a `return` in a method?
○ They run, and then the method returns.
→ Not quite. return ends the method at that point.
◉ They don't run, because the method has already finished.
→ Yes. return leaves the method immediately.
○ They run only if the method has no return value.
→ Not quite. A return always ends the method, whatever its type.
````

````quiz
Which pair of methods can be overloaded?
○ int f(int x) and double f(int x)
→ Not quite. They differ only in their return type, so a call can't tell them apart.
◉ int f(int x) and double f(double x)
→ Yes. Their parameters differ, so the compiler can choose between them.
○ int f(int x) and int f(int x) with the same body.
→ Not quite. Two methods with identical parameters can't coexist, because the call would have no way to choose between them.
````

---

### Try it in the runner

Call `add(8, 5.0)`, which passes an `int` and a `double`, and predict which overload is chosen. The `int` version doesn't accept a `double` argument, but an `int` can be widened to a `double`, so only the `double` version fits. The call compiles, and the result is 13.0. Change `5.0` to `5` to see the `int` version run again.

---

A method can take values in and give a result back, and overloading lets one name serve several types. The next step uses arrays, which are harder to pass to a method than a single value, and the length of the array becomes something the method can find for itself.
