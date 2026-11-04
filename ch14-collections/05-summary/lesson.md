## Summary: Collections

This chapter covered the collections that store groups of values.

* **`ArrayList` grows as you add to it.** `add` puts an element at the end, `size()` gives the count, and the list keeps its order.
* **`get` checks the index.** `scores.get(i)` throws an `IndexOutOfBoundsException` for a bad index, so a bad index is always reported.
* **`Map` finds values by key.** `put` stores a value, `get` reads it, and `containsKey` tests for a key without changing the map.
* **A `TreeMap` keeps its keys sorted.** A loop over the map visits entries in key order.
* **A `HashMap` trades order for lookup speed.** It's a hash table, and it has no guaranteed order, so output that must be stable needs sorting first.
* **Reading a missing key doesn't add it.** `get` returns `null` for a missing key, and the map is unchanged.
* **Choose by the main job.** Use an array for a fixed size, a list for an ordered collection, and a map for lookups by key.
* **Collections can be combined.** A program often keeps one collection for order and another for lookups.

### Check your understanding

*These questions are written for this course.*

````quiz
Which method tests whether a key is in a map, without adding it?
○ get
→ Not quite. get returns the value, or null, and doesn't say whether a key is present in every case.
◉ containsKey
→ Yes. containsKey returns true or false, and doesn't change the map.
○ size
→ Not quite. size gives the number of entries, not whether a key is present.
````

````quiz
In what order does a loop over a `TreeMap` visit its entries?
○ The order they were inserted.
→ Not quite. A TreeMap keeps its entries sorted by key.
◉ Sorted by key.
→ Yes. The key order is part of the map's guarantee.
○ Random order.
→ Not quite. The order is fixed by the keys, so it's the same every time.
````

````quiz
Which method reads an element of a list, and throws an exception for a bad index?
○ A list has no such method.
→ Not quite. get is the method for reading an element.
◉ get
→ Yes. Every index is checked, and a bad one throws IndexOutOfBoundsException.
○ add
→ Not quite. add puts an element in, and doesn't read one.
````
