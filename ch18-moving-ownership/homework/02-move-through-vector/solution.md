## Homework: Move through a list: walkthrough

The first `add` moves the resource from `p` into a new box in the list, so `p` is empty and the list has one box. `take` on the list's first box moves the resource into `back`, and the emptied box is removed.

`back` holds the resource at the end, so the program closes it explicitly after `End`, which prints `Release vessel`. The removed box held nothing, so removing it released nothing.
