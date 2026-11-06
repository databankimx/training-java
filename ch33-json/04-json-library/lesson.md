## A JSON library: serialize and deserialize

The earlier steps wrote JSON by hand. A library packages that work behind two methods, so a program can convert in either direction without knowing how the text is built:

* `serialize` turns a Java value into JSON text.
* `deserialize` reads JSON text and returns the Java values it describes.

The library is the class `Json` in `src/Json.java`. Any other program in the course can use it by copying the file into its `src` folder, as the other steps copy their shared classes.

### What each type becomes

`deserialize` has to choose a Java type for each JSON value, and the choice is written in the class comment:

| JSON | Java |
|---|---|
| object | `LinkedHashMap`, which keeps the key order |
| array | `ArrayList` |
| string | `String` |
| whole number | `Long` |
| decimal number | `Double` |
| `true` and `false` | `Boolean` |
| `null` | `null` |

Whole numbers become `Long`, so a JSON value of 36 comes back as a `long` value, and a decimal such as 3.5 becomes a `Double`. The types are chosen from the text, since JSON doesn't record which Java type a value came from.

### Serializing

`serialize` checks the type of a value and writes it. For a map or a list, it calls itself on each entry or item, so nested data of any depth is written by the same method. This is the same recursive pattern as the previous step:

```java
if (value instanceof Map<?, ?> object) {
    // ... writes each entry by calling serialize on its value
}
```

Anything that isn't a string, map, list, or `null` is written with `toString`. That covers numbers and `Boolean` values, whose text is already valid JSON.

### Deserializing with a parser

Reading JSON needs a parser: a method that reads the text one piece at a time and builds the values. The library uses a recursive descent parser, with one method for each kind of value. `parseValue` looks at the next character and chooses the method:

```java
Object parseValue() {
    skipWhitespace();
    char c = text.charAt(pos);
    if (c == '{') return parseObject();
    if (c == '[') return parseArray();
    if (c == '"') return parseString();
    if (c == '-' || Character.isDigit(c)) return parseNumber();
    // ... true, false, and null
}
```

`parseObject` and `parseArray` call `parseValue` for each of their contents, so an object inside an array inside an object is read by the same methods. The parser's `pos` field records how far it has read. Each method moves `pos` past the piece it has handled.

### Errors

A JSON text can be wrong: a missing colon, a missing brace, or an unknown word. The parser reports the position where it found the problem:

```text
Error: Unexpected character '}' at position 9
```

The position is the character index in the text. It points to the place where the parser stopped, which is usually the first thing that's wrong. `deserialize` throws an `IllegalArgumentException`, so a caller can catch it and handle bad input.

### Running it

The program reads a JSON text, serializes it again, and checks the result against the original. Press **Run**. The output is:

```text
Type: LinkedHashMap
age is a Long
Serialized again: {"name": "Ada", "age": 36, "languages": ["Java", "Python"], "active": true, "nickname": null}
Round trip matches: true
Error: Unexpected character '}' at position 9
```

The round trip matches because the text in the program is written in the same form the library produces. For a JSON text written in a different style, such as one with extra spaces or line breaks, the round trip gives the same values, but not the same text.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does `deserialize` return a `Long` for the number 36, rather than an `int`?
○ Because `int` can't hold 36.
→ Not quite. An int holds 36 easily.
◉ Because the library reads every whole number as a `Long`, since JSON doesn't say how large a number is.
→ Yes. The library picks one type for all whole numbers, so no value is cut short.
○ Because JSON numbers are always large.
→ Not quite. The size of the number isn't the reason for the type.
````

````quiz
What does the parser's `pos` field record?
○ The number of objects read so far.
→ Not quite. The field records a position in the text.
◉ The index of the next character to read, which each method moves past what it handles.
→ Yes. Every method starts from pos and leaves it at the next unread character.
○ The number of errors found.
→ Not quite. Errors are reported with pos, but the field doesn't count them.
````

---

### Try it in the runner

Change the input text to `"[1, 2.5, -3]"`, and predict the Java type of each element before you press **Run**. The first is `Long`, the second is `Double`, and the third is `Long`, since a leading minus sign still gives a whole number. Then change the input to `"[1,]"`, and predict the error. The parser expects a value after the comma, so it reports `Unexpected character ']'`, with the position of the bracket.

---

A serialize and deserialize pair turns Java values into text and back, and the recursive parser handles nested data of any depth. The summary follows, with a note on what this library does not handle.
