## Thermostat

A thermostat keeps a target temperature. The target must stay between 10 and 30 degrees, so a setting outside that range is refused. Write the class, and check its behavior with valid and invalid settings.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own range.

The program should:

* keep the target private, starting at 20
* provide `getTarget`, which returns the target
* provide `setTarget(int value)`, which returns `true` and changes the target when the value is from 10 to 30 inclusive, and returns `false` and changes nothing otherwise
* print the starting target, then set 24 and print the result and the new target, then set 40 and print the result and the target again

### Where to change the code

The range check is the condition in `setTarget`. The bounds 10 and 30 are the only values to change. The field is private, so the only way to change the target is through `setTarget`, which means the check can't be skipped.

### What to check

The output should be:

```text
Start: 20
Set 24: true
Now: 24
Set 40: false
Now: 24
```

The setting of 40 is refused, so the target stays at 24. The return value tells the caller which happened.

### Extensions

* Try a setting of exactly 10 and exactly 30, and check that both are accepted.
* Add a method `warmer(int degrees)` that raises the target by the given amount, using `setTarget` so the same range check applies.

---

*This exercise is not from the book. It practices the ideas in Chapter 11.*
