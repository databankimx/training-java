## Summary: Collections

This chapter covered collections that grow and that find values by key.

* **`ArrayList` is a growing list.** `add` appends, `remove` deletes, `size()` counts, and `get(i)` reads by index from 0.
* **`List` and `Set` are interfaces.** Declare the variable with the interface, and create the object with the class.
* **The angle brackets name the item type.** `List<String>` holds strings only, and the compiler checks each item.
* **`HashMap` stores values under keys.** `put` stores, `get` reads, and `containsKey` checks for a key.
* **A map holds one value per key.** Putting under an existing key replaces the value.
* **`get` returns null for a missing key.** `getOrDefault` gives a fallback value instead.
* **Type arguments are objects.** A map's values are `Integer`, and Java boxes and unboxes `int` values automatically.
* **A set keeps each item once.** Use `contains` to test membership.
* **Choose by the question.** A list for order and repeats, a set for distinct values, a map for lookup by key.

### Check your understanding

*These questions are written for this course.*

````quiz
Which method adds an item to the end of an `ArrayList`?
○ put
→ Not quite. put belongs to a map, and it stores a value under a key.
◉ add
→ Yes. add appends an item to the end of the list.
○ push
→ Not quite. A list uses add, not push.
````

````quiz
After `ages.put("Ada", 36)` and then `ages.put("Ada", 37)`, what does `ages.get("Ada")` return?
○ 36, because the first value is kept.
→ Not quite. The second put replaces the first value.
◉ 37, because the second put replaced the value.
→ Yes. A key holds one value, and the latest put overwrites it.
○ An error, because the key was used twice.
→ Not quite. Using a key again replaces its value, without an error.
````

````quiz
Which collection should you use to find out whether a value appears in a list of names, without caring about order or repeats?
○ A list, using get.
→ Not quite. get reads by index, so it doesn't answer whether a name is present.
◉ A set, using contains.
→ Yes. A set answers membership quickly, and it ignores repeats.
○ A map, using put.
→ Not quite. put stores a value, and doesn't test for presence.
````
