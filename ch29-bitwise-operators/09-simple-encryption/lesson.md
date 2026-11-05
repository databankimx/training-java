## Simple encryption with XOR

XOR is reversible. If `z = x ^ y`, then `z ^ y` gives back `x`, because XORing with `y` twice cancels out. So a value can be scrambled with a key and restored with the same key. This property is the basis of a simple symmetric cipher, one where the same key encrypts and decrypts.

### Why it's reversible

Take `x` as 42 and `y` as 85, which are `0010 1010` and `0101 0101`:

```text
x     = 0010 1010   (42)
y     = 0101 0101   (85)
x ^ y = 0111 1111   (127)

x ^ y = 0111 1111   (127)
    ^ y = 0101 0101   (85)
        = 0010 1010   (42)
```

The second XOR cancels the first, because every bit of `y` that was flipped is flipped back. Only the key is needed for both steps.

### An encryption and decryption algorithm

The algorithm XORs each character of the text with the key. The character's code is a number, so XOR works on it directly:

```java
static String encryptDecrypt(String text, int key) {
    StringBuilder result = new StringBuilder();
    for (char c : text.toCharArray()) {
        result.append((char) (c ^ key));
    }
    return result.toString();
}
```

The `(char)` cast turns the number back into a character. Without it, the result would be a number. The same method both encrypts and decrypts, because the two operations are identical.

With the key 30 and the text `Hello World!`, the program prints:

```text
Plain Text: Hello World!
Encrypted Text: V{rrq>Iqlrz?
Decrypted Text: Hello World!
```

### Checking one character

The character `H` has the code 72, which is `0100 1000`. The key 30 is `0001 1110`:

```text
H   = 0100 1000   (72)
key = 0001 1110   (30)
H ^ key = 0101 0110   (86, which is V)
```

The first character of the ciphertext is `V`. Reversing the step, `V ^ 30` gives 72 again, which is `H`. The other characters work the same way.

### Why this isn't secure

This cipher is for learning, not for protecting real data. It uses one number as the key, and a single byte has only 256 possible keys, so an attacker can try them all. Both parties also need the same key in advance. Modern algorithms use keys that are much larger, and a different system for sharing them.

XOR is still used as one step inside some real algorithms, such as DES and AES. A one-time pad, which uses a different random key for each character, is the strongest form of XOR cipher, and it's unbreakable when the key is truly random and never reused.

---

### Quiz

*These questions are written for this course.*

````quiz
If `c` is encrypted to `e` with a key `k`, what gives `c` back?
○ `e & k`
→ Not quite. AND isn't the reverse of XOR.
◉ `e ^ k`
→ Yes. XOR with the same key twice cancels out.
○ `e | k`
→ Not quite. OR can't undo a XOR.
````

````quiz
Why is the `(char)` cast needed in `encryptDecrypt`?
○ Because XOR only works on text.
→ Not quite. XOR works on the character's code number.
◉ Because the XOR gives a number, and the cast turns it back into a character.
→ Yes. Without the cast, the output would be a list of numbers.
○ Because the key must be a character.
→ Not quite. The key is a number, and it doesn't need to be a character.
````

---

### Try it in the runner

Change the key from 30 to 0, and predict the encrypted text before you press **Run**. XOR with zero changes nothing, so the encrypted text is the same as the plain text. Then change the key to 30 again, and change the text to `"abc"`. Predict the encrypted text: `a` is 97, and 97 ^ 30 is 127, which is the DEL character, so the output contains characters that are not visible on screen.

---

XOR with a key scrambles a value, and XOR with the same key restores it. The next step is bit shifts, which move bits along a number and do the arithmetic of doubling and halving.
