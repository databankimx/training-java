## Homework: Sum nested numbers: walkthrough

Each number is added directly to the total. Each list is handled by calling `sumNested` on it, and that call returns its own total, which is added in. An empty list adds nothing, so `List.of()` returns zero.

For the list in the program, the numbers 1 to 6 add up to 21.
