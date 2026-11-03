public class Main {
    public static void main(String[] args) {
        int day = 3;
        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            default:
                System.out.println("Not a workday");
                break;
        }

        char grade = 'B';
        switch (grade) {
            case 'A':
                System.out.println("Excellent");
                break;
            case 'B':
            case 'C':
                System.out.println("Good");
                break;
            default:
                System.out.println("Keep going");
                break;
        }

        int level = 1;
        switch (level) {
            case 1:
                System.out.println("Level 1 tasks");
                // falls through
            case 2:
                System.out.println("Level 2 tasks");
                break;
        }

        String command = "start";
        switch (command) {
            case "start" -> System.out.println("Starting");
            case "stop" -> System.out.println("Stopping");
            default -> System.out.println("Unknown command");
        }

        int dayNumber = 6;
        String dayName = switch (dayNumber) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 6, 7 -> "Weekend";
            default -> "Weekday";
        };
        System.out.println("Day " + dayNumber + " is " + dayName);
    }
}
