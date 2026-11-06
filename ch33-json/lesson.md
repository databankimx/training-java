## Ch33 · JSON

JSON is a text format for data, used to send records between programs and to store settings. This chapter writes JSON by hand, first for strings and escapes, then for objects, and then for nested arrays and objects. It finishes with a small library, `Json`, whose `serialize` and `deserialize` methods convert between Java values and JSON text.

The steps are:

* [Strings and escapes](#goto/01-strings-and-escapes): quoting text, and escaping the characters JSON needs.
* [Objects](#goto/02-objects): writing key and value pairs, and keeping their order.
* [Arrays and nested values](#goto/03-nested-values): a recursive writer for data of any depth.
* [A JSON library: serialize and deserialize](#goto/04-json-library): reading JSON back into Java values with a recursive descent parser.
* [Summary](#goto/05-summary): a recap of the chapter, the library's limits, and a short quiz. The homework is listed under the chapter.

---

*The examples and explanations are new. The JDK has no JSON library, so the chapter builds one from the standard library.*
