## Dates

Dates look simple, but calendars have month lengths, leap years, and rules that don't follow a pattern. The `java.time` package handles that work, so a program should use it rather than doing arithmetic on numbers by hand. Its date classes are immutable: a method such as `plusDays` returns a new date, and the original is unchanged.

This chapter shows dates in the US form, with the month first. Java's default text for a date puts the year first, so the program formats each date itself.

Here's the program for this step, in `src/Main.java`:

```java
LocalDate launch = LocalDate.of(2024, 3, 15);
DateTimeFormatter us = DateTimeFormatter.ofPattern("M/d/yyyy", Locale.US);
System.out.println(launch.format(us));
```

`LocalDate` holds a date with no time and no time zone. `LocalDate.of` takes the year, the month, and the day, in that order. The formatter turns the date into text, and the output is:

```text
3/15/2024
```

### Formatting a date

`DateTimeFormatter` turns a date into text with a pattern of your choice. The US form is `M/d/yyyy`, and the written form uses the month name:

```java
DateTimeFormatter written = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.US);
System.out.println(launch.format(written));
```

The output is `March 15, 2024`. The pattern letters to remember are:

| Letters | Meaning | Example |
|---|---|---|
| `M` | month number, without a leading zero | 3 |
| `MM` | month number, with a leading zero | 03 |
| `MMMM` | month name | March |
| `d` | day of the month | 15 |
| `yyyy` | four-digit year | 2024 |

Java uses `MM` for the month and `mm` for the minute, so the two are easy to confuse. The `Locale.US` argument sets the language of the month names. Without it, the names follow the computer's own language settings.

### Adding days

`plusDays` moves a date forward, and `minusDays` moves it back:

```java
System.out.println(launch.plusDays(30).format(us));
```

The output is `4/14/2024`. Adding 30 days to March 15 passes the end of March, which has 31 days, so the result is in April. The program doesn't count the days in each month. The `java.time` classes handle it.

---

### Quiz

*These questions are written for this course.*

````quiz
What does `launch.plusDays(30)` return when `launch` is March 15, 2024, in the US form?
○ 3/45/2024
→ Not quite. Days don't continue past the end of March. The date moves into April.
◉ 4/14/2024
→ Yes. March has 31 days, so 30 days later is April 14.
○ 3/15/2024
→ Not quite. The original date is unchanged, and plusDays returns a new date.
````

````quiz
Which pattern letters give the month name, such as March?
○ MM
→ Not quite. MM gives the month number with a leading zero, such as 03.
◉ MMMM
→ Yes. Four letters give the full month name.
○ mm
→ Not quite. mm is the minute, and it's not the month.
````

---

### Try it in the runner

Change `plusDays(30)` to `minusDays(30)`, and predict the result before you press **Run**. It's 2/14/2024, since subtracting 30 days from March 15 passes back through February, which has 29 days in 2024. Then change the launch date to March 1, 2023, and predict the result of `plusDays(30)`. It's 3/31/2023, since 2023 is not a leap year and February has 28 days.

---

The `java.time` classes know the calendar, so a program adds and formats dates without counting days itself. The next step combines a date and a time, and the step after that measures the days between two dates.
