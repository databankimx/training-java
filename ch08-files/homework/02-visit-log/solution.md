## Visit log: walkthrough

Each `writeString` call uses `CREATE` and `APPEND`, so the first call creates the log, and every later call adds to the end. Nothing is replaced, so the log keeps every line from every run.

The printed output is the whole file, read with `readString`. That's why the first run shows two lines, and a later run shows all the lines written so far.

The `CREATE` option is needed in the first run only, when the file doesn't exist yet. It's harmless on later runs, because the file already exists and nothing is created.
