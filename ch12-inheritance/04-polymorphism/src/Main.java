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

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("The dog says: bow wow!");
    }
}

public class Main {
    static void makeSound(Animal animal) {
        animal.sound();
    }

    public static void main(String[] args) {
        makeSound(new Animal());
        makeSound(new Pig());
        makeSound(new Dog());
    }
}
