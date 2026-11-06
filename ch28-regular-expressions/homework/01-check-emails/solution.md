## Homework: Check emails: walkthrough

The local part `[\w.-]+` takes letters, digits, underscores, dots, and dashes. The domain part `[\w-]+` must come after an at sign, and it can't contain another at sign, which is why `two@@signs.com` fails. The suffix `[a-z]{2,}` needs at least two lowercase letters after the last dot.

The dot before the suffix is escaped as `\.`, so it matches only a literal dot. That's what rejects `ada@examplexcom`. Without the backslash, the pattern would accept any character there.

`matches` requires the whole text to fit, so a stray character before or after the address also makes it fail.
