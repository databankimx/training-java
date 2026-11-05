## Summary: Bitwise operators

This chapter covered the bits that store numbers, and the operators that work on them one position at a time.

* **Every value is a pattern of bits, read at a fixed width.** Positions are worth powers of two, and negative numbers use two's complement.
* **Overflow wraps around in Java.** A value past the top of its type comes out at the bottom, and the program keeps running.
* **A truth table lists a bit operator's result for each input.** The same rule applies at every bit position of a whole number.
* **AND keeps bits set in both numbers.** It tests a bit, and `n & 1` tells whether a number is odd.
* **OR keeps bits set in either number.** It turns bits on, and it builds a value that holds several flags.
* **NOT flips every bit, and a mask limits the result to a width.** Clearing a bit uses NOT of its mask.
* **XOR flips the bits where its inputs differ.** It toggles flags, and it swaps two values without a temporary.
* **XOR with a key is reversible.** Applying the same key twice gives back the original, which is the basis of a simple cipher.
* **Shifts multiply and divide by powers of two.** `>>` keeps the sign, and `>>>` fills the top with zeros.
* **Bytes combine into larger values with shifts and OR.** Each byte is masked before it's shifted, because Java bytes are signed.

### Check your understanding

*These questions are written for this course.*

````quiz
Which operator removes a bit from a flag set without changing the others?
○ `|`
→ Not quite. OR turns bits on, so it can't remove one.
◉ `&` with the NOT of the flag's mask
→ Yes. The NOT of the mask keeps every other bit and clears the flag's bit.
○ `^` with the flag's mask
→ Not quite. XOR toggles the bit, so a set flag would become clear, but an unset flag would become set.
````

````quiz
Why does XOR with the same key twice give back the original value?
○ Because the key is removed on the first step.
→ Not quite. The key isn't removed. It's applied again.
◉ Because each bit flipped by the key is flipped back by the second XOR.
→ Yes. XOR undoes itself when the same value is applied twice.
○ Because the second XOR uses a larger key.
→ Not quite. The key is the same both times.
````

````quiz
Why is `-1 >>> 1` a large positive number, while `-1 >> 1` stays at -1?
○ Because `>>>` is faster.
→ Not quite. The difference is what fills the top bits.
◉ Because `>>>` fills the top with zeros, while `>>` copies the sign bit.
→ Yes. The sign bit decides whether the result stays negative.
○ Because -1 can't be shifted.
→ Not quite. Shifts work on negative numbers, and the two operators fill the top bits differently.
````
