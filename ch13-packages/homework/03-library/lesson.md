## Homework: Library

Write a small library program split across files: a `Book` class in its own file, a `Library` class in its own file, and a `Main` class that uses them.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own books.

The program should:

* in `Book.java`, define a class `Book` with a constructor that takes a title, a `print` method that prints `Dune - available` or `Dune - checked out`, and a `checkOut` method that marks the book as checked out
* in `Library.java`, define a class `Library` that holds up to three books in a private array, with an `add` method that stores a book if there's room, and a `printAll` method that prints every book it holds
* in `Main.java`, create a library, create the books `Dune` and `Emma`, check out `Emma` before adding it to the library, and call `printAll`

### A reference, not a copy

`add` stores a reference to the book it's given, not a copy of it. The library and `main` therefore share the same `Book` objects, as the references chapter described. A checkout made after a book is added is visible through the library, since the library holds the same object.

Check out `Emma` before adding it, as the program does, and the output is the same as a copy would give. Move the `checkOut` call after `library.add(emma)`, and predict the output before you run it. It still shows `Emma - checked out`, since the library sees the change.

### What to check

The output should be:

```text
Dune - available
Emma - checked out
```

Each file must be in the `src` folder, with the name of its public class. The runner compiles them together, so a missing file shows up as a compile error.

### Extensions

* Make `add` refuse a fourth book, and print a message when the library is full. Decide whether the message belongs in `add` or in `main`, and say why.
* Add a `count` method to `Library` that returns the number of books, and print it after the books are added.

---

*This exercise is not from the book. It practices the ideas in Chapter 13.*
