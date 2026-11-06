## Homework: Settings round trip

Read a saved settings text, change two values, and write the settings back out.

The `src` folder already contains `Json.java`, the library from the previous step, so the exercise runs as it stands. The runner compiles every file in `src` together.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* deserialize the text `{"volume": 7, "theme": "dark", "autosave": true}`, which gives a map
* set `volume` to the `Long` value 9, and add a `language` entry with the text `en-US`
* serialize the map and print it as `Updated: ` followed by the JSON text
* print `Reads back: true` if deserializing the updated text gives a map equal to the one changed in the program

### What to check

The output should be:

```text
Updated: {"volume": 9, "theme": "dark", "autosave": true, "language": "en-US"}
Reads back: true
```

The new `volume` value must be a `Long`, not an `int`, since `deserialize` produces `Long` values. A map holding an `Integer` 9 would not be equal to one holding a `Long` 9, which is why the example writes `9L`.

### Extensions

* Add a value that is a list, such as the languages a user has set up, and check that it's written as a JSON array.
* Change the text so that it has a missing comma, and predict the error message's position before you run it.

---

*This exercise is not from the book. It practices the ideas in Chapter 33.*
