## Summary: Regular expressions

This chapter covered describing the shape of text with patterns, and using them to check, find, pull apart, and replace text.

* **A pattern describes a shape.** `\\d` is a digit, `\\w` a letter, digit, or underscore, and `\\s` a whitespace character.
* **Quantifiers say how many.** `+` means one or more, `*` zero or more, `?` zero or one, and `{n}` exactly n.
* **A backslash is written twice in Java source.** The regex `\d` appears as `"\\d"` in a string.
* **`matches` checks the whole string.** It returns true only when every character fits the pattern.
* **`find` locates each occurrence.** It is called in a loop, and `group` and `start` describe each match.
* **Groups keep parts of a match.** Parentheses make a group, and `group(n)` returns its text, counting from 1.
* **`replaceAll` returns a new string.** Every match is replaced, and the original string is unchanged.
* **Test a pattern on text it must accept and text it must reject.** A pattern can match more than intended.
* **An unescaped dot matches any character.** Outside a set, escape it as `\\.` when you mean a literal dot.

### Check your understanding

*These questions are written for this course.*

````quiz
Which method checks that a whole string fits a pattern?
○ find
→ Not quite. find searches for the pattern inside a longer text.
◉ matches
→ Yes. Every character must fit the pattern for matches to return true.
○ replaceAll
→ Not quite. replaceAll changes matching text, and doesn't check the whole string.
````

````quiz
What does `group(1)` return after a successful `find` with the pattern `(\\w+)@(\\w+)`?
○ The whole match.
→ Not quite. The whole match is group(0), or group().
◉ The text matched by the first parentheses, before the at sign.
→ Yes. Groups are numbered by their opening parenthesis, starting from 1.
○ The text after the at sign.
→ Not quite. That text is group(2).
````

````quiz
What does `"a1b2".replaceAll("\\d", "#")` return?
○ "a1b2"
→ Not quite. The digits are replaced.
◉ "a#b#"
→ Yes. Each digit is replaced separately, and the letters are kept.
○ "##"
→ Not quite. The letters are not digits, so they stay in the string.
````
