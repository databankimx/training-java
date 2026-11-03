## Save and load scores: walkthrough

The program reads each line as text, then converts it with `Integer.parseInt`, the same method the command-line arguments lesson used. The conversion is needed because `readAllLines` gives back `String` values, even for numbers.

The loop is an enhanced `for` over the list, so each line is visited in turn, and the total grows by each converted score. The count comes from `lines.size()`, which is the number of lines, and the same number as the scores.

The file is written first, every run, so the program can read it. In a real program the file would already exist, and the write would be a separate step.
