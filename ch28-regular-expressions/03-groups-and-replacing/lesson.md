## Groups and replacing

Matching tells you whether text fits a shape. Often a program also needs the pieces inside the match: the year of a date, the domain of an address, the amount of a price. Parentheses in a pattern make a group, and each group keeps the text it matched. Replacing uses the same patterns to change the text that matches.

Here's the program for this step, in `src/Main.java`:

```java
Pattern date = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");
Matcher matcher = date.matcher("Due 2026-11-05.");
if (matcher.find()) {
    System.out.println("Year " + matcher.group(1) + " month " + matcher.group(2) + " day " + matcher.group(3));
}
```

The pattern has three groups, one for each part of the date. `group(1)` is the text matched by the first group, `group(2)` the second, and so on. `group()` with no number is the whole match, as the previous step showed. The output is:

```text
Year 2026 month 11 day 05
```

The groups are numbered by their opening parenthesis, counting from 1. Group 0 is always the whole match.

### Replacing text

`replaceAll` finds every match of a pattern and replaces each one with the text given. It returns a new string, and the original is unchanged:

```java
System.out.println("Cleaned: " + "  many   spaces here ".trim().replaceAll("\\s+", " "));
System.out.println("Masked: " + "phone 555-0101".replaceAll("\\d", "#"));
```

The first line trims the ends of the text, then replaces each run of one or more spaces with a single space. The second replaces every digit with `#`. The output is:

```text
Cleaned: many spaces here
Masked: phone ###-####
```

The pattern `\\s+` means one or more whitespace characters, so a run of three spaces becomes one space. The pattern `\\d` matches each digit separately, so every digit is replaced.

### Being careful with patterns

A pattern can match more than intended. `\\d` matches any digit, so a pattern meant for a phone number could also match part of a longer number. Test a pattern on the text it must accept and the text it must reject, before relying on it. This is the same habit as testing any other code.

---

### Quiz

*These questions are written for this course.*

````quiz
In the pattern `(\\d{4})-(\\d{2})`, what does `group(2)` return for the text "2026-11"?
○ 2026
→ Not quite. That is the first group, which has four digits.
◉ 11
→ Yes. The second group is the two digits after the dash.
○ 2026-11
→ Not quite. group(2) returns only the second group, and group() returns the whole match.
````

````quiz
What does `replaceAll` return?
○ It changes the original string.
→ Not quite. Strings do not change. replaceAll returns a new string.
◉ A new string in which every match has been replaced.
→ Yes. The original string is left as it was.
○ The number of matches.
→ Not quite. That's what a loop with find can count, but replaceAll returns the new text.
````

---

### Try it in the runner

Change `replaceAll("\\s+", " ")` to `replaceAll("\\s", " ")`, and predict the first line of output before you press **Run**. The pattern now matches one space at a time, so each space is replaced by one space, and the three spaces between "many" and "spaces" stay as three spaces. The output is `Cleaned: many   spaces here`. Restore the pattern before you continue.

---

Groups let a program take a match apart, and `replaceAll` changes every match in a text. The next step is the chapter summary, followed by two homework exercises that use both.
