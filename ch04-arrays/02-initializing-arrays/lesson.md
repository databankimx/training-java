## Initializing arrays

The last step set each array element in its braces, which is one way to start an array. Java offers several more, and you'll use each of them in different situations. This step covers the common ones, and the ways to find out how many elements an array holds.

Here's the start of this step's program, in `src/Main.java`. It begins with an array that's only partly filled in:

```java
int[] partial = new int[5];
partial[0] = 7;
partial[1] = 8;
System.out.println(partial[0] + " " + partial[1] + " " + partial[2] + " " + partial[4]);
```

The output is:

```text
7 8 0 0
```

The array has five elements, and only the first two are set. An array created with `new` gives every element a default value: zero for a number, `false` for a `boolean`, and `null` for an object such as a `String`. The elements that weren't set are therefore zero, and the program can read them safely.

A brace list must give a value for every element, so `new int[5]` followed by assignments is the way to fill only part of an array.

### Leaving out the size

If you give the values in the braces, Java counts them for you:

```java
String[] cars = {"Volvo", "BMW", "Ford", "Mazda", "Tesla"};
```

The square brackets are empty, and the array has five elements because five values are listed. Leaving out the size is the usual choice when you initialize the array right away. It's a good habit when the list is fixed and you don't want to count it yourself.

### Finding the length

The next lines of the program ask for the number of elements:

```java
System.out.println("Cars: " + cars.length);
System.out.println("Last: " + cars[cars.length - 1]);
```

The output is:

```text
Cars: 5
Last: Tesla
```

`length` is a field of the array, not a method, so it has no parentheses. Writing `cars.length()` is a compile error, which is a common mistake when moving between `String` and arrays. Use `length` rather than counting by hand, because the count stays correct if you change the list.

The last element is at index `cars.length - 1`, which is 4 here, the position after the four steps from index 0. Writing the index this way means that the code still finds the last element if the list changes.

### Filling an array later

You can also declare an array with a size and leave the values until afterwards:

```java
int[] squares = new int[3];
squares[0] = 1;
squares[1] = 4;
squares[2] = 9;
System.out.println(squares[2]);
```

The output is `9`. Here the array exists before it has any values, and every element starts at zero, so the program can set them one at a time. Reading an element before you set it gives its default, not a leftover value from memory.

Setting each element by hand works for a few values, but it doesn't scale. The loops chapter covers a loop that fills an array in one go.

---

### Quiz

*These questions are written for this course.*

````quiz
What does `int[] nums = new int[5];` hold in `nums[4]` before any value is set?
○ 4
→ Not quite. The index is 4, but the value stored there is not the index.
◉ 0
→ Yes. Every element of a new int array starts at zero.
○ An unpredictable value left in memory.
→ Not quite. Java sets every element to its default, so nothing is left over.
````

````quiz
Which expression gives the number of elements in `String[] cars = {"a", "b", "c"};`?
○ cars.length()
→ Not quite. length is a field of an array, so it has no parentheses.
◉ cars.length
→ Yes. Arrays have a length field, which is 3 here.
○ cars.size()
→ Not quite. size() is a method of collections, not of arrays.
````

---

### Try it in the runner

Change `partial` to `int[] partial = {7, 8, 9, 10, 11};`, and predict the first line of output. Every element has a value now, so the line should read `7 8 9 11`, since `partial[2]` is 9 and `partial[4]` is 11. Change the declaration back before you continue.

Then delete the line that sets `squares[2]`, so `squares[2]` is never assigned, and press **Run**. The value printed is 0, because that's the default for a new `int` array. It isn't a leftover value, which is why Java arrays are safe to read before every element is set.

---

Arrays can be created in several ways, and `length` tells you how many elements they hold. The loops chapter is what makes arrays useful, because it lets you visit every element without writing out each index.
