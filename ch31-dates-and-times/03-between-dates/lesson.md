## Measuring time between dates

Some questions are about the distance between two dates: how many days until a launch, or how long a task took. Subtracting two dates by hand is error-prone, since the answer depends on the months in between. `java.time` provides two tools: `ChronoUnit` counts whole units between two dates or times, and `Duration` represents a length of time.

### Days between two dates

`ChronoUnit.DAYS.between` counts the whole days from the first date to the second:

```java
LocalDate launch = LocalDate.of(2024, 3, 15);
LocalDate christmas = LocalDate.of(2024, 12, 25);
long daysBetween = ChronoUnit.DAYS.between(launch, christmas);
```

The output is:

```text
Days from launch to Christmas: 285
```

The count is positive when the second date is later, and negative when it's earlier. The number of days in each month is taken into account, so the result is correct across months of different lengths.

### Durations

A `Duration` is a length of time, measured internally in seconds. It can be built from hours and minutes, and it prints in a standard form:

```java
Duration length = Duration.ofHours(5).plusMinutes(30);
System.out.println("Length: " + length);
```

The output is:

```text
Length: PT5H30M
```

`PT` begins a time length, and `5H` and `30M` are the hours and minutes. The form is part of the ISO standard, so the same text is understood by other programs that read durations.

### Time between two date-times

`Duration.between` takes two date-times and gives the length of time between them:

```java
LocalDateTime start = LocalDateTime.of(2024, 3, 15, 9, 0);
LocalDateTime end = LocalDateTime.of(2024, 3, 15, 14, 30);
System.out.println("Time between: " + Duration.between(start, end).toMinutes() + " minutes");
```

The output is `Time between: 330 minutes`. `toMinutes` gives the length in whole minutes, and other methods such as `toHours` give the other units.

---

### Quiz

*These questions are written for this course.*

````quiz
Why use `ChronoUnit.DAYS.between` rather than subtracting day numbers?
○ Because subtracting day numbers is not allowed in Java.
→ Not quite. Subtraction is allowed, but it can't account for the months in between.
◉ Because the day numbers don't count the months in between, and `between` gets the answer right across months.
→ Yes. The method uses the calendar, so the result is correct across months of different lengths.
○ Because `between` is faster.
→ Not quite. The reason is the correctness of the calendar.
````

````quiz
What does `Duration.ofHours(5).plusMinutes(30)` print as text?
○ 5:30
→ Not quite. Durations use the ISO form, starting with PT.
◉ PT5H30M
→ Yes. PT starts a time length, followed by hours and minutes.
○ 330
→ Not quite. That's the total in minutes, not the printed form of the duration.
````

---

### Try it in the runner

Change `christmas` to `LocalDate.of(2024, 3, 1)`, and predict the day count before you press **Run**. It's -14, since March 1 is 14 days before March 15. Then change `end` to 9 hours and 15 minutes, and predict the minutes. It's 15, since the difference from 9:00 is a quarter of an hour.

---

`ChronoUnit` counts whole units between two dates, and `Duration` measures a length of time. The next step is the summary, followed by two homework exercises that combine these tools.
