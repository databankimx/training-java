class Animal {
    private final String name;

    Animal(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    String sound() {
        return "...";
    }
}

class Dog extends Animal {
    Dog(String name) {
        super(name);
    }

    @Override
    String sound() {
        return "Woof";
    }
}

class Cat extends Animal {
    Cat(String name) {
        super(name);
    }

    @Override
    String sound() {
        return "Meow";
    }
}

public class Main {
    public static void main(String[] args) {
        Animal[] pets = {new Dog("Rex"), new Cat("Tom"), new Animal("Generic")};
        for (Animal pet : pets) {
            System.out.println(pet.getName() + " says " + pet.sound());
        }
    }
}
