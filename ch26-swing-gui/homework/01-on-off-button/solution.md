## Homework: On and off button: walkthrough

The listener flips the boolean with `on = !on`, then chooses the label text with the conditional operator. The first click makes `on` true, so the label reads `On`, and the next click reverses it.

The field is static so the listener can change it, since a lambda can't change a local variable.
