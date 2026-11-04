## Homework: Count and sum: walkthrough

The lambda runs once for each number. Odd numbers are skipped by the `if`. For each even number the lambda adds 1 to the count, and adds the value to the sum. Both totals are changed through their one-element arrays, which the lambda can capture because the arrays are never reassigned.

The even numbers are 2, 4, 6, 8, and 10: five values with a sum of 30.
