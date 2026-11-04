## Homework: Weekday name: walkthrough

The names are in an array of seven strings, so day 1 is at index 0. The program subtracts 1 from the day number before reading the array.

The range check comes first. `number >= 1 && number <= days.length` is true only for the valid days, and `&&` stops early if the first part fails. The read of `days[number - 1]` happens only inside the true branch, so an invalid number never reaches the array.
