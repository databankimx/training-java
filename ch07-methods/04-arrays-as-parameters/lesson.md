## Arrays as parameters

A method can take an array, and it's common to do so: a method that adds up a list, finds its largest value, or prints its contents. Arrays are passed in a way that's different from `int` values, and the difference is one of the most useful things about Java methods.

Here's the program for this step, in `src/Main.java`:

```java
int[] scores = {90, 85, 77};
System.out.println("total: " + total(scores));

System.out.println("first before: " + scores[0]);
doubleFirst(scores);
System.out.println("first after: " + scores[0]);
```

Press **Run**. The output is:

```text
total: 252
first before: 90
first after: 180
```

The `total` method adds up the array and returns the sum. The `doubleFirst` method changes the array's first element. After the call, the caller's `scores[0]` is 180, so the change was seen outside the method.

### Passing a reference

Recall from the references chapter that an array variable holds a reference to the array. When an array is passed to a method, the method receives a copy of that reference, and both the caller and the method refer to the same array. So a change made through `values` in `doubleFirst` changes the array the caller has.

This is the rule that matters: a method can change the elements of an array it receives, and the caller sees the change. It's the same behavior as the `int` values in the last step, except that the copy is of a reference, so the object is shared.

### The length comes with the array

Notice that neither method receives a separate length. `total` uses `values.length`, which is part of the array itself, so it always matches the array it was given. C++ needs the size passed in as a second argument, because an array doesn't know its own length there. In Java, the method asks the array.

That makes array methods shorter and harder to get wrong, because the length can't disagree with the array.

### Reassigning inside the method

A method can't replace the caller's array by assigning to its parameter. Inside `doubleFirst`, `values = new int[]{1, 2, 3};` would change only the method's copy of the reference, and the caller's `scores` would still refer to the original array. The next step covers this in more detail, with the same rule for any object.

---

### Quiz

*These questions are written for this course.*

````quiz
After `doubleFirst(scores);`, what does `scores[0]` hold?
○ The original value, because arrays are copied when passed.
→ Not quite. The method receives a reference to the same array, so the change is seen by the caller.
◉ The doubled value, because the method changed the shared array.
→ Yes. Both names refer to the same array, so the change is visible outside the method.
○ 0, because the array is cleared.
→ Not quite. The method only changes the first element, and it doubles it.
````

````quiz
How does `total` know how many values to add?
○ It counts them by asking the user.
→ Not quite. The method reads the array it was given.
◉ It uses values.length, which is part of the array.
→ Yes. An array knows its own length, so no separate size is needed.
○ It always adds five values.
→ Not quite. The method works for an array of any length.
````

---

### Try it in the runner

Add `values = new int[]{1, 2, 3};` as the first line of `doubleFirst`, and then predict `scores[0]` after the call. It's still 90, because the method reassigned only its own copy of the reference. The caller's array was not touched. Remove the line before you continue.

Then add `scores[1] = 0;` after the `doubleFirst` call, and predict the total that `total(scores)` prints. The array is now 180, 0, and 77, so the total is 257. Remove the added lines before you continue.

---

An array passed to a method is shared with the caller, so changing its elements is visible outside. Reassigning the parameter itself is not. The next step applies the same rule to the other object types and states it precisely.
