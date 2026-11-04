## Homework: Safe age

Write a program that asks for an age, and keeps asking until it gets a valid one. Invalid input is reported with an exception, and the program recovers from it.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* ask for an age with the prompt `Enter your age (0-120): `
* read the answer with a `Scanner`
* throw an `IllegalArgumentException` with the message `not a number` when the input isn't a number, and remove the bad word from the input so it can be read again
* throw an `IndexOutOfBoundsException` with the message `age must be from 0 to 120` when the number is outside that range
* catch each exception, print its message with `getMessage()`, and ask again
* when a valid age has been read, print `Age accepted: 42`, with the age that was entered, and end

Use a `while (true)` loop, with `break` once a valid age is read. Use `System.out.flush()` on the prompt.

### Why IndexOutOfBoundsException

Java has no class that means "a value out of its valid range". `IndexOutOfBoundsException` is the closest standard class, and it's used here because the check is on a range of values. A common alternative is `IllegalArgumentException`, which is also reasonable. Whichever you choose, use the same class in the throw and the catch.

### What to check

Try these inputs, in this order:

* `abc` should print `not a number` and ask again
* `200` should print `age must be from 0 to 120` and ask again
* `-1` should print the same range message
* `42` should print `Age accepted: 42`

The first input is the important one. Without removing the bad word from the input, the program would print the message over and over, because `hasNextInt` would keep seeing the same word.

### Extensions

* Add a third check for an age that's valid but unlikely, such as above 100, and throw a different exception for it. Think about which standard class fits best.
* Catch the two exceptions separately, so each one has its own handling. Then compare the two versions: which one is easier to change later?

---

*This exercise is not from the book. It practices the ideas in Chapter 9.*
