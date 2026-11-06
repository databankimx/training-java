## Objects

A JSON object is a set of name and value pairs, written between braces. The names are strings, and the values can be strings, numbers, `true` or `false`, `null`, or further objects and arrays. Objects are the usual way JSON describes a record, such as a person, a product, or a setting.

### Writing a key and its value

Each pair is written as a quoted key, a colon, and the value. The writer in this step turns a `Map` into an object:

```java
static String jsonObject(Map<String, ?> object) {
    StringBuilder json = new StringBuilder("{");
    boolean first = true;
    for (Map.Entry<String, ?> entry : object.entrySet()) {
        if (!first) {
            json.append(", ");
        }
        json.append(jsonString(entry.getKey())).append(": ").append(jsonValue(entry.getValue()));
        first = false;
    }
    return json.append("}").toString();
}
```

The `first` flag decides whether a comma goes before a pair. The first pair has no comma in front of it, and each later pair does. This is the same job a `String.join` would do, written out so each step is visible.

### Writing each value

A value has to be written according to its type. `jsonValue` handles the cases this step needs:

```java
static String jsonValue(Object value) {
    if (value == null) {
        return "null";
    }
    if (value instanceof String text) {
        return jsonString(text);
    }
    return value.toString();
}
```

A `null` becomes the JSON word `null`. A `String` is escaped by `jsonString` from the previous step. A number or a `Boolean` prints as its own text, since `true`, `false`, and the digits are already valid JSON.

### Keeping the order

Java's `HashMap` doesn't promise any order for its entries, so the output could list the keys in a different order each run. A `LinkedHashMap` keeps them in the order they were added, which makes the output predictable and easy to compare. JSON doesn't require any order for the keys, but a fixed order helps when the text is read by people.

The program adds four entries to a `LinkedHashMap` and writes it:

```java
Map<String, Object> person = new LinkedHashMap<>();
person.put("name", "Ada");
person.put("age", 36);
person.put("active", true);
person.put("nickname", null);
System.out.println(jsonObject(person));
```

Press **Run**. The output is:

```text
{"name": "Ada", "age": 36, "active": true, "nickname": null}
```

The keys are in the order they were added. The `nickname` value is `null`, which JSON writes as the word `null` without quotes.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does the writer keep a `first` flag rather than always writing a comma before each pair?
○ Because JSON does not allow commas.
→ Not quite. JSON uses commas between pairs.
◉ Because the first pair has no comma in front of it, and every later pair does.
→ Yes. The flag keeps the comma out of the first position, which would be invalid JSON.
○ Because the map might be empty.
→ Not quite. An empty map is handled, but that's not why the flag exists.
````

````quiz
Why is a `LinkedHashMap` used rather than a `HashMap`?
○ Because `HashMap` can't hold strings.
→ Not quite. A HashMap holds strings without difficulty.
◉ Because a `LinkedHashMap` keeps the keys in the order they were added, so the output is predictable.
→ Yes. A HashMap doesn't promise any order for its keys.
○ Because `LinkedHashMap` is faster.
→ Not quite. The reason is the predictable order.
````

---

### Try it in the runner

Add `person.put("languages", List.of("Java", "Python"));` after the other entries, and predict the output before you press **Run**. It prints the list as its own text, `[Java, Python]`, which is not valid JSON, since JSON arrays use square brackets with quoted strings. The next step writes arrays correctly, and that is the reason the output needs the change.

---

An object is a map of keys to values, written in a fixed order with a comma between pairs. The next step adds arrays and nested objects, written by a recursive method.
