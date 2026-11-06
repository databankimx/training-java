## Homework: Sum nested numbers

Write a recursive method that adds every number in a list, where any element may itself be a list of numbers.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* write `static int sumNested(List<?> items)`, which adds the numbers directly in the list, and calls itself on each list it finds
* sum the list `List.of(1, List.of(2, List.of(3, 4)), 5, List.of(6))`
* print `Sum: ` followed by the total

### What to check

The output should be:

```text
Sum: 21
```

The numbers are 1 through 6, which add up to 21. Check that the method still works if one of the branches is empty, such as `List.of()`.

### Extensions

* Change the method so it returns the largest number in the nested list, rather than the sum. Decide what the base case should return for an empty list.
* Write the same sum without recursion, using a stack of iterators as in the iterative lists step.

---

*This exercise is not from the book. It practices the ideas in Chapter 30.*
