## Building strings

A `String` never changes. Every time you join text with `+=`, Java creates a new `String` holding the old text plus the new, and the old one is thrown away. In a short loop that doesn't matter. In a long loop it adds up to a lot of copying. A `StringBuilder` is built to change: it appends into one growing buffer, and it makes the new text only when you ask for it.

Here's the program for this step, in `src/Main.java`:

```java
String text = "";
for (int i = 0; i < 5; i++) {
    text += i;
}
System.out.println("concatenated: " + text);

StringBuilder builder = new StringBuilder();
for (int i = 0; i < 5; i++) {
    builder.append(i);
}
System.out.println("builder: " + builder);
System.out.println("length: " + builder.length());
```

Press **Run**. The output is:

```text
concatenated: 01234
builder: 01234
length: 5
```

Both loops produce the same text. The difference is what happens inside each loop: `text += i` creates a new string on every pass, while `builder.append(i)` adds to the buffer that already exists.

### When it matters

For a handful of joins, `+=` is perfectly clear, and it's the better choice because it reads more naturally. The cost grows with the length of the text and the number of joins, so the rule is: use `+=` for a few pieces, and `StringBuilder` when a loop builds a long text.

A `StringBuilder` has its own methods, such as `append`, `length`, and `toString`. Printing it, as the program does, calls `toString` automatically, so the output reads as normal text.

### Building from a list of parts

The usual pattern is a loop that appends each part, and one call to `toString` at the end if a `String` is needed. Calling `toString` in the middle of the loop makes a copy each time, so do it once, after the loop.

---

### Quiz

*These questions are written for this course.*

````quiz
Why is `text += i` in a long loop slower than `builder.append(i)`?
○ Because += is not allowed in loops.
→ Not quite. It's allowed, and it's the cost of the copying that matters here.
◉ Because each += creates a new String holding all the text so far.
→ Yes. Strings never change, so every join copies the whole text.
○ Because StringBuilder is always compiled to faster machine code.
→ Not quite. The difference is that StringBuilder changes one buffer instead of copying.
````

````quiz
When should you prefer `StringBuilder`?
○ For any two strings joined once.
→ Not quite. A single join is fine with +=.
◉ When a loop builds a long text, one part at a time.
→ Yes. That's where the repeated copying of += adds up.
○ Only when the text contains numbers.
→ Not quite. The type of the parts doesn't matter, only how often the text is joined.
````

---

### Try it in the runner

Change the loop count in both loops from 5 to 3, and predict the output. The text becomes `012`, and the length is 3. The result is the same, and only the amount of work changes. Restore 5 before you continue.

Then add `String copy = builder.toString();` inside the second loop, after the `append`, so the text is copied on every pass. Predict whether the printed result changes. It doesn't, because `toString` returns the same text, but it makes a copy on every pass, which is the cost this step is about. Remove the added line before you continue.

---

A `String` is fixed, so repeated joins copy it again and again. A `StringBuilder` grows in place, and it's the right tool for a long build. The next step looks at numbers held as objects, which have a cost of their own.
