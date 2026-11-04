## Lists

Arrays have a fixed size. Once you've declared `int[] nums = new int[3]`, it holds three values, and that's the end of it. Most real programs don't know how many values they'll need until they run, so the standard library provides `ArrayList`, a list that grows as you add to it.

Here's the start of this step's program, in `src/Main.java`. It starts with an empty list and adds three scores:

```java
List<Integer> scores = new ArrayList<>();
scores.add(72);
scores.add(88);
scores.add(95);
```

`add` puts a value at the end. The list makes room for it, so you never state a size. The class is in `java.util`, which is why the program imports `ArrayList` and `List`. The type is written as `List<Integer>`, where the angle brackets name the type of each element. Elements of a list must be objects, so the number `72` is stored as an `Integer`, and Java converts between the two automatically.

Press **Run**. The output is:

```text
Count: 3
First: 72
Last: 95
Total: 255
Average: 85
After adding: 4
Index 10 is out of range.
```

### Reading the elements

A list is read with `get`, which takes an index, and index 0 is the first element. `scores.size()` gives the number of elements, and `scores.get(i)` gives the element at index `i`.

Java checks every index. `scores.get(10)` throws an `IndexOutOfBoundsException`, which the program catches. There is no unchecked form, so a bad index is always reported. The `try` block shows the exception being caught, and the program prints the message and carries on.

### Looping over a list

The enhanced `for` works on a list just as it does on an array:

```java
for (int score : scores) {
    total += score;
}
```

Each element is given to `score` in turn, and the body runs once per element. The loop doesn't need `size()`, so it can't run past the end.

### Adding and growing

The list grows each time you call `add`. The program adds a fourth score after the loop, and `size()` reports 4. That growth is the reason lists exist: the size is decided while the program runs, from the data it reads.

### More list methods

The program's last part uses the methods you'll reach for next. Each one is called with a dot, as the methods chapter described.

```java
List<Integer> stack = new ArrayList<>(List.of(1, 2, 3));
stack.remove(stack.size() - 1);
stack.add(1, 99);
stack.remove(0);
```

`remove` with an index removes the element at that position, so removing the last one, at `size() - 1`, works like the removal that ends a stack. `add` with two arguments puts a value at a position, and every element at or after that position moves one place along. Removing index 0 closes the gap at the front. The printed lines are `Front: 1, back: 3`, then `After pop_back: size 2, back 2`, and then `Elements: 99 2`.

`List.of(1, 2, 3)` makes a fixed list of three values, and `new ArrayList<>(...)` copies it into a list that can change. The `isEmpty` method tells you whether there are any elements, and `clear` removes all of them.

### Quick reference

| Method | What it does | Typical cost |
|---|---|---|
| `add(x)` | Adds `x` after the last element | Constant on average |
| `remove(size() - 1)` | Removes the last element | Constant |
| `get(0)`, `get(size() - 1)` | Reads the first or last element | Constant |
| `get(i)` | Reads the element at index `i` | Constant |
| `size()`, `isEmpty()` | Counts the elements, or tests for none | Constant |
| `add(i, x)` | Inserts `x` at index `i` | Linear, since later elements move |
| `remove(i)` | Removes the element at index `i` | Linear, since later elements move |
| `clear()` | Removes every element | Linear in the number of elements |

The costs are the typical ones for an `ArrayList`, and they are what the efficiency chapter measures. Only `add` at the end is described as constant on average, since an occasional growth copies the whole array.

### What a list costs

An `ArrayList` keeps its elements side by side in one array. That layout is the reason it's fast to read: neighboring elements are usually already in the processor's cache when the program reaches them, so a loop over a list runs quickly, and `get` is a single step.

Growing costs more, and the cost is worth understanding. When the array is full, the list allocates a larger array and copies every element across. That copy is expensive, but it happens rarely, because the array grows by a fixed multiple each time. Over many `add` calls, the cost of the copies is spread across the additions, so each addition is cheap on average. The efficiency chapter tests that claim with operation counts.

If you know roughly how many elements you'll need, `new ArrayList<>(n)` takes a starting capacity, and the list allocates once for that size. A list can also hold more space than it uses, so its memory footprint can be larger than its size suggests.

---

### Quiz

*These questions are written for this course.*

````quiz
Which method adds a value to the end of an `ArrayList`?
○ push
→ Not quite. Java lists use add to put a value at the end.
◉ add
→ Yes. add puts one element after the last one, and the list grows to fit.
○ append
→ Not quite. That name isn't a method of ArrayList.
````

````quiz
What happens when you call `scores.get(10)` on a list with four elements?
○ The result is undefined, and the value is whatever is in memory.
→ Not quite. Java always checks the index.
◉ An IndexOutOfBoundsException is thrown, which the program can catch.
→ Yes. Java checks every index, so a bad one is always reported.
○ The list grows to hold position 10.
→ Not quite. A list only grows when you add to it.
````

````quiz
A list holds 1000 elements. What happens to the elements after position 10 when you call `add(10, x)`?
○ Nothing. Only the new element is written.
→ Not quite. The elements after the position have to make room for the new one.
◉ Each of them moves one place along, so the work grows with the number of elements after the position.
→ Yes. That's the linear cost in the quick reference table.
○ They are all copied into a new list.
→ Not quite. Only the elements after the position move.
````

---

### Try it in the runner

Change `scores.get(10)` to `scores.get(4)`, and predict the message. It's the same exception, since the list has four elements, so index 4 is past the end. Then change the `add(60)` line to `add(1, 60)`, and predict the value of `scores.get(0)` after the change. It's still 72, since the new value went in at index 1, and the first element didn't move.

Then change `stack.add(1, 99)` to `stack.add(99)`, and predict the `Elements` line. The value goes at the end, so the list is `[2, 99]`, and the line reads `Elements: 2 99`. Restore the original line before you continue.

---

A list is a growing array with a checked way to read elements and a loop that visits them. The next step stores values under keys rather than positions, which is the job of a map.
