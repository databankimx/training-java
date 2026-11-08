## Ch29 · Bitwise operators

Every whole number is stored as a pattern of bits, and usually a program never looks at them. Some problems are easiest to solve by working on the bits themselves: packing yes-or-no settings into one number, reading bytes from a data stream, encrypting a value with a key, and dividing and multiplying by powers of two. This chapter starts with how numbers are stored, then covers each bitwise operator with worked examples.

The steps are:

* [Number representation](#goto/01-number-representation): place values, fixed-width bits, negative numbers, and overflow.
* [Truth tables](#goto/02-truth-tables): the complement, AND, OR, and XOR, one bit at a time.
* [Bitwise AND](#goto/03-bitwise-and): keeping shared bits, and checking whether a number is odd.
* [Bit flags](#goto/04-bit-flags): one bit per product in a license value, and checking each one with AND.
* [Bitwise OR](#goto/05-bitwise-or): turning bits on, and adding a permission.
* [Bit flags, combined with OR](#goto/06-flags-redux): bundles of products built from their bits.
* [Bitwise NOT](#goto/07-bitwise-not): flipping bits, limiting a complement to a width, and setting or clearing one bit.
* [Bitwise XOR](#goto/08-bitwise-xor): an example, swapping two values, and toggling a flag.
* [Simple encryption](#goto/09-simple-encryption): XOR with a key, and why it's reversible.
* [Bit shifts](#goto/10-bit-shifts): multiplying and dividing by powers of two, shifts past the end, and reading each bit of a value.
* [Reconstructing integers](#goto/11-reconstructing-integers): building a 32-bit value from four bytes.
* [Summary](#goto/12-summary): a recap of the chapter and a short quiz. The homework is listed under the chapter.

---

*The examples and explanations are new, and the operations follow the bitwise lessons of the Python course's Beyond the Book track.*
