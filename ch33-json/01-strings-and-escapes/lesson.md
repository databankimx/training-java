## Strings and escapes

JSON is a text format for data. It's used to send data between programs, to store settings, and to exchange records between systems. A JSON file is plain text, so any language can read it. The JDK doesn't include a JSON library, so this chapter writes JSON by hand. That shows what a JSON library does, and why each part of the format exists.

A JSON string is text wrapped in double quotes. Inside the quotes, some characters would end the string or break the text, so they must be escaped with a backslash. The writer in `src/Main.java` handles the four that matter most here:

```java
static String jsonString(String text) {
    StringBuilder json = new StringBuilder("\"");
    for (char c : text.toCharArray()) {
        switch (c) {
            case '"' -> json.append("\\\"");
            case '\\' -> json.append("\\\\");
            case '\n' -> json.append("\\n");
            case '\t' -> json.append("\\t");
            default -> json.append(c);
        }
    }
    return json.append('"').toString();
}
```

The `switch` uses the arrow form from the decisions chapter. Each case appends the escaped form of one character, and the default appends the character unchanged.

### The escapes

A double quote inside a string is written `\"`, so it doesn't end the string. A backslash is written `\\`, so one backslash in the text is two in the JSON. A newline is `\n`, and a tab is `\t`. Each of these is two characters in the JSON text, a backslash and a letter.

Press **Run**. The output is:

```text
"Hello"
"Say \"hi\""
"C:\\Users"
"one\ttwo"
```

The first line has no special characters, so it's written as it is. The others show the escapes. Note that the program's own strings use backslashes to write the characters, which is a separate use of the same symbol. The output is the JSON text, and that text is what a JSON reader would see.

### Why the escapes matter

Without escaping, a quote in the data would end the JSON string early, and the rest of the text would be read as something else. A program that writes data without escaping produces text that other programs can't read, and sometimes text that changes what the data means. Writing the escapes is the part of JSON that protects the data.

---

### Quiz

*These questions are written for this course.*

````quiz
What does `jsonString("a\"b")` produce?
○ "a"b"
→ Not quite. The inner quote would end the JSON string early.
◉ "a\"b"
→ Yes. The quote is escaped with a backslash, so the string continues past it.
○ "a\b"
→ Not quite. That escapes the letter b, not the quote.
````

````quiz
Why is a backslash in the data written as two backslashes in the JSON?
○ Because JSON doesn't allow backslashes.
→ Not quite. JSON allows them, but they start an escape.
◉ Because a single backslash starts an escape sequence, so the second one makes it a literal backslash.
→ Yes. The first backslash marks the escape, and the second is the character itself.
○ Because backslashes are always doubled in the output.
→ Not quite. Only backslashes in the data are doubled.
````

---

### Try it in the runner

Add a line that writes `jsonString("line one\nline two")`, and predict the output before you press **Run**. It's `"line one\nline two"` on a single line, since the newline is written as the two characters `\n`.

---

Strings are the smallest part of JSON, and escaping keeps their contents safe. The next step writes objects from a map of names to values.
