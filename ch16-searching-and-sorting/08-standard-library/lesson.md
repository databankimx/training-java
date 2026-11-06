## The standard library sort and search

In real programs, you almost never write the sort yourself. The standard library provides sorting and searching in `java.util.Arrays`, and they're written and tuned by people who spend their time on exactly this problem. The earlier steps in this chapter taught you how the sorts work, which is what you need to choose and use them well. This step shows the library versions.

### Passing an array

The library methods take the array itself. There is no separate range to name. `Arrays.sort(values)` sorts every element of `values`, in place, and `Arrays.binarySearch(values, key)` searches the whole array. Both are in `java.util`, so the program imports `Arrays`.

### Sorting

Here's the library sort, from `src/Main.java`:

```java
int[] small = {14, 3, 19, 7, 1, 12, 20, 5, 16, 9, 2, 17, 6, 11, 18, 4, 13, 8, 15, 10};
Arrays.sort(small);
```

That's the whole call. It sorts the 20-value list from the sorting steps into 1 through 20. The library doesn't count its steps for you, and it doesn't show them, so this step has no visualizer. The trace of a library sort would be the library's own work, which is not something to step through.

Press **Run**. The output begins:

```text
Short list: 20 values
Sorted: 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
```

The timing list is the same -1,000 to 1,000 shuffle as before. It's sorted in one call, and the output checks the result with a short loop that confirms each value is no smaller than the one before it:

```text
Timing list: 2,001 values, from -1,000 to 1,000
Sorted correctly: yes
```

### Searching a sorted array

`Arrays.binarySearch` answers the question with the binary search from the previous steps. When the value is present, it returns the index of the value. For the short list, the value 12 is the twelfth value in the sorted list, so its index is 11:

```java
int index = Arrays.binarySearch(small, 12);
boolean present = index >= 0;
System.out.println("Search for 12: " + (present ? "present" : "absent") + " at index " + index);
```

Press **Run**. The output for the short list is:

```text
Search for 12: present at index 11
```

### When the value is missing

When the value isn't in the array, `binarySearch` returns a negative number. The number is `-(insertion point) - 1`, where the insertion point is the index where the value would go to keep the array sorted. For a value of 21 in the short list, the insertion point is 20, the end of the array, so the result is -21. Checking `index >= 0` is the test for presence, which is why the program uses it.

The timing list has the same kind of search, and its output reports both results:

```text
Search for 737: present
Search for 5,000: absent
```

Both searches use `binarySearch` on the long sorted array, so they take about eleven comparisons each, as the binary search step showed for a list of this size.

### Big-O analysis

Let n be the number of values.

**Sorting.** `Arrays.sort` on an array of `int` values uses a dual-pivot quick sort, which is O(n log n) on average. It switches to a heap sort when the recursion grows too deep, which keeps the worst case at O(n log n) as well.

**Sorting space.** The sort works in place, so its extra space is small. The recursion uses O(log n) stack space.

**Searching a sorted array.** `binarySearch` makes O(log n) comparisons, the same as the binary search step. The array must be sorted; on an unsorted array, its answers are meaningless.

**Checking order.** A loop that checks each value against the one before it reads each value once, so it's O(n). It's a cheap check after sorting, and it's useful in tests.

So the library gives O(n log n) sorting and O(log n) searching on sorted data, with no algorithm of your own to get wrong. The trade-off the previous steps measured still holds: sorting costs O(n log n) once, and each later search is O(log n), which pays off when the array is searched many times.

### Objects and comparisons

The sort above works on `int` values, which are compared directly. Lists and arrays of objects are sorted with `Arrays.sort` or `Collections.sort` too, but they need a way to say which object comes first. That's a comparison rule, supplied with a `Comparator`. The collections chapter introduced the types, and the lambda chapter covers how to write the rule compactly. This step only needs the plain sort.

### When to use the library

Use `Arrays.sort` and `Arrays.binarySearch` by default. They're faster than most hand-written versions, and they've been tested far more than anything you'd write in a course. Write your own sort when you're learning how sorts work, as this chapter did, or when a special requirement calls for it.

---

### Quiz

*These questions are written for this course.*

````quiz
What does `Arrays.binarySearch` return when the value is not in the array?
○ -1, the same as a linear search.
→ Not quite. Java returns a negative number that also records where the value would go.
◉ A negative number, -(insertion point) - 1, so any negative result means absent.
→ Yes. The insertion point is encoded in the result, so the caller checks for a negative number.
○ The index of the nearest value.
→ Not quite. A missing value gives a negative result, not the index of another value.
````

````quiz
What must be true before `Arrays.binarySearch` gives a correct answer?
○ The array must hold only positive numbers.
→ Not quite. Binary search works with any values, as long as they're in order.
◉ The array must be sorted.
→ Yes. Binary search depends on the order to throw away half the array each time.
○ The array must have an odd number of values.
→ Not quite. The length doesn't matter, only the order.
````

---

### Try it in the runner

Search the sorted short list for 1, and predict the index before you press **Run**. The value 1 is the smallest, so it sits at index 0. Then search for 21, which isn't in the list, and predict the result. The insertion point is 20, the end of the array, so the result is -21.

---

The library gives you tested sorting and searching, with the same costs you measured in this chapter. The chapter summary, the homework, and the chapter overview come next, and they bring the search and sort steps together.
