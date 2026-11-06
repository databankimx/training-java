## Largest value: walkthrough

The method sets `best` to the first element, then checks each later element. Whenever an element is greater than `best`, it becomes the new `best`. When the loop finishes, `best` holds the largest value, and the method returns it.

The loop starts at index 1 because the first element is already the starting value. Starting at 0 would still give the right answer, since comparing the first element with itself changes nothing, but starting at 1 is the clearer choice.

Starting `best` at 0 instead would give the wrong answer when every value is negative. For `{-3, -5}`, a starting value of 0 would never be replaced, and the method would return 0, which isn't in the array. The first element is the safe starting point, because it's always in the array.
