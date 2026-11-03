## Grade letter: walkthrough

The chain tests the bands from the highest down. A score of 95 passes the first test, so it's an `A`, and the rest of the chain is skipped. A score of 85 fails the first test, passes the second, and gives a `B`. Each test only needs the lower bound of its band, because the tests above it have already been ruled out.

The variable `grade` is declared before the chain, and each branch assigns it. Because every branch assigns it, Java knows `grade` has a value when it's printed on the last line. If one branch were missing, and the `else` were left out, `grade` could be unassigned, and the build would fail.

The boundary values are the usual trap. A score of 90 must pass the first test, which is `>= 90`, and not `> 90`. Using `>` would move 90 into the B band.
