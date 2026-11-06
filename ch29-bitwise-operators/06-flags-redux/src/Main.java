public class Main {
    // The single products, each with its own bit.
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

    // Bundles hold several products. Each bundle's value is the OR of its products' bits, so it has all of them set.
    enum Bundle {
        PERSONAL(Product.WORD_PROCESSING.bit | Product.EMAIL_CLIENT.bit),
        WORK(PERSONAL.bits | Product.SPREADSHEETS.bit | Product.PRESENTATIONS.bit
                | Product.NOTEBOOK.bit | Product.COLLABORATION.bit),
        ULTRA_DELUXE(WORK.bits | Product.PROJECT_MANAGEMENT.bit | Product.PUBLISHING.bit);

        final int bits;

        Bundle(int bits) {
            this.bits = bits;
        }
    }

    // The same check as before. It works for a single product, or for a package holding many.
    static boolean isLicensed(int licences, Product product) {
        return (licences & product.bit) != 0;
    }

    public static void main(String[] args) {
        int licences = Bundle.WORK.bits;
        System.out.println("Products in the Work bundle:");
        for (Product product : Product.values()) {
            if (isLicensed(licences, product)) {
                System.out.println("  " + product.name());
            }
        }
    }
}
