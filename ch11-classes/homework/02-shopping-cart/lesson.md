## Homework: Shopping cart

A shopping cart keeps a running total of its item prices, stored in cents so the arithmetic stays exact. The cart refuses a price that isn't positive, and it reports the number of items and the total.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own items.

The program should:

* keep the total and the item count private, starting at zero
* provide `add(int priceCents)`, which adds a positive price to the total and counts the item, and prints `Price rejected: ` followed by the value when the price is zero or below
* provide `getItemCount()`, which returns the number of items added
* provide `total()`, which returns the total as text in dollars, such as `$17.49`
* add three prices, 1299, 450, and 0, and print the item count and the total

### Where to change the code

The cart stores money as a whole number of cents, so the total is always exact. The `total` method turns cents into dollars and cents for display: `totalCents / 100` gives the dollars, and `totalCents % 100` gives the cents, padded to two digits with `String.format("%02d", ...)`.

### What to check

The output should be:

```text
Price rejected: 0
Items: 2
Total: $17.49
```

The price of 0 is rejected, so it isn't counted. The total of 1299 plus 450 is 1749 cents, which displays as `$17.49`.

### Extensions

* Add a price of 5 cents, and check that the total displays as `$17.54`, with the cents padded correctly.
* Add a `remove(int priceCents)` method that takes an item out of the total, and decide what it should do when the price isn't in the cart.

---

*This exercise is not from the book. It practices the ideas in Chapter 11.*
