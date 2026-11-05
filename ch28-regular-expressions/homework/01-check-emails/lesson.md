## Homework: Check emails

Write a pattern that accepts a simple email address, and use it to check a list of addresses. Only the shape is checked here, not whether the address exists.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* compile a `Pattern` named `EMAIL` that accepts a name made of letters, digits, dots, underscores, or dashes, then an at sign, then a domain of letters, digits, or dashes, a dot, and a suffix of at least two lowercase letters
* write `static boolean isEmail(String text)`, which uses `matches` on the whole text
* print each of these lines, with `true` or `false`: `ada@example.com`, `grace.h@mail.org`, `no-at-sign.com`, `two@@signs.com`, and `ada@examplexcom`

### What to check

The output should be:

```text
ada@example.com: true
grace.h@mail.org: true
no-at-sign.com: false
two@@signs.com: false
ada@examplexcom: false
```

The last three are the tests that matter. An address without an at sign fails, and so does one with two at signs. The final line tests the period trap from the phone-number step: the dot before `com` must be a literal dot. A dot left unescaped matches any character, so `ada@examplexcom` would be accepted, since the `x` matches the dot. Write the dot as `\.` in the pattern, as the example does, and the line is rejected.

### Extensions

* Use a pattern with an unescaped dot, run the checks again, and note which lines change. Explain why the change happens.
* Add an address with an uppercase letter in the suffix, such as `ada@example.COM`, and decide whether it should be accepted. Change the pattern if you decide it should.
* Add the list of addresses as an array, and print only the valid ones, using a loop.

---

*This exercise is not from the book. It practices the ideas in Chapter 28.*
