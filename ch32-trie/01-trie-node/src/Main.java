// One node of a trie. It has a child for each letter of the alphabet, and a flag that marks the end of a word.
class TrieNode {
    // Index 0 is 'a', index 1 is 'b', and so on. A null entry means that child doesn't exist yet.
    TrieNode[] children = new TrieNode[26];
    boolean isWordEnd = false;
}

public class Main {
    public static void main(String[] args) {
        TrieNode root = new TrieNode();

        // Create the child for 'a', then the child for 'n' below it, as inserting "an" would.
        root.children['a' - 'a'] = new TrieNode();
        TrieNode a = root.children['a' - 'a'];
        a.children['n' - 'a'] = new TrieNode();
        TrieNode an = a.children['n' - 'a'];
        an.isWordEnd = true;

        System.out.println("Child 'a' exists: " + (root.children['a' - 'a'] != null));
        System.out.println("Child 'b' exists: " + (root.children['b' - 'a'] != null));
        System.out.println("'an' is a word: " + an.isWordEnd);
    }
}
