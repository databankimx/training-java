public class Main {
    // Each product owns one bit in a licence value. The bit is the product's value, a power of two.
    enum Product {
        WORD_PROCESSING(1),
        SPREADSHEETS(2),
        PRESENTATIONS(4),
        EMAIL_CLIENT(8),
        NOTEBOOK(16),
        COLLABORATION(32),
        PROJECT_MANAGEMENT(64),
        PUBLISHING(128);

        final int bit;

        Product(int bit) {
            this.bit = bit;
        }
    }

    // True when the product's bit is set in the licence value.
    static boolean isLicensed(int licences, Product product) {
        return (licences & product.bit) != 0;
    }

    public static void main(String[] args) {
        // 11 is 0000 1011, which has the bits for word processing, spreadsheets, and email.
        int licences = 11;
        System.out.println("Licensed products:");
        for (Product product : Product.values()) {
            if (isLicensed(licences, product)) {
                System.out.println("  " + product.name());
            }
        }
    }
}
