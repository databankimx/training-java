## Ch22 · Concurrency

Modern computers have several processors, and threads are how a Java program puts that work to use. Running work at the same time brings its own hazards: two threads changing the same data can lose updates, and two threads waiting on each other can stop altogether. This chapter covers starting and joining threads, protecting shared data with a lock, and the habits that keep threaded code correct.

The steps are:

* [Threads](#goto/01-threads): starting a thread with `Thread`, and waiting for it with `join`.
* [Locks](#goto/02-locks): protecting shared data with a `synchronized` block.
* [Summary](#goto/03-summary): a recap of the chapter and a short quiz. The homework is listed under the chapter.

---

*The examples and explanations are new.*
