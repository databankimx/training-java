## Homework: Greeting card

Write a program that asks for a person's name and age, and then prints a short greeting.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* ask for the name first, with the prompt `What is your name? `, and read the whole line with `nextLine`
* ask for the age second, with the prompt `How old are you? `, and read it as an `int` with `nextInt`
* print a greeting with the name, and a sentence saying how old the person will be next year, like this:

```text
Hello, Ada.
Next year you will be 37.
```

Use `System.out.flush()` on both prompts. Give the age variable a name that says what it holds, such as `age`.

### What to check

Try a name with a space in it, such as `Grace Hopper`. The whole name should appear in the greeting. If only `Grace` appears, you've read the name with `next` instead of `nextLine`.

Also try an age of `36`, and check that the sentence says `37`.

Remember the leftover newline from the user-input lesson. The name is read first, with `nextLine`, and the age is read after it, with `nextInt`, so the order here is safe. Reversing the two questions would make the name prompt come back empty.

### Extensions

* Ask for the person's favorite color as well, and add it to the greeting.
* Check that the age is positive before you print the greeting. The lesson hasn't covered `if` yet, so you can leave this for later.

---

*This exercise is not from the book. It practices the ideas in Chapter 2.*
