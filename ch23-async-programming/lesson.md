## Ch23 · Asynchronous work

Some work takes time, and a program shouldn't sit still while it waits. The chapter uses `CompletableFuture`, which starts work in the background, and the program waits for its value only when it needs it. This chapter covers starting work, combining results, and handling failures.

The steps are:

* [Futures](#goto/01-futures): starting background work with `supplyAsync`, and waiting for it with `join`.
* [Combining futures](#goto/02-combining-futures): chaining with `thenApply`, combining two results, handling a failure, and waiting for a group.
* [Summary](#goto/03-summary): a recap of the chapter and a short quiz. The homework is listed under the chapter.

---

*The examples and explanations are new.*
