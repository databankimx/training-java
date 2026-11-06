## Choosing a collection

The last three steps introduced three collections that look similar on the surface: arrays, lists, and maps. Each one suits a different job. This step puts them side by side, and shows how a program often uses more than one.

Here's the program for this step, in `src/Main.java`. It keeps the names in a list, and builds a map from each name to its length:

```java
List<String> names = new ArrayList<>(List.of("Ada", "Grace", "Alan"));

Map<String, Integer> lengths = new TreeMap<>();
for (String name : names) {
    lengths.put(name, name.length());
}
```

Press **Run**. The output is:

```text
Names: 3
Ada: 3
Alan: 4
Grace: 5
```

The list holds the names in the order they were given. The map holds the lengths, looked up by name, and prints them sorted. Neither collection is wrong for its job: the list keeps order, and the map answers lookups.

### A comparison

| Collection | Size | Finds a value by | Keeps order | Use it when |
|---|---|---|---|---|
| Array | Fixed when created | Index | The order it was written in | The size is known and never changes |
| `ArrayList` | Grows with `add` | Index | The order it was added in | You have a list, and its size isn't known in advance |
| `TreeMap` | Grows with `put` | Key | Sorted by key | You look values up by a name or other key |
| `HashMap` | Grows with `put` | Key | No order is guaranteed | Lookups dominate, and the order of the entries doesn't matter |

### What each one costs

The table says what each collection is for. Its cost is the other half of the choice, and the figures here are the outline. The efficiency chapter measures them with operation counts.

* **An array** lives in one fixed block, so reading by index is immediate and nothing is allocated after it's created.
* **An `ArrayList`** also lives in one block, so reading by index is just as fast. Adding to the end is cheap on average, as the lists step explained. Inserting or removing in the middle means moving the elements after that point, which takes time in proportion to how many there are.
* **A `TreeMap`** allocates each entry separately, so the entries are spread through memory. A lookup or insertion compares the key against a few entries, and the number compared grows with the logarithm of the map's size.
* **A `HashMap`** stores its entries in buckets, chosen by each key's hash code. A lookup usually goes straight to the right bucket, so the average cost stays about the same as the map grows. The table itself takes memory, and when many keys land in the same bucket, a lookup can slow down.

The default is an `ArrayList`. It's the simplest collection that grows, and it's the one most code reaches for first. Move to a map when the main question is "what is the value for this key?", and use an array only when the size is fixed and small. If the key lookups are the bottleneck and the order doesn't matter, a `HashMap` is the faster choice, as the previous step explained.

### Several collections together

Real programs often combine collections, as this one does. The list keeps the names in input order, and the map answers questions about them. Each collection does the job it's best at, and the program is clearer than one that forces a single collection to do both.

### What the collections share

The collections are used in the same way where it matters. Each can be walked with the enhanced `for`, each reports its size, and each can hold any class type, including your own records and classes. The generics chapter explains how a collection is written once and works for every type.

---

### Quiz

*These questions are written for this course.*

````quiz
Which collection would you choose to store a list of exam scores, whose count is not known until the program runs?
○ An array, since it's the simplest.
→ Not quite. An array's size is fixed when it's created, and this count isn't known in advance.
◉ An ArrayList, since it grows as values are added.
→ Yes. An ArrayList keeps the order and grows to fit the data.
○ A TreeMap, since it stores more information.
→ Not quite. A map is for lookups by key, and a list of scores doesn't need keys.
````

````quiz
Which collection would you choose to find a student's grade from the student's name?
○ An ArrayList of grades, searched from the start.
→ Not quite. That would work, but it's a search through the whole list, and a map is designed for this.
◉ A TreeMap from name to grade.
→ Yes. The map looks up the value for a key directly.
○ An array of names, with the grade at the same index.
→ Not quite. That relies on keeping two lists in step, which is easy to get wrong.
````

---

### Try it in the runner

Add `names.add("Ada");` after the list is created, and press **Run**. Predict how many entries the map holds afterwards. It still holds three, since the map is keyed by name, and the second "Ada" writes to the same entry. The `Names:` line now reads 4.

Then change `lengths` to a `HashMap`, and check whether the output changes. It may not, for three names. Then predict whether the order would stay the same with a thousand names, and explain why the sorted map is the safer choice when the output has to be stable.

---

A list keeps items in order, a map finds values by key, and an array is for a fixed set. Choosing one is a question about the main job the data has to do. The summary covers the chapter's collections, and the homework applies them to two small programs.
