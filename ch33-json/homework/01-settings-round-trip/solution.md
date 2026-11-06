## Homework: Settings round trip: walkthrough

The saved text is read into a `LinkedHashMap`. The program changes `volume` to a `Long`, and adds `language`. Serializing the map writes the entries in their order, with `volume` keeping its place and `language` added at the end. Reading the new text gives an equal map, so the last line is true.
