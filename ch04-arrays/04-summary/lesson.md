## Summary: Arrays

This chapter covered how a program stores a list of values of one type under a single name.

* **An array holds many values of one type.** It's declared with a type, empty brackets, and a name, such as `int[] nums = {10, 20, 30};`, or with `new` and a size, such as `new int[3]`.
* **Indexes start at zero.** `nums[0]` is the first element, and `nums[2]` is the third. Each index must be within the array.
* **Elements can be read and changed.** `nums[2] += 5` works just like a variable.
* **Going past the end throws an exception.** Java checks every index, and a bad one throws `ArrayIndexOutOfBoundsException`.
* **New arrays are filled with defaults.** Every element starts at zero for numbers, `false` for booleans, and `null` for objects such as strings.
* **Leave out the size when you list the values.** `String[] cars = {...}` counts the values for you.
* **`length` gives the number of elements.** It's a field, with no parentheses, and `length()` doesn't compile.
* **Multidimensional arrays use one index per dimension.** `grid[1][2]` is row 1, column 2. Both indexes start at zero, and each must stay within its own length.

### Check your understanding

*These questions are written for this course.*

````quiz
Which index refers to the last element of `int[] nums = new int[4];`?
○ 4
→ Not quite. That's one past the end of a four-element array.
◉ 3
→ Yes. Indexes run from 0 to 3, so the last element is at index 3.
○ 2
→ Not quite. That's the third element.
````

````quiz
What is `cars.length` for `String[] cars = {"Volvo", "BMW"};`?
○ 1
→ Not quite. That would be the index of the last element.
◉ 2
→ Yes. The array has two elements, so its length is 2.
○ The number of characters in the strings.
→ Not quite. length counts the elements, not the characters.
````

````quiz
What is the value of `values[3]` after `int[] values = new int[5];` with only the first three set?
○ 3
→ Not quite. The third value is at index 2, and index 3 was never set.
◉ 0
→ Yes. Every element of a new int array starts at zero, so an unset element is 0.
○ An exception, because it was never set.
→ Not quite. Reading an unset element gives its default, not an exception.
````
