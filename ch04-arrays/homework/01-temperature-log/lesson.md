## Temperature log

A weather station records five temperature readings. Store them in an array, and print the first and last readings, and their average.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own readings.

The program should:

* create a `double` array holding the five readings: 18.5, 19.0, 21.5, 20.0, and 17.0
* print `First reading: ` followed by the first value, using index 0
* print `Last reading: ` followed by the last value, using `readings.length - 1` as the index, so the program still works if the array changes size
* add the readings together, and print `Average: ` followed by the average with two places after the point

### Where to change the code

The readings are in one line, inside curly braces, which creates the array and fills it:

```java
double[] readings = {18.5, 19.0, 21.5, 20.0, 17.0};
```

To use your own readings, change the values in that line. The average line adds the five readings by index, so if you add or remove a value, that line must change too. The loops in a later chapter make that much easier, which is why we keep it simple here.

### What to check

With the readings above, the output should be:

```text
First reading: 18.5
Last reading: 17.0
Average: 19.20
```

The last reading prints as `17.0`, because Java prints a `double` with at least one digit after the point. The average is 96 divided by 5, which is 19.2, shown as `19.20` by the format.

### Extensions

* Print the highest reading, by comparing the values with `Math.max`, as the arithmetic lesson showed.
* Change the array to hold `int` values, and check how the average changes if the division is done with whole numbers. Look back at the division trap in the arithmetic lesson before you run it.

---

*This exercise is not from the book. It practices the ideas in Chapter 4.*
