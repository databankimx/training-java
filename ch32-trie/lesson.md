## Ch32 · The trie

A trie is a tree that stores strings by their letters. Words that begin the same way share the nodes for that beginning, and a search follows one node for each letter. This chapter builds a trie in Java, with its node, its insert and search operations, and delete, which removes only the nodes a word alone uses.

The steps are:

* [The trie node](#goto/01-trie-node): one node with a child for each letter, and a word-end flag.
* [Inserting and searching](#goto/02-insert-and-search): the trie class, with insert, contains, and startsWith.
* [Deleting a word](#goto/03-delete): removing a word without removing the prefixes and words that share its path.
* [Summary](#goto/04-summary): a recap of the chapter and a short quiz. The homework is listed under the chapter.

---

*The examples and explanations are new. The chapter follows the trie lessons of the Python course's Beyond the Book track.*
