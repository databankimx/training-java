## Homework: Two totals

Add the numbers from 1 to 10000 in two ways: with a primitive `long`, and with a list of boxed `Integer` values. Print both totals, and check that they agree. The check is the important part, because a faster method is only useful if it gives the right answer.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own count.

The program should:

* set `count` to 10000
* add the numbers from 1 to `count` into a primitive `long`
* add the same numbers into a `List<Integer>`, then add the list's values into a second `long`
* print `Primitive total: `, `Boxed total: `, and `Totals agree: ` followed by a `true` or `false`

### Where to change the code

The `count` variable sets how many numbers are added. The comparison `primitiveTotal == boxedTotal` is the check, and it compares two `long` primitives, so `==` compares their values.

### What to check

The output should be:

```text
Primitive total: 50005000
Boxed total: 50005000
Totals agree: true
```

The sum of 1 to 10000 is 10000 times 10001 divided by 2, which is 50005000.

### Extensions

* Time each total with `System.nanoTime`, running each loop several times. Explain which total is faster, and why a single run is not enough.
* Change `count` to 1000000, and check that the totals still agree. Think about whether the list of boxes is still a sensible choice at that size.

---

*This exercise is not from the book. It practices the ideas in Chapter 15.*
