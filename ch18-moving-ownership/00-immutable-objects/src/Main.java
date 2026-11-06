import java.util.ArrayList;
import java.util.List;

public class Main {
    // A record cannot be changed after it is created. A change is a new record.
    record Point(int x, int y) {
        Point moved(int dx) {
            return new Point(x + dx, y);
        }
    }

    // The team copies its list when it is created, so the caller's list can change without affecting the team.
    static class Team {
        private final List<String> members;

        Team(List<String> members) {
            this.members = List.copyOf(members);
        }

        List<String> members() {
            return members;
        }
    }

    public static void main(String[] args) {
        List<String> names = new ArrayList<>(List.of("Ada", "Grace"));
        Team team = new Team(names);
        names.add("Alan");
        System.out.println("Team size after the caller adds: " + team.members().size());

        try {
            team.members().add("Bob");
        } catch (UnsupportedOperationException error) {
            System.out.println("Changing the team list: UnsupportedOperationException");
        }

        Point original = new Point(1, 2);
        Point moved = original.moved(3);
        System.out.println("Point: " + original);
        System.out.println("Moved point: " + moved);
        System.out.println("Original unchanged: " + original);
    }
}
