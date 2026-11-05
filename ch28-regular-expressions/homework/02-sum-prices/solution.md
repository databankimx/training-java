## Homework: Sum prices: walkthrough

The pattern `\$(\d+)\.(\d{2})` finds a dollar sign, then the dollars in the first group and the cents in the second. Each price is converted to cents with `dollars * 100 + cents`, and the total is kept as an integer.

The three prices come to 975 cents. Dividing by 100.0 gives 9.75, which `printf` shows as `9.75`.
