## Polymorphism

Polymorphism means "many forms". It's the ability to call one method through a variable of a base type, and have the right version run for the object actually passed in. The caller writes one line, and the object decides what that line does.

The program for this step, in `src/Main.java`, shows the three parts it needs. The base class has a method, and each derived class replaces it:

```java
class Animal {
    void sound() {
        System.out.println("The animal makes a sound...");
    }
}

class Pig extends Animal {
    @Override
    void sound() {
        System.out.println("The pig says: oink oink!");
    }
}
```

The `@Override` annotation says the method replaces one in the base class. It's optional, but it's a check that the compiler performs, as the Try section shows.

A method takes a base-type parameter:

```java
static void makeSound(Animal animal) {
    animal.sound();
}
```

Press **Run**. The output is:

```text
The animal makes a sound...
The pig says: oink oink!
The dog says: bow wow!
```

The three calls all have the same form, `makeSound(something)`, and the method inside calls `sound()` on whatever it was given. The object decides which version runs.

### Instance methods are virtual

In Java, every instance method can be replaced by a subclass, unless it's `final` or `private`. Nothing else is needed to make the call depend on the object: the lookup uses the object's real class, at run time. A variable's declared type, `Animal` here, decides only which methods the compiler allows you to call.

A `static` method is different. It belongs to the class, not to an object, and a subclass that declares the same static method hides it rather than replacing it. Calls to a static method are chosen from the type named in the call, so polymorphism doesn't apply to them.

### A parameter refers to the object

A parameter of a class type holds a reference to the object that was passed in, not a copy of it. The `Pig` object keeps its own `sound`, whatever the parameter's declared type is, so `makeSound` always runs the pig's version when it's given a pig.

---

### Quiz

*These questions are written for this course.*

````quiz
Which `sound` runs when `makeSound` receives a `Pig` object?
○ The one in Animal, because the parameter is declared as Animal.
→ Not quite. The method is chosen from the object's real class, not from the parameter's declared type.
◉ The one in Pig, which prints the pig's message.
→ Yes. The object's own version replaces the base version.
○ Neither, because the call doesn't compile.
→ Not quite. Passing a Pig where an Animal is expected is valid.
````

````quiz
What does the `@Override` annotation do?
○ It removes the base class's version of the method.
→ Not quite. The base version still exists, and is used by objects that don't replace it.
◉ It asks the compiler to check that the method really replaces one in a base class.
→ Yes. A misspelled or mismatched replacement becomes a compile error.
○ It allows the method to be called without an object.
→ Not quite. The annotation says nothing about how the method is called.
````

---

### Try it in the runner

Add `final` in front of `void sound()` in `Animal`, and press **Run**. The build fails, because `Pig` tries to replace a method that can't be replaced. Remove `final` before you continue.

Then change the name of `Pig`'s method to `sond`, keeping `@Override`, and press **Run**. The build fails, because the method doesn't replace anything in `Animal`. Without `@Override`, the same misspelled method would compile, and `makeSound(pig)` would still print the animal's message. Restore the name `sound` before you continue.

---

Polymorphism lets one call do different work for different objects, because each object's own version of a method runs. The summary covers the chapter's inheritance rules, and the homework applies them to a small program.
