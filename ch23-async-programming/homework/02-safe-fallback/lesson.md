## Homework: Safe fallback

Read two sensor values in the background. One sensor works, and the other fails. Use `exceptionally` so the failing read gives a fallback value, and the program still prints both readings and a total.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* write a method `readSensor(int id)`, which returns `id * 10`, and throws an `IllegalArgumentException` when the id is negative
* start two futures with `supplyAsync`: one that reads sensor 3, and one that reads sensor -1
* give each future a fallback of 0 with `exceptionally`
* print `Good reading: ` and the first value, `Bad reading: ` and the second value, and `Total: ` and their sum

### What to check

The output should be:

```text
Good reading: 30
Bad reading: 0
Total: 30
```

The failing read doesn't stop the program. The fallback value takes its place in the total.

### Extensions

* Change the fallback to -1, and predict the total before you run the program. Explain why a negative fallback can be a poor choice for a total.
* Remove `exceptionally` from the bad reading, and predict what `join` does. Read the exception the program throws, and find the original message inside it.

---

*This exercise is not from the book. It practices the ideas in Chapter 23.*
