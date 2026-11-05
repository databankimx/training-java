## Patterns and matching

Many checks on text are about shape rather than exact contents: a phone number has three digits, a dash, and four digits; a postal code is five digits; a word is letters only. A regular expression describes a shape in a short notation, and the `java.util.regex` package checks text against it.

Here's the program for this step, in `src/Main.java`:

```java
System.out.println("Five digits: " + "12345".matches("\\d{5}"));
System.out.println("Letters only: " + "Java".matches("[A-Za-z]+"));
```

`matches` checks whether the whole string fits the pattern. Here `\d` means one digit, and `{5}` means exactly five of them. The second pattern, `[A-Za-z]+`, means one or more letters. Both checks succeed, so the output is:

```text
Five digits: true
Letters only: true
```

Inside a Java string, a backslash must be written twice, so `\d` appears as `"\\d"` in the source. The regex itself has a single backslash.

### Finding a pattern in text

`matches` needs the whole string to fit. To look for a pattern inside a longer text, compile it into a `Pattern`, then ask a `Matcher` to find each occurrence:

```java
String text = "Call 555-0101 or 555-0199 today.";
Pattern phone = Pattern.compile("\\d{3}-\\d{4}");
Matcher matcher = phone.matcher(text);
while (matcher.find()) {
    System.out.println("Found " + matcher.group() + " at " + matcher.start());
}
```

`find` moves to the next match each time it's called, and returns false when there are no more. `group` gives the matched text, and `start` gives the index where the match begins. The output is:

```text
Found 555-0101 at 5
Found 555-0199 at 17
```

### Common pieces

| Piece | Matches |
|---|---|
| `\d` | a digit, 0 to 9 |
| `\w` | a letter, digit, or underscore |
| `\s` | a space, tab, or newline |
| `.` | any character except a newline |
| `[abc]` | one of the characters listed |
| `[a-z]` | one character in the range |
| `x*`, `x+`, `x?` | zero or more, one or more, or zero or one `x` |
| `x{3}`, `x{2,4}` | exactly three, or two to four, `x` |

A `Pattern` compiled once can be reused for many texts, so a program that checks many values should compile its pattern outside the loop.

---

### Quiz

*These questions are written for this course.*

````quiz
What does `"12345".matches("\\d{5}")` check?
○ That the string contains five digits somewhere in it.
→ Not quite. matches checks the whole string, so the string must be exactly five digits.
◉ That the whole string is exactly five digits.
→ Yes. Every character must match the pattern.
○ That the string is a number.
→ Not quite. The pattern checks digits, and a number could have other characters too.
````

````quiz
Which method moves to the next match in the text, and returns false when none is left?
○ matches
→ Not quite. matches checks the whole string once, and it doesn't search.
◉ find
→ Yes. find is called in a loop to visit each match.
○ group
→ Not quite. group returns the text of the current match.
````

---

### Try it in the runner

Change the pattern to `\\d{2}-\\d{4}`, so it asks for two digits before the dash, and predict the output before you press **Run**. The phone numbers have three digits before the dash, so no two digits are followed by it, and the loop prints no lines. Restore the pattern before you continue.

---

A pattern describes a shape, `matches` checks a whole string against it, and `find` locates each occurrence in a longer text. The next step uses groups to pull out the parts of a match, and to replace text that matches.
