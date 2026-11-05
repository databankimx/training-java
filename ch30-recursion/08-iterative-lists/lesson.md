## An iterative solution for counting nested lists

Every recursive method can be written with a loop, and the nested-list count is no exception. The recursive version relied on the call stack to remember where each list had got to. The loop version keeps its own stack, holding the lists that are partly processed, so the program controls the memory instead of the JVM.

### A stack of iterators

Java's `Iterator` is an object that remembers its place in a collection. A `Deque` used as a stack holds one iterator for each list that is in progress:

```java
Deque<Iterator<?>> stack = new ArrayDeque<>();
stack.push(items.iterator());
```

The loop looks at the iterator on the top of the stack. If it has another element, the element is either a leaf, which is counted, or a branch, whose iterator is pushed on top. If the iterator has no more elements, its list is finished, so it's popped and the loop returns to the list it came from:

```java
while (!stack.isEmpty()) {
    Iterator<?> current = stack.peek();
    if (!current.hasNext()) {
        stack.pop();
        continue;
    }
    Object item = current.next();
    if (item instanceof List<?> sublist) {
        stack.push(sublist.iterator());
    } else {
        count++;
    }
}
```

The loop ends when the stack is empty, which means every list has been finished, and the count is returned.

### Comparing the two versions

The recursive version used the call stack, and each call's position was held in its local variables. This version uses a `Deque`, which the program manages. The control flow is harder to follow than the recursive method, and it has more places that could go wrong. That is the trade-off the Python lesson describes: the iterative version avoids the depth limit, but it's harder to read and maintain.

For the Python version, the lesson uses a list of lists and an index for each list, and it needs an `is` check to know when it's back at the top. In Java, the iterator remembers its own place, so no index is kept, and the top list is simply the bottom of the stack. The check for the top list isn't needed at all.

The stack holds one entry for each level of nesting, not one for each leaf. A list nested a thousand levels deep would need a thousand entries, and the `Deque` has no fixed limit, so this version has no depth problem.

### Running it

Press **Run**. The output is:

```text
The list contains 10 leaves
```

The answer matches the recursive version. The two methods give the same result on every input, so either can be used. The lesson's choice is the recursive one for most code, because it's easier to read. The iterative version is the answer when the nesting could be very deep.

---

### Quiz

*These questions are written for this course.*

````quiz
What does the iterative version keep in its stack?
○ The leaves that have been counted.
→ Not quite. The leaves are counted and not stored.
◉ An iterator for each list that is partly processed, with the most recent on top.
→ Yes. The top iterator is the list being read at the moment.
○ The number of leaves so far.
→ Not quite. The count is kept in a separate variable.
````

````quiz
Why does the iterative version have no depth limit like the recursive version?
○ Because loops are not limited by memory.
→ Not quite. Memory still limits a very large stack.
◉ Because it doesn't use the call stack, and its own stack grows only with the depth of the nesting.
→ Yes. The JVM's stack limit applies to calls, and this version makes none.
○ Because the list is never deeper than ten levels.
→ Not quite. The lesson doesn't assume a depth limit, and the method works for any depth.
````

---

### Try it in the runner

Add a branch with a single leaf deep inside a list, such as `List.of(List.of(List.of("Deep")))` as an element of the top list, and predict the count before you press **Run**. It's 11, since the deep leaf is counted like any other.

---

An explicit stack of iterators does the work of recursion without the call stack. The next lesson is palindromes, which use recursion on a string instead of a list.
