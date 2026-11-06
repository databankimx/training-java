## Maps

A list finds a value by its position. Often a program needs to find a value by a name instead: a word's count, a student's grade, or a price for an item. A `Map` stores pairs, where each value is found through its key. `TreeMap` is the map that keeps its keys in sorted order.

Here's the program for this step, in `src/Main.java`. It counts the words in a list:

```java
Map<String, Integer> counts = new TreeMap<>();
String[] words = {"apple", "pear", "apple", "fig", "pear", "apple"};

for (String word : words) {
    counts.put(word, counts.getOrDefault(word, 0) + 1);
}
```

The type `Map<String, Integer>` names the key type first, then the value type. `getOrDefault` returns the count so far, or 0 for a word the map hasn't seen, and `put` stores the new count. The first time a word appears, the fallback of 0 gives a count of one.

Press **Run**. The output is:

```text
apple: 3
fig: 1
pear: 2
apple appears 3 times
kiwi is not in the map
```

### Sorted by key

A `TreeMap` keeps its entries sorted by key. The loop prints `apple`, `fig`, and `pear`, in alphabetical order, whatever order the words were added in. That's a useful property, and it's the reason a sorted map prints the same way every time.

The loop uses `var`, which tells the compiler to work out the type of `entry` from the map itself. Each entry is a pair, so `entry.getKey()` is the key and `entry.getValue()` is the value. Writing out the full type, `Map.Entry<String, Integer>`, is often long, so `var` is common in loops over maps.

### Looking up a key

`counts.get("apple")` reads the value for a key. If the key is missing, `get` returns `null`, and it doesn't add the key. The program checks for `kiwi` with `containsKey`:

```java
if (!counts.containsKey("kiwi")) {
    System.out.println("kiwi is not in the map");
}
```

`containsKey` returns `true` if the key is present and `false` if it isn't, and it doesn't change the map. Use it when you want to test for a key without adding one.

### Map or list

Use a list when the position of a value matters, or when you're working through values in order. Use a map when you're looking up a value by a name or other key. A map of word counts answers "how many times does this word appear?" in one step, where a list would need a search.

---

### Quiz

*These questions are written for this course.*

````quiz
What does `counts.get("kiwi")` return when `kiwi` isn't a key yet?
○ 0, and the key is added with that value.
→ Not quite. get never adds a key, and it returns null for a missing one.
◉ null, and the map is unchanged.
→ Yes. Reading a missing key returns null and leaves the map as it was.
○ An exception is thrown.
→ Not quite. get returns null for a missing key, without throwing.
````

````quiz
In what order does a loop over a `TreeMap`'s entries visit them?
○ The order the entries were added.
→ Not quite. A TreeMap sorts its entries by key, whatever the insertion order.
◉ Sorted by key.
→ Yes. Keys are kept in sorted order, so the output is the same every time.
○ Reverse alphabetical order.
→ Not quite. The default order for a TreeMap's keys is ascending.
````

---

### Try it in the runner

Change `counts.get("apple")` on the line that prints it to `counts.get("kiwi")`, and press **Run**. Predict the output: the line reads `apple appears null times`, since `get` returns `null` for the missing key. Then predict whether `kiwi is not in the map` still prints. It does, because `get` didn't add `kiwi` to the map, so `containsKey` still returns false. Change the line back.

---

A map stores values under keys, and a `TreeMap` keeps them sorted. Together with lists, it covers most of the collections you'll use day to day. The next step covers the map that doesn't keep its keys in order.
