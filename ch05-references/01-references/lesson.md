## References

A variable is a name for a value stored in memory. Sometimes you want a second name for the same thing, so that either name refers to one object. In Java, a variable of an object type holds a reference to the object, and a second variable can hold a reference to the same object. The two names then refer to one thing.

Here's the program for this step, in `src/Main.java`:

```java
int[] food = {1, 2, 3};
int[] meal = food;   // meal is another name for the same array

System.out.println("food[0]: " + food[0]);
System.out.println("meal[0]: " + meal[0]);
```

Press **Run**. The output begins:

```text
food[0]: 1
meal[0]: 1
```

Both lines print the same value, because `meal` isn't a copy of `food`. It's a second name for the same array. The `=` copies the reference, not the array, and that's an easy point to mix up.

Next, the program changes an element through the second name:

```java
meal[0] = 99;
System.out.println("food[0] after changing meal: " + food[0]);
```

The output is `food[0] after changing meal: 99`. Changing an element through `meal` changed the array that `food` refers to, since they are the same array under two names.

### Rules for references

Java references are to objects, such as arrays and strings. A variable of a primitive type, such as `int`, holds its own value, and it can't be a second name for another `int` variable. That's why this step uses an array.

A local variable must be given a value before it's used:

```java
int[] nothing;
System.out.println(nothing[0]);   // error: nothing might not have been initialized
```

Once a reference is set, assigning a new object to the variable makes it refer to that object, and the old object is unchanged. Assigning to an element changes the object, which every name for it sees.

### Why references matter

You already saw references as parameters, where a method receives a reference to its caller's array and can change its elements. A reference declared inside a method works the same way. The rules are the same whatever the reason: the reference and the object are one thing, as far as changes to the object are concerned.

---

### Quiz

*These questions are written for this course.*

````quiz
After `int[] meal = food;`, what happens when you write `meal[0] = 5;`?
○ meal becomes a separate array holding 5, and food is unchanged.
→ Not quite. The assignment copied the reference, not the array, so there is only one array.
◉ food[0] becomes 5, because meal refers to the same array as food.
→ Yes. Changing an element through either name changes the shared array.
○ An error, because an array can't be changed through a reference.
→ Not quite. Changing elements through a reference is the normal way to use one.
````

````quiz
After `int[] meal = food;`, what happens when you write `meal = new int[]{7, 8};`?
○ food is changed to hold 7 and 8.
→ Not quite. Assigning a new object to meal changes only meal.
◉ meal refers to a new array, and food still refers to the original.
→ Yes. Reassigning a variable changes which object it refers to, and no others.
○ Both variables are cleared.
→ Not quite. Reassignment doesn't clear the original object.
````

---

### Try it in the runner

Add a second reference to `food`, called `dinner`, and change `dinner[0]` to `10`. Predict what `food[0]` and `meal[0]` show before you press **Run**. They both show `10`, since all three names refer to the same array.

---

A reference is another name for an existing object, and changes to the object are seen through every name for it. The next step covers null, the value a reference has when it refers to nothing.
