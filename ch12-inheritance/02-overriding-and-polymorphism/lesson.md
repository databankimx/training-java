## Overriding and polymorphism

A subclass can replace an inherited method with its own version. That's overriding, and the `@Override` annotation tells the compiler that the method is meant to replace one from the superclass. The more useful part is what happens when a program calls that method through a variable of the parent type.

Here's the program for this step, in `src/Main.java`. The `Animal` class is the same as in the last step, and `Dog` and `Cat` each replace `sound`:

```java
class Dog extends Animal {
    Dog(String name) {
        super(name);
    }

    @Override
    String sound() {
        return "Woof";
    }
}
```

The `Main` method puts all three kinds of animal into an array of `Animal`:

```java
Animal[] pets = {new Dog("Rex"), new Cat("Tom"), new Animal("Generic")};
for (Animal pet : pets) {
    System.out.println(pet.getName() + " says " + pet.sound());
}
```

Press **Run**. The output is:

```text
Rex says Woof
Tom says Meow
Generic says ...
```

Each element of the array is typed as `Animal`, but `sound()` runs the version for the object's real class. The `Dog` object gives `Woof`, the `Cat` gives `Meow`, and the plain `Animal` gives `...`. That choice is made when the program runs, from the object, not from the variable's type.

### Polymorphism

This behavior is called polymorphism, which means "many forms". One call, `pet.sound()`, does a different thing for each kind of object, and the loop doesn't need to know which kind it has. A new animal can be added to the array, and the loop still works, as long as the new class overrides `sound`.

The loop depends only on the parent type, so it stays the same as the program grows. That's the main reason to use inheritance: the code that uses the objects doesn't need to change when a new kind is added.

### The rules for overriding

An overriding method must have the same name and parameters as the one it replaces, and it can't be less accessible. The return type must be compatible. The `@Override` annotation isn't required, but it makes the compiler check that the method really does replace something. A misspelled name with `@Override` fails to compile, which is the protection you want.

Fields aren't overridden. A subclass that declares a field with the same name as one in its parent creates a second field, which is almost never what you want. Keep fields private in the parent, and override methods instead.

---

### Quiz

*These questions are written for this course.*

````quiz
Which `sound()` runs for `pet` in the loop when `pet` refers to a `Cat`?
○ The Animal version, because pet is declared as Animal.
→ Not quite. The method is chosen by the object's real class, not by the variable's type.
◉ The Cat version, which returns Meow.
→ Yes. The overriding version for the object's class is the one that runs.
○ Neither, because overriding doesn't work with arrays.
→ Not quite. Overriding works the same through an array of the parent type.
````

````quiz
What does the `@Override` annotation do?
○ It makes the method faster.
→ Not quite. It has no effect on speed.
◉ It makes the compiler check that the method replaces one in the superclass.
→ Yes. A misspelled name is then caught at compile time.
○ It stops the method from being overridden again.
→ Not quite. That's the final keyword on a method, which is a separate thing.
````

---

### Try it in the runner

Change `sound()` in `Dog` to `sound(int volume)`, leaving `@Override` in place, and predict the result. The build fails, because the method no longer replaces the superclass method, so `@Override` is wrong. Restore the original method before you continue.

Then add a new animal, `new Animal("Fish")` to the array, and predict its output. It prints `Fish says ...`, because the plain `Animal` has no override. The loop didn't change to handle it. Remove the new element before you continue.

---

Overriding gives each subclass its own behavior, and a parent type lets one loop handle them all. The next step covers abstract classes, which describe a shared behavior that every subclass must supply.
