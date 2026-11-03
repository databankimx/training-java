public class Main {
    public static void main(String[] args) {
        int x = 20;
        int y = 10;

        if (x == y) {
            System.out.println("x is equal to y");
        }
        if (x > y) {
            System.out.println("x is greater than y");
        }
        if (x < y) {
            System.out.println("x is less than y");
        }

        int age = 16;
        int votingAge = 18;
        if (age >= votingAge) {
            System.out.println("You can vote!");
        } else {
            int wait = votingAge - age;
            System.out.println("You will be able to vote in " + wait + " years.");
        }

        int driverAge = 20;
        if (driverAge >= 21) {
            System.out.println("You drive, vote, and rent a car.");
        } else if (driverAge >= 18) {
            System.out.println("You can drive and vote, but you can't rent a car.");
        } else if (driverAge >= 16) {
            System.out.println("You can drive, but you can't vote or rent a car.");
        } else {
            System.out.println("You can't drive, vote, or rent a car.");
        }

        int score = 75;
        if (score >= 90) {
            System.out.println("Excellent");
        } else if (score >= 70) {
            System.out.println("Good");
        }

        int items = 12;
        if (items > 10) {
            System.out.println("Chained: bulk discount");
        } else if (items > 5) {
            System.out.println("Chained: free shipping");
        }
        if (items > 10) {
            System.out.println("Independent: bulk discount");
        }
        if (items > 5) {
            System.out.println("Independent: free shipping");
        }

        String size = (x > y) ? "large" : "small";
        System.out.println("size: " + size);
    }
}
