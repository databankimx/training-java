import java.util.Scanner;

public class Main {
    enum AccessError {
        UNDERAGE,
        TOO_OLD
    }

    static class AccessException extends Exception {
        final AccessError code;

        AccessException(AccessError code) {
            this.code = code;
        }
    }

    static void checkAccess(int age) throws AccessException {
        if (age < 18) {
            throw new AccessException(AccessError.UNDERAGE);
        }
        if (age >= 65) {
            throw new AccessException(AccessError.TOO_OLD);
        }
        System.out.println("Access granted.");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your age: ");
        System.out.flush();
        int age = input.nextInt();

        try {
            checkAccess(age);
        } catch (AccessException err) {
            switch (err.code) {
                case UNDERAGE -> System.out.println("Access denied! Underage!");
                case TOO_OLD -> System.out.println("Access denied! Too old!");
            }
        }
    }
}
