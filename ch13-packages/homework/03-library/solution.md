## Homework: Library: walkthrough

`Book` keeps its title and a flag that records a checkout. `print` shows the title with the status, and `checkOut` sets the flag.

`Library` keeps up to three books in an array, and `count` tracks how many are used. `add` stores the book only while there's room, and `printAll` prints the books from the start of the array up to `count`.

Because the library holds references, `Emma` is the same object in both places. Checking it out before or after adding it gives the same output.
