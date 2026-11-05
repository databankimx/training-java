## Traversing a nested list

Lists in Java hold a fixed type of element, and most programs keep them that way. Sometimes, though, the data is a tree: an element may be a value, or another list of elements. Counting the values in such a tree is a natural recursive problem.

### The example

Here is a list that contains lists, which form the branches of a tree:

```java
List<Object> names = List.of(
        "Adam",
        List.of("Bob", List.of("Chet", "Cat"), "Barb", "Bert"),
        "Alex",
        List.of("Bea", "Bill"),
        "Ann");
```

The elements that hold a value, such as `"Adam"`, are the leaves. The elements that hold another list are the branches. The tree has ten leaves: Adam, Bob, Chet, Cat, Barb, Bert, Alex, Bea, Bill, and Ann.

The `size` method counts only the top-level elements, so the list appears to have five:

```text
size: 5
0 Adam
1 [Bob, [Chet, Cat], Barb, Bert]
2 Alex
3 [Bea, Bill]
4 Ann
```

Two of the five elements are lists themselves. `size` counts each one as a single element, the same as the names, which is why the total is not ten. The same thing happens in any language with nested collections.

### Using recursion

The method `countLeaves` visits each element. A leaf adds one to the count. A branch is handled by calling the same method on it:

```java
static int countLeaves(List<?> items) {
    System.out.println("Counting list: " + items);
    int count = 0;
    for (Object item : items) {
        if (item instanceof List<?> sublist) {
            count += countLeaves(sublist);
        } else {
            System.out.println("Found leaf: " + item);
            count++;
        }
    }
    return count;
}
```

The test `item instanceof List<?> sublist` checks the type and gives the list a name in the same step, as the modern features chapter showed. The `?` means the list can hold any type. The base case is the leaf, which is counted and does no further work. The branch isn't a special case, since it's processed by the same method that processes the current list. That's the recursive case.

The `println` lines are there for learning. Real code wouldn't print on every call.

### Running it

Press **Run**. The output begins with the count for the top list:

```text
Counting list: [Adam, [Bob, [Chet, Cat], Barb, Bert], Alex, [Bea, Bill], Ann]
Found leaf: Adam
Counting list: [Bob, [Chet, Cat], Barb, Bert]
Found leaf: Bob
Counting list: [Chet, Cat]
Found leaf: Chet
Found leaf: Cat
Found leaf: Barb
Found leaf: Bert
Found leaf: Alex
Counting list: [Bea, Bill]
Found leaf: Bea
Found leaf: Bill
Found leaf: Ann
The list contains 10 leaves
```

Java prints a list without quotes around its strings, so the trace looks a little different from the Python version. The calls and the count are the same.

### Folders

A file system is a tree of this kind. Files are leaves and folders are branches. A program that lists or counts the files in a folder can use the same pattern as `countLeaves`, with a call to the operating system in place of the list's contents. The next steps use recursion on other problems, but this one is the same idea applied to real files.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does `size` return 5 for the nested list rather than 10?
○ Because the list contains five names only.
→ Not quite. The list contains ten leaves, but size counts only its top-level elements.
◉ Because size counts only the top-level elements, and each branch counts as one.
→ Yes. The branches are not looked inside by size.
○ Because two of the names are missing.
→ Not quite. All ten names are in the list, inside the branches.
````

````quiz
What does the test `item instanceof List<?> sublist` do?
○ It checks whether the item is a number.
→ Not quite. It checks whether the item is a list.
◉ It checks whether the item is a list, and gives it the name sublist if it is.
→ Yes. The check and the name are in one step.
○ It converts the item to a list.
→ Not quite. The item keeps its type, and the test only checks it.
````

---

### Try it in the runner

Add `List.of("Carl")` as an extra branch at the end of the list, and predict the leaf count before you press **Run**. It's 11, since the new branch holds one more leaf. Then add a branch inside `List.of("Bob", ...)` that contains only another empty list, `List.of()`, and predict the change. The count stays at 11, since an empty list has no leaves.

---

A recursive method handles a tree by treating each branch as a smaller copy of the whole. The next step counts the same leaves without recursion, to compare the two.
