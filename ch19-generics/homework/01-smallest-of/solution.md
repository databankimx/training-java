## Homework: Smallest of: walkthrough

`smallestOf` takes the first value as the smallest so far, then compares each later value with it and keeps the smaller one. The `compareTo` call works for any type with the `Comparable` bound. For numbers, the list holds `Integer` values, and for words it holds `String` values, and the same method handles both.

For the sample lists, the smallest number is 2 and the smallest word is "apple", since "apple" comes first alphabetically.
