## Homework: Grade book

Write a program that keeps a list of scores in an `ArrayList`, and reports the count, the highest, the lowest, and the average.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own scores.

The program should:

* create a `List<Integer>` holding the scores 72, 88, 95, and 60, using `add` for each one
* print `Count: 4`
* use a loop to find the highest score, and print `Highest: 95`
* use a loop to find the lowest score, and print `Lowest: 60`
* use a loop to add the scores, and print the average with two decimal places, as `Average: 78.75`

Use `printf` with `%.2f` for the average. The loops should use the enhanced `for`, as the lists lesson showed.

### What to check

The output should be:

```text
Count: 4
Highest: 95
Lowest: 60
Average: 78.75
```

The average is the total, 315, divided by the count. Dividing two integers would drop the fraction, so convert the total to a `double` before dividing, as the program does with `(double) total`.

### Extensions

* Add a fifth score, and check that every line updates. Does the program need any other change?
* Print the scores above the average, one per line. Think about whether you need the average before the loop that prints them.

---

*This exercise is not from the book. It practices the ideas in Chapter 14.*
