## Recursive quicksort

Quicksort is a sorting algorithm that uses divide and conquer. It chooses one item as the pivot, splits the others into those smaller than the pivot and those larger, and sorts each part by the same method. The recursion stops when a part has zero or one item, since such a part is already sorted.

The steps are:

* Choose a pivot item.
* Split the list into two sub-lists: the items less than the pivot, and the items greater than it.
* Sort each sub-list recursively, then join them with the pivot.

Each split makes the parts smaller, so the recursion always reaches its base case.

### Choosing the pivot

Quicksort works whatever pivot is chosen, but some choices are better. Ideally, the pivot splits the list into two parts of similar size. A poor pivot, such as the smallest item in a list that's nearly sorted, leaves one part almost empty and the other almost the whole list.

This program uses the median of three items: the first, the middle, and the last. The median is the middle value of the three, and it's usually close to the middle of the values in the list. `medianOfThree` finds it with `Math.min` and `Math.max`:

```java
static int medianOfThree(int a, int b, int c) {
    return Math.max(Math.min(a, b), Math.min(Math.max(a, b), c));
}
```

Taking the largest of the two smaller values, and the smaller of the largest value with the third, gives the middle one. For example, for 1, 3, and 2, the result is 2.

### The recursive method

Here is the method from `src/Main.java`:

```java
static List<Integer> quicksort(List<Integer> numbers) {
    if (numbers.size() <= 1) {
        return numbers;
    }

    int pivot = medianOfThree(
            numbers.get(0),
            numbers.get(numbers.size() / 2),
            numbers.get(numbers.size() - 1));

    List<Integer> less = new ArrayList<>();
    List<Integer> equal = new ArrayList<>();
    List<Integer> greater = new ArrayList<>();
    for (int n : numbers) {
        if (n < pivot) {
            less.add(n);
        } else if (n == pivot) {
            equal.add(n);
        } else {
            greater.add(n);
        }
    }

    List<Integer> result = new ArrayList<>(quicksort(less));
    result.addAll(equal);
    result.addAll(quicksort(greater));
    return result;
}
```

The first `if` is the base case. The middle block splits the list into three parts, keeping the items equal to the pivot separately, so repeated values can't be lost or repeated. The last three lines are the recursive case: sort the smaller part, add the equal items, and add the sorted larger part.

The method returns a new list and doesn't change the one it's given. The Python version does the same, with list comprehensions for the three parts.

### Running it

The sample list is the one from the Python lesson, so its result is fixed. Press **Run**. The output is:

```text
Unsorted: [20, 77, 45, -26, -87, 63, -94, -2, -66, 45]
Sorted:   [-94, -87, -66, -26, -2, 20, 45, 45, 63, 77]
Empty:    []
One item: [5]
```

The two 45s appear next to each other in the result, since the pivot's equal items are kept together.

### Cost

Quicksort takes O(n log n) time on average, but a poor pivot on every step can slow it to O(n²). The median of three makes the poor case less likely, but it can't rule it out. The Big-O lesson in the Python course explains these terms.

### Iterative quicksort

A loop can replace the recursion, but it needs an explicit stack of the parts still to be sorted, and the code is harder to follow. That's why recursion is the usual choice for quicksort.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does the method keep the items equal to the pivot in their own list?
○ Because the equal items can't be compared.
→ Not quite. Equal items can be compared, and the problem is the result.
◉ So that items equal to the pivot aren't lost or repeated when the two parts are joined.
→ Yes. Each item goes into exactly one of the three parts.
○ Because the pivot is always the largest item.
→ Not quite. The pivot can be any value, and the equal items are kept in every case.
````

````quiz
What does `medianOfThree(1, 3, 2)` return?
○ 1
→ Not quite. 1 is the smallest of the three, not the middle.
◉ 2
→ Yes. 2 is the middle value of the three numbers.
○ 3
→ Not quite. 3 is the largest of the three.
````

---

### Try it in the runner

Change the list to `List.of(1, 2, 3, 4, 5)`, which is already sorted, and predict the result before you press **Run**. It's the same list, since the median of three is 3 and the sorted parts stay in order. Then change the list to `List.of(5, 5, 5)`, and predict the result. It's three 5s, since all three items are equal to the pivot.

---

Quicksort divides a list around a pivot and sorts each part by the same method. The next lesson counts the calls the recursion makes, so you can see how much work a recursive method does.
