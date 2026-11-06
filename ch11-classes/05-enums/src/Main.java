public class Main {
    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

        boolean isWeekend() {
            return this == SATURDAY || this == SUNDAY;
        }
    }

    enum Planet {
        MERCURY(0.38), EARTH(1.0);

        private final double gravity;

        Planet(double gravity) {
            this.gravity = gravity;
        }

        double gravity() {
            return gravity;
        }
    }

    public static void main(String[] args) {
        Day today = Day.WEDNESDAY;
        System.out.println("Today: " + today);
        System.out.println("Weekend: " + today.isWeekend());
        System.out.println("Position: " + today.ordinal());

        switch (today) {
            case SATURDAY, SUNDAY -> System.out.println("Rest");
            default -> System.out.println("Work");
        }

        for (Planet planet : Planet.values()) {
            System.out.println(planet + " gravity " + planet.gravity());
        }

        Day parsed = Day.valueOf("FRIDAY");
        System.out.println("Parsed: " + parsed);
    }
}
