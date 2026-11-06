## Two totals: walkthrough

The primitive loop never creates an object, so it's the cheaper of the two. The boxed version creates one `Integer` for each number when it's added to the list, and unboxes each one when it's read back.

The check `primitiveTotal == boxedTotal` compares two `long` values, so `==` is correct here. It's the boxed values that need `equals`, which is the point of the boxing step.

The totals agree, which shows that boxing changes the cost of the work, not the result. The extension about one million numbers is the case where that cost starts to matter.
