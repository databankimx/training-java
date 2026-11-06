public class Main {
    // XORs every character with the key. Applying it again with the same key gives back the original text.
    static String encryptDecrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            result.append((char) (c ^ key));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String text = "Hello World!";
        int key = 30;

        String cipherText = encryptDecrypt(text, key);
        System.out.println("Plain Text: " + text);
        System.out.println("Encrypted Text: " + cipherText);

        String plainText = encryptDecrypt(cipherText, key);
        System.out.println("Decrypted Text: " + plainText);
    }
}
