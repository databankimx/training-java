class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isWordEnd = false;
}

// A trie of lowercase English words.
class Trie {
    private final TrieNode root = new TrieNode();

    // Adds a word. Each letter follows or creates the child for that letter, and the last node is marked as a word end.
    void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isWordEnd = true;
    }

    // Finds the node for the given letters, or returns null if the path doesn't exist.
    private TrieNode find(String text) {
        TrieNode node = root;
        for (char c : text.toCharArray()) {
            node = node.children[c - 'a'];
            if (node == null) {
                return null;
            }
        }
        return node;
    }

    // True when the text is a complete word in the trie.
    boolean contains(String word) {
        TrieNode node = find(word);
        return node != null && node.isWordEnd;
    }

    // True when some word in the trie starts with the text.
    boolean startsWith(String prefix) {
        return find(prefix) != null;
    }
}

public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("and");
        trie.insert("ant");
        trie.insert("dad");
        trie.insert("do");

        System.out.println("'an' is a prefix: " + trie.startsWith("an"));
        System.out.println("'anbx' is a prefix: " + trie.startsWith("anbx"));
        System.out.println("'do' is a word: " + trie.contains("do"));
        System.out.println("'an' is a word: " + trie.contains("an"));
    }
}
