## Parameters

A parameter is a variable a method receives from its caller, and the caller supplies the value as an argument. Java has one rule for passing values, and it matters for every method you write.

Here's the program for this step, in `src/Main.java`:

```java
int count = 5;
increment(count);
System.out.println("count after increment: " + count);
```

```java
static void increment(int value) {
    value = value + 1;
    System.out.println("inside increment: " + value);
}
```

Press **Run**. The output is:

```text
inside increment: 6
count after increment: 5
```

Inside `increment`, the parameter `value` becomes 6. But the caller's `count` is still 5 after the call. The method changed its own copy.

### Pass by value

Java always passes a copy of the argument. For an `int`, the copy is the number itself, so the method's `value` is a separate variable that happens to start with the same number. Changing it changes only the copy.

The method call is the same as giving the method a new variable, initialized with the caller's value. Once the method ends, the copy goes away, and the caller's variable was never touched.

### Returning a result instead

If a method's job is to produce a new value, return it, rather than trying to change the caller's variable. The program does that in the last part:

```java
int doubled = doubleValue(count);
System.out.println("doubled: " + doubled);
System.out.println("count still: " + count);
```

```java
static int doubleValue(int value) {
    return value * 2;
}
```

The output is:

```text
doubled: 10
count still: 5
```

`doubleValue` takes the value, computes the double, and returns it. The caller stores the result in `doubled`, and `count` keeps its value. This is the usual Java style: a method computes and returns, and the caller decides what to do with the answer.

### Several parameters

A method can take several parameters, separated by commas. Each one needs its own type, even when the types match: `add(int a, int b)` can't be written as `add(int a, b)`. The arguments are matched to the parameters by position, in the order they're written.

### What about default values?

C++ lets a parameter have a default value, so a caller can leave it out. Java doesn't have that. The usual way to get the same effect is to write two methods with the same name and different parameters, which the next step covers under overloading.

---

### Quiz

*These questions are written for this course.*

````quiz
After calling `increment(count)` where `count` is 5, what is `count`?
○ 6, because the method increased it.
→ Not quite. The method changed its own copy of the value, not the caller's variable.
◉ 5, because the method received a copy.
→ Yes. Java passes a copy of each primitive argument, so the caller's variable is unchanged.
○ 0, because the parameter starts empty.
→ Not quite. The parameter starts with the value the caller gave it.
````

````quiz
Why does `add(int a, b)` fail to compile?
○ Because a and b have to be the same number.
→ Not quite. They can be any values. The problem is the missing type.
◉ Because every parameter needs its own type.
→ Yes. Java needs the type written before each parameter name.
○ Because add takes too few parameters.
→ Not quite. The problem is the second parameter, which has no type.
````

---

### Try it in the runner

Change `increment` so it returns the new value, with `static int increment(int value)`, and `return value + 1;`. Then change the call to `count = increment(count);`, and predict `count` afterwards. It's 6, because the caller now stores the returned value. Restore the original method before you continue.

Then add a parameter to `doubleValue`, such as `int times`, and change the body to `return value * times;`. Predict what the existing call does. It fails to compile, because the call still gives one argument, and the method now needs two. Remove the new parameter before you continue.

---

A parameter is a copy, so a method changes the caller's data only through what it returns. The next step covers return values in more detail, and how one name can cover several methods.
