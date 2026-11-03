## For loops

When you know how many times a loop should run, the `for` loop puts everything about it in one line. The parentheses hold three parts, separated by semicolons: where the loop starts, the condition that keeps it going, and the step taken after each pass.

Here's the first part of this step's program, in `src/Main.java`:

```java
for (int i = 1; i <= 3; i++) {
    System.out.println("for pass " + i);
}
```

Press **Run**. The output is:

```text
for pass 1
for pass 2
for pass 3
```

`int i = 1` runs once, at the start. It declares the counter `i`, which exists only inside the loop. `i <= 3` is checked before each pass, and `i++` runs after each pass, adding one to the counter.

Declaring the counter in the `for` line keeps it from leaking out of the loop, so the next loop in the program can use the name `i` again. Java does this for you, and it's the usual style.

### Changing the step

The step doesn't have to be one. The second loop counts down by five:

```java
for (int i = 10; i >= 0; i -= 5) {
    System.out.println("counting down: " + i);
}
```

The output is:

```text
counting down: 10
counting down: 5
counting down: 0
```

`i -= 5` takes five away after each pass. The condition `i >= 0` stops the loop once the counter goes below zero. The loop runs for 10, 5, and 0, and then 0 minus 5 fails the test.

### Nested loops

A loop inside another loop runs the inner one completely for each pass of the outer one. The last loop in the program prints a small grid:

```java
for (int row = 1; row <= 2; row++) {
    for (int col = 1; col <= 3; col++) {
        System.out.print(row + "," + col + " ");
    }
    System.out.println();
}
```

The output is:

```text
1,1 1,2 1,3 
2,1 2,2 2,3 
```

The outer loop runs twice, for `row` 1 and 2. For each row, the inner loop runs three times, for `col` 1 to 3. The `System.out.print` keeps the output on one line, and `System.out.println()` with no text ends the row.

Each row ends with a space, because the inner loop prints a space after every pair. That's harmless here, but it's worth knowing it's there.

### Which loop to choose

Use `for` when the number of passes is known, or when the counter moves in a regular way. Use `while` when the loop depends on something that changes unpredictably, such as user input. Both can do the same job, but the `for` line makes the counting easier to read.

---

### Quiz

*These questions are written for this course.*

````quiz
How many times does `for (int i = 0; i < 3; i++)` run?
○ Two times.
→ Not quite. The counter starts at 0 and runs while it's below 3, so it's 0, 1, and 2.
◉ Three times.
→ Yes. The counter takes the values 0, 1, and 2.
○ Four times.
→ Not quite. The condition i < 3 stops the loop before i reaches 3.
````

````quiz
In a nested loop, how many times does the inner loop body run in total when the outer loop runs 2 times and the inner loop runs 3 times on each pass?
○ 3
→ Not quite. That's only one pass of the inner loop.
◉ 6
→ Yes. The inner loop runs 3 times for each of the 2 passes of the outer loop.
○ 5
→ Not quite. The inner loop body runs 2 times 3, which is 6.
````

---

### Try it in the runner

Change `i <= 3` in the first loop to `i < 3`, and predict the output. The loop now runs for 1 and 2 only, so it prints two lines. Restore the original condition before you continue.

Then change `i -= 5` to `i -= 3` in the second loop, and predict the output. The counter takes the values 10, 7, 4, 1, and then stops at -2, which is below zero. It prints four lines: 10, 7, 4, and 1.

---

A `for` loop gathers the start, the test, and the step into one line. The next step covers `for` loops that go over every element of an array, which is the most common case in practice.
