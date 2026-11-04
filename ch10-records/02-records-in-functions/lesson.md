## Records in functions

A record is most useful when it travels. A method can build one and return it, receive one and read it, or take one and return a changed copy. The rules are the same ones you saw for values in the methods chapter, with the record taking the place of the single value.

Here's the program for this step, in `src/Main.java`. It begins with a method that builds a car:

```java
static Car makeCar(String brand, String model, int year) {
    return new Car(brand, model, year);
}
```

The return type is `Car`, so the method gives back a whole car. The caller stores it like any other value:

```java
Car mine = makeCar("BMW", "X5", 1999);
```

### Passing a record

The next method receives a car as a parameter:

```java
static void describe(Car car) {
    System.out.println(car.brand() + " " + car.model() + " " + car.year());
}
```

The method reads the components and prints them. It has no way to change the caller's car. In Java, the parameter is a reference to the record, and a record can't be changed after it's created, so the method can't alter the caller's data.

### Changing a record means making a new one

A Java record can't be changed, so the method builds a new car with the year moved back by ten, and returns it:

```java
static Car makeOlder(Car car) {
    return new Car(car.brand(), car.model(), car.year() - 10);
}
```

The caller stores the result in the same variable:

```java
mine = makeOlder(mine);
describe(mine);
```

After the assignment, `mine` refers to the new car, and the year is 1989. The original record is no longer used, and the change is made by replacing the value rather than editing it.

### Arrays of records

The program then makes an array of two cars, and visits each one:

```java
Car[] cars = {makeCar("Ford", "Mustang", 1969), makeCar("Fiat", "500", 2010)};
for (Car car : cars) {
    describe(car);
}
```

An array of records works like any other array. The enhanced `for` gives each element to `car` in turn, and `describe` prints it.

The output for the whole program is:

```text
BMW X5 1999
BMW X5 1989
Ford Mustang 1969
Fiat 500 2010
```

---

### Quiz

*These questions are written for this course.*

````quiz
After `mine = makeOlder(mine);`, which statement is true?
○ mine is unchanged, because makeOlder works on a copy.
→ Not quite. The method returns a new record, and mine is set to it.
◉ mine refers to a car whose year is 10 less than before.
→ Yes. The new record replaces the old one in the variable.
○ The old car is edited in place, and its year changes.
→ Not quite. A record can't be edited, so a new one is built instead.
````

````quiz
Why can a method receive a record without any risk of changing the caller's car?
○ Because records are always copied before a method receives them.
→ Not quite. The parameter is a reference, not a copy, but nothing can change the record.
◉ Because a record's components can't be changed after it's created.
→ Yes. Immutability means no method, however written, can alter the caller's data.
○ Because the method has no access to the caller's variables.
→ Not quite. The method does receive the record, so it can read it.
````

---

### Try it in the runner

Change `mine = makeOlder(mine);` to `makeOlder(mine);`, so the returned car is thrown away, and predict the second line of output. It's `BMW X5 1999`, since `mine` still refers to the original car. The method built a new car and discarded it.

Then add a method `static void tryToChange(Car car) { car.year = 2000; }`, and predict the result. The build fails, because the component can't be assigned. Remove the method before you continue.

---

A record travels through a program as one value, and a method that needs a different value returns a new record. The next step covers initializing records, which sets every component when the record is created.
