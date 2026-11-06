## Declaring arrays

So far, each value has had its own variable. That works for a handful of values, but a program that handles a list of twelve temperatures or a week of sales figures would need twelve names. An array stores many values of the same type under one name, and you pick the value you want by its position.

Here's the program for this step, in `src/Main.java`:

```java
String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
int[] nums = {10, 20, 30};

System.out.println(cars[0]);
System.out.println(nums[2]);
```

Press **Run**. The output begins:

```text
Volvo
30
```

Read the declaration as "cars is an array of four strings". The type comes first, followed by empty square brackets, then the name. The braces hold the starting values, in order, and Java works out the length from them. Every element in an array has the same type, so `cars` holds only strings and `nums` only integers.

You can also create an array with a length and no values, as `int[] scores = new int[5];`. Each element then starts at the default for its type, which is 0 for an `int` and `null` for a `String`.

### Indexing from zero

To get one element, write the array's name followed by its position in square brackets. That position is called the index, and the first element is at index 0, not 1. So `cars[0]` is "Volvo", and `nums[2]` is the third element, 30.

The index starts at zero for the same reason positions in a string did in the previous chapter. It's the distance from the start of the array, so the first element is zero steps away. Once you've got used to it, it stops feeling strange.

### Changing elements

You can read an element and write to it, in the same way you'd use a variable:

```java
cars[0] = "Opel";
nums[2] += 5;

System.out.println(cars[0]);
System.out.println(nums[2]);
```

The output is:

```text
Opel
35
```

Now `cars[0]` holds "Opel", and `nums[2]` holds 35. The `+=` works here as it does on any `int` variable, since `nums[2]` is an `int` that happens to have a position.

### Going past the end

An array of four elements has valid indexes 0, 1, 2, and 3. Asking for index 4 is outside the array, and Java checks every index. The program stops with an `ArrayIndexOutOfBoundsException`, and the runner shows the message and the stack trace.

That check is a useful protection. A bad index is reported at the point it happens, rather than causing a wrong answer later. The check is made when the program runs, since the index may only be known then.

---

### Quiz

*These questions are written for this course.*

````quiz
What is the index of the third element in `int[] nums = {10, 20, 30};`?
○ 3
→ Not quite. That's past the end of a three-element array.
◉ 2
→ Yes. Indexes start at 0, so the third element is at index 2.
○ 1
→ Not quite. That's the second element.
````

````quiz
What is `cars[0]` after `cars[0] = "Opel";`?
○ "Volvo"
→ Not quite. The assignment replaced the value at index 0.
◉ "Opel"
→ Yes. Assignment through an index works just like assignment to a variable.
○ An error, because arrays can't be changed.
→ Not quite. Array elements can be changed, unless the array is declared final.
````

---

### Try it in the runner

Add a line that sets `int index = 4;`, then prints `cars[index]`. Predict what happens before you press **Run**. The array has four elements, so index 4 doesn't exist. Java checks the index when the program runs, and it throws an `ArrayIndexOutOfBoundsException`. The runner shows the message, which includes the index and the length, and the program stops there. Remove the line before you continue.

---

An array gives one name to a whole list of values, and an index picks out one of them. The next step covers how to fill an array when you create it, and how to find out how long it is.
