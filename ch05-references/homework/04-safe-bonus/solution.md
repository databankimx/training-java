## Homework: Safe bonus: walkthrough

`addBonus` checks for `null` first. If the reference is `null`, it prints the message and returns, so the element is never read. Only when there is an array does the method add the bonus to `score[0]`.

The order matters. The check has to come before the element is used, because reading an element through a `null` reference throws a `NullPointerException`.

The call with `null` changes nothing, so `points[0]` is still 60 when it's printed again.
