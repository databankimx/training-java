## Homework: Highest score

Write a program that stores three scores in an array, and prints the highest.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* declare an `int` array with three elements
* ask for each score in turn, with the prompts `Score 1: `, `Score 2: `, and `Score 3: `, and store each one in the array
* compare the three elements, and print `The highest score is 85`, using the highest value

Use `System.out.flush()` on each prompt. You don't need a loop yet, so write out the three reads and the comparisons by hand. An `if` on each of the later scores works well here, since the highest value can be found with a few comparisons.

### What to check

Try these sets of scores:

* `70`, `85`, `60` should give `85`
* `90`, `90`, `40` should give `90`, since a tie is still a highest score
* `50`, `50`, `50` should give `50`

The tie case matters. Make sure your comparisons don't depend on the scores being different. Using `>` rather than `>=` is correct here, because a tie doesn't change the highest value.

### Extensions

* Print the position of the highest score as well, such as `The highest score is 85, in position 2`.
* Add a fourth score, and check that the program still works. What changed?

---

*This exercise is not from the book. It practices the ideas in Chapter 4.*
