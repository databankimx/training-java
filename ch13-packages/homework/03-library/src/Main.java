public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Book dune = new Book("Dune");
        Book emma = new Book("Emma");

        emma.checkOut();
        library.add(dune);
        library.add(emma);
        library.printAll();
    }
}
