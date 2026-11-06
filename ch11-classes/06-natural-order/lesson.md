## Natural order and comparators

Sorting a list needs to know which of two values comes first. For numbers and text, the library already knows. For your own class, you have to say. A class that implements `Comparable` defines its natural order, the order that sorting uses when no other rule is given.

Here's the class from `src/Main.java`:

```java
static class Book implements Comparable<Book> {
    // ...
    @Override
    public int compareTo(Book other) {
        return title.compareTo(other.title);
    }
}
```

`Comparable<Book>` says that a `Book` can be compared with another `Book`. The method `compareTo` returns a negative number when this book comes before the other, zero when they are equal, and a positive number when it comes after. Here the books are ordered by title, and `String.compareTo` does the work of comparing the two titles.

### Sorting with the natural order

`Collections.sort` works on any list whose elements are `Comparable`:

```java
Collections.sort(books);
System.out.println("By title: " + books);
```

The output is:

```text
By title: [Dune (412 pages), Emma (474 pages), Ulysses (730 pages)]
```

The list is printed with each book's `toString`, which is why the output shows the title and page count for every book.

### A comparator for another order

A class can have only one natural order, and sometimes a program needs a different one. A `Comparator` is a separate rule that can be passed to `sort`:

```java
books.sort(Comparator.comparingInt(Book::pages).reversed());
System.out.println("By pages, longest first: " + books);
```

`Comparator.comparingInt` takes a method that returns an `int` for each book, here `pages`, and builds a comparison from it. `reversed` turns the comparison around, so the largest page count comes first. The output is:

```text
By pages, longest first: [Ulysses (730 pages), Emma (474 pages), Dune (412 pages)]
```

The `Book` class is unchanged. The rule lives where it's used, which is why the lambdas chapter showed comparators written inline.

### Consistency

The natural order should agree with `equals`. Two books that `compareTo` reports as equal should also be equal under `equals`, or the sorted results can surprise a reader. Titles are a poor key when two books share a title, so a real class would compare more fields.

---

### Quiz

*These questions are written for this course.*

````quiz
What does a `compareTo` method return when this object comes before the other?
○ true
→ Not quite. compareTo returns an int, not a boolean.
◉ A negative number.
→ Yes. Sorting puts the object first when the result is negative.
○ The number of fields it has.
→ Not quite. The result must describe the order of the two objects.
````

````quiz
Why would a program use a `Comparator` when the class already implements `Comparable`?
○ Because a Comparator is always faster.
→ Not quite. Speed is not the reason.
◉ Because it gives a different order for one sort, without changing the class.
→ Yes. The natural order stays as it is, and the comparator is used where it's needed.
○ Because `Comparable` cannot be used with lists.
→ Not quite. Comparable works with lists, as the first sort shows.
````

---

### Try it in the runner

Change `compareTo` to compare the page counts, `Integer.compare(pages, other.pages)`, and predict the order of the first output line before you press **Run**. Shortest first, it prints `[Dune (412 pages), Emma (474 pages), Ulysses (730 pages)]`, which happens to match the title order here. Then change the comparator to remove `.reversed()`, and predict the second line.

---

A class that implements `Comparable` has a natural order, and a `Comparator` gives another order where it's needed. The next step covers encapsulation, which controls how the class's data is read and changed.
