public class Main {
    static class AccessDeniedException extends Exception {
        final int age;

        AccessDeniedException(int age) {
            this.age = age;
        }
    }

    static void checkAccess(int age) throws AccessDeniedException {
        if (age < 18) {
            throw new AccessDeniedException(age);
        }
        System.out.println("Access granted - you are old enough.");
    }

    public static void main(String[] args) {
        try {
            checkAccess(25);
            checkAccess(15);
            System.out.println("This line never runs.");
        } catch (AccessDeniedException myNum) {
            System.out.println("Access denied - you must be at least 18 years old.");
            System.out.println("Age is: " + myNum.age);
        }
    }
}
