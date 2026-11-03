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
}

public class Main {
    public static void main(String[] args) {
        Dog rex = new Dog("Rex");
        System.out.println(rex.getName() + " says " + rex.sound());
    }
}
