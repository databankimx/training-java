## Arrays as parameters

Passing a single `int` to a method is straightforward, and the method gets its own copy. An array is different. Copying every element into the method would be slow for a large array, so Java passes a reference to the array instead, and that changes what the method can do with it.

Here's the program for this step, in `src/Main.java`. The method's parameter is written like an array:

```java
static void printNumbers(int[] numbers, int count) {
    for (int i = 0; i < count; i++) {
        System.out.println(numbers[i]);
    }
}
```

Press **Run**. The main method calls it twice, once for all five values and once for the first three:

```java
printNumbers(myNumbers, 5);
System.out.println("First three:");
printNumbers(myNumbers, 3);
```

The output is:

```text
10
20
30
40
50
First three:
10
20
30
```

### The array knows its length

In this program, `main` passes the size as a second argument, so the method can print only part of the array. Java has a better option for the full array: the method can ask the array for its length with `numbers.length`. The array carries its own length, so the method doesn't need a separate count to know where the array ends.

The count in this program lets the method print only part of the array. Using `numbers.length` in the loop prints every element, and it can't go past the end, since the length is always right.

### Changes reach the caller

Because the method works on the array's own elements, a change made inside it stays changed afterwards. Assigning to `numbers[0]` in the method changes the element in `main`'s array. This is the opposite of the behavior for a single `int`, where the method changes only its own copy. Keep it in mind when you decide whether a method should modify its input, since the caller will see the result.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does `printNumbers` in this program take a `count` parameter?
○ Because Java requires every method to have two parameters.
→ Not quite. The method has two parameters because it needs both the array and how many values to print.
◉ So it can print only part of the array, since the count limits the loop.
→ Yes. The array alone would let the method print every element, and the count changes how many it prints.
○ To make the method run faster.
→ Not quite. The count is there so the loop knows where to stop.
````

````quiz
After `printNumbers` assigns `numbers[0] = 99;`, what does `main` see in `myNumbers[0]`?
○ 10, because the method works on a copy.
→ Not quite. The method works on the array's own elements, not a copy.
◉ 99, because the change is made to the array itself.
→ Yes. The array's elements are changed, and the caller sees the change.
○ 0, because the array is cleared on each call.
→ Not quite. Nothing clears the array.
````

---

### Try it in the runner

Add `numbers[0] = 99;` inside `printNumbers`, before the loop. Predict the output before you press **Run**. The first call changes `myNumbers[0]` to 99 and then prints it, so the first line is 99. The loop prints the rest unchanged. The second call sees the changed value, so its first line is 99 too.

Then replace `count` in the loop condition with `numbers.length`, and predict the output. Both calls now print every element, since the loop uses the length of the array. The second call still passes 3, but the method ignores it, so the output has five values after `First three:` as well. Restore the original condition before you continue.

---

Passing an array to a method is how a method can work on a whole list. The last step in this chapter is recursion, which is a method that calls itself, and it needs the same care about when to stop.
