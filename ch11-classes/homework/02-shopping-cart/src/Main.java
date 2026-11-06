class ShoppingCart {
    private int totalCents;
    private int itemCount;

    void add(int priceCents) {
        if (priceCents <= 0) {
            System.out.println("Price rejected: " + priceCents);
            return;
        }
        totalCents += priceCents;
        itemCount++;
    }

    int getItemCount() {
        return itemCount;
    }

    String total() {
        return "$" + (totalCents / 100) + "." + String.format("%02d", totalCents % 100);
    }
}

public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.add(1299);
        cart.add(450);
        cart.add(0);

        System.out.println("Items: " + cart.getItemCount());
        System.out.println("Total: " + cart.total());
    }
}
