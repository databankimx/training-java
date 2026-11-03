## Shared scores

Two variables refer to one array of scores, and then one of them is given a new array. Work out what each variable sees, then write the program to check your answer.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* create an array called `teamScores` with the values 10, 20, and 30
* create a second variable called `gameScores` that refers to the same array
* change element 1 through `gameScores` to 25, and print element 1 through both names
* reassign `gameScores` to a new array of three zeros, change its element 1 to 40, and print element 1 through both names again

### Where to change the code

The two lines that matter most are the assignment that makes the alias, and the one that reassigns it:

```java
int[] gameScores = teamScores;
gameScores = new int[]{0, 0, 0};
```

The first shares the array, and the second points `gameScores` at a different one. Changing either array's values later only affects the names that refer to that array.

### What to check

The output should be:

```text
teamScores[1]: 25
gameScores[1]: 25
teamScores[1] after reassigning: 25
gameScores[1] now: 40
```

Both names show 25 before the reassignment, because they share the array. After the reassignment, `teamScores[1]` is still 25, because only `gameScores` changed which array it refers to.

### Extensions

* Print `gameScores == teamScores` after each step, and check that it is `true` only before the reassignment.
* Change the reassignment to `gameScores = teamScores.clone();`. Predict whether `teamScores[1]` changes after `gameScores[1]` is set, and check your answer.

---

*This exercise is not from the book. It practices the ideas in Chapter 5.*
