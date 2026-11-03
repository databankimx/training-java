## Break and continue

Two statements change how a loop runs, once it has started. `break` ends the loop straight away, and `continue` skips the rest of the current pass and moves on to the next one.

Here's the first part of this step's program, in `src/Main.java`:

```java
for (int i = 1; i <= 10; i++) {
    if (i == 4) {
        break;
    }
    System.out.println("break check: " + i);
}
```

Press **Run**. The output is:

```text
break check: 1
break check: 2
break check: 3
```

The loop is set to run ten times, but when `i` reaches 4, the `if` runs `break`. The loop ends at that point, and the `println` for 4 never runs. The loop doesn't count any further, and the program continues after the loop's closing brace.

`break` is the usual way to stop a loop when the answer has been found, such as the first matching element of an array. Once the answer is known, there's no point checking the rest.

### Continue

`continue` skips the remaining statements in the loop body, and the loop goes on with its next pass:

```java
for (int i = 1; i <= 5; i++) {
    if (i == 3) {
        continue;
    }
    System.out.println("continue check: " + i);
}
```

The output is:

```text
continue check: 1
continue check: 2
continue check: 4
continue check: 5
```

When `i` is 3, the `continue` skips the `println`, and the loop moves on to 4. The loop itself keeps running to the end, unlike `break`.

### Which one to use

Use `break` when the loop has finished its job. Use `continue` when one pass doesn't need the rest of the body, and the loop should go on. A common pattern is to `continue` past values you don't want, so the main work of the body only handles the rest.

In a `while` loop, `continue` moves to the condition check. Make sure the counter or the change that ends the loop happens before the `continue`, or the loop can run forever. The `for` loop's step always runs after `continue`, so it's safer for this reason.

---

### Quiz

*These questions are written for this course.*

````quiz
In a loop, what does `break` do?
○ It skips to the next pass.
→ Not quite. That's what continue does.
◉ It ends the loop immediately, and the program continues after it.
→ Yes. break leaves the innermost loop at once.
○ It pauses the program until the user presses a key.
→ Not quite. break leaves a loop. It doesn't wait for input.
````

````quiz
In the continue example, why is 3 not printed?
○ Because the loop stops at 3.
→ Not quite. break stops a loop, but this loop goes on past 3.
◉ Because continue skips the rest of the body for that pass.
→ Yes. The println is after the continue, so it doesn't run when i is 3.
○ Because 3 is not in the array.
→ Not quite. The loop uses a counter, not an array.
````

---

### Try it in the runner

Change the `break` condition from `i == 4` to `i == 1`, and predict the output. The loop breaks on its first pass, before anything is printed, so the output is empty. Restore the original condition before you continue.

Then change `continue` in the second loop to `break`, and predict the output. The loop stops at 3, so only `continue check: 1` and `continue check: 2` appear. Restore `continue` before you continue.

---

`break` leaves a loop, and `continue` moves it to the next pass. The next step puts loops to work in a complete program, a game of Battleship.
