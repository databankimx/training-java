## Shared scores: walkthrough

After `int[] gameScores = teamScores;`, there's one array with two names. Setting `gameScores[1] = 25` changes the array both names refer to, so both print 25.

The reassignment `gameScores = new int[]{0, 0, 0};` makes `gameScores` refer to a new array. The first array is still referred to by `teamScores`, so its element 1 keeps the value 25. Setting `gameScores[1] = 40` changes only the new array, which is why `gameScores[1]` now prints 40 and `teamScores[1]` doesn't.

The extension with `clone()` is a copy rather than a second name. A clone is a new array with the same values, so after cloning, changes to one array don't affect the other.
