public class Main {
    public static void main(String[] args) {
        String[] inputs = {"12", "abc", "30", "", "7"};
        int total = 0;
        int skipped = 0;
        for (String input : inputs) {
            try {
                total += Integer.parseInt(input);
            } catch (NumberFormatException error) {
                skipped++;
            }
        }
        System.out.println("Total: " + total);
        System.out.println("Skipped: " + skipped);
    }
}
