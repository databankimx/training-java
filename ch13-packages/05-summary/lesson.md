## Summary: Interfaces, classes, and multiple files

This chapter covered how a program is split across files, so that each class is kept in a file of its own, and its public face is separated from its details.

* **An interface declares, and a class defines.** The interface holds method declarations, and the class file holds the code that provides them.
* **`implements` ties a class to an interface.** The compiler checks that the class provides every method the interface declares.
* **Each public type has a file with its name.** `Car` is in `Car.java`, and the compiler finds it from its name.
* **The runner compiles every file in `src`.** Classes in the same package are found without an import, and classes in subfolders are compiled too.
* **`import` names a class.** It tells the compiler what a name refers to, and it's compiled once.
* **Each class is compiled once.** No text is copied between files, so a class is never defined twice.
* **Inheritance uses the base's constructor.** `super(...)` passes arguments to the base before the derived class's own statements run.
* **Packages organize classes.** A file in a package names it with `package`, and other packages use it through `import`.

### Check your understanding

*These questions are written for this course.*

````quiz
Which file should hold the code for a method that an interface declares?
○ The interface file.
→ Not quite. An interface holds declarations, and the code belongs in the class.
◉ The file for the class that implements the interface.
→ Yes. The implementing class defines what the interface declares.
○ The Main file, always.
→ Not quite. Each class's methods usually live in that class's own file.
````

````quiz
What happens if a public class is saved in a file with a different name?
○ The program runs, and the file name doesn't matter.
→ Not quite. The compiler requires a public class to match its file name.
◉ The build fails, because the compiler can't find the class by its name.
→ Yes. A public class must be declared in a file with its name.
○ The class is renamed to match the file.
→ Not quite. The compiler reports an error rather than renaming anything.
````

````quiz
Which statement about an `import` is correct?
○ It copies the imported class into the file.
→ Not quite. An import only tells the compiler what a name refers to.
◉ It names a class from another package, and the class is compiled once.
→ Yes. Classes are compiled once, and imports refer to them by name.
○ It runs the imported class.
→ Not quite. Imports have no effect at run time.
````
