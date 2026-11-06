## Reference parameters

The parameters in this chapter so far have each received a copy of the caller's value. Changing a copy inside the method leaves the caller's variable alone. That's the right behavior most of the time, but some jobs need to change the caller's data, and a swap is the classic example. A reference parameter gives the method access to the caller's data itself.

Every parameter of a primitive type is a copy. A parameter of an object type, such as an array, receives a reference to the object. So this step does the swap on a two-element array, which holds the two values.

Here's the program for this step, in `src/Main.java`:

```java
static void swapNums(int[] nums) {
    int z = nums[0];
    nums[0] = nums[1];
    nums[1] = z;
}
```

The parameter is an array, so the method receives a reference to it. Each element is reached by its index, and writing to `nums[0]` changes the element the caller holds. The method swaps the two elements in place.

Press **Run**. The output is:

```text
10 20
20 10
```

The first line is printed before the call, and the second after it. The swap ran on the array `main` owns, so the values it holds have changed. The program copies `firstNum` and `secondNum` into the array before the call, and reads them back from the array afterwards.

### Copy or reference

Compare this with the earlier parameters. In `introduce(String fname, int age)`, the method receives copies, so changing `age` inside it would have no effect outside. A parameter of a primitive type is always a copy, and a parameter of an object type is a reference to the object.

Choose the copy by default. It keeps the method from changing things it wasn't asked to change, and it makes the method's effect easier to see from its call. Use a reference when the job is to change the caller's data, or when the data is large and copying it would be wasteful. Passing an array is the usual case.

### Why a swap needs references

If you write the swap with two `int` parameters, as `swapNums(int x, int y)`, the method still runs, and the swap happens inside it. But the swap is then done on copies, and `main` prints the original values again. That's a bug that compiles without any warning, which is why the array matters. Check what a method changes, and what it's meant to change.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does the swap in this program use an array rather than two `int` parameters?
○ Because arrays make the swap faster.
→ Not quite. The reason is what the method can change, not speed.
◉ Because the method must change the caller's values, and an array is passed as a reference.
→ Yes. Two int parameters would be copies, so the caller would see no change.
○ Because two int parameters won't compile.
→ Not quite. They compile, but the caller would see no change.
````

````quiz
What does a method receive when its parameter is an array?
○ A copy of every element.
→ Not quite. Copying every element would be slow, and Java doesn't do it.
◉ A reference to the array, so changes to its elements are visible to the caller.
→ Yes. The method and the caller share the same array.
○ A copy that is cleared when the method returns.
→ Not quite. That describes an ordinary int parameter.
````

---

### Try it in the runner

Change `swapNums` to take two `int` parameters, `swapNums(int x, int y)`, with the same body but using `x` and `y`, and change the call to pass `firstNum` and `secondNum` directly. Press **Run**. Predict the second line of output before you do. The swap happens on copies, so the second line shows 10 20 again, the same as before the call. Restore the array version before you continue.

---

Reference parameters let a method change its caller's data, and a parameter of a primitive type always copies. The next step is recursion, a method that calls itself.
