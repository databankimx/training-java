## Homework

These exercises practise releasing resources and tracking references: a guard that runs its cleanup when an exception leaves a method, and a list of holders that shows how references add and remove. Each one is a step in this folder.

The steps are:

* [Guarded section](#goto/01-guarded-section): an `AutoCloseable` guard that closes when a method is left early by an exception.
* [Shared holders](#goto/02-shared-holders): a list of references to one object, and what happens to the count as they are added and cleared.

Write your own program in your project first. If you get stuck, reveal the example. The source shows the finished program, and the section below says which lines to change for your own version.
