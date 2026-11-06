## Homework: Swap with references

The functions chapter swapped two numbers with a method that received references to them. Java can't pass two `int` variables by reference, so this version uses a two-element array as the pair, and the method swaps the elements through the reference it receives.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* ask for two integers, `a` and `b`, with the prompts `Enter a: ` and `Enter b: `, and read them with `Scanner`
* define `swapValues`, which takes an `int[]` of two elements, and swaps them
* call `swapValues` from `main`, passing an array holding `a` and `b`
* print the values as `a = 8, b = 3`, after the swap

Use `System.out.flush()` on each prompt. Inside `swapValues`, read and write the elements by index, and don't create a new array, since the caller must see the change.

### What to check

Try these inputs, and compare the output with the answers:

* `3` and `8` should print `a = 8, b = 3`
* `5` and `5` should print `a = 5, b = 5`
* `-2` and `7` should print `a = 7, b = -2`

Check the second case as well. A swap of two equal values changes nothing, and that's correct.

### Extensions

* Write `swapValues` so it does nothing when the array is `null`, and explain why the check has to come before the swap.
* Compare your `swapValues` with the reference version from the functions chapter. Which one reads more clearly to you, and which one would make a mistake easier to spot?

---

*This exercise is not from the book. It practices the ideas in Chapter 5.*
