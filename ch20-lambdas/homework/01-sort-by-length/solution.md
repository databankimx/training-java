## Homework: Sort by length: walkthrough

The lambda compares the lengths of the two words, and `Integer.compare` gives a negative result when the first word is shorter, which puts it first. Equal lengths give zero, and `List.sort` leaves those words in their original order.

For the sample list, "fig" is shortest, then "pear" and "kiwi" (both four letters, kept in their original order), then "banana".
