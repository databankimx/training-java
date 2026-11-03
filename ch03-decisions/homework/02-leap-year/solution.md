## Leap year: walkthrough

The expression is built from the rule itself. `year % 4 == 0` is true when the year is divisible by 4. `year % 100 != 0` is true when it isn't a century year. Together, in parentheses, they say "divisible by 4, but not by 100". The `|| year % 400 == 0` adds the exception that makes 2000 a leap year.

The parentheses around the first part matter. The `&&` binds more tightly than `||`, so without them, the expression would still give the right answer, but the reader would have to work out the precedence rules to check it. The parentheses make the two parts visible.

The result is a `boolean`, so it's stored in `isLeap` and tested by the `if` afterwards. Storing it first, with a name that says what it means, makes the final line easier to read.
