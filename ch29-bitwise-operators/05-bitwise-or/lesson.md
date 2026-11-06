## Bitwise OR

The `|` operator returns the union of the set bits in two numbers. A bit in the result is 1 whenever it's 1 in either input, and it's also 1 when it's 1 in both. That's the inclusive OR from the truth table in the previous step.

### A worked example

Take the same two numbers as in the AND step:

```text
a = 1001 1100   (156)
b = 0011 0100   (52)
a | b = 1011 1100   (188)
```

Any position where either number has a 1 gives a 1 in the result. The bit worth 32, which only `b` has, is kept. So is the bit worth 128, which only `a` has. The bits worth 16, 8, and 4, which both numbers have, are kept too.

```java
int a = 0b10011100;   // 156
int b = 0b00110100;   // 52
System.out.println("a | b: " + bits8(a | b) + " = " + (a | b));
```

The output is:

```text
a | b: 10111100 = 188
```

### Adding a permission

OR is the operation that turns bits on. Permissions are a good example. Suppose a file can be opened for reading, for writing, or for both, and each permission is a bit:

```java
int read = 1;
int write = 2;
int readWrite = 3;
```

`readWrite` is 3, which is `0011`. It has the bits of both `read` and `write`, so it isn't a single flag. It's the union of two flags.

A program starts with the permission it always needs, then adds the other one only when it's needed:

```java
int permissions = read;
if (needToWrite) {
    permissions |= write;
}
```

Each `|=` keeps the bits already set and adds the new one. Here `permissions` starts as `0001`, and with the write permission it becomes `0011`, which is `readWrite`. Press **Run** with `needToWrite` set to true to see the output:

```text
0011 = 3
Success!
```

If `needToWrite` is false, `permissions` stays at 1, and the test for `readWrite` fails silently. The `if` is what decides which permissions the value holds.

---

### Quiz

*These questions are written for this course.*

````quiz
What is `4 | 1`?
○ 0
→ Not quite. OR keeps a bit when either input has it, so the result isn't zero.
◉ 5
→ Yes. The bit worth 4 comes from one number and the bit worth 1 from the other, so the result is 101.
○ 4
→ Not quite. The bit worth 1 is in the second number, so it's kept as well.
````

````quiz
Why does `permissions |= write` keep the read permission?
○ Because OR removes bits that are not in the second value.
→ Not quite. OR keeps every bit that is set in either value.
◉ Because OR keeps every bit that is already 1 in the first value, and adds the bit from the second.
→ Yes. Adding a permission never clears one that was already set.
○ Because the read permission is stored in a separate variable.
→ Not quite. The read permission is stored in the same number, as one of its bits.
````

---

### Try it in the runner

Change `permissions` to start from `write` instead of `read`, and then add `read`. Predict the output before you press **Run**. It's `0011 = 3`, followed by `Success!`, since OR doesn't depend on the order of the operands.

---

OR turns bits on and keeps the bits that were already on. The next step builds on that to give a single flag a set of bits, using OR to combine the flags of a package.
