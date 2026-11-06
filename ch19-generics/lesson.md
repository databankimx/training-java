## Ch19 · Generics

The same algorithm often works for many types: finding the larger of two values, storing a pair, holding a list. Generics let you write the code once, with the type left as a placeholder, and the same code is checked against each type the program uses. This chapter covers generic methods, generic classes, and the rules that make them work.

The steps are:

* [Generic methods](#goto/01-generic-methods): a generic method, inference of the type, and naming it explicitly.
* [Generic classes](#goto/02-generic-classes): a class written once for any type, and the type arguments it takes.
* [Summary](#goto/03-summary): a recap of the chapter and a short quiz. The homework is listed under the chapter.

---

*The examples and explanations are new. The collections from Chapter 14 are generic classes, and this chapter explains how they're written.*
