## Homework: Factorial: walkthrough

The recursive version stops at 1. Its base case returns 1 for n of 1 or less, so 0! and 1! are both 1, and each higher value multiplies n by the factorial of one less.

The loop version starts `result` at 1 and multiplies it by each number from 2 up to n. For 0 and 1 the loop never runs, so the result stays 1, the same as the base case.

The two methods agree for every n in the range, so each line ends in `(agree)`. The conditional expression chooses the text, and `recursive == loop` compares two `int` values.
