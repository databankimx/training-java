## Homework: Swap with references: walkthrough

The pair is a two-element array, so `swapValues` receives a reference to it, and both the method and `main` see the same array. The method saves `pair[0]` in `temp`, copies `pair[1]` into `pair[0]`, and then copies `temp` into `pair[1]`.

The swap works on the elements of the array, not on local variables. A method that tried to swap two `int` parameters would only change its own copies, which is why the exercise uses an array.

Two equal values swap to the same values, which is correct: the output is unchanged.
