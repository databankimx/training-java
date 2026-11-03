## Declaring arrays

A variable holds one value. An array holds a fixed-size list of values, all of the same type, under one name. Java arrays are objects, so you create one with `new`, and give it a length.

Here's the program for this step, in `src/Main.java`:

```java
int[] scores = new int[5];
scores[0] = 90;
scores[1] = 85;
scores[2] = 77;
scores[3] = 64;
scores[4] = 92;

System.out.println("First score: " + scores[0]);
System.out.println("Third score: " + scores[2]);
System.out.println("Length: " + scores.length);
```

Press **Run**. The output is:

```text
First score: 90
Third score: 77
Length: 5
```

`int[] scores` declares the variable. The square brackets after the type say it's an array of `int`, and the name follows. `new int[5]` creates the array itself, with room for five values.

### Indexing from zero

Each element has an index, and Java numbers them from 0. The first element is `scores[0]`, and the last is `scores[4]`, so an array of five elements has indexes 0 to 4. The program assigns each element with an index, then reads two of them back.

Index 2 is the third element, which is why `scores[2]` prints 77. The number in the square brackets is the position, counted from zero.

### The length

`scores.length` gives the number of elements. It's a field of the array, so there are no parentheses after it. The `String` you met in Chapter 1 uses `length()` with parentheses, which is a method. Arrays use the field, and the difference is worth remembering, because the two look alike.

The length is fixed when the array is created. You can't add a sixth element to this array, so if you need more room later, a different kind of collection is the right tool, which a later chapter covers.

### An index past the end

An index must be between 0 and `length - 1`. Java checks every index when the program runs, and an index outside that range throws an `ArrayIndexOutOfBoundsException`. The program stops, and the runner shows the error with its stack trace. Java never silently reads or writes memory outside the array.

That check is one of the protections Java gives you. In some other languages, an index past the end silently reads whatever happens to be in memory, and the bug can go unnoticed for a long time.

---

### Quiz

*These questions are written for this course.*

````quiz
What index refers to the third element of an array?
○ 3
→ Not quite. Indexes start at 0, so the third element is at index 2.
◉ 2
→ Yes. The first element is at 0, the second at 1, and the third at 2.
○ 1
→ Not quite. That's the second element.
````

````quiz
What is the value of `scores.length` for `int[] scores = new int[5];`?
○ 4
→ Not quite. The length is the number of elements, which is 5. The last index is 4.
◉ 5
→ Yes. An array created with 5 elements has a length of 5.
○ 0
→ Not quite. The length is set when the array is created, and it's 5 here.
````

---

### Try it in the runner

Add `scores[5] = 100;` after the last assignment, and press **Run**. The program stops at that line, with an `ArrayIndexOutOfBoundsException`, so none of the three output lines appear. Predict the message before you press Run: it says `Index 5 out of bounds for length 5`. Remove the line before you continue.

Then change `scores.length` in the last line to `scores.length()`. The build fails, because an array has a length field, not a length method.

---

Arrays give one name to a whole list of values, and each value is reached by its index. The next step shows the ways to fill an array when you create it.
