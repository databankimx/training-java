## Same place

Two places are recorded as points. Use `equals` and `==` to compare them, and predict each result before you run the program.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own points.

The program should:

* create `home` and `cafe`, both at (0, 0), and `park` at (5, 2)
* print the result of `home.equals(cafe)`, with the label `home equals cafe: `
* print the result of `home.equals(park)`, with the label `home equals park: `
* print the result of `home == cafe`, with the label `home == cafe: `
* print the result of `home == home`, with the label `home == home: `

### Where to change the code

The four comparisons are the lines to change, and each one tests something different. To compare other points, change the `new Point` values, or add a new point and compare it with the others.

### What to check

The output should be:

```text
home equals cafe: true
home equals park: false
home == cafe: false
home == home: true
```

`home` and `cafe` hold the same values, so `equals` is `true`. They are separate objects, so `==` is `false`. A variable is always equal to itself by reference, so `home == home` is `true`.

### Extensions

* Add a third point, the same as `park`, and check that `park.equals(third)` is `true` while `park == third` is `false`.
* Put the points into an array, and find out which pair is equal, using nested loops from the loops chapter.

---

*This exercise is not from the book. It practices the ideas in Chapter 10.*
