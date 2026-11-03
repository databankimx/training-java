## Enhanced for loops

Most of the time, a loop over an array only needs each element in turn. The enhanced `for` does that without a counter, and without any index to get wrong.

Here's the start of this step's program, in `src/Main.java`:

```java
int[] scores = {90, 85, 77};
for (int score : scores) {
    System.out.println("score: " + score);
}
```

Press **Run**. The output is:

```text
score: 90
score: 85
score: 77
```

The line reads as "for each `score` in `scores`". The loop variable `score` takes each element of the array in order, from the first to the last, and the block runs once for each. There's no counter and no `length` to check, so the loop can't go past the end.

### Over any collection of values

The enhanced `for` works with arrays of any type, including `String` arrays, which the next part of the program uses:

```java
String[] names = {"Ada", "Grace"};
int count = 0;
for (String name : names) {
    count++;
    System.out.println(count + ": " + name);
}
```

The output is:

```text
1: Ada
2: Grace
```

Here the loop variable is a `String`, and it refers to each name in turn. The counter `count` is a separate variable, which the loop updates itself, so the printed numbers count the passes.

### The loop variable is a copy of the value

The last loop shows a limit that matters. For an `int` array, the loop variable holds a copy of each element:

```java
for (int score : scores) {
    score = 0;
}
System.out.println("first score is still: " + scores[0]);
```

The output is:

```text
first score is still: 90
```

Setting `score` changes only the variable for that pass. It doesn't change the array, so `scores[0]` still holds 90. To change the elements, use an ordinary `for` loop with an index, as the last step showed.

When the elements are objects, such as `String` values, the loop variable holds a reference to each object. Changing the variable's reference doesn't change the array, and changing the object through the reference does, if the object can be changed. `String` objects can't be changed, so for text this is mostly a matter of not expecting the array to change.

### When to use the enhanced form

Use the enhanced `for` when you need every element and don't need its index. Use an ordinary `for` when you need the index, need to change the array, or need to skip elements.

---

### Quiz

*These questions are written for this course.*

````quiz
In `for (int score : scores)`, what does `score` hold on each pass?
○ The index of the element.
→ Not quite. The enhanced form gives you the element itself, not its index.
◉ The next element of the array, in order.
→ Yes. Each pass takes the next element, from the first to the last.
○ The length of the array.
→ Not quite. The length isn't used by the enhanced for.
````

````quiz
After `for (int score : scores) { score = 0; }`, what does `scores[0]` hold?
○ 0
→ Not quite. Assigning to the loop variable doesn't change the array.
◉ Its original value.
→ Yes. The loop variable is a copy of each int element.
○ null
→ Not quite. An int element can't be null.
````

---

### Try it in the runner

Add `scores[0] = 100;` after the last loop, and print `scores[0]`. Predict the output before you press **Run**. It's 100, because this time the array element itself is changed. Then remove the line, and change the enhanced loop to `for (int score : scores) { scores[0] = 0; }`. Predict the first score afterwards. It's 0, because the loop body changes the element through the array. Remove the change before you continue.

---

The enhanced `for` visits every element with no index to manage. The next step covers `break` and `continue`, which change how any of these loops run.
