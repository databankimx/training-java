## Ch17 · Resources and references

A program holds resources such as files and connections, and it creates many objects. This chapter covers how Java releases each kind. A resource that must be closed is released at a known point with `try`-with-resources. An ordinary object is freed by the garbage collector after its last reference is gone, at a time the program doesn't control. Shared objects and weak references round out the picture.

The steps are:

* [Closing resources](#goto/01-closing-resources): `AutoCloseable` and `try`-with-resources, which close a resource on every path out of a block.
* [Garbage collection](#goto/02-garbage-collection): how objects are freed once no references remain, and what the program can and can't rely on.
* [Shared references](#goto/03-shared-references): several references to one object, and a `WeakReference` that doesn't keep the object alive.
* [Summary](#goto/04-summary): a recap of the chapter and a short quiz. The homework is listed under the chapter.

---

*The examples and explanations are new.*
