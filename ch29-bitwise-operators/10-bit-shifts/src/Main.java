public class Main {
    enum Setting {
        DEBUG_MODE(1),
        INTERACTIVE_MODE(2),
        REMEMBER_SETTINGS(4),
        OPTIMIZE(8);

        final int bit;

        Setting(int bit) {
            this.bit = bit;
        }
    }

    // Finds the setting whose bit has the given value.
    static String nameOf(int bit) {
        for (Setting setting : Setting.values()) {
            if (setting.bit == bit) {
                return setting.name();
            }
        }
        return "unknown";
    }

    // Prints the name of every setting that is switched on in a value.
    static void printSettings(int settings) {
        int currentValue = 1;
        while (settings > 0) {
            if ((settings & 1) != 0) {
                System.out.println("- " + nameOf(currentValue));
            }
            settings >>= 1;
            currentValue <<= 1;
        }
    }

    public static void main(String[] args) {
        // Shifting right by 3 divides by 8, and shifting left by 3 multiplies by 8.
        System.out.println("128 >> 3 = " + (128 >> 3));
        System.out.println("16 << 3 = " + (16 << 3));

        // An int has 32 bits, so 64 << 3 is 512. In 8 bits the 1 is lost off the top, leaving 0.
        System.out.println("64 << 3 = " + (64 << 3));
        System.out.println("64 << 3 in 8 bits = " + ((64 << 3) & 0xFF));

        // Shifting right past the ones place loses the 1.
        System.out.println("4 >> 3 = " + (4 >> 3));

        // Java has two right shifts. >> keeps the sign, and >>> fills the top with zeros.
        System.out.println("-1 >> 1 = " + (-1 >> 1));
        System.out.println("-1 >>> 1 = " + (-1 >>> 1));

        System.out.println("With settings 10, the following are set:");
        printSettings(10);
    }
}
