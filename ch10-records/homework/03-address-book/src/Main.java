import java.util.Scanner;

public class Main {
    record Contact(String name, String phone) {
    }

    public static void main(String[] args) {
        Contact[] contacts = {
            new Contact("Ada", "555-0101"),
            new Contact("Grace", "555-0102"),
            new Contact("Alan", "555-0103")
        };

        Scanner input = new Scanner(System.in);
        System.out.print("Enter a name: ");
        System.out.flush();
        String wanted = input.nextLine();

        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.name().equalsIgnoreCase(wanted)) {
                System.out.println(contact.name() + ": " + contact.phone());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No contact named " + wanted + ".");
        }
    }
}
