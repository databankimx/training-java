## Homework: Ownership handoff: walkthrough

`consume` takes the box and calls `take`, which returns the resource and leaves the box empty. The resource is declared in the `try` header, so it's closed when `consume` finishes, and the `Release handoff` line prints before `main` continues.

The `if` check covers a box that's already empty, where `take` returns `null`. A `try` header with a `null` resource doesn't call `close`, so nothing else prints.
