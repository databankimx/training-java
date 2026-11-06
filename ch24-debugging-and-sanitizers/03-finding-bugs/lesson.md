## Finding bugs

Most bugs don't stop a program with a message. They give a wrong answer, and the work is to find the cause. The method is the same for most of them: work out what the answer should be, compare it with what the program prints, and find the step where the two first differ.

This step has an off-by-one error, a common kind of bug. Here's a first version of `sumFirst`, which adds the first `count` values of an array. It has the loop condition `i <= count` instead of `i < count`:

```java
static int sumFirst(int[] values, int count) {
    int total = 0;
    for (int i = 0; i <= count; i++) {
        total += values[i];
    }
    return total;
}
```

With the values `{4, 8, 15, 16}` and a count of 3, the expected sum is 4 + 8 + 15 = 27. The buggy loop adds one more value, the 16 at index 3, and prints 43. No exception is thrown, because index 3 exists in the array, so nothing in the output looks broken.

### Finding the cause

The check is to compare the expected answer with the actual one, and then to count. The expected sum has three terms. The program printed a sum with four terms, so the loop ran four times for a count of three. A loop that runs one time too many is an off-by-one error, and its condition is the first place to look.

A stack trace would not have helped here, since nothing was thrown. A useful tool in that case is a temporary print. Adding `System.out.println(i)` inside the loop shows the index on each pass, and the final pass shows the extra one. Remove the print once the cause is clear.

When a bug appears only with particular values, a debugger does the same job more directly. An IDE's debugger lets you set a breakpoint on the loop, step through each pass, and watch `i` and `total` change. This course's runner doesn't have a debugger, so the printed check is the method used here.

### The fix

Changing the condition to `i < count` runs the loop exactly `count` times. The program in `src/Main.java` has that version:

```java
for (int i = 0; i < count; i++) {
    total += values[i];
}
```

Press **Run**. The output is:

```text
Sum of first 3: 27
Sum of first 4: 43
```

Both answers now match the sums of the first values. The rule that prevents the bug is one to remember: a loop that counts values from index 0 to `count` must stop before `count`, since the last index is `count - 1`.

---

### Quiz

*These questions are written for this course.*

````quiz
With `i <= count` in the loop, why is the sum for a count of 3 wrong?
○ Because the array holds only three values.
→ Not quite. The array holds four values, so the loop can reach index 3 without an exception.
◉ Because the loop runs four times, adding the value at index 3 that is not one of the first three.
→ Yes. Counting the terms shows the extra pass.
○ Because `total` starts at zero.
→ Not quite. Starting at zero is correct, since a sum starts there.
````

````quiz
What is the first thing to compare when a program gives a wrong answer?
○ The line numbers in the source.
→ Not quite. The line numbers come after you know what is wrong.
◉ The expected answer, which you worked out by hand, against the answer the program printed.
→ Yes. The difference between the two points to the step where the program went wrong.
○ The number of lines in the file.
→ Not quite. File length doesn't show where a wrong answer comes from.
````

---

### Try it in the runner

Change `i < count` back to `i <= count` in `sumFirst`, and predict the output for a count of 3 before you press **Run**. It prints `Sum of first 3: 43`. Then predict the output for a count of 4, which also reads index 4 and throws `ArrayIndexOutOfBoundsException` in this version. Restore `i < count` before you continue.

---

A wrong answer is found by comparing it with the expected one and counting the steps. The next step covers the checks the JVM makes on its own as the program runs.
