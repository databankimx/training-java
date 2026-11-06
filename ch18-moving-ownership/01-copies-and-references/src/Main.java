public class Main {
    static class Buffer {
        private String data;

        Buffer(String data) {
            this.data = data;
            System.out.println("Create " + data);
        }

        // A copy is a second, independent object with the same contents.
        Buffer copy() {
            System.out.println("Copy " + data);
            return new Buffer(data);
        }

        void changeTo(String text) {
            data = text;
        }

        String text() {
            return data;
        }
    }

    public static void main(String[] args) {
        Buffer a = new Buffer("alpha");
        Buffer b = a.copy();
        Buffer c = a;

        System.out.println("a and c are the same object: " + (a == c));
        System.out.println("a and b are the same object: " + (a == b));

        c.changeTo("changed");
        System.out.println("a: " + a.text());
        System.out.println("b: " + b.text());
        System.out.println("c: " + c.text());
    }
}
