## Name tracker

A program has a `String` variable for the name of the user, which may be `null` until they've typed it. Write a program that greets the user, and handles the `null` case before it uses the name.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* start with the name set to `null`, and print `No name entered.` when the name is `null`, without calling any method on it
* print `Welcome back, Ada.` when the name equals `"Ada"`, comparing with `equals`, not `==`
* print `Hello, ` followed by the name, and a full stop, for any other name
* then set the name to `"Ada"`, and run the same checks again

### Where to change the code

The checks are written twice, once for the `null` name and once after the name is set. Each check is an `if` chain with the `null` test first. To use a different greeting, change the text of the message. To check for a different name, change the text in `equals`.

Notice the order: `name == null` comes before `name.equals`. Reversing them would throw a `NullPointerException` on the first check, because `null` has no `equals` to call.

### What to check

The output should be:

```text
No name entered.
Welcome back, Ada.
```

Try another name in the second check, such as `"Grace"`, and the output should be `Hello, Grace.`.

### Extensions

* Put the checks in one place, and use it for both cases, so the program doesn't repeat the same chain.
* Change the `equals` test to `equalsIgnoreCase`, and check that `"ADA"` is also welcomed back.

---

*This exercise is not from the book. It practices the ideas in Chapter 5.*
