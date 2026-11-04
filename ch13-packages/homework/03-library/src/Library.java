class Library {
    private final Book[] books = new Book[3];
    private int count = 0;

    void add(Book book) {
        if (count < books.length) {
            books[count] = book;
            count++;
        }
    }

    void printAll() {
        for (int i = 0; i < count; i++) {
            books[i].print();
        }
    }
}
