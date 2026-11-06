## Homework: Sealed counter

A class in the `tally` package counts in steps of 5. Callers can add to the total and read it, but the step and the internal reset are hidden from them. Use access modifiers to enforce that, and check the result from `Main`.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own step.

The program should:

* keep the total private, and keep the step as a private constant of 5
* provide `addOne()` and `total()` as public methods
* provide `resetInternal()` with package access, so only the `tally` package can call it
* in `Main`, add one twice, and print `Total: ` followed by the total

### Where to change the code

The step is the constant `STEP` in `Tally`. Changing it changes how much each `addOne` adds, and callers can't change it, because it's private. To expose a reset to callers, make `resetInternal` public, which is a decision about what the package offers.

### What to check

The output should be:

```text
Total: 10
```

Two additions of 5 give 10.

### Extensions

* Try calling `tally.resetInternal()` from `Main`, and predict the compiler's message before you check it.
* Make `total` a public field instead of a method, and explain what the caller could do that it can't do now.

---

*This exercise is not from the book. It practices the ideas in Chapter 13.*
