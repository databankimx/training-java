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
                // no break here: the code falls through on purpose
            case 2:
                System.out.println("Level 2 tasks");
                break;
        }

        String season = "winter";
        switch (season) {
            case "winter" -> System.out.println("Cold");
            case "summer" -> System.out.println("Warm");
            default -> System.out.println("In between");
        }
    }
}
