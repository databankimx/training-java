## Building a phone pattern

Writing a regular expression is easier when you build it up in small steps. Start with the simplest pattern that could work, test it against the text, see what it misses or wrongly accepts, and then change one thing at a time. A phone number makes a good example, because the same number can be written in several ways.

Here are the test numbers for this step, in `src/Main.java`. The first six are phone numbers, and the last one is text that must not match:

```java
String[] phones = {
    "7134833111",
    "713-483-3111",
    "713.483.3111",
    "713,483,3111",
    "(713) 483-3111",
    "(713) 483-3111 ext: 42",
    "Don't match this one: (713) 483-3111 - BAD"
};
```

The goal is one pattern that matches the six numbers and rejects the last line. The helper `tryPattern` prints each pattern, then each line it matches. It uses `find`, which looks for the pattern anywhere in the line, so every attempt shows its matches clearly.

### Attempt 1: a plain run of digits

```java
tryPattern("\\d{10}", phones);
```

`\d` matches a digit, and `{10}` asks for exactly ten of them in a row. Only `7134833111` has ten digits together, so only that line matches. The rule of thumb is to use units as large as you can without letting an unexpected character break the match. Ten digits in a row is too large a unit, since most phone numbers have separators.

### Attempt 2: three, three, and four digits

```java
tryPattern("\\d{3}-\\d{3}-\\d{4}", phones);
```

Now the number is split into the groups a person would say, with dashes between them. Only `713-483-3111` matches, because the other numbers use different separators.

### Attempt 3: a set of separators

```java
tryPattern("\\d{3}[-.,]\\d{3}[-.,]\\d{4}", phones);
```

The square brackets make a set, which matches any one of the characters inside. `[-.,]` accepts a dash, a dot, or a comma. This matches three lines. It still misses the ones with brackets, or with no separator at all.

Notice that the dot inside the set needs no backslash. Inside a set, `.` stands for itself.

### Attempt 4: optional parts

```java
tryPattern("\\(?\\d{3}\\)?\\s?[-.,]?\\d{3}[-.,]?\\d{4}", phones);
```

A question mark makes the item before it optional. Now the brackets around the area code are optional, and so are the space and the separators. Every phone number matches, including the last line, since `find` finds the phone number inside the text. The pattern is too loose, so the next attempt tightens it.

### Attempt 5: anchors

```java
tryPattern("^\\(?\\d{3}\\)?\\s?[-.,]?\\d{3}[-.,]?\\d{4}$", phones);
```

`^` requires the match to start at the beginning of the line, and `$` requires it to end at the end. Text before the number or after it now makes the match fail. The bad line is rejected, but the line with an extension is rejected too, since the extension follows the number.

### Attempt 6: the extension

```java
tryPattern("^\\(?\\d{3}\\)?\\s?[-.,]?\\d{3}[-.,]?\\d{4}\\s?e?x?t?:?\\s?\\d*$", phones);
```

The end of the pattern now allows an optional extension. `\s?` allows a space, `e?x?t?` allows any of `ext`, `ex`, `e`, or `x`, `:?` allows a colon, and `\d*` allows any number of digits, including none, since the extension may be missing. This matches the six phone numbers and not the bad line.

Press **Run**. The output ends with the final pattern, attempt 6. Its output is:

```text
Pattern: ^\(?\d{3}\)?\s?[-.,]?\d{3}[-.,]?\d{4}\s?e?x?t?:?\s?\d*$
  matches: 7134833111
  matches: 713-483-3111
  matches: 713.483.3111
  matches: 713,483,3111
  matches: (713) 483-3111
  matches: (713) 483-3111 ext: 42
```

### The period trap

A dot in a pattern, outside a set, matches any character at all. That makes it easy to write a pattern that accepts text it should reject. The program in `src/Main.java` ends with attempt 6, so the trap is shown here as examples, which you can try in a scratch copy of the program. They aren't part of the program's output:

```java
String[] trap = {"713.483.3111", "713x483x3111"};
tryPattern("\\d{3}.\\d{3}.\\d{4}", trap);
tryPattern("\\d{3}\\.\\d{3}\\.\\d{4}", trap);
```

The first pattern's dots match anything, so it accepts `713x483x3111` as well as `713.483.3111`. The second escapes each dot with a backslash, so it matches only a literal dot. The output shows the difference:

```text
Pattern: \d{3}.\d{3}.\d{4}
  matches: 713.483.3111
  matches: 713x483x3111

Pattern: \d{3}\.\d{3}\.\d{4}
  matches: 713.483.3111
```

The rule is simple. Outside a set, escape a dot when you mean a literal dot. Inside a set, it's already literal, so the escape isn't needed.

### Where this leaves the pattern

Six attempts later, the pattern handles the formats in the list, but not every phone number in the world. Country codes, toll-free numbers, and other formats would need changes. A pattern is a working rule for the cases you've tested, and it should be updated when new cases appear.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does attempt 5 reject the line with an extension?
○ Because the pattern contains no digits after the area code.
→ Not quite. The pattern does have digits, and the number itself is well formed.
◉ Because the anchor $ requires the match to end at the end of the line, and the extension follows the number.
→ Yes. The anchors are what makes the extension fail to match.
○ Because `find` never matches a line with a colon.
→ Not quite. find can match a line with a colon, and the anchors are the cause here.
````

````quiz
Which pattern matches the text `713x483x3111` only if the dots are wrong?
○ `\d{3}\.\d{3}\.\d{4}`, with escaped dots.
→ Not quite. Escaped dots match only a literal dot, so this pattern rejects that text.
◉ `\d{3}.\d{3}.\d{4}`, with unescaped dots.
→ Yes. An unescaped dot matches any character, including the letter x.
○ `\d{3}[-.,]\d{3}[-.,]\d{4}`
→ Not quite. A set matches only the characters listed, so the letter x is rejected.
````

---

### Try it in the runner

Add the line `"713 483 3111"` to the list of phone numbers, and predict whether attempts 3 and 4 match it before you press **Run**. Neither does. Attempt 3 has no space in its set `[-.,]`, and attempt 4 allows a space only directly after the area code, so the space between `483` and `3111` breaks the match. Restore the list before you continue.

---

A pattern grows from the simplest version that works, one change at a time, and each change is tested on every case. The next step explains patterns and matching in detail, including the special characters used here.
