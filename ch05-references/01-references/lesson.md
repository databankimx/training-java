## References

When you write `int[] alias = original;`, Java doesn't copy the array. It copies the reference, so both variables now refer to the same array. This is the first thing to understand about object types in Java, and it's different from the `int` variables you've used so far, which each hold their own value.

Here's the program for this step, in `src/Main.java`:

```java
int[] original = {1, 2, 3};
int[] alias = original;

alias[0] = 99;
System.out.println("original[0]: " + original[0]);
System.out.println("alias[0]: " + alias[0]);
```

Press **Run**. The output is:

```text
original[0]: 99
alias[0]: 99
```

Changing an element through `alias` changes the element `original` sees, because both names refer to one array. The array exists once, and both variables are different routes to it.

### Reassigning a name

Assigning a new object to a variable changes which object that variable refers to. It doesn't change the object the variable used to refer to:

```java
alias = new int[]{7, 8, 9};
System.out.println("original[0] after alias is reassigned: " + original[0]);
System.out.println("alias[0] now: " + alias[0]);
```

The output is:

```text
original[0] after alias is reassigned: 99
alias[0] now: 7
```

`alias` now refers to a new array, with the values 7, 8, and 9. `original` still refers to the first array, and its first element is still 99. Only the variable that was reassigned has changed.

### Keeping references apart

This is why copying an array needs care. If you want two independent arrays with the same values, you can't use `=`, because that only makes a second name for the first. Copying is done with a method, such as `Arrays.copyOf`, which creates a new array and fills it with the values. The methods chapter covers how to call such methods.

### What the variable holds

A variable of an array type holds a reference, not the array's values. The array itself lives elsewhere in memory, and Java manages that memory for you. You never see the address or do arithmetic on it. Java offers no way to look inside a reference, which is one of the things that keeps Java programs safer than languages with raw pointers.

---

### Quiz

*These questions are written for this course.*

````quiz
After `int[] b = a;`, what happens when you change `b[0]`?
○ Only b changes, because it's a separate copy.
→ Not quite. The assignment copies the reference, not the array, so there is only one array.
◉ a[0] changes too, because both names refer to the same array.
→ Yes. Both variables refer to the same array, so the change is seen through either name.
○ Nothing changes, because arrays can't be modified.
→ Not quite. Arrays can be modified through any name that refers to them.
````

````quiz
After `alias = new int[]{7, 8, 9};`, what does `original` refer to?
○ The new array with 7, 8, and 9.
→ Not quite. Reassigning alias doesn't change what original refers to.
◉ The first array, which still holds its values.
→ Yes. Only the variable that was reassigned now refers to something else.
○ Nothing, because original was cleared.
→ Not quite. Java never clears an object because a name was reassigned.
````

---

### Try it in the runner

Add `original = new int[]{1, 2, 3};` after the last line, and then print `alias[0]`. Predict the value before you press **Run**. It's still 7, because `alias` still refers to the 7, 8, 9 array, and `original` being reassigned doesn't touch it. Remove the added lines before you continue.

Then change `alias = new int[]{7, 8, 9};` to `alias = original;`, and predict the value of `original[0]` after the change. It's 99, because `alias` now refers to the same array as `original` again.

---

Several names can refer to one object, and reassigning a name only changes which object that name refers to. The next step covers the special value that means a reference refers to nothing at all.
