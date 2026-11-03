## Leap year

A year is a leap year when it is divisible by 4, with one exception: a year divisible by 100 is not a leap year, unless it's also divisible by 400. So 2024 is a leap year, 1900 is not, and 2000 is.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* ask for a year, with the prompt `Enter a year: `, and read it as an `int`
* decide whether the year is a leap year, using logical operators to combine the rules
* print `2024 is a leap year.` or `1900 is not a leap year.`, with the year in place

Use `%` to test divisibility. A year is divisible by 4 when `year % 4` is 0.

### Where to change the code

The example puts all of the rule into one `boolean`, called `isLeap`. The line that does the work is:

```java
boolean isLeap = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
```

Read it as two parts joined by `||`. The first part, in parentheses, is "divisible by 4, but not by 100". The second part is "divisible by 400". Either one makes the year a leap year.

### What to check

Try these years:

* `2024` should be a leap year, since it's divisible by 4 and not by 100
* `2023` should not be a leap year, since it isn't divisible by 4
* `1900` should not be a leap year, since it's divisible by 100 but not by 400
* `2000` should be a leap year, since it's divisible by 400

The two century years are the ones to check most carefully. A simple test that only checks for 4 gets 1900 wrong.

### Extensions

* Print the reason for the answer, such as `divisible by 400`, rather than only the yes or no.
* Write the same rule as a chain of `if` and `else if` blocks instead of one expression. Which version is easier to check against the rule?

---

*This exercise is not from the book. It practices the ideas in Chapter 3.*
