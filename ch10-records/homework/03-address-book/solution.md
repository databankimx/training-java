## Homework: Address book: walkthrough

The contacts are an array of `Contact` records, created with a `new` for each one. The loop checks each record's `name()` against the typed name with `equalsIgnoreCase`, which ignores letter case, so `alan` finds Alan.

When a match is found, the loop prints the stored name, not the typed one, then sets `found` and uses `break` to stop. Without the `break`, the loop would print every later match as well.

The `found` flag lets the program print the not-found message after the loop, once it knows no match was made.
