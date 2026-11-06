## Shopping cart: walkthrough

The total is kept in cents as an `int`, so adding prices never loses a fraction of a cent. Money stored as a `double` can drift from the exact value, which is why whole cents are the usual choice.

`total()` builds the display text. Dividing by 100 gives the dollars, and the remainder after dividing by 100 gives the cents. `String.format("%02d", ...)` pads a single-digit cent value, so 5 cents shows as `05`, not `5`.

The rejected price never reaches the total or the count, because the check returns before either changes. Both fields stay private, so the cart's rules hold for every caller.
