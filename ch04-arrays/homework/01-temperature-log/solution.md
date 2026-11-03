## Temperature log: walkthrough

The average is built by adding the five readings by index and dividing by `readings.length`. The division is a `double` division, because the readings are `double` values, so 96.0 divided by 5 is 19.2 with the decimals kept.

The last reading uses `readings.length - 1`. The length is 5, so the index is 4, the last element. Using the length keeps the line correct if you change the number of readings, as long as the addition line is updated too.

The `printf` line prints the average with two places after the point, so the result reads `19.20`.
