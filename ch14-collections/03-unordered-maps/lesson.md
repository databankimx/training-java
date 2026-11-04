## Hash maps

The sorted map from the last step keeps its entries in order, and that costs something. Finding a key in a `TreeMap` takes a number of comparisons that grows with the logarithm of the number of entries. For most programs that's fast enough, and the sorted order is useful. Sometimes it isn't enough, and a program has many lookups, with no need for any particular order. That's where `HashMap` comes in.

Here's the program for this step, in `src/Main.java`. It's the word count from the last step, with one change to the class:

```java
Map<String, Integer> counts = new HashMap<>();
```

The class is in `java.util`, and the rest of the code is the same. `put` and `getOrDefault` work in the same way, `containsKey` tests for a key in the same way, and `size` reports the number of distinct words.

Press **Run**. The output is:

```text
Distinct words: 3
apple: 3
pear: 2
fig: 1
kiwi is not in the map
```

The program prints only single lookups, not a loop over the whole table. That's deliberate, and the next section explains why.

### What makes it fast

A `HashMap` is a hash table. It uses each key's `hashCode` method to turn the key into a number, and that number tells the table where to look for the key. Finding a key then means computing its hash and checking the few entries stored there, rather than comparing the key with many others. On average, a lookup takes about the same time however many entries the table holds. Computer scientists describe this as constant time, and the efficiency chapter explains what that means and where it stops being true.

This is why keys of a class need both `equals` and `hashCode`. Two equal keys must produce the same hash, or a lookup could miss an entry that's there. `String` provides both, so text keys work as the program shows.

### What it gives up

The table has no order. A loop over a `HashMap` visits the entries in whatever order the table happens to hold them, and that order is not guaranteed. It can change when the number of entries grows and the table is rebuilt, and it can differ between versions of Java. A program whose output must be the same every time should not print a `HashMap` directly. Sort the results first, or use a `TreeMap`.

The same applies to tests. A test that compares printed output from a `HashMap` may pass on one run and fail on another, even with unchanged code.

### Choosing between them

Use `TreeMap` when you need the entries in key order, or when the keys have an ordering that matters, such as dates. Use `HashMap` when lookups dominate, the order doesn't matter, and the number of entries is large enough that speed matters. For a small number of entries, the difference is rarely noticeable, and the sorted map is often the clearer choice.

---

### Quiz

*These questions are written for this course.*

````quiz
What must two equal keys produce, so that a `HashMap` finds the entry?
○ The same key object.
→ Not quite. The keys can be different objects, as long as they are equal.
◉ The same hash code.
→ Yes. The table looks in the bucket for the hash, so equal keys must hash alike.
○ The same position in the list of entries.
→ Not quite. A hash map doesn't keep its entries in a list.
````

````quiz
Which statement about the order of a `HashMap` is true?
○ Entries are always in the order they were inserted.
→ Not quite. Insertion order isn't kept.
◉ The order is not guaranteed, so output that must be stable should be sorted first.
→ Yes. The table's layout decides the order, and it can change.
○ Entries are always sorted by key.
→ Not quite. That's the TreeMap guarantee, not this class's.
````

---

### Try it in the runner

Add a loop that prints every entry of `counts`, with `for (var entry : counts.entrySet())`, and press **Run**. Then predict whether the order would be the same on a different version of Java, or with more words added. The honest answer is that it might not be. You can't depend on the order, so any test or report that needs a fixed order has to sort the results first, or use a `TreeMap` instead.

---

A `HashMap` trades order for speed on lookups, and it's a hash table underneath. The next step puts it beside the other collections in the choosing guide.
