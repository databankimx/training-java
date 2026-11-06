## References and arrays

You've used `numbers[1]` for an element of an array, and the functions chapter said that an array parameter receives a reference to the array. This step shows how the two ideas fit together.

Here's the program for this step, in `src/Main.java`:

```java
int[] numbers = {10, 20, 30};
int[] first = numbers;

System.out.println(first[0]);
System.out.println(first[1]);
System.out.println(first[2]);
```

Press **Run**. The output is:

```text
10
20
30
```

The variable `first` refers to the same array as `numbers`. Each element is reached by its index, counting from zero, and `first[2]` is the same element as `numbers[2]`, the third value, 30.

### Indexes, not addresses

Java reads the array elements by index. Elements are reached with a square-bracket index, and the index is checked against the array's length. An index past the end throws an `ArrayIndexOutOfBoundsException` rather than reading unrelated memory.

That's why the program reads each element by its index, and never by moving a reference. It's also why the check is built into the language, and the programmer doesn't have to make sure an index is in range by hand.

### Passing the array

The last line of the program passes the array to a method:

```java
printNumbers(first);
```

```java
static void printNumbers(int[] numbers) {
    for (int i = 0; i < numbers.length; i++) {
        System.out.println(numbers[i]);
    }
}
```

The method receives a reference to the same array, and it prints the three values. The method doesn't need a separate count of the elements. The array knows its own length, so `numbers.length` gives the number of elements.

---

### Quiz

*These questions are written for this course.*

````quiz
With `int[] first = numbers;` for `int[] numbers = {10, 20, 30};`, what is `first[2]`?
○ 10
→ Not quite. That's first[0], the first element.
◉ 30
→ Yes. first refers to the same array, and index 2 is the third element.
○ 20
→ Not quite. That's first[1], the second element.
````

````quiz
Why does `printNumbers` in the program not need a separate count?
○ Because the method always prints ten values.
→ Not quite. It prints as many values as the array holds.
◉ Because the array knows its own length, so numbers.length gives the count.
→ Yes. An array parameter carries its length with it.
○ Because Java arrays can't be passed to methods.
→ Not quite. Arrays are passed to methods all the time.
````

---

### Try it in the runner

Set `first` to `new int[]{40, 50}`, and predict what `printNumbers(first)` prints before you press **Run**. It prints `40` and `50`, since the method uses the length of the array it receives, which is now two.

Then add `System.out.println(first[2]);` at the end, and predict the result. It throws an `ArrayIndexOutOfBoundsException`, because index 2 is past the end of a two-element array. Remove the line before you continue.

---

An array reference reaches each element by index, and the array carries its own length. The summary gathers the chapter together, and the homework uses references in two small programs.
