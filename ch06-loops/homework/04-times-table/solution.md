## Homework: Times table: walkthrough

The loop counter `i` runs from 1 to 10, so the body runs exactly ten times, whatever number the user entered. Each pass prints the number, the counter, and the product, as `number x i = product`.

The product is calculated inside the `println` call with `number * i`. The brackets make sure the multiplication happens before it's joined to the text, so the line reads `7 x 3 = 21`, not a joined string of the numbers.

For a number of 0, every product is 0, so every line ends in `= 0`, as the lesson's check expects.
