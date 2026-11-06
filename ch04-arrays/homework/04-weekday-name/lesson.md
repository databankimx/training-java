## Homework: Weekday name

Write a program that looks up the name of a day, given its number from 1 to 7, where 1 is Monday.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* declare an array of seven strings holding the day names, from `Monday` to `Sunday`
* ask for a number, with the prompt `Enter a day number (1-7): `, and read it as an `int` with `nextInt`
* print the name for that day, such as `Day 3 is Wednesday`
* print `That isn't a valid day number.` if the number is outside 1 to 7, and not print a name

Remember that indexes start at zero, so day 1 is at index 0. Subtract 1 from the number before you use it as an index, and check that the number is in range before you read the array.

### What to check

Try these inputs:

* `1` should give `Monday`
* `7` should give `Sunday`
* `0` should give the invalid message, not a name
* `8` should give the invalid message

The last two are the cases that matter. Reading `days[-1]` or `days[7]` would throw an `ArrayIndexOutOfBoundsException`, which is why the range check comes before the read. The check uses `&&`, so the second comparison only runs when the first has passed.

### Extensions

* Use `days.length` in the check, rather than typing 7. Does the check still work if you add an eighth name?
* Print the name of the next day as well, wrapping from Sunday back to Monday. The `%` operator from the arithmetic lesson makes the wrap a single calculation.

---

*This exercise is not from the book. It practices the ideas in Chapter 4.*
