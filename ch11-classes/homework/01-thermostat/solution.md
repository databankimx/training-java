## Thermostat: walkthrough

`setTarget` checks the value before it changes anything. The condition `value < 10 || value > 30` refuses any value outside the range, and the `return false` leaves the target as it was. The change `target = value` happens only on the accepted path.

The range includes both ends, because the test uses `<` and `>`, not `<=` and `>=`. A setting of 10 or 30 passes.

Because the target is private, no other code can bypass the check, which is the encapsulation idea from the chapter.
