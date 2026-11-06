class Thermostat {
    private int target = 20;

    int getTarget() {
        return target;
    }

    boolean setTarget(int value) {
        if (value < 10 || value > 30) {
            return false;
        }
        target = value;
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        Thermostat home = new Thermostat();
        System.out.println("Start: " + home.getTarget());

        System.out.println("Set 24: " + home.setTarget(24));
        System.out.println("Now: " + home.getTarget());

        System.out.println("Set 40: " + home.setTarget(40));
        System.out.println("Now: " + home.getTarget());
    }
}
