## Name tracker: walkthrough

The `null` test comes first in each chain. `name == null` is a reference comparison, and it's safe for any value, including `null`. Only when the name is not `null` does the chain reach `name.equals("Ada")`, so `equals` is never called on `null`.

The second chain runs after `name = "Ada";`, so the first branch is skipped and the `equals` test is used. The output is the welcome-back message, because the text matches.

The `equals` test is also the reason the program prints `Welcome back, Ada.` and not a `Hello` message for the same name. `equals` compares the text, so any `String` with the characters `Ada` matches, whatever object it's stored in.
