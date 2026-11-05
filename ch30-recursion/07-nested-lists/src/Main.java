import java.util.List;

public class Main {
    // Counts the leaves in a list that may contain other lists. A String is a leaf, and a List is a branch.
    static int countLeaves(List<?> items) {
        System.out.println("Counting list: " + items);
        int count = 0;
        for (Object item : items) {
            if (item instanceof List<?> sublist) {
                count += countLeaves(sublist);
            } else {
                System.out.println("Found leaf: " + item);
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        List<Object> names = List.of(
                "Adam",
                List.of("Bob", List.of("Chet", "Cat"), "Barb", "Bert"),
                "Alex",
                List.of("Bea", "Bill"),
                "Ann");

        // size counts only the top-level elements, so the branches count as single items.
        System.out.println("size: " + names.size());

        for (int index = 0; index < names.size(); index++) {
            System.out.println(index + " " + names.get(index));
        }

        int num = countLeaves(names);
        System.out.println("The list contains " + num + (num == 1 ? " leaf" : " leaves"));
    }
}
