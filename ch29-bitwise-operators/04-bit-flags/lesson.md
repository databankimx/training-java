## Bit flags

A flag is a yes-or-no setting. A program may have many of them, and the question is how to store them. The obvious approach is one `boolean` for each, but a boolean takes at least one byte, and the storage grows with every setting.

### A MacroWare example

You are employed by a large company called MacroWare. One of the company's flagship software products is an office product suite that contains several different programs.

For a number of reasons, including simplifying delivery by packaging only one installer and providing an easy path to post-purchase upsells, the installation package installs all of the programs, regardless which have been purchased by the user.

You are tasked with creating a mechanism that allows the software to check the licensed state of each of the programs before allowing each program to run.

An obvious approach is to use boolean values to enable or disable each feature:

```java
class ProductLicenses {
    boolean wordProcessing;
    boolean spreadsheets;
    boolean presentations;
    boolean emailClient;
    boolean notebook;
    boolean collaboration;
    boolean projectManagement;
    boolean publishing;
}
```

This approach works, but it's not very mindful of memory usage, since each of those boolean values consumes one byte in memory.

This might not matter given the large amount of memory in current computers, but if you're storing active license information in a database, it's using eight columns (at a minimum of one byte apiece) for this information.

Supposing MacroWare sells a billion copies, that's consuming about 8 GB of disk space.

Suppose, instead of using one byte per product, instead, you used one bit. That would reduce the size of the licensing data eightfold, and the database licensing data would be only 1 GB. The idea sounds great, but how would we do it?

One approach is to define each product as a binary bit position value. Each product owns one bit of a single integer. The enum holds the bit for each product:

```java
enum Product {
    WORD_PROCESSING(1),
    SPREADSHEETS(2),
    PRESENTATIONS(4),
    EMAIL_CLIENT(8),
    NOTEBOOK(16),
    COLLABORATION(32),
    PROJECT_MANAGEMENT(64),
    PUBLISHING(128);

    final int bit;

    Product(int bit) {
        this.bit = bit;
    }
}
```

Each bit is a power of two, so a single bit is set for each product and no two products share one. The values run from 1 to 128, which uses the eight bits of a `byte`.

### Checking a flag

A flag is on when its bit is 1 in the license value. AND keeps only that bit, so the result is zero when the flag is off, and the bit's own value when it's on:

```java
static boolean isLicensed(int licenses, Product product) {
    return (licenses & product.bit) != 0;
}
```

The comparison with zero turns the result into a yes or no. The result of the AND is the bit itself, not a true or false value, so it can't be used as a condition until it's compared.

### A worked example

Take a license value of 11. In binary, 11 is `0000 1011`, so the bits worth 1, 2, and 8 are set:

```text
licenses  = 0000 1011   (11)
WORD_PROCESSING = 0000 0001
licenses & WORD_PROCESSING = 0000 0001   (1, so licensed)
PRESENTATIONS   = 0000 0100
licenses & PRESENTATIONS   = 0000 0000   (0, so not licensed)
```

The program loops over every product and tests each one:

```java
int licenses = 11;
for (Product product : Product.values()) {
    if (isLicensed(licenses, product)) {
        System.out.println("  " + product.name());
    }
}
```

The output is:

```text
Licensed products:
  WORD_PROCESSING
  SPREADSHEETS
  EMAIL_CLIENT
```

Change the value of `licenses` and run again. Each value gives a different set of products, and each set is read from its bits.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does the flag test compare the AND result with zero?
○ Because the AND always returns a true or false value.
→ Not quite. AND returns a number, which must be compared before it can be a condition.
◉ Because the AND result is the flag's bit, which is non-zero only when the flag is on.
→ Yes. Zero means the flag is off, and any other value means it's on.
○ Because the enum values can't be used with AND.
→ Not quite. The enum values are numbers, and AND works on them.
````

````quiz
What is the storage saving for the license table compared with one boolean per product?
○ None, since a boolean already takes one bit.
→ Not quite. A boolean usually takes a whole byte, which is why the bit form is smaller.
◉ About eight times less, since eight products fit in the bits of one byte.
→ Yes. One byte holds eight flags, where eight booleans would take eight bytes.
○ Sixteen times less, since each product takes half a byte.
→ Not quite. One byte holds eight flags, so the saving is eightfold.
````

---

### Try it in the runner

Change `licenses` to 128, and predict the output before you press **Run**. Only PUBLISHING is listed, since 128 has only its own bit set. Then change it to 255, and predict the list. All eight products are listed, since every bit is set.

---

A set of yes-or-no settings fits in one number when each setting owns a bit, and AND reads any one of them. The next step gives a set of flags the reverse operation: turning them on with OR.
