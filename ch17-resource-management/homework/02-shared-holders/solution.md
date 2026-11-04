## Homework: Shared holders: walkthrough

The list gets a reference to the resource each time `add` is called, so after the second `add` there are two references and the size is 2. `clear` removes both, so the size becomes 0. `original` is also a reference, and setting it to `null` removes the last one.

No line of the program prints when the resource is freed, because Java doesn't report it. The collector may free the object at any time after the last reference is gone.
