## References as parameters

The last step showed that a method can change the elements of an array it receives. This step states the rule precisely, because it applies to every object type, including `String` and the classes you'll write later.

Here's the program for this step, in `src/Main.java`:

```java
int[] data = {1, 2, 3};

replaceWith(data);
System.out.println("after replaceWith: " + data[0]);

fillWith(data, 7);
System.out.println("after fillWith: " + data[0]);
```

Press **Run**. The output is:

```text
inside replaceWith: 9
after replaceWith: 1
after fillWith: 7
```

The two calls behave differently, and the difference is the rule.

### What the method receives

Java passes a copy of the reference. The method's `values` and the caller's `data` are two variables that refer to the same array, as the references chapter described.

In `replaceWith`, the method assigns a new array to `values`. That changes which array `values` refers to, and only inside the method. The caller's `data` still refers to the original array, whose first element is 1. So the caller sees the old array after the call.

In `fillWith`, the method doesn't reassign `values`. It changes the elements of the array that `values` refers to, and that array is the one `data` refers to too. So the caller sees the change: every element is now 7.

### The rule

A method can change the object it receives, and the caller sees the change. A method can't change which object the caller's variable refers to, because the method only has its own copy of the reference.

This is why Java programmers say that references are passed by value. The value passed is the reference, and the method can't change the caller's variable through it.

### Getting a new value back

If a method needs to produce a new array for the caller, return it, and let the caller store it:

```java
data = makeArray();
```

That's the reliable way to give the caller a new object. It's the same reason a method that computes a value returns it, rather than trying to change the caller's variable.

---

### Quiz

*These questions are written for this course.*

````quiz
After `replaceWith(data);`, which array does `data` refer to?
○ The new array with 9, 9, 9.
→ Not quite. The method changed its own copy of the reference, not the caller's variable.
◉ The original array, whose first element is still 1.
→ Yes. The method reassigned only its copy of the reference.
○ No array, because the method set it to null.
→ Not quite. The method never touched the caller's variable.
````

````quiz
Which change made inside a method does the caller see?
○ Assigning a new array to the parameter.
→ Not quite. That changes only the method's copy of the reference.
◉ Changing an element of the array the parameter refers to.
→ Yes. Both names refer to the same array, so the change is seen by the caller.
○ Changing the parameter's type.
→ Not quite. Parameter types can't be changed inside a method.
````

---

### Try it in the runner

Change `fillWith(data, 7);` to `fillWith(data, 8);`, and predict the output of `after fillWith`. It's 8, because every element is set to the number passed in. Restore the original call before you continue.

Then in `replaceWith`, replace the assignment with `values[0] = 9;`, and predict the value printed after `replaceWith`. It's 9, because this time the element of the shared array is changed, and the caller sees it. Restore the original assignment before you continue.

---

A method can change an object it's given, but it can't change which object the caller's variable refers to. The next step covers recursion, a method that calls itself.
