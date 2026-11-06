class Book {
    private final String title;
    private boolean checkedOut = false;

    Book(String title) {
        this.title = title;
    }

    void print() {
        System.out.println(title + " - " + (checkedOut ? "checked out" : "available"));
    }

    void checkOut() {
        checkedOut = true;
    }
}
