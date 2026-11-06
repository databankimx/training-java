## Ch18 · Copies, references, and ownership

Passing an object from one part of a program to another raises a question: does the other part get its own copy, a shared reference, or sole possession of the object? This chapter covers the three answers. Java passes references, so a copy is always a choice the program makes, and a hand-off is a rule the program keeps.

The steps are:

* [Copies and references](#goto/01-copies-and-references): making a second object with a copy, and sharing one object with a reference.
* [Handing off ownership](#goto/02-handing-off-ownership): giving an object to one new holder, and emptying the first.
* [Summary](#goto/03-summary): a recap of the chapter and a short quiz. The homework is listed under the chapter.

---

*The examples and explanations are new.*
