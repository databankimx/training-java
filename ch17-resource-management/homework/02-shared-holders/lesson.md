## Homework: Shared holders

In this exercise, several places hold the same resource, and you watch how many holders there are as they're added and removed.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* define a `Resource` class whose constructor prints `Acquire <name>`
* in `main`, create a `Resource` named `original` for a resource called `shared`
* create a `List<Resource>` named `holders`
* add `original` to `holders` with `add`, and print `Holders: ` followed by `holders.size()`
* add `original` to `holders` a second time, and print the number of holders again
* clear `holders` with `clear()`, and print the number again
* set `original` to `null`, print `End`, and let `main` finish

### What to check

The output should be:

```text
Acquire shared
Holders: 1
Holders: 2
Holders: 0
End
```

The list holds two references to the same object after the second `add`, so the count is 2. Clearing the list removes both, and the count is 0. Setting `original` to `null` drops the last reference, so the object can be freed after that line, though the program can't see when.

### Why there is no release message

A release message can't be printed when the collector frees an object, because the collector decides when that happens. The program can't print one without a method that runs at that moment, and ordinary objects have none. If the resource must be released at a known point, it should implement `AutoCloseable` and be closed with `try`-with-resources, as the first step of this chapter showed.

### Extensions

* Use a second `Resource`, and show that its references don't affect the count of the first.
* Explain why a list that holds references can keep objects alive longer than you expect, and how a `WeakReference` avoids that.

---

*This exercise is not from the book. It practices the ideas in Chapter 17.*
