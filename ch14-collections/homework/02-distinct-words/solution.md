## Distinct words: walkthrough

`add` on a set does nothing when the item is already present, so the repeated `the` and `cat` don't increase the size. After the loop, the set holds one copy of each word, and `size()` gives 4.

`contains` answers membership directly. It's the right question for a set, and it doesn't need a loop over the words.

The count of seven comes from `words.length`, the array's length, which counts every word, repeats included. The difference between seven and four is the number of repeats.
