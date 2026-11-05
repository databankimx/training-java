## Homework: Sum prices

A receipt has prices written in the text, such as `$3.50`. Use a pattern with groups to find every price, add them up, and print the total.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* hold the text `Apples $3.50, pears $2.25, and a melon for $4.00.`
* compile a pattern for a price: a dollar sign, one or more digits for the dollars, a dot, and two digits for the cents, with the dollars and the cents each in a group
* use `find` in a loop, and add each price to a total kept in cents, so no decimal arithmetic is needed while adding
* print `Total: ` followed by the total with two decimal places

### What to check

The output should be:

```text
Total: 9.75
```

The total is 350 + 225 + 400 cents, which is 975 cents, or $9.75. Keeping the total in cents avoids the rounding that decimal numbers can introduce.

### Extensions

* Add a price with no cents, such as `$5`, and decide whether the pattern should accept it. Change the pattern if you decide it should, and make the cents optional.
* Print each price as it's found, next to the running total.

---

*This exercise is not from the book. It practices the ideas in Chapter 28, with the integers from Chapter 2.*
