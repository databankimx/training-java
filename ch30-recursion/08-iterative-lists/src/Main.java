import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class Main {
    // Counts the leaves without recursion. A stack holds an iterator for each list that's partly processed, so the
    // most recent list is always the one being read. An iterator remembers its place, so no index is needed.
    static int countLeaves(List<?> items) {
        int count = 0;
        Deque<Iterator<?>> stack = new ArrayDeque<>();
        stack.push(items.iterator());

        while (!stack.isEmpty()) {
            Iterator<?> current = stack.peek();
            if (!current.hasNext()) {
                // This list is finished, so return to the list it was inside.
                stack.pop();
                continue;
            }
            Object item = current.next();
            if (item instanceof List<?> sublist) {
                stack.push(sublist.iterator());
            } else {
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

        int num = countLeaves(names);
        System.out.println("The list contains " + num + (num == 1 ? " leaf" : " leaves"));
    }
}
