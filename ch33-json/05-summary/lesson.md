## Summary: JSON

This chapter covered JSON, the text format for data, and built a small library that writes and reads it.

* **JSON is plain text.** Any program can read it, which makes it a common way to exchange data.
* **Strings are quoted and escaped.** A quote, a backslash, a newline, or a tab must be written with a backslash, so the data can't end the string early.
* **An object is a set of key and value pairs, in braces.** The writer puts a comma between pairs, and not before the first one.
* **Arrays use square brackets, and nest with objects.** One recursive writer handles any depth, since each object and array calls the writer on its own values.
* **`serialize` turns Java values into JSON.** Maps become objects, lists become arrays, and strings are escaped.
* **`deserialize` reads JSON with a recursive descent parser.** Each kind of value has a method, and `pos` records how far the parser has read.
* **Each Java type comes from a JSON form.** Whole numbers become `Long`, decimals become `Double`, and objects become `LinkedHashMap`, which keeps the key order.
* **Bad input is reported with its position.** The parser throws an exception that says where it stopped.

### What the library does not handle

The library covers the common cases, but a production JSON library would do more:

* Pretty printing, with indentation and line breaks. The output is always on one line.
* Surrogate pairs in `\u` escapes, which the library reads as separate characters.
* Converting Java objects to and from JSON by their fields. The library works with maps, lists, and simple values only.
* Very large numbers, which are read as `Long` or `Double` and may lose precision.

These limits are the reason to use an established library in a real project. The library in this chapter shows how the work is done, so the choice of library can be made with an understanding of what it does.

### Check your understanding

*These questions are written for this course.*

````quiz
Why is a backslash in the data written as two backslashes in the JSON text?
○ Because JSON doesn't allow backslashes at all.
→ Not quite. JSON allows them, but a single backslash starts an escape.
◉ Because a single backslash starts an escape sequence, and the second one makes the backslash a literal character.
→ Yes. The escape rules need the doubled form to represent a plain backslash.
○ Because `serialize` always doubles every character.
→ Not quite. Only backslashes and the special characters are changed.
````

````quiz
What type does `deserialize` produce for the JSON text `[1, 2.5]`?
○ `ArrayList` holding two `Long` values.
→ Not quite. The decimal 2.5 is not a whole number.
◉ `ArrayList` holding a `Long` and a `Double`.
→ Yes. Whole numbers become Long, and decimals become Double.
○ `String[]` holding both numbers as text.
→ Not quite. Arrays become ArrayList, and numbers keep their numeric types.
````

````quiz
Why does the parser report the position of an error?
○ So the text can be printed in a different order.
→ Not quite. The position is for finding the problem, not for reordering.
◉ So the reader can find where in the text the parser stopped, which is usually where the mistake is.
→ Yes. The position points to the first thing the parser could not accept.
○ Because positions are needed to build the JSON text.
→ Not quite. Positions are used while reading, to report problems.
````
