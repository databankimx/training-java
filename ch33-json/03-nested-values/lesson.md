## Arrays and nested values

Real data is rarely flat. A person has a list of languages, an address that is itself a record, and a set of scores. JSON handles this with arrays, written in square brackets, and with objects inside objects. The writer for this data is recursive: an object's values can be objects or arrays, and an array's items can be objects or arrays, so each one is written by the same method.

### The three writers

`jsonValue` decides what a value is, and passes it to the writer for its type:

```java
static String jsonValue(Object value) {
    if (value == null) {
        return "null";
    }
    if (value instanceof String text) {
        return jsonString(text);
    }
    if (value instanceof Map<?, ?> object) {
        return jsonObject(object);
    }
    if (value instanceof List<?> array) {
        return jsonArray(array);
    }
    return value.toString();
}
```

`jsonObject` and `jsonArray` both call `jsonValue` on each of their own values. An object's values may be arrays, and an array's items may be objects, so the three methods call each other. This is the recursive pattern from the recursion chapter, with the base cases being the strings, numbers, and nulls, which are written directly.

The array writer is the same as the object writer without the keys:

```java
static String jsonArray(List<?> array) {
    StringBuilder json = new StringBuilder("[");
    boolean first = true;
    for (Object item : array) {
        if (!first) {
            json.append(", ");
        }
        json.append(jsonValue(item));
        first = false;
    }
    return json.append("]").toString();
}
```

### The data

The program builds a person with a nested address, a list of languages, and a list of scores:

```java
Map<String, Object> address = new LinkedHashMap<>();
address.put("city", "The Colony");
address.put("state", "TX");

Map<String, Object> person = new LinkedHashMap<>();
person.put("name", "Ada");
person.put("languages", List.of("Java", "Python"));
person.put("address", address);
person.put("scores", List.of(90, 85));
System.out.println(jsonValue(person));
```

Press **Run**. The output is one line:

```text
{"name": "Ada", "languages": ["Java", "Python"], "address": {"city": "The Colony", "state": "TX"}, "scores": [90, 85]}
```

The address is written as an object inside the person's object, and the lists are written in square brackets. Each nested part is written by the same methods that wrote the outer one.

### Depth

The writer works for nested data of any depth, and the recursion depth matches the depth of the data. A person inside a team inside a company adds a level to the calls for each level of nesting. For ordinary records the depth is small, so the stack is never a problem, as the recursion chapter's limits showed.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does `jsonArray` call `jsonValue` on each item rather than writing the items directly?
○ Because items are always strings.
→ Not quite. Items can be any supported type.
◉ Because an item may itself be an object or an array, and `jsonValue` writes whichever type it is.
→ Yes. The same writer handles every level of nesting.
○ Because arrays can't contain numbers.
→ Not quite. Arrays can contain numbers, and jsonValue writes them.
````

````quiz
What is the base case for the recursive writers?
○ An empty object.
→ Not quite. An empty object is written, but it isn't the base case that stops the calls.
◉ A string, a number, a Boolean, or null, which are written directly without calling the writers again.
→ Yes. These values have nothing inside them to visit.
○ The first item of an array.
→ Not quite. The base case is about the type of a value, not its position.
````

---

### Try it in the runner

Add `address.put("zip", 77459);` and predict the output before you press **Run**. The address becomes `{"city": "The Colony", "state": "TX", "zip": 77459}`, since the number is written as its own text. Then add `person.put("pets", List.of(Map.of("name", "Rex")));`, and predict the new array. It's `[{"name": "Rex"}]`, an object inside an array, written by the same methods.

---

Objects and arrays nest to any depth, and one pair of recursive writers handles all of them. The next step is the summary, followed by the homework that writes a small data record.
