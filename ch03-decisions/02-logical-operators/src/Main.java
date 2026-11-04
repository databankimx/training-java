public class Main {
    public static void main(String[] args) {
        boolean x = true;
        boolean y = false;

        System.out.println("x && y: " + (x && y));
        System.out.println("x || y: " + (x || y));
        System.out.println("!x: " + !x);

        int age = 25;
        boolean hasTicket = false;
        System.out.println("Adult and has ticket: " + (age >= 18 && hasTicket));
        System.out.println("Adult or has ticket: " + (age >= 18 || hasTicket));

        int count = 0;
        boolean ok = (count != 0) && (10 / count > 1);
        System.out.println("ok: " + ok);

        boolean p = true;
        boolean q = false;

        boolean myXor = (p || q) && !(p && q);
        System.out.println("(p || q) && !(p && q): " + myXor);

        myXor = p != q;
        System.out.println("p != q: " + myXor);
        System.out.println("p ^ q: " + (p ^ q));

        System.out.println("true || false && false: " + (true || false && false));
    }
}
