## Homework: Word totals

Print each word and its count from a `TreeMap`, then print the total of all the counts. Use `var` for the loop variables, so each entry's type is worked out from the map.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* create a `Map<String, Integer>` holding "pear" with 5, "apple" with 3, and "fig" with 2, in that order
* loop over the entries with `for (var entry : counts.entrySet())`, printing each key and its value on one line, separated by a space
* add the values together in a second loop, which also uses `var`, and print `Total: ` followed by the sum

### What to check

The output should be:

```text
apple 3
fig 2
pear 5
Total: 10
```

The map keeps its keys sorted, so the words come out alphabetically, even though "pear" was added first.

### Extensions

* Add a second map, and print the words that appear in both. Use `containsKey` to check the second map.
* Change the loop variable's type to `Map.Entry<String, Integer>` in place of `var`, and compare how much each version says. Explain when `var` makes a loop clearer and when the full type is clearer.

---

*This exercise is not from the book. It practices the ideas in Chapter 25, with the maps from Chapter 14.*
