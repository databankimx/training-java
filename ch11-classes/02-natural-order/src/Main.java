import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    static class Book implements Comparable<Book> {
        private final String title;
        private final int pages;

        Book(String title, int pages) {
            this.title = title;
            this.pages = pages;
        }

        int pages() {
            return pages;
        }

        // The natural order of books is alphabetical by title.
        @Override
        public int compareTo(Book other) {
            return title.compareTo(other.title);
        }

        @Override
        public String toString() {
            return title + " (" + pages + " pages)";
        }
    }

    public static void main(String[] args) {
        List<Book> books = new ArrayList<>(List.of(
                new Book("Emma", 474),
                new Book("Dune", 412),
                new Book("Ulysses", 730)));

        // Collections.sort uses compareTo, the natural order defined by the class.
        Collections.sort(books);
        System.out.println("By title: " + books);

        // A Comparator gives a different order without changing the class: longest books first.
        books.sort(Comparator.comparingInt(Book::pages).reversed());
        System.out.println("By pages, longest first: " + books);
    }
}
