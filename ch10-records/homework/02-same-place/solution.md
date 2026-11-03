## Same place: walkthrough

`equals` on a record compares the components, so two points at (0, 0) are equal, and a point at (5, 2) is not equal to either.

`==` compares references, so `home == cafe` is `false` even though the values match: they are two objects. `home == home` is `true`, because a reference is always the same object as itself.

The rule to take away is the one from the references chapter. Use `equals` when the question is whether two values match, and `==` only when the question is whether two names refer to the same object.
