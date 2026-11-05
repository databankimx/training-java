## Dates and times together

A `LocalDateTime` holds a date and a time, and like `LocalDate` it has no time zone. Time zones need a separate class, which this chapter doesn't cover.

Dates in this chapter are shown in the US form, with the month first. Times use the 24-hour clock, so 14:30 is half past two in the afternoon.

Here's the program for this step, in `src/Main.java`:

```java
LocalDateTime meeting = LocalDateTime.of(2024, 3, 15, 14, 30);
DateTimeFormatter us = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm", Locale.US);
System.out.println(meeting.format(us));
```

The output is:

```text
3/15/2024 14:30
```

`LocalDateTime.of` takes the year, month, day, hour, and minute. The pattern `M/d/yyyy HH:mm` puts the month first, then the day and year, then the hour and minute. `HH` is the hour on a 24-hour clock, and it always has two digits.

### Using the date and time together

A formatter can also name the weekday and the month, which reads more like a sentence:

```java
DateTimeFormatter spoken = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy 'at' HH:mm", Locale.US);
System.out.println(meeting.format(spoken));
```

The output is `Friday, March 15, 2024 at 14:30`. The words in quotes, such as `'at'`, are printed as they are, rather than read as pattern letters. `EEEE` is the weekday name, and it's worked out from the date for you.

To use a 12-hour clock, the pattern would use `hh` for the hour and `a` for the AM or PM marker, such as `h:mm a`. Those letters only change how the value is displayed. The value stays the same.

---

### Quiz

*These questions are written for this course.*

````quiz
Which letters in a pattern give the hour on a 24-hour clock?
○ hh
→ Not quite. hh is the hour on a 12-hour clock.
◉ HH
→ Yes. Uppercase H is the 24-hour hour, from 0 to 23.
○ mm
→ Not quite. mm is the minute.
````

````quiz
What does `meeting.format(spoken)` print for 2:30 PM on March 15, 2024, with the pattern `EEEE, MMMM d, yyyy 'at' HH:mm`?
○ 3/15/2024 14:30
→ Not quite. That's the numeric form, from the previous pattern.
◉ Friday, March 15, 2024 at 14:30
→ Yes. The weekday name, the month name, and the time in 24-hour form.
○ Friday, 14:30:00
→ Not quite. The pattern doesn't include seconds, and the date is included.
````

---

### Try it in the runner

Change the time to `LocalDateTime.of(2024, 3, 15, 9, 5)`, and predict the output of the first line before you press **Run**. It's `3/15/2024 09:05`, with a leading zero in the hour and minute. Then change the pattern to `h:mm a`, and predict the output. It's `9:05 AM`, since the hour is 9 on the 12-hour clock.

---

A `LocalDateTime` holds a date and a time together, and formatting it uses the same patterns as a date. The next step measures the time between two dates.
