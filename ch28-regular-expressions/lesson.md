## Ch27 · Regular expressions

Many checks on text are about shape: a phone number has a fixed pattern of digits, a word is letters only, and a date has a year, a month, and a day. Regular expressions describe those shapes in a short notation, and the `java.util.regex` package uses them to check, find, pull apart, and replace text.

The steps are:

* [Building a phone pattern](#goto/01-building-a-phone-pattern): building a pattern one step at a time, and the trap of an unescaped dot.
* [Patterns and matching](#goto/02-patterns-and-matching): describing shapes, checking a whole string, and finding each match in a longer text.
* [Groups and replacing](#goto/03-groups-and-replacing): taking the parts of a match apart, and replacing every match.
* [Summary](#goto/04-summary): a recap of the chapter and a short quiz. The homework is listed under the chapter.

---

*The examples and explanations are new.*
