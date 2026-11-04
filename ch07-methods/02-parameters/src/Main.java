public class Main {
    static void introduce(String fname, int age) {
        System.out.println(fname + " is " + age + " years old.");
    }

    static void welcome() {
        welcome("United States");
    }

    static void welcome(String country) {
        System.out.println("The country is: " + country);
    }

    public static void main(String[] args) {
        introduce("Scott", 53);
        welcome();
        welcome("Mexico");
    }
}
