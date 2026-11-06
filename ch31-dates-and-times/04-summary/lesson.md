## Summary: Dates and times

This chapter covered the `java.time` package, which handles the calendar for a program.

* **Use `java.time` rather than arithmetic on numbers.** Month lengths, leap years, and the calendar are handled for you.
* **`LocalDate` holds a date, and `LocalDateTime` holds a date and a time.** Neither has a time zone.
* **Date objects are immutable.** Methods such as `plusDays` return a new value, and the original is unchanged.
* **`DateTimeFormatter` turns a value into text.** Its pattern letters are Java's: `MM` for the month, `mm` for the minute, and `EEEE` for the weekday name.
* **`Locale.US` sets the names of months and days.** Without it, the names follow the computer's language settings.
* **`ChronoUnit.DAYS.between` counts whole days between two dates.**
* **`Duration` measures a length of time,** and prints in the ISO form, such as `PT5H30M`.

Dates in this chapter are shown in the US form, with the month first, such as March 15, 2024. Java's default text for a date puts the year first, so a program formats the date itself.

### Check your understanding

*These questions are written for this course.*

````quiz
Why should a program use `LocalDate` rather than storing a day, month, and year as three numbers?
○ Because three numbers take more memory.
→ Not quite. The reason is that the calendar rules are handled for you.
◉ Because `LocalDate` knows the month lengths and leap years, so the arithmetic is correct.
→ Yes. Adding or counting days uses the real calendar.
○ Because the three numbers can't be printed.
→ Not quite. The numbers can be printed, but the arithmetic on them is where errors come from.
````

````quiz
Which pattern letters give the minute?
○ MM
→ Not quite. MM is the month number.
◉ mm
→ Yes. The lowercase letters are the minute, and the uppercase MM is the month.
○ HH
→ Not quite. HH is the hour on a 24-hour clock.
````

````quiz
What does `ChronoUnit.DAYS.between(a, b)` return when `b` is earlier than `a`?
○ Zero.
→ Not quite. The count is negative, showing that the second date is earlier.
◉ A negative number.
→ Yes. The sign shows the direction from the first date to the second.
○ The absolute value of the difference.
→ Not quite. The sign is kept, so the direction is clear.
````
