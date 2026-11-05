## Bit flags, combined with OR

Sometimes one setting needs to stand for several bits. A licence bundle, for example, covers several products, and a check for any one of them should still work. OR makes this simple. The bits of several flags are combined into one value, and that value has every one of their bits set. A value like that is called a bit-mask.

### The scenario

Back at MacroWare, your boss is pleased with how you chose to implement license checks for the component software.

You're now asked to handle checks for packages that contain several of the separate programs as a single license type.

Specifically:

* Personal:
    * WordProcessing
    * EmailClient
* Work:
    * WordProcessing
    * EmailClient
    * Spreadsheets
    * Presentations
    * Notebook
    * Collaboration
* Ultra Deluxe:
    * All Programs

### An obvious approach

Intuition might suggest that the easy approach is to switch to using a larger integer type and add values for each package.

Then, when checking whether a specific program should run, you could check for multiple conditions.

### Obvious Is Not Necessarily Good

This implementation has a number of inefficiencies.

* The values for the packages are not coupled to the products they include.
* The logic is unnecessarily overcomplicated.
* Future changes in the included products in a given package require tracking down the locations where a given product is checked.
    * So updates have to be made in multiple locations in your code.

### An Alternative Approach: Combined Bit-Flags

Using a bitwise OR permits combining multiple bit values, since it returns the union of the bit values rather than the intersection.

For example, you know that the Personal license includes Word Processing and an Email Client. In your existing bit-flag enumeration, you have values for those.

### Combining bits

The alternative is to build each bundle's value from the product bits, using OR. Here's the Java version of the products, from the previous step:

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
    // ...
}
```

The bundles are built from those bits. The Personal bundle is word processing OR the email client, which is 1 | 8 = 9:

```java
PERSONAL(Product.WORD_PROCESSING.bit | Product.EMAIL_CLIENT.bit),
```

The Work bundle builds on the Personal bundle, and adds the products that Work includes. Ultra Deluxe builds on Work. Each bundle reuses the value it already has, so a change to one bundle is made in one place:

```java
enum Bundle {
    PERSONAL(Product.WORD_PROCESSING.bit | Product.EMAIL_CLIENT.bit),
    WORK(PERSONAL.bits | Product.SPREADSHEETS.bit | Product.PRESENTATIONS.bit
            | Product.NOTEBOOK.bit | Product.COLLABORATION.bit),
    ULTRA_DELUXE(WORK.bits | Product.PROJECT_MANAGEMENT.bit | Product.PUBLISHING.bit);

    final int bits;

    Bundle(int bits) {
        this.bits = bits;
    }
}
```

The enum is named `Bundle` in the program, to avoid confusion with the `Package` class from the standard library. The Python lesson calls these groups packages.

### Checking a product in a bundle

The check from the bit flags step doesn't change at all. It asks whether a product's bit is set, and a bundle has the bits of all its products set. So a Work licence passes the word processing check, because the word processing bit is part of the Work value:

```java
int licences = Bundle.WORK.bits;
for (Product product : Product.values()) {
    if (isLicensed(licences, product)) {
        System.out.println("  " + product.name());
    }
}
```

Press **Run**. The Work bundle is 63, which is 0011 1111, and the output lists its six products:

```text
Products in the Work bundle:
  WORD_PROCESSING
  SPREADSHEETS
  PRESENTATIONS
  EMAIL_CLIENT
  NOTEBOOK
  COLLABORATION
```

The check is the same whether the licence is a single product or a whole bundle. Only the enum has to change when a bundle changes.

---

### Quiz

*These questions are written for this course.*

````quiz
What is the value of the Personal bundle, which is word processing (1) OR email client (8)?
○ 1
→ Not quite. The email client's bit is part of the bundle too.
◉ 9
→ Yes. The two bits are 0000 1001, which is 9.
○ 8
→ Not quite. The word processing bit is also set in the bundle.
````

````quiz
Why doesn't the licence check change when a bundle gains a product?
○ Because bundles are not checked at all.
→ Not quite. Bundles are checked, through their bits.
◉ Because the check only asks whether a product's bit is set, and the bundle's value already has that bit.
→ Yes. Only the bundle's value changes, and the check reads it as before.
○ Because the enum is recompiled automatically.
→ Not quite. The enum is changed by hand, and the check works unchanged once it is.
````

---

### Try it in the runner

Change the line that sets `licences` to `Bundle.PERSONAL.bits`, and predict the list before you press **Run**. It shows WORD_PROCESSING and EMAIL_CLIENT only. Then change it to `Bundle.ULTRA_DELUXE.bits`, and predict the number of products listed. All eight are listed, since Ultra Deluxe has every bit set.

---

A combined value keeps the bits of everything it includes, so one check works for single products and bundles alike. The next step inverts bits with NOT, which turns a flag off without disturbing the others.
